package com.study.test;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

public class Test3 {
    public static void main(String[] args) throws IOException {
        //获取文件输入流对象
        FileReader fr = new FileReader("D:\\Test\\a\\test.txt");
        //创建int类型数据,用来存储读取到的数据
        int ch;
        //创建SB对象,用来拼接字符串
        StringBuilder sb = new StringBuilder();
        //当还能读取到文件,也就是fr.read的返回值不是-1时进入循环
        while ((ch = fr.read()) != -1){
            //拼接当前数据到sb对象中
            sb.append((char)ch);
        }

        //关流
        fr.close();

        //将sb对象转变为String类型
        String str = sb.toString();
        //将字符串通过"-"切割存入数组中
        String[] numStr = str.split("-");
        //创建用来存储转变为数字的集合
        ArrayList<Integer> list = new ArrayList<>();
        //遍历字符串数组
        for (String num : numStr) {
            //将字符串转变为整形再放入数组中
            int i = Integer.parseInt(num);
            list.add(i);
        }

        //用Collections.sort方法将list中的数字自动排序(默认从小到大)
        Collections.sort(list);

        //创建文件输出流对象
        FileWriter fw = new FileWriter("D:\\Test\\a\\test.txt");

        //遍历集合,将集合里的内容写入文件
        for (int i = 0; i < list.size(); i++) {
            if (i == list.size() - 1){
                //当写入最后一个数据时不拼接"-"
                fw.write(list.get(i) + "");
            }else {
                //写入数据时拼接一个"-"
                fw.write(list.get(i) + "-");
            }
        }

        //关流
        fw.close();
    }
}
