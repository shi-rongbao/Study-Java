package com.study.tcp;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

public class Client {
    public static void main(String[] args) throws IOException {
        //TCP协议发送数据
        //创建Socket对象,连接服务器
        Socket socket = new Socket("127.0.0.1",10000);

        //创建输出流对象
        OutputStream os = socket.getOutputStream();

        //写出字节数组数据
        os.write("你是谁啊".getBytes());

        //释放资源
        os.close();
        socket.close();
    }
}
