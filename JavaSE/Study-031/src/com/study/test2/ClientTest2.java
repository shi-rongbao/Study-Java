package com.study.test2;

import java.io.*;
import java.net.Socket;

public class ClientTest2 {
    public static void main(String[] args) throws IOException {
        /*
            客户端:发送一条数据,接受服务端反馈的消息并打印
            服务器:接收数据并打印,再给客户端反馈消息
         */

        Socket socket = new Socket("127.0.0.1",9998);

        OutputStream os = socket.getOutputStream();
        os.write("这是客户端向服务端发送的数据".getBytes());
        //写出一个结束标记
        socket.shutdownOutput();

        BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        int b;
        while ((b = br.read()) != -1){
            System.out.print((char) b);
        }

        //释放资源
        socket.close();
    }
}
