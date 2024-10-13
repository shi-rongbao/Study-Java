package com.shirongbao.service;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class TimerInvocationHandler implements InvocationHandler {
    private Object target;

    public TimerInvocationHandler (Object target){
        this.target = target;
    }

    /*
    invoke方法的三个参数:
        Object proxy, 代理对象的引用   这个用的较少
        Method method,  目标对象上的目标方法
        Object[] args   目标方法要传入的参数
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Long begin = System.currentTimeMillis();
        Object retValue = method.invoke(target);
        Long end = System.currentTimeMillis();
        System.out.println("总共耗时" + (end - begin) + "毫秒");
        return retValue;
    }
}
