package com.liuyetech.onlinecinemamanager.controller;

import cn.dev33.satoken.stp.StpUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.google.code.kaptcha.Producer;
import com.liuyetech.onlinecinemamanager.domain.User;
import com.liuyetech.onlinecinemamanager.entity.R;
import com.liuyetech.onlinecinemamanager.entity.UserRegisterVo;
import com.liuyetech.onlinecinemamanager.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;

@Slf4j
@RestController
@RequestMapping("api/v1/user")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private Producer kaptchaProducer;

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

    @PostMapping("register")
    public R<?> userRegister(@RequestBody UserRegisterVo userRegisterVo) {
        User user = new User();
        user.setUserName(userRegisterVo.getUserName());
        user.setUserPassword(userRegisterVo.getPassWord());
        user.setUserNickname(userRegisterVo.getNickName());
        user.setUserSex(userRegisterVo.getSex());
        user.setUserAvator("100.jpg");
        if (userService.save(user)) {
            return R.success("注册成功");
        } else {
            return R.fail("注册失败");
        }
    }

    @GetMapping("captcha")
    public void captcha(HttpServletResponse response) throws IOException {
        // Set to expire far in the past.
        response.setDateHeader("Expires", 0);
        // Set standard HTTP/1.1 no-cache headers.
        response.setHeader("Cache-Control", "no-store, no-cache, must-revalidate");
        // Set IE extended HTTP/1.1 no-cache headers (use addHeader).
        response.addHeader("Cache-Control", "post-check=0, pre-check=0");
        // Set standard HTTP/1.0 no-cache header.
        response.setHeader("Pragma", "no-cache");
        // return a jpeg
        response.setContentType("image/jpeg");

        String text = kaptchaProducer.createText();
        BufferedImage image = kaptchaProducer.createImage(text);
        getRequest().setAttribute("code", text);
        ImageIO.write(image, "jpg", response.getOutputStream());
    }

    private static HttpServletRequest getRequest() {
        return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
    }

    @GetMapping("info")
    public R<User> getUserInfo(@RequestParam Integer userId) {
        return R.success(userService.getById(userId));
    }

    @PostMapping("checkToken")
    public R<String> checkToken(@RequestHeader("onlinecinema") String token) {
        return StpUtil.getTokenTimeout() > 0 ? R.success("ok") : R.fail("fail");
    }

}
