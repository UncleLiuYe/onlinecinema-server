package com.liuyetech.onlinecinemamanager.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

@TableName(value = "tb_cast")
@Data
public class Cast implements Serializable {
    @TableId(value = "cast_id", type = IdType.AUTO)
    private Integer castId;
    @TableField
    private String castName;
    @TableField
    private String castCharacter;
    @TableField
    private String castProfileImg;

    @Serial
    private static final long serialVersionUID = 1L;
}