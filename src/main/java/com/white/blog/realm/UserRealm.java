package com.white.blog.realm;

import com.auth0.jwt.exceptions.TokenExpiredException;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.white.blog.entity.User;
import com.white.blog.service.UserService;
import com.white.blog.utils.JWTToken;
import com.white.blog.utils.JWTUtils;
import com.white.blog.utils.RedisUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @author white_
 * @version 1.0
 * @project HuaJiao-system
 * @description 自定义realm类
 * @date 2024/4/17 21:10:08
 */
@Component
public class UserRealm extends AuthorizingRealm {

    @Autowired
    private RedisUtils redisUtils;

    @Autowired
    private UserService userService;

    // 自定义token shiro识别
    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof JWTToken;
    }

    // 自定义授权方法
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        return new SimpleAuthorizationInfo();
    }

    // 自定义认证方法
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        // 此处为重写后的返回的token
        String token = (String) authenticationToken.getCredentials();
        Map<String, Object> claims;
        try {
            claims = JWTUtils.parseToken(token);
        } catch (TokenExpiredException e) {
            throw new AuthenticationException("token已过期");
        }
        // 根据token中的信息查询redis中缓存是否认证通过
        User cacheUser = (User) redisUtils.get("wCacheUser:" + claims.get("username").toString());

        if (cacheUser == null) {
            LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
            queryWrapper.eq(User::getUsername, claims.get("username"));
            User result = userService.getOne(queryWrapper);
            if (result == null)
                throw new AuthenticationException("用户不存在或已注销");
            return new SimpleAuthenticationInfo(result, token, this.getName());
        }

        // 返回数据库中用户实体信息 + 用户认证成功凭证token + realm对象的名称
        return new SimpleAuthenticationInfo(cacheUser, token, this.getName());
    }
}
