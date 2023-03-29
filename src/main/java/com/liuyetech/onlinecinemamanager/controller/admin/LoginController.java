package com.liuyetech.onlinecinemamanager.controller.admin;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.liuyetech.onlinecinemamanager.domain.User;
import com.liuyetech.onlinecinemamanager.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

@Slf4j
@Controller
@RequestMapping("admin")
public class LoginController {

    @Autowired
    private UserService userService;

    @GetMapping("login")
    public String login() {
        return "index";
    }

    @GetMapping("home")
    public String home() {
        return "home";
    }

    @PostMapping("login")
    public String userLogin(@RequestParam String username, @RequestParam String password, HttpSession session, ModelMap modelMap) {
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        userQueryWrapper.eq("user_name", username);
        userQueryWrapper.eq("user_password", password);
        User user = userService.getOne(userQueryWrapper);
        if (user != null) {
            session.setAttribute("admin", user);
            return "redirect:/admin/home";
        } else {
            modelMap.addAttribute("msg", "用户名或密码错误！");
            return "index";
        }
    }
}
