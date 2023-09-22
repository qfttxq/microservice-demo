package com.example.goodservice.service.impl;

import com.example.comonbeans.common.Result;
import com.example.comonbeans.entity.good.Good;
import com.example.dubboapi.good.GoodService;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

@DubboService(version = "1")
public class GoodServiceV1 implements GoodService {
    private com.example.goodservice.service.GoodService goodService;

    public GoodServiceV1(@Autowired com.example.goodservice.service.GoodService goodService) {
        this.goodService = goodService;
    }

    @Override
    public Result add(Good good) {
        goodService.save(good);
        return Result.build(200);
    }
}
