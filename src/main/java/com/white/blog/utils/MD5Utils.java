package com.white.blog.utils;

import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.lang.util.ByteSource;

/**
 * @author white_
 * @version 1.0
 * @project HuaJiao-system
 * @description shiro MD5加密工具类
 * @date 2024/4/16 22:19:58
 */
public class MD5Utils {
    public static String getMD5Password(String password, String salt) {
        // 参数依次为： 加密算法类型 被加密密码 盐值 加密次数
        // ByteSource.Util.bytes 根据传入值获取盐值
        // toString 结果转为 字符串
        // toHex 结果转为 16进制字符串
        // toBase64 结果转为 Base64码
        return new SimpleHash("MD5", password, ByteSource.Util.bytes(salt), 1024).toString();
    }
}
