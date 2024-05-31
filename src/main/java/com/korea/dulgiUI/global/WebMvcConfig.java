package com.korea.dulgiUI.global;

import com.korea.dulgiUI.dulgiUIApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/images/**") // 원하는 URL 패턴 설정
                .addResourceLocations(dulgiUIApplication.getOsType().getResourceHandler()); // 정적 자원이 위치한 디렉토리 설정
    }
}