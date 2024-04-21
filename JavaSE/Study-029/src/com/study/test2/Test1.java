package com.study.test2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Random;

public class Test1 {
    public static void main(String[] args) throws IOException {
        /*
            需求:
                有一个文件里面存储了班级同学的信息,每个信息占一行.
                格式为:张三-男-23
                要求通过程序实现随机点名器.

            运行效果:
                第一次运行程序:随机同学姓名1(只显示名字)
                第二次运行程序:随机同学姓名2(只显示名字)
                第三次运行程序:随机同学姓名3(只显示名字)
         */

        /*
            分析:
                先获取有多少个学生信息,再创建随机数,循环读取n行数据,每次运行返回学生姓名
         */

        //获取字符缓冲流对象,用来读取数据,确定一共有多少个信息数据
        BufferedReader br = new BufferedReader(new FileReader("Study-029\\names.txt"));
        //创建计数器变量
        int count = 0;
        //当文件中还有数据能够读取,也就是返回值不是null时进入循环
        String line;
        while ((line = br.readLine()) != null){
            //每次读取文件 计数器变量count自增一次
            count++;
        }

        //关流
        br.close();

        //再次创建字符缓冲流对象,用来读取数据
        BufferedReader br1 = new BufferedReader(new FileReader("Study-029\\names.txt"));

        //创建随机数对象
        Random r = new Random();
        //随机数的范围是1到count(左右都包括)
        int index = r.nextInt(count) + 1;
        //循环读取文件的每一行,但不接收,循环次数为随机数 -1
        for (int i = 0; i < index -1; i++) {
            br1.readLine();
        }
        //再次读取一行的数据,这行的数据就是随机数所对应行
        String info = br1.readLine();

        //关流
        br1.close();
        //将得到的学生信息按照"-"切割,获取第一个数据,也就是0索引数据,为学生的姓名
        String name = info.split("-")[0];
        //输出打印
        System.out.println(name);
    }
}
