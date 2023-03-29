package com.liuyetech.onlinecinemamanager.entity;

import lombok.Data;

@Data
public class R<T> {
    private int code;
    private String msg;
    private T data;


    public static <T> R<T> success(String msg) {
        return success(msg, null);
    }

    public static <T> R<T> success(String msg, T data) {
        R<T> result = new R<>();
        result.setCode(200);
        result.setMsg(msg);
        result.setData(data);
        return result;
    }

    public static <T> R<T> success(T data) {
        return success("操作成功", data);
    }

    public static <T> R<T> fail(String msg, T data) {
        R<T> result = new R<>();
        result.setCode(500);
        result.setMsg(msg);
        result.setData(data);
        return result;
    }

    public static <T> R<T> fail(String msg) {
        R<T> result = new R<>();
        result.setCode(500);
        result.setMsg(msg);
        result.setData(null);
        return result;
    }

    public static <T> R<T> unauth() {
        R<T> result = new R<>();
        result.setCode(401);
        result.setMsg("");
        result.setData(null);
        return result;
    }

    public static <T> R<T> unauth(String msg) {
        R<T> result = new R<>();
        result.setCode(401);
        result.setMsg(msg);
        result.setData(null);
        return result;
    }
}
