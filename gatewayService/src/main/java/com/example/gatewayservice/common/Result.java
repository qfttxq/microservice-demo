package com.example.gatewayservice.common;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

@Data
@AllArgsConstructor
public class Result<T> implements Serializable {
    private int code;
    private String msg;
    private T data;

    public static <T>  Result<T>  build(int code){
        return  new Result<>(code,"操作成功",null);
    }

    public static <T>  Result<T>  build(int code,String msg){
        return  new Result<>(code,msg,null);
    }

    public static <T>  Result<T>  build(int code,String msg,T data){
        return  new Result<>(code,msg,data);
    }
}
