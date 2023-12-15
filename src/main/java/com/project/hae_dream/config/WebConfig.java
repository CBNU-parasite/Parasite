package com.project.hae_dream.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
@Configuration
public class WebConfig implements WebMvcConfigurer {
    private String savePath = "file:///Users/wonsick/springboot_img/"; // 실제 파일 저장 경로
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        //view 에서 접근 할 경로
        String resourcePath = "/upload/**";
        registry.addResourceHandler(resourcePath)
                .addResourceLocations(savePath);
    }
}