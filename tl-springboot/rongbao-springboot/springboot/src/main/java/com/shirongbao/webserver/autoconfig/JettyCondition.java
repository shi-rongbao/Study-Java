package com.shirongbao.webserver.autoconfig;

import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;

/**
 * @author ShiRongbao
 * @create 2024/10/6
 * @description:
 */
public class JettyCondition implements Condition {
    @Override
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
        ClassLoader classLoader = context.getClassLoader();
        try {
            Class<?> aClass = classLoader.loadClass("org.eclipse.jetty.util.Jetty");
            return true;
        } catch (ClassNotFoundException e) {
            return false;
        }
    }
}
