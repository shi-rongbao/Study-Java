package com.shirongbao.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/s1")
public class Servlet1 extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("s1 start");

        // 然后响应重定向, 第一步设置状态码302, 第二步返回目标地址
        // resp.setStatus(302);
        // resp.setHeader("location", "s2");

        resp.sendRedirect("s2");  // 该方法完成了上述两步操作
    }
}
