package com.hmdp.utils.login;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

/**
 * @author ShiRongbao
 * @create 2024/8/4 15:52
 * @description:
 */
/*@Aspect
@Component*/
public class LoginAspect {

    @Around("@annotation(com.hmdp.utils.login.DianPingLogin)")
    public Object process(ProceedingJoinPoint joinPoint) throws Throwable {

        return joinPoint.proceed();
    }
}
