package com.carboncapture.carbon.framework.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author Santa Antilles
 * @description 配置全局允许5173端口跨域
 * @date 2024/1/20-16:17:14
 */
@Configuration
public class CorsConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("5173")
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTION")
                .allowCredentials(true);
    }
}
