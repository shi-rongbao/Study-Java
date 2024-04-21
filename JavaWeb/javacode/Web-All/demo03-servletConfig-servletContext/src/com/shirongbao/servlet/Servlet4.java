package com.shirongbao.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Enumeration;

@WebServlet("/s4")
public class Servlet4 extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        System.out.println(req.getMethod());  // 获取请求方式 GET
        System.out.println(req.getScheme());  // 获取请求协议 http
        System.out.println(req.getProtocol());  // 获取请求协议及版本号   HTTP/1.1
        System.out.println(req.getRequestURI());  // 获取请求的uri 项目内的资源路径  /d03/s4
        System.out.println(req.getRequestURL());  // 获取请求的url 项目内资源的完整路径    http://localhost:8080/d03/s4

        /*
            URI     统一资源标识符 /demo01/a.html                  interface URI{}             资源定位的要求和规范      动物类
            URL     统一资源定位符 http://ip:port/demo01/a.html    class URL implements URI{}  一个具体的资源路径        哺乳动物类
         */

        // 端口号相关
        System.out.println(req.getLocalPort());  // 获取本应用容器的端口号
        System.out.println(req.getServerPort());  // 获取客户端发请求时的端口号
        System.out.println(req.getRemotePort());  // 获取客户端软件的端口号

        // 请求头相关
        String accept = req.getHeader("Accept");
        System.out.println(accept);

        // 如果不知道请求头中的键
        // 也可以直接获取本次请求中所有请求头的键
        Enumeration<String> names = req.getHeaderNames();
        while (names.hasMoreElements()) {
            String name = names.nextElement();
            System.out.println(name + ":" + req.getHeader(name));
        }
    }
}
