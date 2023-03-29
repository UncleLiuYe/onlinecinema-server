package com.liuyetech.onlinecinemamanager.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.liuyetech.onlinecinemamanager.domain.Order;
import com.liuyetech.onlinecinemamanager.mapper.OrderMapper;
import com.liuyetech.onlinecinemamanager.service.OrderService;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order>
        implements OrderService {
}