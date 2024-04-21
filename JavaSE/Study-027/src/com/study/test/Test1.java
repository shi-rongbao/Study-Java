package com.study.test;

import java.io.*;

public class Test1 {
    public static void main(String[] args) throws IOException {
        /*
            需求:
                拷贝一个文件夹,考虑子文件夹
         */
        File src = new File("D:\\Test\\a");
        File copy = new File("D:\\Test\\b");
        copyFile(src,copy);
    }


    public static void copyFile(File src, File copy) throws IOException {
        //创建要拷贝的文件,如果存在则不创建,返回false,如果不存在则创建文件夹,返回true,不会报错
        copy.mkdirs();
        //进入src目录,将元素放入File数组中
        File[] files = src.listFiles();
        //遍历files数组
        for (File file : files) {
            //判断元素是否为文件
            if (file.isFile()){
                //如果是文件,则创建文件输入输出流对象
                FileInputStream fis = new FileInputStream(file);//文件输入流的路径为文件路径
                FileOutputStream fos = new FileOutputStream(new File(copy,file.getName()));//文件输出流的路径为父级路径为要拷贝到的文件夹路径,子级路径为与该文件的文件名相同
                //创建len变量,用来记录bytes数组中存入了长度为多少的字节
                int len;
                //创建byte类型的数组,用来存入数据,每次存入2M大小的内容进入数组
                byte[] bytes = new byte[1024 * 1024 * 2];
                //进入循环,当从文件中还能读取到数据,也就是返回值len不等于-1进入
                while ((len = fis.read(bytes)) != -1){
                    //写入数据,从bytes数组中写入,从0索引开始,到len结束,每次读取到多少数据就写入多少数据
                    fos.write(bytes,0,len);
                }

                //关流
                fos.close();
                fis.close();
            }else {
                //如果该元素不是文件,则递归
                copyFile(file,new File(copy,file.getName()));
            }
        }
    }
}
