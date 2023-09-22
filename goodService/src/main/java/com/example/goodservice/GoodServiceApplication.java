package com.example.goodservice;

import com.example.comonbeans.config.MybatisConfig;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.transaction.jta.JtaAutoConfiguration;
import org.springframework.context.annotation.Import;

@SpringBootApplication(exclude = JtaAutoConfiguration.class)
@Import(MybatisConfig.class)
@MapperScan("com.example.goodservice.mapper")
public class GoodServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(GoodServiceApplication.class, args);
    }

}
