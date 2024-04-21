package com.shirongbao.client;

import com.shirongbao.service.OrderService;
import com.shirongbao.service.OrderServiceImpl;
import com.shirongbao.util.ProxyUtil;

public class Client {
    public static void main(String[] args) {
        // 创建目标对象
        OrderService target = new OrderServiceImpl();
        // 创建代理对象
        /**
         * newProxyInstance 翻译为 新建代理对象
         * 也就是说,通过这个方法可以创建代理对象.
         * 本质上,这个Proxy.newProxyInstance()方法的执行,做了两件事情:
         *      第一件事:在内存中动态的生成了一个代理类的字节码文件class.
         *      第二件事:new对象了.通过内存中生成的代理类这个代码,实例化了代理对象.
         * 关于newProxyInstance()方法的三个重要的参数,每一个什么含义,有什么用?
         *      第一个参数:ClassLoader loader
         *          类加载器. 在内存中生成的字节码class文件,要执行也得先加载到JVM当中,加载类就需要类加载器
         *          并且JDK要求,目标类的类加载器必须和代理类的类加载器使用同一个.
         *      第二个参数:Class<?>[] interfaces
         *          代理类和目标类要实现同一个接口或者一些接口
         *          在内存中生成代理类的时候,这个代理类需要你告诉他实现哪些接口.
         *      第三个参数:InvocationHandler h
         *
         */

        OrderService proxy = (OrderService) ProxyUtil.newProxyInstance(target);
        // 调用代理对象的代理方法
        proxy.detail();
        proxy.generate();
        proxy.modify();
        System.out.println(proxy.getName());
    }
}
