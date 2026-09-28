package com.kmbeast.service;

import com.kmbeast.pojo.api.Result;
import com.kmbeast.pojo.dto.CommentQueryDTO;
import com.kmbeast.pojo.entity.Comment;

/**
 * 评论服务接口
 */
public interface CommentService {

    Result<Object> insert(Comment comment);

    Result<Object> list(Integer contentId, String contentType);

    Result<Object> query(CommentQueryDTO commentQueryDto);

    Result<String> deleteById(Integer id);

    Result<Void> update(Comment comment);

    Result<Object> upvoteOperation(Comment comment);

}