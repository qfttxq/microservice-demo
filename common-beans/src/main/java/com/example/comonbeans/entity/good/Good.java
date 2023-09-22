package com.example.comonbeans.entity.good;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

@Data
@TableName("t_good")
public class Good implements Serializable {
    @TableId
    private Long id;
    private String goodName;
    private String goodDesc;
}
