package com.example.comonbeans.config;

import lombok.Getter;

@Getter
public enum PayStatus {
    SUCCESS(1, "支付成功"),
    FAIL(2, "支付失败"),
    BEPAYING(3, "正在支付");
    private int code;
    private String msg;

    PayStatus(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }


}
