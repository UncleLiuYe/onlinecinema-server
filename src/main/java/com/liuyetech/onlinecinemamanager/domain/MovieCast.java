package com.liuyetech.onlinecinemamanager.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

@TableName(value = "tb_movie_cast")
@Data
public class MovieCast implements Serializable {
    @TableId(value = "movie_cast_id", type = IdType.AUTO)
    private Integer movieCastId;
    @TableField
    private Integer movieCastMovieId;
    @TableField
    private Integer movieCastCastId;

    @TableField(exist = false)
    private Cast cast;

    @Serial
    private static final long serialVersionUID = 1L;
}