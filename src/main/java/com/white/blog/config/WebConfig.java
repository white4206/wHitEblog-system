package com.white.blog.config;

import com.white.blog.utils.aop.MultiRequestBodyArgumentResolver;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.CacheControl;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.nio.charset.Charset;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author white_
 * @version 1.0
 * @project wHitEblog-system
 * @description WebConfig配置类
 * @date 2024/6/25 16:30:49
 */

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Value("${wHitEblog.uploadPath}")
    private String uploadPath;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        /** 本地文件上传路径 */
        registry.addResourceHandler("resource" + "/**")
                .addResourceLocations("file:" + uploadPath + "/");

    }

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
        // 添加MultiRequestBody参数解析器
        argumentResolvers.add(new MultiRequestBodyArgumentResolver());
    }

    @Bean
    public HttpMessageConverter<String> responseBodyConverter() {
        // 解决中文乱码问题
        return new StringHttpMessageConverter(Charset.forName("UTF-8"));
    }

    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        converters.add(responseBodyConverter());
    }
}