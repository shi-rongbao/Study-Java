package com.shirongbao.servlet;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/sa")
public class ServletA extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("SA start");

        // 请求转发给SB
        // 获得请求转发器
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("http://www.atguigu.com");  // 参数传递目标路径

        // 让请求转发器做出转发动作
        requestDispatcher.forward(req, resp);
    }
}
