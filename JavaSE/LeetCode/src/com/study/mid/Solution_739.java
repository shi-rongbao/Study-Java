package com.study.mid;

public class Solution_739 {
    public static void main(String[] args) {
        /*
            给定一个整数数组temperatures,表示每天的温度,返回一个数组answer,其中answer[i]是指对于第i天,下一个更高温度出现在几天后.
            如果气温在这之后都不会升高,请在该位置用0来代替.

            实例:
            输入temperatures = [73,74,75,71,69,72,76,73];
            输出[1,1,4,2,1,1,0,0];

            输入temperatures = [30,40,50,60];
            输出[1,1,1,0];
         */
        int[] temperatures = {73,74,75,71,69,72,76,73};
        int[] arr = dailyTemperatures(temperatures);

        //遍历数组
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
    }

    public static int[] dailyTemperatures(int[] temperatures) {
        int length = temperatures.length;//获取数组长度
        int[] answer = new int[length];//创建新数组,与老数组长度一致
        for (int i = 0; i < temperatures.length; i++) {
            int count = 0;//定义计数器变量
            for (int j = i + 1; j < temperatures.length; j++) {
                if (temperatures[j] > temperatures[i]){//判断,只要有温度大于第i天的则进入if语句
                    count++;//计数器自增
                    answer[i] = count;//记录数据
                    break;//结束循环
                }
                count++;//如果不大于,计数器也自增
            }
            //如果循环结束还没有找到更高温度的天,则不将计数器记录到answer数组中,answer[i]还继续记录初始数据 0 ;
        }
        return answer;
    }
}
