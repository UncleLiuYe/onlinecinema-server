package com.liuyetech.onlinecinemamanager.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.liuyetech.onlinecinemamanager.domain.OrderDetail;
import com.liuyetech.onlinecinemamanager.entity.OrderDetailVO;
import com.liuyetech.onlinecinemamanager.entity.TicketStatisticsVO;
import com.liuyetech.onlinecinemamanager.mapper.OrderDetailMapper;
import com.liuyetech.onlinecinemamanager.service.OrderDetailService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderDetailServiceImpl extends ServiceImpl<OrderDetailMapper, OrderDetail>
        implements OrderDetailService {

    @Override
    public List<TicketStatisticsVO> getTicketStatistics() {
        return baseMapper.getTicketStatistics();
    }

    @Override
    public List<OrderDetailVO> selectAllOrderDetail() {
        return baseMapper.selectAllOrderDetail();
    }
}




