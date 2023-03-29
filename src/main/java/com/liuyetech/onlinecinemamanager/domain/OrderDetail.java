package com.liuyetech.onlinecinemamanager.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

@TableName(value = "tb_order_detail")
@Data
public class OrderDetail implements Serializable {
    @TableId(value = "order_detail_id", type = IdType.AUTO)
    private Integer orderDetailId;
    @TableField
    private Integer orderId;
    @TableField
    private Integer movieId;
    @TableField
    private Double price;
    @TableField
    private Integer num;

    @TableField(exist = false)
    private Order order;
    @TableField(exist = false)
    private Movie movie;

    @Serial
    private static final long serialVersionUID = 1L;
}