package com.kmbeast.controller;

import com.kmbeast.aop.Pager;
import com.kmbeast.pojo.api.Result;
import com.kmbeast.pojo.dto.CommentQueryDTO;
import com.kmbeast.pojo.entity.Comment;
import com.kmbeast.service.CommentService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

/**
 * 评论控制器
 */
@RestController
@RequestMapping("/comment")
public class CommentController {

    @Resource
    private CommentService commentService;

    /**
     * 评论
     *
     * @return Result<String>
     */
    @PostMapping(value = "/insert")
    @ResponseBody
    public Result<Object> insert(@RequestBody Comment comment) {
        return commentService.insert(comment);
    }

    /**
     * 点赞或取消点赞
     *
     * @return Result<String>
     */

    @PostMapping(value = "/upvoteOperation")
    @ResponseBody
    public Result<Object> upvoteOperation(@RequestBody Comment comment) {
        return commentService.upvoteOperation(comment);
    }

    /**
     * 评论修改
     *
     * @return Result<String>
     */

    @PutMapping(value = "/update")
    @ResponseBody
    public Result<Void> update(@RequestBody Comment comment) {
        return commentService.update(comment);
    }

    /**
     * 查询内容下的全部评论
     *
     * @return Result<String>
     */
    @GetMapping(value = "/list/{contentId}/{contentType}")
    @ResponseBody
    public Result<Object> list(@PathVariable Integer contentId,
                               @PathVariable String contentType) {
        return commentService.list(contentId, contentType);
    }

    /**
     * 分页查询评论
     *
     * @return Result<String>
     */
    @Pager
    @PostMapping(value = "/query")
    @ResponseBody
    public Result<Object> query(@RequestBody CommentQueryDTO commentQueryDto) {
        return commentService.query(commentQueryDto);
    }

    /**
     * 通过ID删除评论信息
     *
     * @return Result<String>
     */
    @DeleteMapping(value = "/{id}")
    @ResponseBody
    public Result<String> delete(@PathVariable Integer id) {
        return commentService.deleteById(id);
    }

}