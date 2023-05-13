package com.liuyetech.onlinecinemamanager.controller.admin;

import com.liuyetech.onlinecinemamanager.service.OrderDetailService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequestMapping("admin/statistics")
public class AdminStatisticsController {

    @Autowired
    private OrderDetailService orderDetailService;

    @GetMapping("list")
    public String list(ModelMap modelMap) {
        modelMap.addAttribute("data", orderDetailService.getTicketStatistics());
        return "statistics/list";
    }
}
