package com.example.orderservice.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.comonbeans.common.Result;
import com.example.comonbeans.entity.order.OrderGood;
import com.example.orderservice.service.OrderGoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("orderGood")
public class OrderGoodController {
    @Autowired
    private OrderGoodService orderGoodService;
    @RequestMapping("add")
    public Result add(@RequestBody OrderGood orderGood){
        orderGoodService.save(orderGood);
        return Result.build(200);
    }
    @RequestMapping("del")
    public Result del(String id){
        orderGoodService.removeById(id);
        return Result.build(200);
    }
    @RequestMapping("edit")
    public Result edit(@RequestBody OrderGood orderGood){
        orderGoodService.saveOrUpdate(orderGood);
        return Result.build(200);
    }
    @RequestMapping("list")
    public Result list(){
        List<OrderGood> list = orderGoodService.list();
        return Result.build(200,"操作成功",list);
    }
    @RequestMapping("page")
    public Result page(int current,int size){
        Page<OrderGood> page = orderGoodService.page(new Page<>(current, size));
        return Result.build(200,"操作成功",page);
    }

}
