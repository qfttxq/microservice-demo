package com.example.goodservice.listener;

import com.example.comonbeans.entity.good.Good;
import com.example.goodservice.service.GoodService;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.support.GenericMessage;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RocketMQMessageListener(consumerGroup = "good_cg", topic = "orderMsg", selectorExpression = "good")
public class OrderListener implements RocketMQListener<Good> {
    @Autowired
    private GoodService goodService;

    @Override
    public void onMessage(Good good) {
        log.info("收到下单的商品消息:{}", good);
        goodService.save(good);
    }
}
