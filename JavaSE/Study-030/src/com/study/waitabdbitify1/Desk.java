package com.study.waitabdbitify1;

public class Desk {
    //创建一个变量,用来表示桌子上是否有面条
    static int foodFlag = 0;  //0 代表没有,1 代表有

    //创建一个变量,用来表示面条的总碗数,用于结束
    static int count = 10;

    //创建锁对象
    public static Object lock = new Object();
}
