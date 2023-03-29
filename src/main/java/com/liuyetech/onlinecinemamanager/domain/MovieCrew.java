package com.liuyetech.onlinecinemamanager.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;


@TableName(value = "tb_movie_crew")
@Data
public class MovieCrew implements Serializable {
    @TableId(value = "movie_crew_id", type = IdType.AUTO)
    private Integer movieCrewId;
    @TableField
    private Integer movieCrewMovieId;
    @TableField
    private Integer movieCrewCrewId;

    @TableField(exist = false)
    private Crew crew;

    @Serial
    private static final long serialVersionUID = 1L;
}