package com.liuyetech.onlinecinemamanager.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

@TableName(value = "tb_category")
@Data
public class Category implements Serializable {
    @TableId(value = "category_id", type = IdType.AUTO)
    private Integer categoryId;
    @TableField
    private String categoryName;

    @Serial
    private static final long serialVersionUID = 1L;
}