package com.example.payservice.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.comonbeans.common.Result;
import com.example.comonbeans.entity.pay.Pay;
import com.example.payservice.service.PayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PayController {
    @Autowired
    private PayService payService;

    @RequestMapping("add")
    public Result add(@RequestBody Pay pay) {
        payService.save(pay);
        return Result.build(200);
    }

    @RequestMapping("del")
    public Result del(String id) {
        payService.removeById(id);
        return Result.build(200);
    }

    @RequestMapping("edit")
    public Result edit(@RequestBody Pay pay) {
        payService.saveOrUpdate(pay);
        return Result.build(200);
    }

    @RequestMapping("list")
    public Result list() {
        List<Pay> list = payService.list();
        return Result.build(200, "操作成功", list);
    }
    @RequestMapping("page")
    public Result page(int current, int size) {
        Page<Pay> page = payService.page(new Page<>(current, size));
        return Result.build(200, "操作成功", page);
    }
}
