package com.example.warehouseservice.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.comonbeans.common.Result;
import com.example.comonbeans.entity.user.User;
import com.example.comonbeans.entity.warehouse.Warehouse;
import com.example.warehouseservice.service.WarehouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.Date;
import java.util.List;

@RestController
public class WareHouseController {
    @Autowired
    private WarehouseService warehouseService;
    @RequestMapping("add")
    public Result add(@RequestBody Warehouse warehouse){
        warehouseService.save(warehouse);
        return Result.build(200);
    }
    @RequestMapping("del")
    public Result del(String id){
        warehouseService.removeById(id);
        return Result.build(200);
    }
    @RequestMapping("edit")
    public Result edit(@RequestBody Warehouse warehouse){
        warehouseService.saveOrUpdate(warehouse);
        return Result.build(200);
    }
    @RequestMapping("list")
    public Result list(){
        List<Warehouse> list = warehouseService.list();
        return Result.build(200,"操作成功",list);
    }
    @RequestMapping("page")
    public Result page(int current,int size){
        Page<Warehouse> page = warehouseService.page(new Page<>(current, size));
        return Result.build(200,"操作成功",page);
    }

}
