package com.example.userservice.controller;

import com.example.comonbeans.common.Result;
import com.example.comonbeans.entity.user.User;
import com.example.userservice.service.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Slf4j
@RestController
@RequestMapping("check")
public class CheckController {
    @Autowired
    private UserService userService;
    @Autowired
    private HttpServletRequest request;
    @Autowired
    private HttpServletResponse response;
    @Autowired
    private ObjectMapper objectMapper;
    @Value("${spring.session.timeout}")
    private Integer timeout;

    @RequestMapping("login")
    public Result login(String userName, String password) {
        User user = userService.validate(userName, password);
        HttpSession session = request.getSession();
        session.setMaxInactiveInterval(timeout);
        String userJson = null;
        try {
            userJson = objectMapper.writeValueAsString(user);
            session.setAttribute("user", userJson);
        } catch (JsonProcessingException e) {
            log.info(e.getMessage(), e);
        }

        if (user == null) {
            return Result.build(401, "用户名或密码错误");
        }
        return Result.build(200, "登录成功");
    }
}
