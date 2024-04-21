package com.study.test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class Test2 {
    public static void main(String[] args) throws IOException {
        /*
            为了保证文件的安全性,就需要对原始文件进行加密存储,再使用的时候再对其进行解密处理.
            加密原理:
                对原始文件中的每一个字节数据进行更改,然后将更改以后得数据存储到新的文件中.
            解密原理:
                读取加密之后的文件,按照加密的规则反向操作,变成原始文件
         */

        //创建文件输入输出流对象
        FileInputStream fis = new FileInputStream("D:\\Test\\a\\z.txt");//路径为需要加密的原文件地址
        FileOutputStream fos = new FileOutputStream("D:\\Test\\a\\x.txt");//路径为需要加密的新文件地址

        //创建int类型变量,用来确定文件当前读取到的数据
        int b;
        //进入循环,只有当fis从文件中读取到了内容,才进入循环,如果读取不到内容,返回-1,则不进入循环
        while ((b = fis.read()) != -1){
            //将读取到的数据与2做^(异或)处理
            fos.write(b ^ 2);
        }

        //关流
        fos.close();
        fis.close();
    }
}
