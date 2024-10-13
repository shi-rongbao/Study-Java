package com.study.test2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerTest2 {
    public static void main(String[] args) throws IOException {
        ServerSocket ss = new ServerSocket(9998);

        Socket accept = ss.accept();

        BufferedReader br = new BufferedReader(new InputStreamReader(accept.getInputStream()));
        int b;
        while ((b = br.read()) != -1) {
            System.out.print((char) b);
        }

        OutputStream os = accept.getOutputStream();
        os.write("这是服务器向客户端发送的数据".getBytes());

        accept.close();
    }
}
