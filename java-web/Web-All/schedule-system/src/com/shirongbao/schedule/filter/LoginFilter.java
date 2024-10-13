package com.shirongbao.schedule.filter;

import com.shirongbao.schedule.pojo.SysUser;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

// @WebFilter(urlPatterns = {"/showSchedule.html", "/schedule/*"})
public class LoginFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        // 做登录校验
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        // 首先获得session对象
        HttpSession session = request.getSession();
        // 然后从session域中获得登录的对象
        SysUser sysUser = (SysUser) session.getAttribute("sysUser");

        // 判断对象是否为空,如果为空,那么返回登录页. 如果登录了,那么直接放行
        if (null != sysUser) {
            filterChain.doFilter(request, response);
        } else {
            response.sendRedirect("/login.html");
        }
    }
}
