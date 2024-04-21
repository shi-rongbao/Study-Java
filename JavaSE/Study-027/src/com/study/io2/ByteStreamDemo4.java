package com.study.io2;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class ByteStreamDemo4 {
    public static void main(String[] args) throws IOException {
        long start = System.currentTimeMillis();

        //创建fis和fos对象
        FileInputStream fis = new FileInputStream("D:\\Test\\a\\abc\\a.txt");
        FileOutputStream fos = new FileOutputStream("D:\\Test\\a\\abc\\copy.txt");

        //定义长度变量
        int len;
        //定义byte数组,数组的长度为1024的整数倍,这里1024 * 1024 * 5为5M
        byte[] bytes = new byte[1024 * 1024 * 5];
        //赋值len = fis.read(bytes) 每次读取的长度赋值给len,当len 不等于 -1时进入循环
        while ((len = fis.read(bytes)) != -1) {
            //将读取到的数据写入文件中,从0到len
            fos.write(bytes,0,len);
        }
        //释放资源, 规则 一般情况先开的后释放
        fos.close();
        fis.close();

        long end = System.currentTimeMillis();

        System.out.println(end - start);
    }
}
