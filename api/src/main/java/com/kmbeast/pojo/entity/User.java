package com.kmbeast.pojo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * 用户实体类
 * 对应数据库表：user
 *
 * @author B站【程序员辰星】原创出品
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "user") // 使得Java Bean与数据库user表对应
public class User {

    /**
     * 用户主键ID，自增
     */
    @TableId(type = IdType.AUTO) // ID策略为自增
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
     * 用户密码
     */
    private String password;

    /**
     * 用户头像URL
     */
    private String avatar;

    /**
     * 用户邮箱
     */
    private String email;

    /**
     * 用户角色
     * 1-管理员
     * 2-普通用户
     */
    private Integer role;

    /**
     * 用户性别
     * 1-女
     * 2-男
     */
    private Integer gender;

    /**
     * 用户生日
     */
    private LocalDate birthday;

    /**
     * 用户手机号
     */
    private String phone;

    /**
     * 用户登录状态
     * 0-正常
     * 1-封号
     */
    private Boolean loginStatus;

    /**
     * 用户禁言状态
     * 0-正常
     * 1-禁言
     */
    private Boolean speakStatus;

    /**
     * 上一次登录时间
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime lastLoginTime;

    /**
     * 创建时间/注册时间
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;
}