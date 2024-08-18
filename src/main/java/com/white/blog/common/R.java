package com.white.blog.common;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * @author white_
 * @version 1.0
 * @project HuaJiao-system
 * @description 通用结果响应类
 * @date 2024/4/11 16:22:33
 */
@Data
public class R<T> {

    private Integer code; // 状态码 1成功, 0失败

    private String msg; // 错误信息

    private T data; // 数据

    private Map<String, Object> info = new HashMap<>(); // 动态数据

    public static <T> R<T> success(T object) {
        R<T> r = new R<T>();
        r.data = object;
        r.code = 200;
        return r;
    }

    public static <T> R<T> success(T object, Integer code) {
        R<T> r = new R<T>();
        r.data = object;
        r.code = Objects.requireNonNullElse(code, 200);
        return r;
    }

    public static <T> R<T> error(String msg) {
        R<T> r = new R<T>();
        r.msg = msg;
        r.code = 500;
        return r;
    }

    public static <T> R<T> error(String msg, Integer code) {
        R<T> r = new R<T>();
        r.msg = msg;
        r.code = Objects.requireNonNullElse(code, 500);
        return r;
    }

    public R<T> add(String key, Object value) {
        this.info.put(key, value);
        return this;
    }

}
