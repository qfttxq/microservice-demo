package com.example.dubboapi.good;

import com.example.comonbeans.common.Result;
import com.example.comonbeans.entity.good.Good;

public interface GoodService {
    default Result add(Good good){
        return Result.build(200);
    }
}
