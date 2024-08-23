package com.white.blog.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author white_
 * @version 1.0
 * @project HuaJiao-system
 * @description swagger配置类
 * @date 2024/4/21 19:22:16
 */
@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI().info(new Info()
                .title("Blog | wHitE 博客")
                .version("1.0.0")
                .description("wHitE博客接口文档"));
    }
}