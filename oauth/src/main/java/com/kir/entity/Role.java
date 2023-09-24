package com.kir.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("role")
public class Role {

    @TableId(value = "id",type = IdType.AUTO)
    private int id;

    @TableField("user_id")
    private int userId;

    @TableField("role")
    private String role;
}
