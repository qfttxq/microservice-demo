package com.example.comonbeans.entity.pay;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

@TableName("t_pay")
@Data
public class Pay implements Serializable {
    @TableId
    private String id;
    private String orderId;
    private Double payAmount;
    private Integer payStatus;
}
