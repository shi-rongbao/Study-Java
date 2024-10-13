package com.shirongbao.webserver;

/**
 * @author ShiRongbao
 * @create 2024/10/6
 * @description:
 */
public class JettyWebServer implements WebServer{
    @Override
    public void onStart() {
        System.out.println("Jetty启动了");
    }
}
