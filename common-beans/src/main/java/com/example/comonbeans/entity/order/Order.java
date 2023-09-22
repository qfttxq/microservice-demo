package com.example.comonbeans.entity.order;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.sql.Timestamp;
@Data
@TableName("t_order")
public class Order implements Serializable {
    @TableId
    private String id;
    private String orderNo;
    private String userId;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Timestamp orderTime;
    private Double orderAmount;
}
