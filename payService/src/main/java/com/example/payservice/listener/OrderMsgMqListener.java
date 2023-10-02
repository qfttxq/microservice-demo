package com.example.payservice.listener;

import com.example.comonbeans.entity.pay.Pay;
import com.example.payservice.service.PayService;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RocketMQMessageListener(consumerGroup = "pay_cg",topic = "orderMsg",selectorExpression = "pay")
public class OrderMsgMqListener implements RocketMQListener<Pay> {
    @Autowired
    private PayService payService;
    @Override
    public void onMessage(Pay pay) {
        log.info("收到的消息为：{}",pay);
        payService.save(pay);
    }
}
