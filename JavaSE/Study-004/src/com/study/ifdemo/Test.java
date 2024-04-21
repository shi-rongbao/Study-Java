package com.study.ifdemo;

import java.util.Scanner;

public class Test {
    public static void main(String[] args) {
        /*
            需求:
                用户在超市实际购买,商品总价为600元
                键盘录入一个整数表示用户实际支付的钱.
                如果付款大于等于600,输出支付成功,否则支付失败
         */

        Scanner sc = new Scanner(System.in);
        System.out.println("请输入支付的钱:");
        int payMoney = sc.nextInt();
        if (payMoney >= 600){
            System.out.println("支付成功");
        }else {
            System.out.println("支付失败");
        }
    }
}
