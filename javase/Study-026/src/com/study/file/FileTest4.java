package com.study.file;

import java.io.File;

public class FileTest4 {
    public static void main(String[] args) {
        /*
            需求:
                找到电脑中所有以avi结尾的电影.
                (需要考虑子文件夹)
         */

        findAvi();
    }
    public static void findAvi(){
        //使用File.listRoots方法,获取电脑的盘符,全部放入File数组中
        File[] files = File.listRoots();
        //遍历files数组,每个盘符调用findAvi方法
        for (File file : files) {
            findAvi(file);
        }
    }

    public static void findAvi(File file){
        //进入file文件,将里面的内容存入数组
        File[] files = file.listFiles();
        //确保文件不为空,当访问有权限的文件时,会返回null
        if (files != null){
            //遍历files数组
            for (File f : files) {
                //如果当前files元素是文件,则判断该文件是否已.avi结尾
                if (f.isFile()){
                    //如果是,则打印输出
                    if (f.getName().endsWith(".avi")){
                        System.out.println(f);
                    }
                }
                //如果当前files元素不是文件,则是文件夹,再次调用findAvi方法,递归处理
                else {
                    findAvi(f);
                }
            }
        }
    }
}
