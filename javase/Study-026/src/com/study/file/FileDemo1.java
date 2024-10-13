package com.study.file;

import java.io.File;

public class FileDemo1 {
    public static void main(String[] args) {
        String str1 = "D:\\";
        String str2 = "a.txt";

        File f1 = new File("D:\\a.txt");
        System.out.println(f1);

        File f2 = new File(str1,str2);
        System.out.println(f2);

        File f3 = new File(str1 + str2);
        System.out.println(f3);

        File f4 = new File(str1);
        File f5 = new File(f4,str2);
        System.out.println(f5);

     }
}
