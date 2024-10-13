package com.study.file;

import java.io.File;

public class FileDemo2 {
    public static void main(String[] args) {
        File f1 = new File("D:\\Test\\a");
        boolean exists = f1.exists();
        System.out.println("是否存在" + exists);
        boolean file = f1.isFile();
        System.out.println("是否为文件" + file);
        boolean directory = f1.isDirectory();
        System.out.println("是否为文件夹" + directory);

        File f2 = new File("D:\\Test\\aaaaaa");
        //对不存在的File对象进行判断,不会报错
        boolean directory1 = f2.isDirectory();
        System.out.println("是否为文件夹" + directory1);

        //length方法只能获取文件的大小,不能获取文件夹的大小,单位是字节

    }
}
