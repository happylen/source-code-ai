package com.kmbeast.pojo.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 图书借阅状态枚举
 */
@Getter
@AllArgsConstructor
public enum BookLoansStatusEnum {

    STATUS_1(1, "借阅中"),
    STATUS_2(2, "已逾期"),
    STATUS_3(3, "已归还");

    private final Integer status;
    private final String desc;
}