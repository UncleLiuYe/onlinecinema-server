package com.liuyetech.onlinecinemamanager.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

@TableName(value = "tb_director")
@Data
public class Director implements Serializable {
    @TableId(value = "director_id", type = IdType.AUTO)
    private Integer directorId;
    @TableField
    private String directorName;
    @TableField
    private String directorAvator;

    @Serial
    private static final long serialVersionUID = 1L;
}