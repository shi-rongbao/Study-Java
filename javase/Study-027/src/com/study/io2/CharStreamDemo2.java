package com.study.io2;

import java.io.FileReader;
import java.io.IOException;

public class CharStreamDemo2 {
    public static void main(String[] args) throws IOException {
        //获取字符流读取对象
        FileReader fr = new FileReader("D:\\StudyJava\\JavaSE\\Study-001\\Study-027\\src\\com\\study\\io2\\aaa.txt");
        //创建一个char类型的数组,用来决定一次读取几个数据
        char[] chars = new char[3];
        //创建int类型的变量,用来存储读取了几个数据的长度
        int len;
        //当没读取到数据,返回-1时停止循环
        while ((len = fr.read(chars)) != -1) {
            //将读取到的数据变为字符串输出,变的内容是数组中的内容,从数组的0索引开始,到len索引结束
            System.out.print(new String(chars, 0, len));
        }
        //关流
        fr.close();
    }
}
