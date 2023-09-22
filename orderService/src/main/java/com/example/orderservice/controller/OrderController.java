package com.example.orderservice.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.comonbeans.common.Result;
import com.example.comonbeans.entity.order.Order;
import com.example.dubboapi.user.UserService;
import com.example.orderservice.config.MyConfig;
import com.example.orderservice.service.OrderService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class OrderController {
    @DubboReference(version = "2")
    private UserService userService;
    @Autowired
    private OrderService orderService;
    @Autowired
    private MyConfig myConfig;
    @RequestMapping("say")
    public String say() {
        System.out.println(String.format("test:%s,shared:%s,extension:%s",myConfig.getTest(),myConfig.getShared(),myConfig.getExtension()));
        return userService.sayUser("zhangsan");
    }

    @RequestMapping("add")
    public Result add(@RequestBody Order order){
        orderService.save(order);
        return Result.build(200);
    }

    @RequestMapping("del")
    public Result del(String id){
        orderService.removeById(id);
        return Result.build(200);
    }

    @RequestMapping("edit")
    public Result edit(@RequestBody Order order){
        orderService.saveOrUpdate(order);
        return Result.build(200);
    }

    @RequestMapping("list")
    public Result list(){
        List<Order> list = orderService.list();
        return Result.build(200,"操作成功",list);
    }

    @RequestMapping("page")
    public Result page(int current,int size){
        Page<Order> page = orderService.page(new Page<>(current, size));
        return Result.build(200,"操作成功",page);
    }
}
