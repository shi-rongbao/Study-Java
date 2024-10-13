package com.shirongbao.servlet;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet("/sa")
public class ServletA extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 请求域
        // 单次请求有效
        req.setAttribute("request", "requestMessage");
        // 会话域
        // 单个客户端在session存活时间内有效
        HttpSession session = req.getSession();
        session.setAttribute("session", "sessionMessage");
        // 应用域
        // 服务内有效
        ServletContext application = getServletContext();
        application.setAttribute("application", "applicationMessage");

        // 获取请求域
        String request = (String) req.getAttribute("request");
        System.out.println("sa request" + request);

        // 做请求转发
        req.getRequestDispatcher("sb").forward(req, resp);
    }
}
