package com.study.io1;

import java.io.FileOutputStream;
import java.io.IOException;

public class ByteStreamDemo3 {
    public static void main(String[] args) throws IOException {
        //第一个参数,文件的路径,第二个参数,是否清空文件中原有的内容
        //true表示不清空,false表示清空
        FileOutputStream fos = new FileOutputStream("Study-027\\c.txt", true);

        String str1 = "shirongbaohaoshuai";
        byte[] bytes1 = str1.getBytes();
        fos.write(bytes1);

        String str2 = "\r\n";
        byte[] bytes2 = str2.getBytes();
        fos.write(bytes2);

        String str3 = "fuck fuck";
        byte[] bytes3 = str3.getBytes();
        fos.write(bytes3);

        fos.close();
    }
}
