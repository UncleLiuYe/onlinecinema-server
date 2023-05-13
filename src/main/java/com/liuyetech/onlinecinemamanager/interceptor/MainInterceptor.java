package com.liuyetech.onlinecinemamanager.interceptor;

import cn.dev33.satoken.stp.StpUtil;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
public class MainInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, @NotNull HttpServletResponse response, @NotNull Object handler) throws Exception {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        //管理员登录判断逻辑
        if (request.getRequestURI().contains("/admin")) {
            if (request.getSession().getAttribute("admin") != null) {
                return true;
            } else {
                request.setAttribute("msg", "需要登录！");
                request.getRequestDispatcher(request.getContextPath() + "/admin/login").forward(request, response);
                return false;
            }
        }

        //客户端用户登录判断逻辑
        if (request.getHeader("onlinecinema") != null) {
            long timeout = StpUtil.getTokenTimeout();
            if (timeout <= 0) {
                response.setStatus(401);
                return false;
            } else if (timeout < 5) {
                StpUtil.renewTimeout(10);
            }
        } else {
            response.setStatus(401);
            return false;
        }
        return true;
    }

    @Override
    public void postHandle(@NotNull HttpServletRequest request, @NotNull HttpServletResponse response, @NotNull Object handler, ModelAndView modelAndView) throws Exception {
    }

    @Override
    public void afterCompletion(@NotNull HttpServletRequest request, @NotNull HttpServletResponse response, @NotNull Object handler, Exception ex) throws Exception {
    }
}
