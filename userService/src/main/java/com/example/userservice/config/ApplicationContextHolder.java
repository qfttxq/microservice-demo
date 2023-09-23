package com.example.userservice.config;

import org.springframework.context.ApplicationContext;

public class ApplicationContextHolder {

    private static ApplicationContext context;

    public static ApplicationContext getContext() {
        return context;
    }

    public static void setContext(ApplicationContext context1) {
        context = context1;
    }

}
