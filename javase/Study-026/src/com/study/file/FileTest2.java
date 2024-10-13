package com.study.file;

import java.io.File;

public class FileTest2 {
    public static void main(String[] args) {
        /*
            需求:
                定义一个方法找某一个文件夹中,是否有以avi结尾的电影.(暂时不考虑子文件夹)
                D:\\aaa
                D:\\aaa\\bbb
                D:\\aaa\\ccc
         */


    }

    public static boolean getAvi(File file){
        //进入file文件,将file文件中的每一个内容都放入File数组中
        File[] files = file.listFiles();
        //遍历files数组
        for (File f : files) {
            //如果files的元素是file文件,且以.avi结尾,返回true
            if (f.isFile() && f.getName().endsWith(".avi")){
                return true;
            }
        }
        //如果遍历完数组还没有找到,则返回false
        return false;
    }
}
