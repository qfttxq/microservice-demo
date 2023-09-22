package com.example.comonbeans.entity.warehouse;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

@Data
@TableName("t_warehouse")
public class Warehouse implements Serializable {
    @TableId
    private String id;
    private String goodId;
    private Integer amount;
    private String address;
}
