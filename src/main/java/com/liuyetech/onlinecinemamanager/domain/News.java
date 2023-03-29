package com.liuyetech.onlinecinemamanager.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@TableName(value = "tb_news")
@Data
public class News implements Serializable {
    @TableId(value = "news_id", type = IdType.AUTO)
    private Integer newsId;
    @TableField
    private String newsContent;
    @TableField
    private LocalDateTime newsCreatetime;
    @TableField
    private String newsTitle;
    @TableField
    private String newsTagline;
    @TableField
    private Integer newsCreaterId;

    @TableField(exist = false)
    private User user;
    private static final long serialVersionUID = 1L;
}