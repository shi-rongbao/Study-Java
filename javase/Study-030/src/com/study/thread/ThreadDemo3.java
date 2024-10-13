package com.study.thread;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class ThreadDemo3 {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        /*
            多线程的第三种实现方式:
                特点:可以获取到多线程的运行结果
                1:创建一个类MyCallable实现Callable接口
                2:重写call(是有返回值的,表示多线程运行的结果)
                3:创建MyCallable的对象(表示多线程要执行的任务)
                4:创建FutureTask的对象,FutureTask是Future的实现类,Future是接口(作用管理多线程运行的结果)
                5:创建Thread类的对象,并启动(表示线程)
         */
        MyThread3 mt = new MyThread3();
        FutureTask<Integer> ft = new FutureTask<>(mt);
        Thread t1 = new Thread(ft);
        t1.start();

        Integer result = ft.get();
        System.out.println(result);

        /*
            多线程三种实现方式对比
                继承Thread类       优点:编程简单,可以直接使用Thread类中的方法        缺点:可以扩展性较差,不能再继续继承其他的类
                实现Runnable接口
                实现Callable接口    优点:扩展性强,实现该类接口的同时还可以继承其他的类      缺点:变成相对复杂,不能直接使用Thread类种的方法
         */
    }
}
