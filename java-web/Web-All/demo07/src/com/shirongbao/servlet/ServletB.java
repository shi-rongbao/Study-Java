package com.shirongbao.servlet;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet("/sb")
public class ServletB extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String request = (String) req.getAttribute("request");
        System.out.println("请求域:" + request);

        HttpSession session = req.getSession();
        System.out.println("会话域:" + (String) session.getAttribute("session"));

        ServletContext application = getServletContext();
        System.out.println("应用域:" + (String) application.getAttribute("application"));
    }
}
