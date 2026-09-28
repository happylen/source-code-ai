package com.kmbeast.pojo.enums;

import lombok.Getter;

/**
 * 状态枚举
 */
public class StatusEnum {

    /**
     * 登录状态枚举
     */
    @Getter
    public enum LoginStatus {
        NORMAL(false, "正常"),
        BANNED(true, "封号");

        private final Boolean code;
        private final String desc;

        LoginStatus(Boolean code, String desc) {
            this.code = code;
            this.desc = desc;
        }
    }

    /**
     * 禁言状态枚举
     */
    @Getter
    public enum SpeakStatus {
        NORMAL(false, "正常"),
        MUTED(true, "禁言");

        private final Boolean code;
        private final String desc;

        SpeakStatus(Boolean code, String desc) {
            this.code = code;
            this.desc = desc;
        }
    }
}