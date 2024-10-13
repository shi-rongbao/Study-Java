package com.study.tcp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) throws IOException {
        //创建ServerSocket对象
        ServerSocket ss = new ServerSocket(10000);

        //监听客户端的链接
        Socket accept = ss.accept();

        //获取输入流
        BufferedReader br = new BufferedReader(new InputStreamReader(accept.getInputStream()));

        int b;
        while ((b = br.read()) != -1) {
            System.out.println((char) b);
        }

        //释放资源
        //断开连接
        accept.close();
        //关流
        br.close();
        //释放资源
        ss.close();
    }
}
