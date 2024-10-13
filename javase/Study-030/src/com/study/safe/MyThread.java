package com.study.safe;

public class MyThread extends Thread {
    //将变量用static修饰,表示被所有对象所共享
    static int ticket = 0;
    @Override
    public void run() {
        while (true) {
            //小括号内传递锁对象,对象是任意的,但要确保是唯一的,因此用static关键字修饰,确保不管创建多少个类的对象,锁对象都是唯一的

            //这种方法叫同步代码块,将操作共享的代码锁起来,程序的安全性得到了提升
            //为了确保锁的唯一性,一般将锁定义为当前类的字节码文件,这个字节码文件是唯一的 MyThread.class
            synchronized (MyThread.class){
                if (ticket < 100) {
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    ticket++;
                    System.out.println(getName() + "正在卖第" + ticket + "张票");
                } else {
                    System.out.println("票卖完了");
                    break;
                }
            }
        }
    }
}
