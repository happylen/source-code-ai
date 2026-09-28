package com.kmbeast.pojo.vo;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Token响应凭证VO
 */
@Data
@AllArgsConstructor
public class TokenResponseVO {
    /**
     * 用户ID
     */
    private Integer id;
    /**
     * token令牌
     */
    private String token;
    /**
     * 角色
     */
    private Integer role;
}
