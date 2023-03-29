package com.liuyetech.onlinecinemamanager.controller.admin;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.liuyetech.onlinecinemamanager.domain.User;
import com.liuyetech.onlinecinemamanager.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequestMapping("admin/user")
public class AdminUserController {

    @Autowired
    private UserService userService;

    @GetMapping("list")
    public String userList(Integer currentPage, ModelMap modelMap) {
        if (currentPage == null || currentPage <= 0) {
            currentPage = 1;
        }
        IPage<User> page = new Page<>(currentPage, 8);
        modelMap.addAttribute("datas", userService.page(page));
        return "user/list";
    }
}
