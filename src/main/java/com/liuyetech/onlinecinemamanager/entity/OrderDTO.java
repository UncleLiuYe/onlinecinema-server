package com.liuyetech.onlinecinemamanager.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
@Data
@NoArgsConstructor
public class OrderDTO implements Serializable {
    private Integer movieId;
    private String movieReleaseTime;
}
