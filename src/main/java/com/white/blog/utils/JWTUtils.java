package com.white.blog.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

import java.util.Date;
import java.util.Map;

/**
 * @author white_
 * @version 1.0
 * @project HuaJiao-system
 * @description 登录校验令牌工具类
 * @date 2024/4/11 17:08:16
 */
public class JWTUtils {
    private static final long EXPIRATION_TIME = 1000 * 60 * 60 * 24 * 7;
    private static final String KEY = "wHitE";

    // 接收业务数据,生成token并返回
    public static String getToken(Map<String, Object> claims) {
        return JWT.create().withClaim("claims", claims)
                .withExpiresAt(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .sign(Algorithm.HMAC256(KEY));
    }

    // 接收token,验证token,并返回业务数据
    public static Map<String, Object> parseToken(String token) {
        return JWT.require(Algorithm.HMAC256(KEY))
                .build()
                .verify(token)
                .getClaim("claims")
                .asMap();
    }
}
