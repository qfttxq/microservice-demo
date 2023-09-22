package com.example.orderservice.service.impl;

import com.example.comonbeans.common.Result;
import com.example.comonbeans.entity.order.OrderGood;
import com.example.dubboapi.order.OrderGoodService;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

@DubboService(version = "1")
public class OrderGoodServiceV1 implements OrderGoodService {
    private com.example.orderservice.service.OrderGoodService orderGoodService;

    public OrderGoodServiceV1(@Autowired com.example.orderservice.service.OrderGoodService orderGoodService) {
        this.orderGoodService = orderGoodService;
    }

    @Override
    public Result add(OrderGood orderGood) {
        orderGoodService.save(orderGood);
        return Result.build(200);
    }
}
