package com.liuyetech.onlinecinemamanager.controller;

import cn.dev33.satoken.stp.StpUtil;
import com.aliyun.oss.OSSClient;
import com.aliyun.oss.model.PutObjectRequest;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.google.code.kaptcha.Producer;
import com.liuyetech.onlinecinemamanager.domain.User;
import com.liuyetech.onlinecinemamanager.entity.LoginVo;
import com.liuyetech.onlinecinemamanager.entity.R;
import com.liuyetech.onlinecinemamanager.entity.UserRegisterVo;
import com.liuyetech.onlinecinemamanager.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.time.Instant;
import java.util.UUID;

@Slf4j
@RestController
@RequestMapping("api/v1/user")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private Producer kaptchaProducer;
    @Autowired
    private OSSClient ossClient;

    @PostMapping("login")
    public R<String> userLogin(@RequestBody @Validated LoginVo loginVo, HttpServletRequest request) {
        log.info(request.getSession().getId());
        Object code = request.getSession().getAttribute("code");
        if (code == null || !loginVo.getCaptchaCode().equalsIgnoreCase(code.toString())) {
            return R.fail("验证码错误");
        }
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        userQueryWrapper.eq("user_name", loginVo.getUsername());
        userQueryWrapper.eq("user_password", loginVo.getPassword());
        User user = userService.getOne(userQueryWrapper);
        if (user != null) {
            StpUtil.login(user.getUserId());
            request.setAttribute("code", "");
            return R.success("登陆成功", StpUtil.getTokenValue());
        } else {
            return R.fail("用户名或密码错误");
        }
    }

    @PostMapping("register")
    public R<?> userRegister(@RequestBody UserRegisterVo userRegisterVo, HttpServletRequest request) {
        log.info(userRegisterVo.toString());
        Object code = request.getSession().getAttribute("code");
        if (code == null || !userRegisterVo.getCaptchaCode().equalsIgnoreCase(code.toString())) {
            return R.fail("验证码错误");
        }

        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        userQueryWrapper.eq("user_name", userRegisterVo.getUsername());
        if (userService.list(userQueryWrapper).size() > 0) {
            return R.fail("用户名已存在");
        }

        User user = new User();
        user.setUserName(userRegisterVo.getUsername());
        user.setUserPassword(userRegisterVo.getPassword());
        user.setUserNickname("user" + Instant.now().toEpochMilli());
        user.setUserSex(1);
        user.setUserAvator("/" + userRegisterVo.getAvatarImgPath());
        if (userService.save(user)) {
            return R.success("注册成功");
        } else {
            return R.fail("注册失败");
        }
    }

    @PostMapping("avatarUpload")
    public R<String> userAvatarUpload(MultipartFile file) throws IOException {
        if (file.isEmpty()) {
            return R.fail("文件不能为空");
        }
        String suffix = file.getOriginalFilename().split("\\.")[1];
        String newFileName = UUID.randomUUID() + "." + suffix;
        log.info(newFileName);
        try {
            boolean isExists = ossClient.doesObjectExist("onlinecinema", newFileName);
            if (!isExists) {
                PutObjectRequest putObjectRequest = new PutObjectRequest("onlinecinema", "image/" + newFileName, file.getInputStream());
                putObjectRequest.setProcess("true");
                ossClient.putObject(putObjectRequest);
            }
            return R.success("上传成功", newFileName);
        } catch (Exception e) {
            e.printStackTrace();
            return R.fail("文件上传失败");
        }
    }

    @GetMapping("captcha")
    public void captcha(HttpServletRequest request, HttpServletResponse response) throws IOException {
        log.info("验证码SessionId：" + request.getSession().getId());
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
        request.getSession().setAttribute("code", text);
        ImageIO.write(image, "jpg", response.getOutputStream());
    }

    @GetMapping("info")
    public R<User> getUserInfo() {
        Integer uid = Integer.valueOf(StpUtil.getLoginId().toString());
        return R.success(userService.getById(uid));
    }

    @PostMapping("checkToken")
    public R<String> checkToken(@RequestHeader("onlinecinema") String token) {
        return StpUtil.getTokenTimeout() > 0 ? R.success("ok") : R.fail("fail");
    }
}
