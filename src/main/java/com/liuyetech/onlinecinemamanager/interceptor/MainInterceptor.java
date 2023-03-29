package com.liuyetech.onlinecinemamanager.interceptor;

import cn.dev33.satoken.stp.StpUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
public class MainInterceptor implements HandlerInterceptor {
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public boolean preHandle(HttpServletRequest request, @NotNull HttpServletResponse response, @NotNull Object handler) throws Exception {
        request.setCharacterEncoding("utf-8");
        if (request.getHeader("onlinecinema") != null) {
            response.setContentType("application/json;charset=utf-8");
            long timeout = StpUtil.getTokenTimeout();
            if (timeout <= 0) {
                response.setStatus(401);
                response.getWriter().println(objectMapper.writeValueAsString("token无效"));
                return false;
            } else if (timeout < 5) {
                StpUtil.renewTimeout(10);
            }
            return true;
        }

        if (request.getSession().getAttribute("admin") != null) {
            return true;
        } else {
            response.setContentType("text/html;charset=utf-8");
            request.setAttribute("msg", "需要登录！");
            request.getRequestDispatcher(request.getContextPath() + "admin/login").forward(request, response);
            return false;
        }
    }

    @Override
    public void postHandle(@NotNull HttpServletRequest request, @NotNull HttpServletResponse response, @NotNull Object handler, ModelAndView modelAndView) throws Exception {
    }

    @Override
    public void afterCompletion(@NotNull HttpServletRequest request, @NotNull HttpServletResponse response, @NotNull Object handler, Exception ex) throws Exception {
    }
}
