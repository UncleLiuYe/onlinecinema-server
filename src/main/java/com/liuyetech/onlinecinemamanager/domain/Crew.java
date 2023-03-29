package com.liuyetech.onlinecinemamanager.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

@TableName(value = "tb_crew")
@Data
public class Crew implements Serializable {
    @TableId(value = "crew_id", type = IdType.AUTO)
    private Integer crewId;
    @TableField
    private String crewName;
    @TableField
    private String crewProfileImg;
    @TableField
    private String crewJob;

    @Serial
    private static final long serialVersionUID = 1L;
}