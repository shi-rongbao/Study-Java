package com.study.test;

public class Test3 {
    public static void main(String[] args) {
        /*
            需求:
                将一个十进制的整数转成字符串表示的二进制

                方法:
                    不断地除以基数(几进制,基数就是几) 得到余数
                    直到商为0,再将余数倒着拼起来即可
                    eg:
                        6 / 2 = 3 ··· 0
                        3 / 2 = 1 ··· 1
                        1 / 2 = 0 ··· 1
                        6的二进制为110
         */

        System.out.println(toBinaryString(32));
    }

    public static String toBinaryString(int number) {
        StringBuilder sb = new StringBuilder();
        while (true) {
            if (number == 0) {
                break;
            }
            int i = number % 2;
            number = number / 2;
            sb.insert(0, i);
        }

        return sb.toString();
    }
}
