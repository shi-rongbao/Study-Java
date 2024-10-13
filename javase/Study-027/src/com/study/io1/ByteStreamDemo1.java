package com.study.io1;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class ByteStreamDemo1 {
    public static void main(String[] args) throws IOException {
        File file = new File("Study-027\\a.txt");
        file.createNewFile();
        FileOutputStream fos = new FileOutputStream(file);
        fos.write(99);
        fos.close();
    }
}
