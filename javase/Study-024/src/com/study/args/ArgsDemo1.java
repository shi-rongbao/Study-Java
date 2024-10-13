package com.study.args;

public class ArgsDemo1 {
    public static void main(String[] args) {
        /*
            可变参数的练习:
                假如需要定义一个方法求和,该方法可以灵活 的完成如下需求:
                    计算2个数据的和
                    计算3个数据的和
                    计算4个数据的和
                    计算n个数据的和
         */

        //可变参数, 格式: 数据类型...参数名称 eg: int...args

        //注意事项:形参列表中可变参数只能有一个
        //        可变参数必须放在形参列表的最后面
        System.out.println(getSum(1, 2, 3, 4, 5, 6, 7, 8, 9, 10));
    }

    public static int getSum(int... args) {//形参为可变参数
        //可变参数底层为数组

        int sum = 0;//定义求和变量

        for (int number : args) {//遍历集合
            sum += number;//累加
        }

        return sum;//返回求得的和
    }
}
