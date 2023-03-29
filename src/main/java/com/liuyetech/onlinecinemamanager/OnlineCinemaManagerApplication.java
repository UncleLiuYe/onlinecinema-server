package com.liuyetech.onlinecinemamanager;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

@SpringBootApplication
@MapperScan(basePackages = "com.liuyetech.onlinecinemamanager.mapper")
@EnableRedisHttpSession
public class OnlineCinemaManagerApplication {
    public static void main(String[] args) {
        SpringApplication.run(OnlineCinemaManagerApplication.class, args);
    }
}
