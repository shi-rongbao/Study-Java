package com.shirongbao.webserver;

import org.apache.catalina.*;
import org.apache.catalina.connector.Connector;
import org.apache.catalina.core.StandardContext;
import org.apache.catalina.core.StandardEngine;
import org.apache.catalina.core.StandardHost;
import org.apache.catalina.startup.Tomcat;
import org.springframework.web.servlet.DispatcherServlet;

/**
 * @author ShiRongbao
 * @create 2024/10/6
 * @description:
 */
public class TomcatWebServer implements WebServer{

    @Override
    public void onStart() {
        System.out.println("tomcat 启动了");
    }
}
