package com.hmdp.utils.login;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author ShiRongbao
 * @create 2024/8/4 15:51
 * @description:
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface DianPingLogin {
    boolean required() default true;
}
