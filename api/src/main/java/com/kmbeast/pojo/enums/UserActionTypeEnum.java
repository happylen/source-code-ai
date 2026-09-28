package com.kmbeast.pojo.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 用户行为操作类型枚举
 */
@Getter
@AllArgsConstructor
public enum UserActionTypeEnum {

    STATUS_1(1, "收藏"),
    STATUS_2(2, "点击"),
    STATUS_3(3, "想看"),
    STATUS_4(4, "停留时长");


    private final Integer status;
    private final String desc;
}