package com.study.exception;

import com.study.Girlfriend;

import java.util.Scanner;

public class ExceptionTest1 {
    public static void main(String[] args) {
        /*
            需求:
                键盘录入自己心仪的女朋友姓名和年龄.
                姓名长度在3-10之间.
                年龄的范围在18-40之间.
                超出这个范围是异常数据不能赋值,需要重新录入,一直录入到正确为止.
            提示:
                需要考虑用户在键盘录入时的所有情况
                比如:录入年龄时超出范围,录入年龄时录入了abc等情况
         */

        Scanner sc = new Scanner(System.in);
        Girlfriend gf = new Girlfriend();
        while (true) {
            try {
                System.out.println("请输入你女神的姓名:");
                String name = sc.nextLine();
                gf.setName(name);
                System.out.println("请输入你女神的年龄:");
                String age = sc.nextLine();
                gf.setAge(Integer.parseInt(age));
                break;
            } catch (NumberFormatException e) {
                e.printStackTrace();
            } catch (NameFormatException e) {
                e.printStackTrace();
            } catch (AgeOutOfBoundsException e) {
                e.printStackTrace();
            }
        }
        System.out.println(gf);
    }
}
