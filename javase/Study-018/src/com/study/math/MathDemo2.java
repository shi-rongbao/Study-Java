package com.study.math;

public class MathDemo2 {
    public static void main(String[] args) {
        System.out.println(isPrime(997));
        System.out.println(method(548834));
        System.out.println("==============");
        method1();
        System.out.println("==============");
        System.out.println(method2());
        System.out.println("==============");
        method3();
        System.out.println("==============");
        method4();
    }

    public static boolean isPrime(int number) {
        int i = 2;
        for (; i <= Math.sqrt(number); i++) {
            if (number % i == 0) {
                System.out.println(i);
                return false;
            }
        }
        System.out.println(i - 2);
        return true;
    }

    /*
        自幂数,一个n位自然数等于自身各个数位上数字的n次幂之和
        例如: 3位数: 1^3 + 5^3 + 3^ = 153
              4位数: 1^4 + 6^4 + 3^4 + 4^4 = 1634
     */

    public static boolean method(int number) {
        int num = 0;
        int temp = number;
        int result = number;

        while (number > 0) {
            number = number / 10;
            num++;
        }

        int sum = 0;
        for (int i = 0; i < num; i++) {
            sum += Math.pow(temp % 10, num);
            temp /= 10;
        }
        if (result != sum) {
            return false;
        }
        return true;
    }

    public static void method1() {
        int count = 0;
        for (int i = 100; i < 999; i++) {
            int ge = i % 10;
            int shi = i / 10 % 10;
            int bai = i / 10 / 10 % 10;
            int number = (int) (Math.pow(ge, 3) + Math.pow(shi, 3) + Math.pow(bai, 3));
            if (number == i) {
                System.out.println(i);
                count++;
            }
        }
        System.out.println(count);
    }

    public static boolean method2() {
        for (int i = 10; i < 99; i++) {
            int ge = i & 10;
            int shi = i / 10 % 10;
            int number = (int) (Math.pow(ge, 2) + Math.pow(shi, 2));
            if (number == i) {
                return true;
            }
        }
        return false;
    }

    public static void method3() {
        int count = 0;
        for (int i = 1000; i < 9999; i++) {
            int ge = i % 10;
            int shi = i / 10 % 10;
            int bai = i / 10 / 10 % 10;
            int qian = i / 10 / 10 / 10 % 10;
            int number = (int) (Math.pow(ge, 4) + Math.pow(shi, 4) + Math.pow(bai, 4) + Math.pow(qian, 4));
            if (number == i) {
                System.out.println(i);
                count++;
            }
        }
        System.out.println(count);
    }

    public static void method4() {
        int count = 0;
        for (int i = 10000; i < 99999; i++) {
            int ge = i % 10;
            int shi = i / 10 % 10;
            int bai = i / 10 / 10 % 10;
            int qian = i / 10 / 10 / 10 % 10;
            int wan = i / 10 / 10 / 10 / 10 % 10;
            int number = (int) (Math.pow(ge, 5) + Math.pow(shi, 5) + Math.pow(bai, 5) + Math.pow(qian, 5) + Math.pow(wan, 5));
            if (number == i) {
                System.out.println(i);
                count++;
            }
        }
        System.out.println(count);
    }
}
