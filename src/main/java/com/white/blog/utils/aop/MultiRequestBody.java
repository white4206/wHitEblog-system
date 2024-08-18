package com.white.blog.utils.aop;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author white_
 * @version 1.0
 * @project wHitEblog-system
 * @description Controller层接受多个RequestBody参数注解接口
 * @date 2024/6/25 16:30:49
 */

@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
public @interface MultiRequestBody {
    /**
     * 是否必须出现的参数
     */
    boolean required() default true;

    /**
     * 当value的值或者参数名不匹配时，是否允许解析最外层属性到该对象
     */
    boolean parseAllFields() default true;

    /**
     * 解析时用到的JSON的key
     */
    String value() default "";
}
