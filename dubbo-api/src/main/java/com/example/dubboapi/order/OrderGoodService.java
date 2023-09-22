package com.example.dubboapi.order;

import com.example.comonbeans.common.Result;
import com.example.comonbeans.entity.order.OrderGood;

public interface OrderGoodService {
    default Result add(OrderGood orderGood){
        return Result.build(200);
    }
}
