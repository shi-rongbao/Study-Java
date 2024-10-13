package com.study.file;

import java.io.File;

public class FileDemo4 {
    public static void main(String[] args) {
        File f1 = new File("D:\\Test\\a");
        File[] files = f1.listFiles();
        for (File file : files) {
            System.out.println(file);
        }
    }
}
