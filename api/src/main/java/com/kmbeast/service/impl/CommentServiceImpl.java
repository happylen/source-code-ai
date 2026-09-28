package com.kmbeast.service.impl;

import com.kmbeast.context.LocalThreadHolder;
import com.kmbeast.mapper.CommentLikeMapper;
import com.kmbeast.mapper.CommentMapper;
import com.kmbeast.mapper.UserMapper;
import com.kmbeast.pojo.api.ApiResult;
import com.kmbeast.pojo.api.Result;
import com.kmbeast.pojo.dto.CommentQueryDTO;
import com.kmbeast.pojo.entity.Comment;
import com.kmbeast.pojo.entity.CommentLike;
import com.kmbeast.pojo.entity.User;
import com.kmbeast.pojo.vo.CommentChildVO;
import com.kmbeast.pojo.vo.CommentParentVO;
import com.kmbeast.pojo.vo.CommentVO;
import com.kmbeast.service.CommentService;
import com.kmbeast.utils.AhoCorasickFilter;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 评论服务实现类
 */
@Service
public class CommentServiceImpl implements CommentService {

    @Resource
    private CommentMapper commentMapper;
    @Resource
    private UserMapper userMapper;
    @Resource
    private CommentLikeMapper commentLikeMapper;

    /**
     * 评论
     *
     * @return Result<String>
     */
    @Override
    public Result<Object> insert(Comment comment) {
        // 原始评论文本
        String content = comment.getContent();
        // ====================Trie树敏感词过滤算法起始===============
        AhoCorasickFilter ahoCorasickFilter = new AhoCorasickFilter(); // 创建过滤器实例
        for (String sensitiveWord : AhoCorasickFilter.sensitiveWords) { // 添加词典
            ahoCorasickFilter.addWord(sensitiveWord);
        }
        ahoCorasickFilter.buildFailurePointer(); // 构建失败指针
        String filteredContent = ahoCorasickFilter.filter(content); // 过滤敏感词
        // ====================结束======================================
        comment.setContent(filteredContent); // 将过滤后的敏感词替换原始文本内容
        comment.setCommenterId(LocalThreadHolder.getUserId());
        User user = userMapper.getUserById(LocalThreadHolder.getUserId());
        comment.setCreateTime(LocalDateTime.now());
        commentMapper.save(comment);
        return ApiResult.success("评论成功");
    }

    /**
     * 查询全部评论
     *
     * @return Result<String>
     */
    @Override
    public Result<Object> list(Integer contentId, String contentType) {
        List<CommentParentVO> parentComments = commentMapper.getParentComments(
                contentId,
                LocalThreadHolder.getUserId(),
                contentType
        );
        Integer count = commentMapper.totalCount(contentId, contentType);
        return ApiResult.success(new CommentVO(count, parentComments));
    }

    /**
     * 分页查询评论
     *
     * @return Result<String>
     */
    @Override
    public Result<Object> query(CommentQueryDTO commentQueryDto) {
        List<CommentChildVO> list = commentMapper.query(commentQueryDto);
        Integer totalPage = commentMapper.queryCount(commentQueryDto);
        return ApiResult.success(list, totalPage);
    }

    /**
     * 评论删除
     *
     * @return Result<String>
     */
    @Override
    public Result<String> deleteById(Integer id) {
        ArrayList<Integer> ids = new ArrayList<>();
        ids.add(id);
        commentMapper.batchDelete(ids);
        return ApiResult.success();
    }

    /**
     * 评论修改
     *
     * @return Result<String>
     */
    @Override
    public Result<Void> update(Comment comment) {
        commentMapper.update(comment);
        return ApiResult.success();
    }

    @Override
    public Result<Object> upvoteOperation(Comment comment) {
        Integer userId = LocalThreadHolder.getUserId();
        Integer commentId = comment.getId();

        // 创建查询条件对象
        CommentLike query = new CommentLike();
        query.setUserId(userId);
        query.setCommentId(commentId);

        // 检查用户是否已点赞
        boolean hasUpvote = commentLikeMapper.queryCount(query) > 0;

        if (hasUpvote) {
            // 取消点赞 - 必须同时设置userId和commentId
            CommentLike deleteCondition = new CommentLike();
            deleteCondition.setUserId(userId);
            deleteCondition.setCommentId(commentId);
            commentLikeMapper.delete(deleteCondition);
        } else {
            // 添加点赞
            CommentLike newUpvote = new CommentLike();
            newUpvote.setUserId(userId);
            newUpvote.setCommentId(commentId);
            commentLikeMapper.save(newUpvote);
        }

        // 获取更新后的总点赞数
        CommentLike countQuery = new CommentLike();
        countQuery.setCommentId(commentId);
        int total = commentLikeMapper.queryCount(countQuery);

        // 构建返回结果
        Map<String, Object> rep = new HashMap<>();
        rep.put("count", total);
        rep.put("haveUpvote", !hasUpvote); // 返回操作后的状态

        return ApiResult.success(rep);
    }
}