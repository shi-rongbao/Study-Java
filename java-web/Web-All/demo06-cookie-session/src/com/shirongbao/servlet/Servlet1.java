package com.shirongbao.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet("/s1")
public class Servlet1 extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        HttpSession session = req.getSession();
        session.setMaxInactiveInterval(300);  // 通过API动态设置session的最大存活时间   单位秒
        // 判断当前是否有 jsessionid cookie存在,如果没有创建新的HttpSession对象,并将jsessionid放入response中
        // 如果有jsessionid cookie 存在,那么去找HttpSession对象
        //      如果找到了,那么就返回这个session对象,如果没找到,那就创建一个新的HttpSession对象
        System.out.println(session.getId());
        System.out.println(session.isNew());  // 判断是否是新的session

        session.setAttribute("username", username);

        resp.setContentType("text/html;charset=UTF-8");
        resp.getWriter().write("牛逼");
    }
}
