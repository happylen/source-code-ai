package com.kmbeast.utils;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.UUID;

/**
 * JWT Token 工具类
 * @author B站【程序员辰星】原创出品
 */
@Component
public class JwtUtil {

    private static final Logger logger = LoggerFactory.getLogger(JwtUtil.class);

    // 使用安全的密钥生成方式
    private static final SecretKey SECRET_KEY;
    private static final long EXPIRATION_TIME = 1000L * 60 * 60 * 24 * 7; // 有效期一周

    static {
        // 使用安全的密钥生成，避免 Base64 编码问题
        String privateKey = "d8c986df-8512-42b5-906f-eeea9b3acf86";
        SECRET_KEY = Keys.hmacShaKeyFor(privateKey.getBytes(StandardCharsets.UTF_8));
    }

    /**
     * 生成 token
     *
     * @param id   用户ID
     * @param role 用户角色
     * @return String
     */
    public static String toToken(Integer id, Integer role) {
        try {
            return Jwts.builder()
                    .setHeaderParam("typ", "JWT")
                    .setHeaderParam("alg", "HS256")
                    .claim("id", id)
                    .claim("role", role)
                    .setSubject("用户认证")
                    .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                    .setId(UUID.randomUUID().toString())
                    .signWith(SECRET_KEY, SignatureAlgorithm.HS256)
                    .compact();
        } catch (Exception e) {
            logger.error("生成JWT Token失败: {}", e.getMessage(), e);
            throw new RuntimeException("生成Token失败", e);
        }
    }

    /**
     * 解析 token
     *
     * @param token token信息
     * @return Claims
     */
    public static Claims fromToken(String token) {
        if (token == null || token.trim().isEmpty()) {
            logger.warn("Token为空");
            return null;
        }

        try {
            // 清理token中的可能空格
            String cleanToken = token.trim();

            return Jwts.parserBuilder()
                    .setSigningKey(SECRET_KEY)
                    .build()
                    .parseClaimsJws(cleanToken)
                    .getBody();

        } catch (ExpiredJwtException e) {
            logger.warn("JWT Token已过期: {}", token);
            return null;
        } catch (MalformedJwtException e) {
            logger.warn("JWT Token格式错误: {}", token);
            return null;
        } catch (SecurityException e) {
            logger.warn("JWT Token签名验证失败: {}", token);
            return null;
        } catch (IllegalArgumentException e) {
            logger.warn("JWT Token参数错误: {}", e.getMessage());
            return null;
        } catch (Exception e) {
            logger.error("解析JWT Token异常: {}", e.getMessage(), e);
            return null;
        }
    }

    /**
     * 验证token是否有效
     *
     * @param token token信息
     * @return boolean
     */
    public static boolean validateToken(String token) {
        try {
            Claims claims = fromToken(token);
            return claims != null && !isTokenExpired(claims);
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * 检查token是否过期
     *
     * @param claims token声明
     * @return boolean
     */
    private static boolean isTokenExpired(Claims claims) {
        return claims.getExpiration().before(new Date());
    }

    /**
     * 从token中获取用户ID
     *
     * @param token token信息
     * @return Integer
     */
    public static Integer getUserIdFromToken(String token) {
        try {
            Claims claims = fromToken(token);
            return claims != null ? claims.get("id", Integer.class) : null;
        } catch (Exception e) {
            logger.error("从Token获取用户ID失败: {}", e.getMessage());
            return null;
        }
    }

    /**
     * 从token中获取用户角色
     *
     * @param token token信息
     * @return Integer
     */
    public static Integer getRoleFromToken(String token) {
        try {
            Claims claims = fromToken(token);
            return claims != null ? claims.get("role", Integer.class) : null;
        } catch (Exception e) {
            logger.error("从Token获取用户角色失败: {}", e.getMessage());
            return null;
        }
    }
}