package com.kmbeast.mapper;

import com.kmbeast.pojo.entity.CommentLike;

/**
 * 评论点赞持久化接口
 */
public interface CommentLikeMapper {

    void save(CommentLike commentLike);

    void delete(CommentLike commentLike);

    int queryCount(CommentLike commentLike);

}