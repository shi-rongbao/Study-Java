package com.study.bufferedstream;

import java.io.*;

public class BufferedStreamDemo2 {
    public static void main(String[] args) throws IOException {
        BufferedInputStream bis = new BufferedInputStream(new FileInputStream("Study-028\\a.txt"));
        BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream("Study-028\\copy2.txt"));

        byte[] bytes = new byte[1024 * 1024];
        int len;
        while ((len = bis.read(bytes)) != -1){
            bos.write(bytes,0,len);
        }

        bos.close();
        bis.close();
    }
}
