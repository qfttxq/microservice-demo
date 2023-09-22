package com.example.payservice.service.impl;

import com.example.comonbeans.common.Result;
import com.example.comonbeans.entity.pay.Pay;
import com.example.dubboapi.pay.PayService;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

@DubboService(version = "1")
public class PayServiceV1 implements PayService {
    private com.example.payservice.service.PayService payService;

    public PayServiceV1(@Autowired com.example.payservice.service.PayService payService) {
        this.payService = payService;
    }

    @Override
    public Result add(Pay pay) {
        payService.save(pay);
        return Result.build(200);
    }
}

