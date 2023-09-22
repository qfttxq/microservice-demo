package com.example.userservice.controller;

import com.example.comonbeans.common.Result;
import com.example.comonbeans.utils.JwtUtils;
import com.example.userservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("check")
public class CheckController {
    @Autowired
    private UserService userService;
    @RequestMapping("login")
    public Result login(String userName,String password){
         boolean bool = userService.checkUser(userName,password);
         if(bool){
             String jwt = JwtUtils.generateJwt2(30, userName);
             return Result.build(200,"登录成功",jwt);
         }else {
             return Result.build(401,"用户名或密码错误");
         }
    }
}
