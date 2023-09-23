package com.example.userservice;

import com.example.userservice.config.ApplicationContextHolder;
import com.example.userservice.config.RedisConfig;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.cache.CacheProperties;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.session.MapSession;
import org.springframework.session.data.redis.RedisIndexedSessionRepository;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

@SpringBootApplication
@MapperScan(basePackages = "com.example.userservice.mapper")
@ComponentScan(basePackages = {"com.example.comonbeans.config","com.example.userservice"})
@EnableRedisHttpSession
public class UserServiceApplication {

    public static void main(String[] args) throws InterruptedException {

        ConfigurableApplicationContext applicationContext = SpringApplication.run(UserServiceApplication.class, args);
        ApplicationContextHolder.setContext(applicationContext);
    }

}
