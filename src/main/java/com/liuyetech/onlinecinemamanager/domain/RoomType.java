package com.liuyetech.onlinecinemamanager.domain;

import lombok.Getter;

@Getter
public enum RoomType {
    PRIVATE(1), PUBLIC(0);

    private int code;

    private RoomType(int code) {
        this.code = code;
    }
}
