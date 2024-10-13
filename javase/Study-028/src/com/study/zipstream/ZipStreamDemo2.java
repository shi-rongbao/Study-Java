package com.study.zipstream;

import java.io.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class ZipStreamDemo2 {
    public static void main(String[] args) throws IOException {
        File src = new File("D:\\Test\\a.txt");
        File dest = new File("D:\\Test");
        toZip(src,dest);
    }

    public static void toZip(File src, File dest) throws IOException {
        //创建压缩流的对象,并且指定路径
        ZipOutputStream zip = new ZipOutputStream(new FileOutputStream(new File(dest,"a.zip")));
        //创建新的ZipEntry对象,并且指定文件名为a.txt
        ZipEntry entry = new ZipEntry("a.txt");
        zip.putNextEntry(entry);
        FileInputStream fis = new FileInputStream(src);
        int b;
        while ((b = fis.read()) != -1){
            zip.write(b);
        }
        zip.closeEntry();
        zip.close();
    }
}
