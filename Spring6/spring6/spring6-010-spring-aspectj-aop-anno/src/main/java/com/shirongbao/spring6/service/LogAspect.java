package com.shirongbao.spring6.service;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component("logAspect")
@Aspect  // 切面类是需要使用 @Aspect注解进行标注的
@Order(2)
public class LogAspect {

    // 写一个切面
    // 切面 = 通知 + 切点
    // 通知就是具体的代码,以方法的形式出现,通知也叫增强

    // @Before注解标注的方法就是一个前置通知,里面写切点表达式
//    @AfterReturning("execution(* com.shirongbao.spring6.service..*(..))")
//    public void 增强 (){
//        System.out.println("我是一个通知,我是一段增强代码");
//    }

    // 定义切点表达式
    @Pointcut("execution(* com.shirongbao.spring6.service..*(..))")
    public void pointCutException(){}

    // 前置通知
    @Before("pointCutException()")
    public void beforeAdvice(JoinPoint joinPoint){
        System.out.println("前置通知");
        System.out.println(joinPoint.getSignature().getModifiers());
        System.out.println(joinPoint.getSignature().getName());
    }

    // 后置通知
    @AfterReturning("pointCutException()")
    public void afterReturningAdvice(){
        System.out.println("后置通知");
    }

    // 环绕通知
    @Around("pointCutException()")
    public void aroundAdvice(ProceedingJoinPoint joinPoint) throws Throwable {
        System.out.println("前环绕");
        joinPoint.proceed();  // 执行业务代码
        System.out.println("后环绕");
    }

}
