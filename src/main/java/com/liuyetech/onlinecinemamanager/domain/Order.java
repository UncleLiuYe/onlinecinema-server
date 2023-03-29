package com.liuyetech.onlinecinemamanager.domain;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

@TableName(value = "tb_order")
@Data
public class Order implements Serializable {
    @TableId(value = "order_id", type = IdType.AUTO)
    private Integer orderId;
    @TableField
    private String tradeNo;
    @TableField
    private String alipayTradeNo;
    @TableField
    private Double totalAmount;
    @TableField
    private LocalDateTime createTime;
    @TableField
    private LocalDateTime updateTime;
    @TableField
    private Integer userId;
    @TableField
    private Integer status;

    @TableField(exist = false)
    private User user;
    @TableField(exist = false)
    private OrderDetail orderDetail;

    @Serial
    private static final long serialVersionUID = 1L;
}