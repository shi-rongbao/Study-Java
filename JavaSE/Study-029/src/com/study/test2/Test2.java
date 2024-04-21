package com.study.test2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

public class Test2 {
    public static void main(String[] args) throws IOException {
        /*
            需求:
                一个文件里面存储了班级同学的信息,每一个学生信息占一行
                格式为:张三-男-23
                要求通过程序实现随机点名器

            运行效果:
                70%的概率随机到男生
                30%的概率随机到女生
                总共随机100万次,统计结果.
                注意观察:看生成男生和女生的比例是不是接近于7:3
         */

        /*
            分析:
                1.先读取文件中有多少个学生数据
                2.创建一个长度为10的集合,里面装有7个1与3个0,每次打乱集合,再获取集合第一个元素,若是1,就抽取男生,若是0就抽取女生
                3.将学生信息读取到集合中,用来抽取
                4.定义两个计数器变量,一个统计男生被点到名多少次,一个统计女生被点到名多少次

         */

        //获取字符缓冲流对象,用来读取数据,确定一共有多少个信息数据
        BufferedReader br = new BufferedReader(new FileReader("Study-029\\names.txt"));
        //创建计数器变量
        int count = 0;
        //当文件中还有数据能够读取,也就是返回值不是null时进入循环
        String line;
        while ((line = br.readLine()) != null) {
            //每次读取文件 计数器变量count自增一次
            count++;
        }
        //关流
        br.close();
        //以上获取一共有多少个学生数据


        //创建两个计数器变量
        int boyCount = 0;
        int girlCount = 0;

        //创建一个用于模拟概率的集合
        ArrayList<Integer> list = new ArrayList<>();
        Collections.addAll(list, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0);

        //创建两个集合,用于存储从文件中读取到的学生信息
        ArrayList<String> boyNameList = new ArrayList<>();
        ArrayList<String> girlNameList = new ArrayList<>();


        //在创建一个字符缓冲流对象关联文件
        BufferedReader newBr = new BufferedReader(new FileReader("Study-029\\names.txt"));
        String str;
        while ((str = newBr.readLine()) != null) {
            if (str.split("-")[1].equals("男")) {
                boyNameList.add(str.split("-")[0]);
            } else {
                girlNameList.add(str.split("-")[0]);
            }
        }

        //将存储男女生姓名的集合打乱
        Collections.shuffle(boyNameList);
        Collections.shuffle(girlNameList);


        for (int i = 0; i < 1000000; i++) {
            //每次循环之前打乱集合,并获取集合第一个元素
            Collections.shuffle(list);
            int num = list.get(0);
            if (num == 1) {
                //如果num是1,则从男生当中抽取
                boyCount++;
            } else {
                //如果num不是1,则从女生当中抽取
                girlCount++;
            }
        }

        System.out.println("抽到男生的个数为:" + boyCount);
        System.out.println("抽到女生的个数为:" + girlCount);
    }

}
