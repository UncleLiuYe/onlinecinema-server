package com.liuyetech.onlinecinemamanager.domain;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Msg {
    public static final int TYPE_RECEIVED = 0;
    public static final int TYPE_SEND = 1;
    public static final int TYPE_JOIN = 2;
    public static final int TYPE_EXIT = 3;
    
    private String content;
    private int type;
    private RoomVip roomVip;
}
