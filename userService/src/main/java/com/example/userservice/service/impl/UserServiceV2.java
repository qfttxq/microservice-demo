package com.example.userservice.service.impl;

import com.example.dubboapi.user.UserService;
import org.apache.dubbo.config.annotation.DubboService;

@DubboService(version = "2")
public class UserServiceV2 implements UserService {

    public String sayUser(String name){
        return String.format("Hello 22 %s",name);
    }

}
