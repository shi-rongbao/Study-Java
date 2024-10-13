package com.study.file;

import java.io.File;

public class FileTest6 {
    public static void main(String[] args) {
        /*
            统计一个文件夹的总大小
         */
        File file = new File("D:\\StudyJava");
        System.out.println(getLen(file));//17,177,076,539
                                         //17,177,076,539
    }

    public static long getLen(File src) {
        //先进入src目录,将里面所有文件或文件夹放入File数组中
        File[] files = src.listFiles();
        //定义long类型变量,用来记录文件大小的和
        long len = 0;
        //遍历数组
        for (File file : files) {
            //如果该元素为文件,则获取该文件的大小,并累加到len
            if (file.isFile()) {
                len = len + file.length();
            } else {
                //如果该元素为文件夹,递归处理,但也要把文件夹的大小累加到len当中
                len += getLen(file);
            }
        }
        return len;
    }
}
