package com.liuyetech.onlinecinemamanager.controller;

import cn.dev33.satoken.stp.StpUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.liuyetech.onlinecinemamanager.domain.User;
import com.liuyetech.onlinecinemamanager.entity.R;
import com.liuyetech.onlinecinemamanager.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("api/v1/user")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("login")
    public R<?> userLogin(@RequestParam String username, @RequestParam String password) {
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        userQueryWrapper.eq("user_name", username);
        userQueryWrapper.eq("user_password", password);
        User user = userService.getOne(userQueryWrapper);
        if (user != null) {
            StpUtil.login(user.getUserId());
            return R.success(StpUtil.getTokenValue(), user);
        } else {
            return R.fail("用户名或密码错误");
        }
    }

    @GetMapping("info")
    public R<User> getUserInfo(@RequestParam Integer userId) {
        return R.success(userService.getById(userId));
    }

    @PostMapping("checkToken")
    public R<String> checkToken(@RequestHeader("onlinecinema") String token) {
        log.info(token);
        log.info(StpUtil.getTokenTimeout() + "");
        return StpUtil.getTokenTimeout() > 0 ? R.success("ok") : R.fail("fail");
    }

}
