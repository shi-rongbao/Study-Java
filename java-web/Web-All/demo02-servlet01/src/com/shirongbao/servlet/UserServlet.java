package com.shirongbao.servlet;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;


public class UserServlet extends HttpServlet{
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 根据参数名获取参数值,无论是get还是post,在url上异或是在请求体中,都可以获取到
        String username = request.getParameter("username");
        String info = "<h1>YES</h1>";

        if ("atguigu".equalsIgnoreCase(username)) {
            info = "<h1>NO</h1>";
        }

        // 在将输入放入response之前还应该要设置Content-Type的值,让浏览器正确的解析响应报文响应行,响应头,响应体)
        // response.setHeader("Content-Type","text/html");
        // 也有专门的api用于设置Content-Type
        response.setContentType("text/html");

        // 该方法返回的是一个向响应体中打印字符串的 打印流
        PrintWriter writer = response.getWriter();
        writer.write(info);
    }
}
