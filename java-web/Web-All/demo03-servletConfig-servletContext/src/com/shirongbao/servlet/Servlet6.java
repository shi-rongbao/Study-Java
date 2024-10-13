package com.shirongbao.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/s6")
public class Servlet6 extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 设置响应行相关的API
        // 默认不设置为200正常访问
//        resp.setStatus(500);

        String info = "<h1>fuck</h1>";

        // 设置响应头相关的API
        resp.setHeader("Content-Type", "text/html");
        resp.setContentType("text/html");
        resp.setContentLength(info.getBytes().length);

        // 设置响应体内容
        // 获得一个向响应体中输入文本的字符输入流
        PrintWriter writer = resp.getWriter();
        writer.write(info);

        // 如果要响应文件
        // 那么获得一个向响应体中输入二进制信息的字节输出流
//        ServletOutputStream outputStream = resp.getOutputStream();

    }
}
