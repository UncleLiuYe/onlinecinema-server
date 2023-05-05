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
import org.springframework.web.bind.annotation.RequestParam;

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

    @GetMapping("delete")
    public String deleteCategory(@RequestParam("uid") Integer uid, ModelMap modelMap) {
        if (userService.removeById(uid)) {
            modelMap.addAttribute("msg", "删除成功！");
            modelMap.addAttribute("url", "/admin/user/list");
            return "msg";
        }
        modelMap.addAttribute("msg", "删除失败！");
        modelMap.addAttribute("url", "/admin/user/list");
        return "msg";
    }
}
