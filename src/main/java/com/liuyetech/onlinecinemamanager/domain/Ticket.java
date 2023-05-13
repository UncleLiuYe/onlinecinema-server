package com.liuyetech.onlinecinemamanager.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

@TableName(value = "tb_ticket")
@Data
public class Ticket implements Serializable {
    @TableId(value = "ticket_id", type = IdType.AUTO)
    private Integer ticketId;
    @TableField
    private String ticketNo;
    @TableField
    private Integer ticketUserId;
    @TableField
    private Integer ticketMovieId;
    @TableField
    private LocalDateTime ticketExpireTime;
    @TableField
    private LocalDateTime ticketCreateTime;
    @TableField
    private Integer ticketStatus;
    @Serial
    private static final long serialVersionUID = 1L;

    @TableField(exist = false)
    @JsonIgnore
    private User user;
    @TableField(exist = false)
    private Movie movie;
}