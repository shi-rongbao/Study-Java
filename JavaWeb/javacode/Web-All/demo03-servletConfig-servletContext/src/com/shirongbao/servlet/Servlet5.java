package com.shirongbao.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.ServletInputStream;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.Map;
import java.util.Set;

@WebServlet("/s5")
public class Servlet5 extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 获取键值对形式的参数
        // 根据参数名获取单个参数值
        String username = req.getParameter("username");
        String userPwd = req.getParameter("userPwd");
        System.out.println(username);
        System.out.println(userPwd);
        // 根据参数名获得多个参数值
        String[] hobbies = req.getParameterValues("hobby");
        for (String hobby : hobbies) {
            System.out.println(hobby);
        }

        System.out.println("----------------------------------");

        // 获取参数名
        Enumeration<String> parameterNames = req.getParameterNames();
        while (parameterNames.hasMoreElements()) {
            String name = parameterNames.nextElement();  // 因为不确定这个name是单值还是多值,因此直接调用处理多值的方法
            String[] values = req.getParameterValues(name);
            if (values.length > 1) {
                System.out.println(Arrays.toString(values));
            } else {
                System.out.println(values[0]);
            }
        }

        System.out.println("----------------------------------");

        // 返回所有参数的map集合, key = 参数名,  value = 参数值
        Map<String, String[]> map = req.getParameterMap();
        Set<Map.Entry<String, String[]>> entries = map.entrySet();
        for (Map.Entry<String, String[]> entry : entries) {
            if (entry.getValue().length > 1) {
                System.out.println(entry.getKey() + ":" + Arrays.toString(entry.getValue()));
            } else {
                System.out.println(entry.getKey() + ":" + entry.getValue()[0]);
            }
        }

        // 以上的API,只要是key:value形式的,那么都可以获取到,不管是get还是post
        // 注意: get方式也是有请求体的,并不是没有

        // 获得请求体中非键值对的数据    例如 JSON串, 文件
        // 获取一个从请求体中读取字符的字符输入流
//        BufferedReader reader = req.getReader();  // JSON串

        // 获得一个从请求中读取二进制数据字节的输入流
//        ServletInputStream inputStream = req.getInputStream();  // 文件

        System.out.println(req.getServletPath());
    }
}
