package com.white.blog.utils;

import com.white.blog.entity.User;
import org.apache.shiro.SecurityUtils;

/**
 * @author white_
 * @version 1.0
 * @project HuaJiao-system
 * @description shiro工具类
 * @date 2024/4/25 21:46:56
 */
public class ShiroUtils {
    public static User getCurrentUser() {
        return (User) SecurityUtils.getSubject().getPrincipal();
    }
}
