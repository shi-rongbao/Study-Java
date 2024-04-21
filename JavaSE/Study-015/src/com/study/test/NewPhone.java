package com.study.test;

/**
 * @author ShiRongbao
 * @create 2024/3/18 22:03
 * @description:
 */
public class NewPhone extends Phone implements IPlay{

    // 这是重写玩游戏的方法
    @Override
    public void IPlay() {
        System.out.println("新视觉玩游戏");
    }

    // 下面是继承父类Phone的行为
    @Override
    public void call() {
        super.call();
    }

    @Override
    public void sentMessage() {
        super.sentMessage();
    }

}
