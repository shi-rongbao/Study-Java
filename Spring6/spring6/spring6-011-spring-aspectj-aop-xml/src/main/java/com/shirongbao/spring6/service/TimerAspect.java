package com.shirongbao.spring6.service;

import org.aspectj.lang.ProceedingJoinPoint;

public class TimerAspect {
    public void aroundAdvice(ProceedingJoinPoint joinPoint) throws Throwable {
        long begin = System.currentTimeMillis();
        joinPoint.proceed();
        long end = System.currentTimeMillis();
        System.out.println("耗时" + (end - begin) + "毫秒");
    }
}
