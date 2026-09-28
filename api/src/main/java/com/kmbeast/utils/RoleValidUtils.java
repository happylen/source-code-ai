package com.kmbeast.utils;

import com.kmbeast.context.LocalThreadHolder;
import com.kmbeast.pojo.enums.RoleEnum;

/**
 * 角色鉴权工具类
 */
public class RoleValidUtils {

    /**
     * 要求用户必须是管理员，否则抛异常
     *
     * @param message 异常消息
     */
    public static void requestedAdmin(String message) {
        AssertUtils.equals(
                LocalThreadHolder.getRoleId(),
                RoleEnum.ADMIN.getRole(),
                message
        );
    }

}
