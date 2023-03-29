package com.liuyetech.onlinecinemamanager.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

@TableName(value = "tb_movie")
@Data
public class Movie implements Serializable {
    @TableId(value = "movie_id", type = IdType.AUTO)
    private Integer movieId;
    @TableField
    private String movieName;
    @TableField
    private String movieOverview;
    @TableField
    private String movieLang;
    @TableField
    private String movieTagline;
    @TableField
    private String movieArea;
    @TableField
    private Integer movieRuntime;
    @TableField
    private Integer movieStatus;
    @TableField
    private String movieReleaseTime;
    @TableField
    private String movieGenres;
    @TableField
    private Double moviePrice;
    @TableField
    private Integer movieType;
    @TableField
    private String moviePoster;
    @TableField
    private String moviePreviewUrl;
    @TableField
    private String moviePlayUrl;

    @TableField(exist = false)
    private Category category;

    @Serial
    private static final long serialVersionUID = 1L;
}