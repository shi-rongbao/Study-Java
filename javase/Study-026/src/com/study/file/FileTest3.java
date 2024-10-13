package com.study.file;

import java.io.File;

public class FileTest3 {
    public static void main(String[] args) {
        /*
            需求:
                删除一个多级文件夹
         */

        File f1 = new File("D:\\Test");

    }

    public static void delete(File src){
        //进入src文件夹,将src文件夹里面的内容放入File数组中
        File[] files = src.listFiles();
        //遍历files数组,得到每一个元素(文件或者文件夹)
        for (File file : files) {
            //判断该元素是否为文件
            if (file.isFile()){
                //如果是文件,则直接删除
                file.delete();
            }else{
                //如果不是文件,则重新调用delete方法,将该文件夹传入
                delete(file);
            }
        }
        //最后删除src文件
        src.delete();
    }
}
