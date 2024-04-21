package com.study.test2;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;

public class Test3 {
    public static void main(String[] args) throws IOException {
        /*
            需求:
                一个文件里面存储了同班同学的姓名,每一个姓名占一行.
                要求通过程序实现随机点名器.
                第三次必定是张三同学

            运行效果:
                第一次运行程序:随机同学姓名1
                第二次运行程序:随机同学姓名2
                第三次运行程序:张三
         */

        /*
            分析:
                1.将文件中的信息读取到集合中
                2.随机点名
                3.定义一个保存到本地的计数器
                4.点名,同时每运行一次程序,计数器自增一次
                5.判断是否为第三次点名,如果是,则点名张三
         */

        //创建字符缓冲输入流关联文件
        BufferedReader br = new BufferedReader(new FileReader("Study-029\\names.txt"));
        String line;
        //创建集合
        ArrayList<String> list = new ArrayList<>();
        while ((line = br.readLine()) != null) {
            list.add(line.split("-")[0]);
        }
        //打乱集合
        Collections.shuffle(list);

        BufferedReader newBr = new BufferedReader(new FileReader("Study-029\\count.txt"));
        String str = newBr.readLine();
        if (str.equals("2")){
            System.out.println("张三");
        }else {
            System.out.println(list.get(0));
        }
        newBr.close();

        BufferedWriter bw = new BufferedWriter(new FileWriter("Study-029\\count.txt"));
        bw.write("" + (Integer.parseInt(str) + 1));
        bw.close();
    }
}
