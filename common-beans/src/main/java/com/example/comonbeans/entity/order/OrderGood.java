package com.example.comonbeans.entity.order;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

@Data
@TableName("t_order_good")
public class OrderGood implements Serializable {
    @TableId
    private String id;
    private String orderId;
    private String goodId;
}
