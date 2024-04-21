package com.study.test;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class Test4 {
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

        //将sb通过toString转成字符串,再通过"-"切割变成数组,再通过Arrays.stream放入流中
        Integer[] arr = Arrays.stream(sb.toString().split("-"))
                //再用流中的map方法,将字符串类型数据转为int类型
                .map(Integer::parseInt)
                //再使用默认排序将数据排序
                .sorted()
                //最后将流中的数据转变为Integer类型数组
                .toArray(Integer[]::new);

        //将数组变为字符串,替换", "为"-"
        String replace = Arrays.toString(arr).replace(", ", "-");
        //再将得到的结果去掉前后的中括号得到最终的结果
        String result = replace.substring(1, replace.length() - 1);

        //创建文件输出流对象
        FileWriter fw = new FileWriter("D:\\Test\\a\\test.txt");
        //写入最终的字符串结果
        fw.write(result);

        //关流
        fw.close();
    }
}
