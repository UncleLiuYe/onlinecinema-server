package com.liuyetech.onlinecinemamanager.entity;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

@Data
public class UserRegisterVo {
    @NotBlank(message = "请填写用户名")
    @Length(min = 8, message = "用户名长度必须大于8位")
    private String username;
    @NotBlank(message = "请填写密码")
    @Length(min = 8, message = "密码长度必须大于8位")
    private String password;
    @NotBlank(message = "请上传头像")
    private String avatarImgPath;
    @NotBlank(message = "请填写验证码")
    private String captchaCode;
}
