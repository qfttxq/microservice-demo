package com.example.warehouseservice.listener;

import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.example.comonbeans.dto.MqMessage;
import com.example.comonbeans.entity.warehouse.Warehouse;
import com.example.warehouseservice.service.WarehouseService;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RocketMQMessageListener(consumerGroup = "warehouse_cg", topic = "orderMsg", selectorExpression = "warehouse",maxReconsumeTimes=2)
public class OrderMsgMqListener implements RocketMQListener<MqMessage<Warehouse>> {

    @Autowired
    private WarehouseService warehouseService;

    @Override
    public void onMessage(MqMessage<Warehouse> message) {
        log.info("收到仓库信息：{}", message);
        Warehouse warehouse = message.getBody();
        warehouseService.save(warehouse);
    }
}


