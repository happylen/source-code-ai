package com.kmbeast.pojo.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 用户行为操作查询条件类
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class UserActionQueryDTO extends QueryDTO {

    /**
     * 用户ID，外键，关联的是用户表
     */
    private Integer userId;

    /**
     * 书目ID，外键，关联的是书目信息表
     */
    private Integer booklistId;

    /**
     * 类型（1-收藏；2-点击；3-想看；4-停留时长）
     */
    private Integer type;

}
