package com.white.blog.utils;

import org.apache.shiro.authc.AuthenticationToken;

/**
 * @author white_
 * @version 1.0
 * @project HuaJiao-system
 * @description JWTToken生成类
 * @date 2024/4/19 20:55:42
 */
public class JWTToken implements AuthenticationToken {

    private final String token;

    public JWTToken(String token) {
        this.token = token;
    }

    // 重写 shiro 的获取方法改为获取 jwt token

    // shiro 获取用户名
    @Override
    public Object getPrincipal() {
        return token;
    }

    // shiro获取密码
    @Override
    public Object getCredentials() {
        return token;
    }
}

