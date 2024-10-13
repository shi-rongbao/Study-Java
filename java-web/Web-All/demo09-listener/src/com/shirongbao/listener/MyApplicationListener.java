package com.shirongbao.listener;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebListener;

@WebListener
public class MyApplicationListener implements ServletContextListener, ServletContextAttributeListener {
    @Override
    public void attributeAdded(ServletContextAttributeEvent scae) {
        ServletContext application = scae.getServletContext();
        String name = scae.getName();
        String value = (String) scae.getValue();
        System.out.println(application.hashCode() + "应用域添加数据:" + name + " = " + value);
    }

    @Override
    public void attributeRemoved(ServletContextAttributeEvent scae) {
        ServletContext application = scae.getServletContext();
        String name = scae.getName();
        String value = (String) scae.getValue();
        System.out.println(application.hashCode() + "移除的数据是:" + name + " = " + value);
    }

    @Override
    public void attributeReplaced(ServletContextAttributeEvent scae) {
        ServletContext application = scae.getServletContext();
        String name = scae.getName();
        String value = (String) scae.getValue();
        Object newValue = application.getAttribute(name);
        System.out.println(application.hashCode() + "应用域修改数据了,原数据是:" + name + " = " + value + "修改后的数据是:" + name + " = " + newValue);
    }

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ServletContext application = sce.getServletContext();
        System.out.println(application.hashCode() + "应用域初始化了");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        ServletContext application = sce.getServletContext();
        System.out.println(application.hashCode() + "应用域销毁了");
    }
}
