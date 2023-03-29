package com.liuyetech.onlinecinemamanager.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

@TableName(value = "tb_video")
@Data
public class Video implements Serializable {
    @TableId(value = "video_id", type = IdType.AUTO)
    private Integer videoId;
    @TableField
    private String videoName;
    @TableField
    private String videoArea;
    @TableField
    private String videoLang;
    @TableField
    private String videoBlurb;
    @TableField
    private Integer videoDuration;
    @TableField
    private String videoTag;
    @TableField
    private LocalDateTime videoReleaseTime;
    @TableField
    private String videoDirector;
    @TableField
    private String videoActor;
    @TableField
    private Double videoMoney;
    @TableField
    private String videoType;
    @TableField
    private String videoPreviewUrl;
    @TableField
    private String videoPlayUrl;
    @TableField
    private String videoImg;

    @Serial
    private static final long serialVersionUID = 1L;
}