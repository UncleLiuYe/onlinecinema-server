package com.liuyetech.onlinecinemamanager.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

@TableName(value = "tb_user")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User implements Serializable {
    @TableId(value = "user_id", type = IdType.AUTO)
    private Integer userId;
    @TableField
    private String userName;
    @TableField
    @JsonIgnore
    private String userPassword;
    @TableField
    private String userNickname;
    @TableField
    private String userAvator;
    @TableField
    private Integer userSex;

    @Serial
    private static final long serialVersionUID = 1L;
}