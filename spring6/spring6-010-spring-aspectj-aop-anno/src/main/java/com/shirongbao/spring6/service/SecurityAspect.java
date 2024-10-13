package com.shirongbao.spring6.service;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Aspect
@Component("securityAspect")
@Order(1)
public class SecurityAspect {
    @Before("execution(* com.shirongbao.spring6.service..*(..))")
    public void beforeAdvice(){
        System.out.println("前置切面:..安全");
    }
}
