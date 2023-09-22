package com.example.orderservice.service.impl;

import com.example.comonbeans.common.Result;
import com.example.comonbeans.entity.order.Order;
import com.example.dubboapi.order.OrderService;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

@DubboService(version = "1")
public class OrderServiceV1 implements OrderService {
    private com.example.orderservice.service.OrderService orderService;

    public OrderServiceV1(@Autowired com.example.orderservice.service.OrderService orderService) {
        this.orderService = orderService;
    }

    @Override
    public Result add(Order order) {
        orderService.save(order);
        return Result.build(200);
    }
}
