package com.liuyetech.onlinecinemamanager.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

@TableName(value = "tb_actor")
@Data
public class Actor implements Serializable {
    @TableId(value = "actor_id", type = IdType.AUTO)
    private Integer actorId;
    @TableField
    private String actorName;
    @TableField
    private String actorAvator;

    @Serial
    private static final long serialVersionUID = 1L;
}