package com.study.test;

public class Test1 {
    public static void main(String[] args) {
        /*
            需求: 机票价格按照淡季旺季,头等舱和经济舱收费,输入机票原价,月份和头等舱或经济舱
            按照如下规则计算机票价格:旺季(5-10月)头等舱9折,经济舱8.5折,淡季(11月到来年4月)头等舱7折,经济舱6.5折
         */
        double price = flyPrice(5,869,"1");
        System.out.println(price);
    }

    public static double flyPrice(int mouth, double price, String type) {
        double result = 0;
        if (mouth > 0 && mouth < 12) {
            switch (mouth) {
                case 5:
                case 6:
                case 7:
                case 8:
                case 9:
                case 10: {
                    if (type.equals("经济舱")) {
                        result = price * 0.85;
                    } else if (type.equals("头等舱")) {
                        result = price * 0.9;
                    } else {
                        System.out.println("输入有误");
                    }
                    break;
                }
                case 1:
                case 2:
                case 3:
                case 4:
                case 11:
                case 12: {
                    if (type.equals("经济舱")) {
                        result = price * 0.65;
                    } else if (type.equals("头等舱")) {
                        result = price * 0.7;
                    } else {
                        System.out.println("输入有误");
                    }
                }
            }
        }else {
            System.out.println("您的月份信息有误!");
        }
        return result;
    }
}
