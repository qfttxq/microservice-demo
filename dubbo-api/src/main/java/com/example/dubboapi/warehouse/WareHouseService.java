package com.example.dubboapi.warehouse;

import com.example.comonbeans.common.Result;
import com.example.comonbeans.entity.warehouse.Warehouse;

public interface WareHouseService {
    default Result add(Warehouse warehouse) throws Exception {
        return Result.build(200);
    }
}
