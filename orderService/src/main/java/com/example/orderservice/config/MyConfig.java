package com.example.orderservice.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "my")
@Data
public class MyConfig {

    private Integer test;
    private Boolean shared;
    private Boolean  extension;
}
