package com.shirongbao.proxy.service;

import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

public class TimerMethodInterceptor implements MethodInterceptor {

    @Override
    public Object intercept(Object target, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        Long begin = System.currentTimeMillis();
        Object retValue = methodProxy.invokeSuper(target, objects);
        Long end = System.currentTimeMillis();
        System.out.println("总共耗时" + (end - begin) + "毫秒");
        return retValue;
    }
}
