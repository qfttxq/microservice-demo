package com.example.comonbeans.entity.user;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.sql.Timestamp;
@Data
@TableName("t_user")
public class User implements Serializable {
    @TableId
    private String id;
    private String userName;
    private Integer sex;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Timestamp birthday;
    private String address;
    private String password;
}
