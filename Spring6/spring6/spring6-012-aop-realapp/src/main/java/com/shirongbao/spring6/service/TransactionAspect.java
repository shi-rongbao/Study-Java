package com.shirongbao.spring6.service;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class TransactionAspect {
    @Pointcut("execution(* com.shirongbao.spring6.service..*(..))")
    public void executionPath() {
    }

    @Around("executionPath()")
    public void aroundAdvice(ProceedingJoinPoint joinPoint) {
        try {
            System.out.println("开启事务");
            joinPoint.proceed();
            System.out.println("事务提交");
        } catch (Throwable e) {
            System.out.println("回滚事物");
        }
    }
}
