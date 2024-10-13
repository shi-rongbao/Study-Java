package com.shirongbao.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/sc")
public class ServletC extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 这种方式不推荐
        // resp.setCharacterEncoding("GBK");
        // 设置响应头的ContentType
        // 但是我们不确定tomcat的版本是否是用utf8来进行编码的,因此我们会先设置响应体的编码方式为utf8
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html;charset=UTF-8");  //推荐
        resp.getWriter().write("草拟吗");
    }
}
