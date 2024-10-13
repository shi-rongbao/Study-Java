package com.study.file;

import java.io.File;
import java.io.IOException;

public class FileDemo3 {
    public static void main(String[] args) throws IOException {
        File f1 = new File("D:\\Test\\c.txt");
        System.out.println(f1.createNewFile());

        File f2 = new File("D:\\Test\\aaa\\a.txt");
        System.out.println(f2.mkdirs());

    }
}
