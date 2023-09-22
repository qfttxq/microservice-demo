package com.example.dubboapi.order;

import com.example.comonbeans.common.Result;
import com.example.comonbeans.entity.order.Order;

public interface OrderService {
    default Result add(Order order){
        return Result.build(200);
    }
}
