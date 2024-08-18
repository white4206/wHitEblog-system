package com.white.blog.filter;

import com.white.blog.utils.JWTToken;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.web.filter.authc.BasicHttpAuthenticationFilter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMethod;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

/**
 * @author white_
 * @version 1.0
 * @project HuaJiao-system
 * @description shiro登录过滤器类
 * @date 2024/4/19 21:03:29
 */
@Slf4j
public class LoginFilter extends BasicHttpAuthenticationFilter {

    // 是否允许访问
    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
        // 有认证意愿
        try {
            executeLogin(request, response);
            return true;
        } catch (Exception e) {
            // token错误
            responseError(response, e.getMessage());
            return false;
        }
    }

    // 执行认证
    @Override
    protected boolean executeLogin(ServletRequest request, ServletResponse response) {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        String token = httpServletRequest.getHeader("Authorization");
        if (token == null) {
            throw new AuthenticationException("unauthorized");
        }
        // 使用自定义的JWTToken而不是默认的UsernamePasswordToken
        try {
            getSubject(request, response).login(new JWTToken(token));
        } catch (AuthenticationException e) {
            // 捕获AccessToken过期异常
            log.warn(e.getMessage());
            throw new AuthenticationException("expired");
        }
        // 调用了realm中的认证方法，没有出现异常则证明认证成功
        return true;
    }

    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) {
        return false;
    }

    @Override
    protected boolean preHandle(ServletRequest request, ServletResponse response) throws Exception {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        res.setHeader("Access-control-Allow-Origin", req.getHeader("Origin"));
        res.setHeader("Access-control-Allow-Methods", "GET,POST,OPTIONS,PUT,DELETE");
        res.setHeader("Access-control-Allow-Headers", req.getHeader("Access-Control-Request-Headers"));
        // 跨域时会首先发送一个option请求，这里我们给option请求直接返回正常状态
        if (req.getMethod().equals(RequestMethod.OPTIONS.name())) {
            res.setStatus(HttpStatus.OK.value());
            // 返回true则继续执行拦截链，返回false则中断后续拦截，直接返回，option请求显然无需继续判断，直接返回
            return false;
        }
        return super.preHandle(request, response);
    }

    // 非法请求跳转
    private void responseError(ServletResponse response, String msg) {
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        // msg封装为get请求的请求参数，即拼接在url后面，对于中文信息需要进行utf-8编码
        msg = URLEncoder.encode(msg, StandardCharsets.UTF_8);
        // 跳转至控制器unauthorized
        try {
            httpServletResponse.sendRedirect("/unauthorized?message=" + msg);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
