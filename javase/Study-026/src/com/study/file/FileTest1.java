package com.study.file;

import java.io.File;
import java.io.IOException;

public class FileTest1 {
    public static void main(String[] args) throws IOException {
        /*
            需求:在当前模块下的aaa文件夹中创建一个a.txt文件
         */
        //先创建该项目路径的File对象
        File file = new File("Study-026\\aaa");
        //创建Study-026\aaa目录
        boolean success = file.mkdirs();
        //将父级目录与子级目录拼接位File对象
        File src = new File(file,"a.txt");
        if (success){
            System.out.println("文件夹创建成功");
        }else {
            System.out.println("文件夹创建失败");
        }
        //按照File对象src创建文件
        boolean success1 = src.createNewFile();
        if(success1){
            System.out.println("文件创建成功");
        }else {
            System.out.println("文件创建失败");
        }
    }
}
