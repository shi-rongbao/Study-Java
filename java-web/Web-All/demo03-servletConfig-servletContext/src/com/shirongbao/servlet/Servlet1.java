package com.shirongbao.servlet;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebInitParam;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Enumeration;

@WebServlet(
        urlPatterns = "/s1",
        initParams = {@WebInitParam(name = "keyA", value = "valueA"), @WebInitParam(name = "keyB", value = "valueB")}
)
public class Servlet1 extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ServletConfig servletConfig = getServletConfig();
        System.out.println("-------------------servletConfig---------------------------");
        String value = getInitParameter("keyA");
        System.out.println("keyA:" + value);

        Enumeration<String> initParameterNames = getInitParameterNames();
        while (initParameterNames.hasMoreElements()) {
            String keyName = initParameterNames.nextElement();
            System.out.println(keyName + ":" + getInitParameter(keyName));
        }
        System.out.println("-------------------servletContext---------------------------");
        ServletContext servletContext1 = servletConfig.getServletContext();
        ServletContext servletContext2 = req.getServletContext();
        ServletContext servletContext3 = getServletContext();
        System.out.println(servletContext1 == servletContext2);
        System.out.println(servletContext2 == servletContext3);

        String valueC = servletContext1.getInitParameter("encoding");
        System.out.println("encoding:" + valueC);

        Enumeration<String> initParameterNames1 = servletContext1.getInitParameterNames();
        while (initParameterNames1.hasMoreElements()) {
            String k = initParameterNames1.nextElement();
            System.out.println(k + ":" + servletContext1.getInitParameter(k));
        }

        // 向域中存储/修改数据
        // 键的类型为String,value的类型为Object
        servletContext1.setAttribute("ka", "va");
        servletContext1.setAttribute("ka", "vaa");
//
//        // 从域中获取数据
//        // 默认返回值为Object
//        String val = (String) servletContext1.getAttribute("ka");
//        System.out.println(val);
//
//        // 从域中移除数据
        servletContext1.removeAttribute("ka");
    }
}
