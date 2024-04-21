package com.study.test;

import java.io.*;

public class Test4 {
    public static void main(String[] args) throws IOException {
        /*
            软件运行次数
                实现一个验证程序运行次数的小程序,要求如下:
                    1.当程序运行超过3次时给出提示:本软件只能免费使用3次,欢迎您注册会员后继续使用~
                    2.程序运行演示如下:
                        第一次运行控制台输出:欢迎使用本软件,第一次免费使用~
                        第二次运行控制台输出:欢迎使用本软件,第二次免费使用~
                        第三次运行控制台输出:欢迎使用本软件,第三次免费使用~
                        第四次及之后控制台输出:本软件只能免费使用三次,欢迎您注册会员后继续使用~
         */

        BufferedReader br = new BufferedReader(new FileReader("Study-028\\count.txt"));
        String line = br.readLine();
        br.close();
        BufferedWriter bw = new BufferedWriter(new FileWriter("Study-028\\count.txt"));
        bw.write("" + (Integer.parseInt(line) + 1));
        if (Integer.parseInt(line) < 3) {
            System.out.println("欢迎使用本软件,第" + (Integer.parseInt(line) + 1) + "次免费使用~");
        }else {
            System.out.println("本软件只能免费使用三次,欢迎您注册会员后继续使用~");
        }
        bw.close();
    }
}
