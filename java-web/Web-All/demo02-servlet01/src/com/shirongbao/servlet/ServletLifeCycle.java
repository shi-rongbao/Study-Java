package com.shirongbao.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

/*
    Servlet的声明周期

        1. 首先是实例化ServletLifeCycle(当前类)           构造器      第一次请求时执行
        2. 初始化                                       init()     构造器执行后执行
        3. 接收请求                                      service()  每一次请求都会执行
        4. 销毁                                         destroy()  关闭服务后执行一次
 */

/*
    Servlet在Tomcat中是单例的
    Servlet的成员变量在多个线程栈之中是共享的
    不建议在service方法中修改成员变量的值      因为在并发请求时,如果修改,会引发线程安全问题
    如果加锁,则会大大降低运行效率
 */
@WebServlet("/slc")
public class ServletLifeCycle extends HttpServlet {
    public ServletLifeCycle(){
        System.out.println("构造器");
    }

    @Override
    public void init() throws ServletException {
        System.out.println("初始化");
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("test");
    }

    @Override
    public void destroy() {
        System.out.println("destroy");
    }
}
