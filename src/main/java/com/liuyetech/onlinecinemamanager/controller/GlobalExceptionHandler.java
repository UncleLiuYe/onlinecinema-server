package com.liuyetech.onlinecinemamanager.controller;

import cn.dev33.satoken.exception.NotLoginException;
import com.liuyetech.onlinecinemamanager.entity.R;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public R<String> handlerMethodArgumentNotValidException(MethodArgumentNotValidException methodArgumentNotValidException) {
        return R.fail(methodArgumentNotValidException.getBindingResult().getFieldError().getDefaultMessage());
    }

    @ExceptionHandler(NotLoginException.class)
    @ResponseStatus(code = HttpStatus.UNAUTHORIZED)
    public R<String> handlerNotLoginException(NotLoginException notLoginException) {
        return R.fail(notLoginException.getMessage());
    }
}
