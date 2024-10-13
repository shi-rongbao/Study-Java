package com.shirongbao;

import com.shirongbao.webserver.WebServer;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;

/**
 * @author ShiRongbao
 * @create 2024/10/4
 * @description:
 */
public class RongbaoSpringApplication {

    // 方法被main方法调用
    public static void run(Class<?> clazz) {
        AnnotationConfigWebApplicationContext webApplicationContext = new AnnotationConfigWebApplicationContext();
        webApplicationContext.register(clazz);
        // 启动容器
        webApplicationContext.refresh();
        // 创建web服务器
        createWebServer(webApplicationContext);
    }

    private static void createWebServer(WebApplicationContext webApplicationContext) {
        WebServer webServer = webApplicationContext.getBean(WebServer.class);

        webServer.onStart();
    }

}
