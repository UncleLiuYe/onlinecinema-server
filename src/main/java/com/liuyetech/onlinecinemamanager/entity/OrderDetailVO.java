package com.liuyetech.onlinecinemamanager.entity;

import com.liuyetech.onlinecinemamanager.domain.Movie;
import com.liuyetech.onlinecinemamanager.domain.Order;
import com.liuyetech.onlinecinemamanager.domain.User;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class OrderDetailVO {
    private User user;
    private Order order;
    private Movie movie;
}
