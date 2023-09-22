package com.example.userservice;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@MapperScan(basePackages = "com.example.userservice.mapper")
@ComponentScan(basePackages = {"com.example.comonbeans.config","com.example.userservice"})
public class UserServiceApplication {

    public static void main(String[] args) throws InterruptedException {
        SpringApplication.run(UserServiceApplication.class, args);
    }

}
