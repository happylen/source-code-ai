package com.kmbeast.pojo.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 馆藏书目状态枚举
 */
@Getter
@AllArgsConstructor
public enum BookItemStatusEnum {

    STATUS_1(1, "在馆"),
    STATUS_2(2, "借出"),
    STATUS_3(3, "损毁"),
    STATUS_4(4, "销毁");

    private final Integer status;
    private final String desc;
}