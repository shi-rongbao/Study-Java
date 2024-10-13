package com.atguigu.cache;

/**
 * @author ShiRongbao
 * @create 2024/6/11 18:02
 * @description:
 */
public class Test01_Singleton {
    private Test01_Singleton() {
    }

    private volatile static Test01_Singleton instance;


    public static Test01_Singleton getInstance() {
        if (instance == null) {
            synchronized (Test01_Singleton.class) {
                if (instance == null) {
                    instance = new Test01_Singleton();
                }
            }
        }
        return instance;
    }
}
