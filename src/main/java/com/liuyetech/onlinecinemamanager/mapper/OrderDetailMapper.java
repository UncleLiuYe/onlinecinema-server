package com.liuyetech.onlinecinemamanager.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.liuyetech.onlinecinemamanager.domain.OrderDetail;
import com.liuyetech.onlinecinemamanager.entity.OrderDetailVO;
import com.liuyetech.onlinecinemamanager.entity.TicketStatisticsVO;

import java.util.List;


public interface OrderDetailMapper extends BaseMapper<OrderDetail> {
    List<TicketStatisticsVO> getTicketStatistics();

    List<OrderDetailVO> selectAllOrderDetail();
}