package com.example.orderservice.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.comonbeans.entity.order.OrderGood;
import com.example.orderservice.mapper.OrderGoodMapper;
import com.example.orderservice.service.OrderGoodService;
import org.springframework.stereotype.Service;

@Service
public class OrderGoodServiceImpl extends ServiceImpl<OrderGoodMapper, OrderGood> implements OrderGoodService {
}
