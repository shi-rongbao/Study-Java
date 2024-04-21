package com.shirongbao.schedule.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.lang.reflect.Method;

public class BaseController extends HttpServlet {


    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String requestURI = req.getRequestURI();    // 获取到URI
        String[] split = requestURI.split("/");  // 根据 / 进行切割
        String methodName = split[split.length - 1];    // 拿到后面的标志
        Class aClass = this.getClass();
        try {
            Method declaredMethod = aClass.getDeclaredMethod(methodName, HttpServletRequest.class, HttpServletResponse.class);
            // 暴力破解方法的访问修饰权限
            declaredMethod.setAccessible(true);
            // 执行方法
            declaredMethod.invoke(this, req, resp);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
