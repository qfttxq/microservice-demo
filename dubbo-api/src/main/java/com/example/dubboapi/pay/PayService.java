package com.example.dubboapi.pay;

import com.example.comonbeans.common.Result;
import com.example.comonbeans.entity.pay.Pay;

public interface PayService {
    default Result add(Pay pay) {
        return Result.build(200);
    }
}
