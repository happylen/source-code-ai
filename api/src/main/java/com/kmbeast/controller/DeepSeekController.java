package com.kmbeast.controller;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONObject;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.regex.Pattern;

@RestController
@RequestMapping("/ask")
public class DeepSeekController {

    private static final OkHttpClient CLIENT = new OkHttpClient();
    private static final String API_URL = "https://api.deepseek.com/chat/completions";
    private static final String API_KEY = "sk-da3e6f46143d445887afbc226dac8f02";
    private final ExecutorService executor = Executors.newCachedThreadPool();

    // Markdown 模式定义
    private static final Pattern CODE_BLOCK_START = Pattern.compile("^```(\\w*)?\\s*$");
    private static final Pattern CODE_BLOCK_END = Pattern.compile("^```\\s*$");
    private static final Pattern TABLE_ROW = Pattern.compile("^\\|.*\\|\\s*$");
    private static final Pattern TABLE_SEPARATOR = Pattern.compile("^\\|[-:\\s|]+\\|\\s*$");

    /* ======== 1. 创建请求体 ======== */
    private JSONObject createRequestBody(String question) {
        JSONObject body = new JSONObject();
        JSONArray messages = new JSONArray();

        messages.add(JSONObject.of("role", "system",
                
                "content", "你是一个有用的助手，请用中文回答所有问题，并且在回答时使用合适的Markdown格式来组织内容，包括标题、列表、代码块等，让回答更加结构化和易读。"));
        messages.add(JSONObject.of("role", "user", "content", question));

        messages.add(JSONObject.of("role", "user", "content", question));
        body.put("messages", messages);
        body.put("model", "deepseek-chat");
        body.put("stream", true);
        body.put("temperature", 0.7);
        body.put("max_tokens", 2048);
        return body;
    }

