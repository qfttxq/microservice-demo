package com.example.orderservice.listener;

import com.example.comonbeans.entity.order.OrderGood;
import com.example.orderservice.service.OrderGoodService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Slf4j
@Component
@RocketMQMessageListener(consumerGroup = "orderGood_cg", topic = "orderMsg", selectorExpression = "orderGood")
public class OrderMsgGoodMQListener implements RocketMQListener<MessageExt> {
    @Autowired
    private OrderGoodService orderGoodService;
    @Autowired
    private ObjectMapper objectMapper;
    @Override
    public void onMessage(MessageExt messageExt) {
        log.info("收到的下单订单商品信息：{}", messageExt);
        try {
            OrderGood orderGood = objectMapper.readValue(messageExt.getBody(), OrderGood.class);
            orderGoodService.save(orderGood);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
