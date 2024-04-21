package com.study.test;

import java.io.*;
import java.util.Arrays;

public class Test1 {
    public static void main(String[] args) throws IOException {
        /*
            拷贝文件
                四种方式拷贝文件,并统计各自用时

                字节流的基本流:一次读写一个字节
                字节流的基本流:一次读写一个字节数组
                字节缓冲流:一次读写一个字节
                字节缓冲流:一次读写一个字节数组
         */

        File file = new File("Study-028\\a.txt");
        method1(file);
        System.out.println("===================");
        method2(file);
        System.out.println("===================");
        method3(file);
        System.out.println("===================");
        method3_3(file);

    }

    public static void method1(File file) throws IOException {
        long start = System.currentTimeMillis();
        FileInputStream fis = new FileInputStream(file);
        FileOutputStream fos = new FileOutputStream("Study-028\\method1.txt");
        int b;
        while ((b = fis.read()) != -1){
            fos.write(b);
        }
        fos.close();
        fis.close();
        long end = System.currentTimeMillis();
        System.out.println(end - start);
    }

    public static void method2(File file) throws IOException {
        long start = System.currentTimeMillis();
        FileInputStream fis = new FileInputStream(file);
        FileOutputStream fos = new FileOutputStream("Study-028\\method2.txt");
        byte[] bytes = new byte[8192];
        int len;
        while ((len = fis.read(bytes)) != -1){
            fos.write(bytes,0,len);
        }

        fos.close();
        fis.close();
        long end = System.currentTimeMillis();
        System.out.println(end - start);
    }

    public static void method3(File file) throws IOException {
        long start = System.currentTimeMillis();
        BufferedReader br = new BufferedReader(new FileReader(file));
        BufferedWriter bw = new BufferedWriter(new FileWriter("Study-028\\method3.txt"));
        String line;
        while ((line = br.readLine()) != null){
            bw.write(line);
            bw.newLine();
        }
        bw.close();
        br.close();
        long end = System.currentTimeMillis();
        System.out.println(end - start);
    }

    public static void method3_3(File file) throws IOException {
        long start = System.currentTimeMillis();
        BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file));
        BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream("Study-028\\method3_3.txt") );
        int b;
        while ((b = bis.read()) != -1){
            bos.write(b);
        }

        bos.close();
        bis.close();
        long end = System.currentTimeMillis();
        System.out.println(end - start);
    }

    public static void method4(File file) throws IOException {
        long start = System.currentTimeMillis();
        BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file));
        BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream("Study-028\\method4.txt") );
        byte[] bytes = new byte[8192];
        int len;
        while ((len = bis.read(bytes)) != -1){
            bos.write(bytes,0,len);
        }

        bos.close();
        bis.close();
        long end = System.currentTimeMillis();
        System.out.println(end - start);
    }
}
