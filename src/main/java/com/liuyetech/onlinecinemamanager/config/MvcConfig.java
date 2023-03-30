package com.liuyetech.onlinecinemamanager.config;

import com.liuyetech.onlinecinemamanager.interceptor.MainInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new MainInterceptor())
                .excludePathPatterns("/api/v1/user/login", "/api/v1/video/**", "/api/v1/movie/**", "/api/v1/category/**", "/api/v1/room/list", "/api/v1/news/**", "/api/v1/pay/notify")
                .excludePathPatterns("/bootstrap4/**", "/jquery/**", "/layer/**")
                .excludePathPatterns("/", "/admin/login")
                .addPathPatterns("/**");
    }
}
