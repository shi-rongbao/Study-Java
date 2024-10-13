package com.shirongbao.webserver.autoconfig;

import com.shirongbao.webserver.JettyWebServer;
import com.shirongbao.webserver.TomcatWebServer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;

/**
 * @author ShiRongbao
 * @create 2024/10/6
 * @description:
 */
@Configuration
public class WebServerAutoConfig {

    @Bean
    @Conditional(JettyCondition.class)
    public JettyWebServer jettyWebServer() {
        return new JettyWebServer();
    }

    @Bean
    @Conditional(TomcatCondition.class)
    public TomcatWebServer tomcatWebServer() {
        return new TomcatWebServer();
    }

}

