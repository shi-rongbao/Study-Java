package com.study.string;

import java.util.Scanner;

public class StringTest {
    public static void main(String[] args) {
        /*
            需求:
                已知正确的用户名和密码,请用程序实现模拟用户登录.
                总共给三次机会,登录之后,给出相应的提示
         */
        String realUsername = "admin";
        String realPassword = "password";

        Scanner sc = new Scanner(System.in);
        for (int i = 0; i < 3; i++) {
            System.out.println("请输入用户名:");
            String userInputUsername = sc.next();
            System.out.println("请输入密码:");
            String userInputPassword = sc.next();
            if (userInputUsername.equals(realUsername)
                    && userInputPassword.equals(realPassword)) {
                System.out.println("登录成功!");
                break;
            } else {
                if (2 - i <= 0){
                    System.out.println("不能登录了!");
                }else{
                    System.out.println("登录失败,请重新尝试!您还剩下" + (2 - i) + "次机会");
                }
            }
        }
    }
}
