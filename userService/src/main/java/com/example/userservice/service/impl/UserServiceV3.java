package com.example.userservice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.comonbeans.common.Result;
import com.example.comonbeans.config.PayStatus;
import com.example.comonbeans.dto.MqMessage;
import com.example.comonbeans.entity.good.Good;
import com.example.comonbeans.entity.order.Order;
import com.example.comonbeans.entity.order.OrderGood;
import com.example.comonbeans.entity.pay.Pay;
import com.example.comonbeans.entity.user.User;
import com.example.comonbeans.entity.warehouse.Warehouse;
import com.example.dubboapi.good.GoodService;
import com.example.dubboapi.order.OrderGoodService;
import com.example.dubboapi.order.OrderService;
import com.example.dubboapi.pay.PayService;
import com.example.dubboapi.warehouse.WareHouseService;
import com.example.userservice.mapper.UserMapper;
import com.example.userservice.service.UserService;
import com.fasterxml.jackson.databind.ObjectReader;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboReference;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.support.GenericMessage;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.*;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Slf4j
@Service
public class UserServiceV3 extends ServiceImpl<UserMapper, User> implements UserService {

    public static final String TOPIC_ORDER_MSG = "orderMsg";
    @DubboReference(version = "1")
    private OrderService orderService;
    @DubboReference(version = "1")
    private OrderGoodService orderGoodService;
    @DubboReference(version = "1")
    private GoodService goodService;
    @DubboReference(version = "1")
    private PayService payService;
    @DubboReference(version = "1")
    private WareHouseService wareHouseService;

    @Autowired
    private RocketMQTemplate rocketMQTemplate;

    @Override
    public Result placeAnOrder() throws Exception {
        //生成用户
        User user = new User();
        Long id = IdWorker.getId();
        user.setId(String.valueOf(id));
        user.setUserName("wangwu"+id);
        user.setSex(1);
        LocalDate birthday = LocalDate.of(1988, 8, 22);
        user.setBirthday(new Timestamp(birthday.atStartOfDay(ZoneOffset.ofHours(0)).toInstant().toEpochMilli()));
        user.setAddress("中国湖南省长沙市");

        rocketMQTemplate.send(TOPIC_ORDER_MSG+":user",new GenericMessage<>(user));

        //生成商品
        Good good = new Good();
        good.setId(IdWorker.getId());
        good.setGoodName("测试商品");
        good.setGoodDesc("测试描述");
        //goodService.add(good);
        rocketMQTemplate.send(TOPIC_ORDER_MSG + ":good", new GenericMessage<>(good));
        //生成订单
        Order order = new Order();
        order.setId(IdWorker.getIdStr());
        order.setOrderNo(UUID.randomUUID().toString().replace("-", ""));
        order.setUserId(user.getId());
        order.setOrderAmount(1000.0);
        LocalDateTime orderTime = LocalDateTime.now();
        order.setOrderTime(new Timestamp(orderTime.toInstant(ZoneOffset.ofHours(0)).toEpochMilli()));
        rocketMQTemplate.send(TOPIC_ORDER_MSG + ":order", new GenericMessage<>(order));
        //生成订单与商品关联
        OrderGood orderGood = new OrderGood();
        orderGood.setId(IdWorker.getIdStr());
        orderGood.setOrderId(order.getId());
        orderGood.setGoodId(String.valueOf(good.getId()));
        //orderGoodService.add(orderGood);
        //rocketMQTemplate.send(TOPIC_ORDER_MSG + ":orderGood", new GenericMessage<>(orderGood));
        Map<String,Object> headers1 = new HashMap<>();
        headers1.put("abc",1234);
        rocketMQTemplate.convertAndSend(TOPIC_ORDER_MSG + ":orderGood",orderGood,headers1);
        //支付
        Pay pay = new Pay();
        pay.setOrderId(order.getId());
        pay.setPayAmount(order.getOrderAmount());
        pay.setPayStatus(PayStatus.BEPAYING.getCode());
        //payService.add(pay);
        rocketMQTemplate.send(TOPIC_ORDER_MSG + ":pay", new GenericMessage<>(pay));
        //仓库处理
        Warehouse warehouse = new Warehouse();
        warehouse.setGoodId(String.valueOf(good.getId()));
        warehouse.setAmount(1);
        warehouse.setAddress("中国湖南长沙");
        //wareHouseService.add(warehouse);
        Map<String,Object> headers = new HashMap<>();
        headers.put("abc","cba");
        MqMessage message = new MqMessage(warehouse,headers);
        rocketMQTemplate.send(TOPIC_ORDER_MSG + ":warehouse", new GenericMessage<>(message));
        return Result.build(200);
    }

    @Override
    public boolean checkUser(String userName, String password) {
        boolean matches = false;
        try {
            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            QueryWrapper<User> wrapper = new QueryWrapper<>();
            wrapper.eq("user_name", userName);
            wrapper.select("password");
            User user = getOne(wrapper);
            String pw = user.getPassword();
            matches = passwordEncoder.matches(password, pw);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return matches;
    }

    @Override
    public User validate(String userName, String password) {

        boolean matches = false;
        try {
            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            QueryWrapper<User> wrapper = new QueryWrapper<>();
            wrapper.eq("user_name", userName);
            User user = getOne(wrapper);
            String pw = user.getPassword();
            matches = passwordEncoder.matches(password, pw);
            if (matches) {
                return user;
            }
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return null;
    }


}
