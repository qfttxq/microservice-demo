package com.example.warehouseservice.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.comonbeans.entity.warehouse.Warehouse;
import com.example.warehouseservice.mapper.WarehouseMapper;
import com.example.warehouseservice.service.WarehouseService;
import org.springframework.stereotype.Service;

@Service
public class WarehouseServiceImpl extends ServiceImpl<WarehouseMapper, Warehouse> implements WarehouseService {
}
