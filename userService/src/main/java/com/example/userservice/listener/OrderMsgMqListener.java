package com.example.userservice.listener;

import com.example.comonbeans.entity.user.User;
import com.example.userservice.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RocketMQMessageListener(consumerGroup = "user-cg", topic = "orderMsg", selectorExpression = "user")
public class OrderMsgMqListener implements RocketMQListener<User> {
    @Autowired
    private UserService userService;

    @Override
    public void onMessage(User user) {
        log.info("收到的信息为：{}", user);
        userService.save(user);
    }
}
