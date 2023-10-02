package com.example.orderservice.listener;

import com.example.comonbeans.entity.order.Order;
import com.example.orderservice.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.support.GenericMessage;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RocketMQMessageListener(consumerGroup = "order_cg", topic = "orderMsg", selectorExpression = "order")
public class OrderMsgMQListener implements RocketMQListener<Order> {
    @Autowired
    private OrderService orderService;

    @Override
    public void onMessage(Order order) {
        log.info("收到的下单订单信息：{}", order);
        orderService.save(order);
    }
}
