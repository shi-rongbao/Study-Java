package com.shirongbao.boot3.core.listener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ConfigurableBootstrapContext;
import org.springframework.boot.SpringApplicationRunListener;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;

import java.time.Duration;

/**
 * @author ShiRongbao
 * @create 2024/2/23 16:42
 * @description:
 */
@Slf4j
public class MyAppListener implements SpringApplicationRunListener {
    @Override
    public void starting(ConfigurableBootstrapContext bootstrapContext) {
        System.out.println("=======starting=======");
        log.info("=======starting=======");
    }

    @Override
    public void environmentPrepared(ConfigurableBootstrapContext bootstrapContext, ConfigurableEnvironment environment) {
        log.info("=======environmentPrepared=======");
    }

    @Override
    public void contextPrepared(ConfigurableApplicationContext context) {
        log.info("=======contextPrepared=======");
    }

    @Override
    public void contextLoaded(ConfigurableApplicationContext context) {
        log.info("=======contextLoaded=======");
    }

    @Override
    public void started(ConfigurableApplicationContext context, Duration timeTaken) {
        log.info("=======started=======");
    }

    @Override
    public void ready(ConfigurableApplicationContext context, Duration timeTaken) {
        log.info("=======ready=======");
    }

    @Override
    public void failed(ConfigurableApplicationContext context, Throwable exception) {
        log.info("=======failed=======");
    }
}
