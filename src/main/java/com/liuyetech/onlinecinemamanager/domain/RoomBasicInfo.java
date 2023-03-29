package com.liuyetech.onlinecinemamanager.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class RoomBasicInfo {
    //房间名称
    private String roomName;
    //房间贵宾座位最大值
    private Integer roomMaxSize;
    //房间类型
    private Integer roomType;
    //房间视频信息
    private VideoInfo videoInfo;
    //房间创建时间
    private String roomCreateTime;
    //房间创建者信息
    private RoomCreaterInfo createrInfo;
    //房间贵宾信息
    private List<RoomVip> roomVips;
}
