package com.hmdp.utils;

import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author ShiRongbao
 * @create 2024/4/19 16:04
 * @description:
 */
public class LoginInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 1. 判断是否需要拦截(ThreadLocal中是否有用户)
        if (UserHolder.getUser() == null) {
            // 没有，需要拦截，设置转台吗
            response.setStatus(401);
            return false;
        }
        // 有用户，放行
        return true;
    }

}
