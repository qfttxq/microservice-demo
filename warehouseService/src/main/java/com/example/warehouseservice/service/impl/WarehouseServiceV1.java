package com.example.warehouseservice.service.impl;

import com.example.comonbeans.common.Result;
import com.example.comonbeans.entity.warehouse.Warehouse;
import com.example.dubboapi.warehouse.WareHouseService;
import com.example.warehouseservice.service.WarehouseService;
import org.apache.dubbo.config.annotation.DubboService;

@DubboService(version = "1")
public class WarehouseServiceV1 implements WareHouseService {
    private WarehouseService warehouseService;

    public WarehouseServiceV1(WarehouseService warehouseService) {
        this.warehouseService = warehouseService;
    }

    @Override
    public Result add(Warehouse warehouse) throws Exception {
        warehouseService.save(warehouse);
        //throw new Exception("发生异常了");
        return Result.build(200);
    }
}
