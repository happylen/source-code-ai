package com.kmbeast.pojo.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 图书类别查询条件类
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class CategoryQueryDTO extends QueryDTO{

    /**
     * 名称
     */
    private String name;

    /**
     * 层级
     */
    private Integer level;

    /**
     * 父级ID
     */
    private Integer parentId;

}
