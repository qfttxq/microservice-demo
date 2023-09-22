package com.example.userservice.service.impl;

import com.example.dubboapi.user.UserService;
import org.apache.dubbo.config.annotation.DubboService;

@DubboService(version = "1")
public class UserServiceImpl implements UserService {

    public String sayUser(String name){
        return String.format("Hello %s",name);
    }

}
