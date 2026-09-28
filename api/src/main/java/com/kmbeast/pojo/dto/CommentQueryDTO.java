package com.kmbeast.pojo.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 评论查询参数Dto类
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class CommentQueryDTO extends QueryDTO {
    private String contentType; //内容类型
    private Integer userId; //用户ID
    private String content; //评论的内容
}