package com.liuyetech.onlinecinemamanager.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.liuyetech.onlinecinemamanager.domain.OrderDetail;
import com.liuyetech.onlinecinemamanager.entity.OrderDetailVO;
import com.liuyetech.onlinecinemamanager.entity.TicketStatisticsVO;

import java.util.List;

public interface OrderDetailService extends IService<OrderDetail> {
    List<TicketStatisticsVO> getTicketStatistics();

    List<OrderDetailVO> selectAllOrderDetail();
}