    /* ======== 2. 流式接口 ======== */
    @PostMapping(value = "/seek", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public SseEmitter askStream(@RequestBody Map<String, String> request) {
        SseEmitter emitter = new SseEmitter(120_000L);
        String question = request.get("question");

        executor.execute(() -> {
            MarkdownProcessor processor = new MarkdownProcessor(emitter);
            try {
                okhttp3.RequestBody body = okhttp3.RequestBody.create(
                        createRequestBody(question).toString(),
                        okhttp3.MediaType.parse("application/json; charset=utf-8"));

                Request httpRequest = new Request.Builder()
                        .url(API_URL)
                        .post(body)
                        .addHeader("Authorization", "Bearer " + API_KEY)
                        .addHeader("Accept", "text/event-stream")
                        .build();

                try (Response response = CLIENT.newCall(httpRequest).execute()) {
                    if (!response.isSuccessful()) {
                        String err = response.body() == null ? "Unknown" : response.body().string();
                        emitter.completeWithError(new IOException("HTTP " + response.code() + ": " + err));
                        return;
                    }

                    assert response.body() != null;
                    BufferedReader reader = new BufferedReader(response.body().charStream());
                    String line;

                    while ((line = reader.readLine()) != null) {
                        if (line.startsWith("data: ")) {
                            String data = line.substring(6).trim();
                            if ("[DONE]".equals(data)) {
                                processor.flushAll();
                                emitter.send(SseEmitter.event().data("[DONE]"));
                                break;
                            }
                            if (data.isEmpty()) continue;

                            JSONObject json = JSON.parseObject(data);
                            JSONObject delta = json.getJSONArray("choices")
                                    .getJSONObject(0).getJSONObject("delta");
                            String content = delta == null ? null : delta.getString("content");
                            if (content == null || content.isEmpty()) continue;

                            processor.processContent(content);
                        }
                    }
                    emitter.complete();
                }
            } catch (Exception e) {
                try {
                    processor.flushAll();
                } catch (IOException ex) {
                    // 忽略刷新异常
                }
                emitter.completeWithError(e);
            }
        });

        emitter.onTimeout(emitter::complete);
        emitter.onCompletion(() -> System.out.println("SSE completed"));
        return emitter;
    }

    /* ======== 3. 修复的 Markdown 处理器 ======== */
    private static class MarkdownProcessor {
        private final SseEmitter emitter;
        private final StringBuilder lineBuffer = new StringBuilder(); // 行级缓冲区

        // 状态标志 - 使用栈结构避免冲突
        private final Deque<String> contextStack = new ArrayDeque<>();
        private boolean inCodeBlock = false;
        private boolean inTable = false;
        private final List<String> tableBuffer = new ArrayList<>();

        public MarkdownProcessor(SseEmitter emitter) {
            this.emitter = emitter;
        }

        public void processContent(String content) throws IOException {
            if (content == null) return;

            // 按字符处理，构建完整行
            for (char c : content.toCharArray()) {
                lineBuffer.append(c);

                // 检查是否形成完整行
                if (c == '\n') {
                    processCompleteLine(lineBuffer.toString());
                    lineBuffer.setLength(0);
                }
            }

            // 处理没有换行符的剩余内容（特殊情况下）
            if (lineBuffer.length() > 100) { // 防止缓冲区过大
                String partialLine = lineBuffer.toString();
                // 如果是代码块或表格中，继续累积
                if (!inCodeBlock && !inTable) {
                    sendChunk(partialLine, false);
                    lineBuffer.setLength(0);
                }
            }
        }

        private void processCompleteLine(String line) throws IOException {
            String trimmedLine = line.trim();

            // 1. 优先处理代码块（代码块优先级最高）
            String currentLanguage = "";
            if (CODE_BLOCK_START.matcher(trimmedLine).matches()) {
                if (!inCodeBlock) {
                    // 进入代码块
                    inCodeBlock = true;
                    currentLanguage = trimmedLine.replace("```", "").trim();
                    sendChunk(line, false);
                    return;
                }
            }

            if (inCodeBlock && CODE_BLOCK_END.matcher(trimmedLine).matches()) {
                // 退出代码块
                inCodeBlock = false;
                currentLanguage = "";
                sendChunk(line, false);
                return;
            }

            if (inCodeBlock) {
                // 代码块内的所有内容直接发送
                sendChunk(line, false);
                return;
            }

            // 2. 处理表格（不在代码块中时才处理表格）
            if (TABLE_ROW.matcher(trimmedLine).matches()) {
                handleTableLine(trimmedLine, line);
                return;
            }

            // 3. 如果之前在表格中，但当前行不是表格行，则结束表格
            if (inTable && !TABLE_ROW.matcher(trimmedLine).matches()) {
                flushTable();
                // 继续处理当前行
                processCompleteLine(line);
                return;
            }

            // 4. 普通文本行
            sendChunk(line, false);
        }

        private void handleTableLine(String trimmedLine, String originalLine) throws IOException {
            if (!inTable) {
                // 开始表格
                inTable = true;
                tableBuffer.clear();
            }

            tableBuffer.add(trimmedLine);

            // 检查是否是分隔线
            boolean isSeparator = TABLE_SEPARATOR.matcher(trimmedLine).matches();

            if (isSeparator) {
                // 分隔线出现，发送完整的表格头部（表头+分隔线）
                if (tableBuffer.size() >= 2) {
                    sendCompleteTableHeader();
                }
            } else if (tableBuffer.size() == 1) {
                // 第一行（表头），暂时不发送，等待分隔线
                return;
            } else if (tableBuffer.size() > 2) {
                // 数据行，立即发送
                sendTableDataRow(trimmedLine);
            }
        }

        private void sendCompleteTableHeader() throws IOException {
            if (tableBuffer.size() < 2) return;

            String header = tableBuffer.get(0) + "\n" + // 表头
                    tableBuffer.get(1) + "\n"; // 分隔线

            sendChunk(header, true);

            // 移除已发送的表头和分隔线
            tableBuffer.subList(0, 2).clear();
        }

        private void sendTableDataRow(String row) throws IOException {
            sendChunk(row + "\n", true);

            // 从缓冲区移除已发送的行
            tableBuffer.remove(row);
        }

        private void flushTable() throws IOException {
            if (!tableBuffer.isEmpty()) {
                // 发送表格剩余的所有行
                StringBuilder remaining = new StringBuilder();
                for (String row : tableBuffer) {
                    remaining.append(row).append("\n");
                }
                sendChunk(remaining.toString(), true);
                tableBuffer.clear();
            }
            inTable = false;
        }

        private void sendChunk(String content, boolean isTable) throws IOException {
            if (content.isEmpty()) return;

            JSONObject chunk = new JSONObject();
            chunk.put("content", content);
            chunk.put("type", "chunk");
            if (isTable) {
                chunk.put("isTable", true);
            }

            emitter.send(SseEmitter.event().data(chunk.toJSONString()));
        }

        public void flushAll() throws IOException {
            // 刷新表格
            if (inTable) {
                flushTable();
            }

            // 刷新行缓冲区
            if (!lineBuffer.isEmpty()) {
                String remaining = lineBuffer.toString();
                if (inCodeBlock) {
                    sendChunk(remaining, false);
                } else sendChunk(remaining, inTable);
                lineBuffer.setLength(0);
            }

        }
    }

}