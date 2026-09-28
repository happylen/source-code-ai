package com.kmbeast.pojo.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * 用户VO类
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserVO {

    /**
     * 用户ID
     */
    private Integer id;
    /**
     * 用户账号
     */
    private String account;
    /**
     * 用户昵称
     */
    private String username;
    /**
     * 用户头像
     */
    private String avatar;
    /**
     * 用户邮箱
     */
    private String email;
    /**
     * 用户角色
     */
    private Integer role;
    /**
     * 性别 0-女；1-男
     */
    private Integer gender;
    /**
     * 生日
     */
    private LocalDate birthday;
    /**
     * 手机号
     */
    private String phone;
    /**
     * 登录状态
     */
    private Boolean loginStatus;
    /**
     * 禁言状态
     */
    private Boolean speakStatus;
    /**
     * 上一次登录时间
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime lastLoginTime;
    /**
     * 用户注册时间
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;

}
