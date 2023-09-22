package com.example.goodservice.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.comonbeans.common.Result;
import com.example.comonbeans.entity.good.Good;
import com.example.goodservice.service.GoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class GoodController {
    @Autowired
    public GoodService goodService;

    @RequestMapping("add")
    public Result add(@RequestBody Good good) {
        goodService.save(good);
        return Result.build(200);
    }
    @RequestMapping("del")
    public Result del(String id) {
        goodService.removeById(id);
        return Result.build(200);
    }
    @RequestMapping("edit")
    public Result edit(@RequestBody Good good) {
        goodService.saveOrUpdate(good);
        return Result.build(200);
    }
    @RequestMapping("list")
    public Result list() {
        List<Good> list = goodService.list();
        return Result.build(200,"操作成功",list);
    }
    @RequestMapping("page")
    public Result page(int current,int size) {
        Page<Good> page = goodService.page(new Page<>(current, size));
        return Result.build(200,"操作成功",page);
    }
}
