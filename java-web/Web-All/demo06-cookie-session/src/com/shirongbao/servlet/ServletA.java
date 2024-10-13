package com.shirongbao.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/sa")
public class ServletA extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Cookie cookie1 = new Cookie("keya", "valuea");
//        cookie1.setMaxAge(100);  // 设置cookie的存在时间,参数单位为妙
        cookie1.setPath("/demo06/sb");  // 设置cookie的提交路径
        Cookie cookie2 = new Cookie("keyb", "valueb");
        resp.addCookie(cookie1);
        resp.addCookie(cookie2);
    }
}

