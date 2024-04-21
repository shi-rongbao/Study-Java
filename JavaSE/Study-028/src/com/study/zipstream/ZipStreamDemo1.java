package com.study.zipstream;

import java.io.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class ZipStreamDemo1 {
    public static void main(String[] args) throws IOException {
        File src = new File("D:\\Test\\a\\aaa.zip");
        File dest = new File("D:\\Test");
        unzip(src,dest);
    }

    public static void unzip(File src,File dest) throws IOException {
        //创建解压流对象
        ZipInputStream zip = new ZipInputStream(new FileInputStream("src"));
        //创建一个ZipEntry类型变量
        ZipEntry entry;
        //读取压缩包内文件,当压缩包内还能读取到文件,也就是返回值不为-1时进入循环
        while ((entry = zip.getNextEntry()) != null){
            //判断当前entry是否为文件夹
            if (entry.isDirectory()){
                //如果是文件夹,则创建当前目录的File对象,目录的父级路径为dest,要拷贝的位置,子级路径为当前读取到的路径
                File file = new File(dest,entry.toString());
                //创建文件夹
                file.mkdirs();
            }else {
                //如果当前entry为文件,则通过字节流拷贝到该位置
                FileOutputStream fos = new FileOutputStream(new File(dest,entry.toString()));
                int b;
                while ((b = zip.read()) != -1){
                    fos.write(b);
                }
                fos.close();
                zip.closeEntry();
            }
        }
        zip.close();
    }
}
