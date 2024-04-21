package com.study.test;

import javax.swing.plaf.nimbus.AbstractRegionPainter;
import java.util.Scanner;

public class Test {
    public static void main(String[] args) {
        /*
            需求:
                你和对象正在约会,键盘录入两个整数,表示你和你约会对象衣服的时髦度
                手动录入1~10之间的整数,不能录入其他
                如果你的时髦程度大于你对象的时髦程度,想亲就能成功,输出true
                否则输出false
         */

        Scanner sc = new Scanner(System.in);
        System.out.println("请输入对你自己的评分");
        int num1 = sc.nextInt();
        if (num1 <=0 || num1 >=10){
            System.out.println("您输入的数字有误,请检查");
            System.exit(0);
        }
        System.out.println("请输入对你约会对象的评分");
        int num2 = sc.nextInt();
        if (num2 <=0 || num2 >=10){
            System.out.println("您输入的数字有误,请检查");
            System.exit(0);
        }
        if (num1 > num2){
            System.out.println("true");
        }else System.out.println("false");

    }
}
