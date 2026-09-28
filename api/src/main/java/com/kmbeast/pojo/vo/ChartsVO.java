package com.kmbeast.pojo.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 图表VO类
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChartsVO {
    /**
     * 名称
     */
    private String name;
    /**
     * 值
     */
    private Integer value;
}
