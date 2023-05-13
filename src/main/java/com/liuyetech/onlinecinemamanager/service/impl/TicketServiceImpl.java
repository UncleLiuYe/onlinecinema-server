package com.liuyetech.onlinecinemamanager.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.liuyetech.onlinecinemamanager.domain.Ticket;
import com.liuyetech.onlinecinemamanager.mapper.TicketMapper;
import com.liuyetech.onlinecinemamanager.service.TicketService;
import org.springframework.stereotype.Service;

@Service
public class TicketServiceImpl extends ServiceImpl<TicketMapper, Ticket>
        implements TicketService {

    @Override
    public boolean addTicket(Ticket ticket) {
        return baseMapper.insert(ticket) > 0;
    }
}