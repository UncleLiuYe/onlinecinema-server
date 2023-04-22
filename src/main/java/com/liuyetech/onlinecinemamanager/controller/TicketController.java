package com.liuyetech.onlinecinemamanager.controller;

import cn.dev33.satoken.stp.StpUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.liuyetech.onlinecinemamanager.domain.Ticket;
import com.liuyetech.onlinecinemamanager.entity.R;
import com.liuyetech.onlinecinemamanager.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/ticket")
public class TicketController {
    @Autowired
    private TicketService ticketService;

    @GetMapping("list")
    public R<?> list() {
        Integer uid = Integer.valueOf(StpUtil.getLoginId().toString());
        QueryWrapper<Ticket> ticketQueryWrapper = new QueryWrapper<>();
        ticketQueryWrapper.eq("ticket_user_id", uid);
        return R.success(ticketService.list(ticketQueryWrapper));
    }

}
