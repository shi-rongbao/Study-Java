package com.study.io2;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class ByteStreamDemo3 {
    public static void main(String[] args) throws IOException {
        long start = System.currentTimeMillis();
        FileInputStream fis = new FileInputStream("D:\\StudyJava\\JavaSE\\Study-001\\Study-027\\src\\com\\study\\io2\\aaa.txt");
        FileOutputStream fos = new FileOutputStream("D:\\StudyJava\\JavaSE\\Study-001\\Study-027\\src\\com\\study\\io2\\aa.txt");

        int b;
        while ((b = fis.read()) != -1){
            fos.write(b);
        }

        fos.close();
        fis.close();
        long end = System.currentTimeMillis();
        System.out.println(end - start);
    }
}
