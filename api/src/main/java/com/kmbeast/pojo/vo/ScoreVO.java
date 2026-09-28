package com.kmbeast.pojo.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 评分VO
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ScoreVO {
    private Integer userId;
    private Integer booklistId;
    private Double score;
}
