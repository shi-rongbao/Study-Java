package com.study.test;

public class TestMonkey {
    public static void main(String[] args) {
        /*
            有一堆桃子,猴子第一天吃了其中的一半,并多吃了一个!以后每天猴子都吃当前剩下来的一半,然后在多吃一个
            第十天的时候(还没吃),发现只剩下一个桃子了,请问,最初总共多少桃子?

            第一天 1534 / 2 = 767 - 1 = 766
            第二天 766 / 2 = 383 - 1 = 382
            第三天 382 / 2 = 191 - 1 = 190
            第四天 190 / 2 = 95 - 1 = 94
            第五天 94 / 2 = 47 - 1 = 46
            第六天 46 / 2 = 23 - 1 = 22
            第七天 22 / 2 = 11 - 1 = 10
            第八天 10 / 2 = 5 -1 = 4
            第九天 4 / 2 = 2 - 1 = 1
         */
        int number = getNumber(1);
        System.out.println(number);
    }

    public static int getNumber(int day) {
        if (day == 10) {
            return 1;
        }
        return (getNumber(day + 1) + 1) * 2;
    }
}
