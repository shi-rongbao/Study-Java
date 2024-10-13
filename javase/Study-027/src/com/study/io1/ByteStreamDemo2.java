package com.study.io1;

import java.io.FileOutputStream;
import java.io.IOException;

public class ByteStreamDemo2 {
    public static void main(String[] args) throws IOException {
        FileOutputStream fos = new FileOutputStream("Study-027\\b.txt");
        fos.write(97);
        fos.write(98);

        byte[] b = {99, 100, 101, 102, 103, 104};
        fos.write(b);
        fos.write(b,0,2);
        fos.close();
    }
}
