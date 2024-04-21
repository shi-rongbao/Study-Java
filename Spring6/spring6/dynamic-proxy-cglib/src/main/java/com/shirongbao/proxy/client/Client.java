package com.shirongbao.proxy.client;

import com.shirongbao.proxy.service.TimerMethodInterceptor;
import com.shirongbao.proxy.service.UserService;
import net.sf.cglib.proxy.Enhancer;

public class Client {
    public static void main(String[] args) {
        // 创建字节码增强器对象
        Enhancer enhancer = new Enhancer();

        // 告诉CGLIB父类是谁.也就是目标类
        enhancer.setSuperclass(UserService.class);

        // 设置回调(等同于JDK动态代理当中的调用处理器.InvocationHandler)
        enhancer.setCallback(new TimerMethodInterceptor());

        // 创建代理对象
        // 这一步也是做两件事情
        // 第一件事,在内存中生成UserServices的子类,其实就是代理类的字节码.
        // 第二件事,创建代理对象.
        // 父类是UserService,子类这个代理类一定是UserService
        UserService proxy = (UserService) enhancer.create();
        boolean success = proxy.login("admin", "123");
        System.out.println(success ? "登录成功" : "登录失败");

        proxy.logout();
    }
}
