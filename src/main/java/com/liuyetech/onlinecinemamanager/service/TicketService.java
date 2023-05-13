package com.liuyetech.onlinecinemamanager.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.liuyetech.onlinecinemamanager.domain.Ticket;

public interface TicketService extends IService<Ticket> {
    boolean addTicket(Ticket ticket);
}

