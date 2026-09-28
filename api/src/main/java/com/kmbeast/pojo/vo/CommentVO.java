package com.kmbeast.pojo.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 评论VO
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommentVO {

    private Integer count; // 总数

    private List<CommentParentVO> data; // 评论数据
}