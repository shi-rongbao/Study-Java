package com.study.test1;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Scanner;

public class ClientTest1 {
    public static void main(String[] args) throws IOException {
        /*
            客户端:多次发送数据
            服务器:接收多次数据,并打印
         */
        Socket socket = new Socket("127.0.0.1", 9999);

        OutputStream os = socket.getOutputStream();
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("请输入你要发送的消息:");
            String str = sc.nextLine();
            if ("886".equals(str)){
                break;
            }
            os.write(str.getBytes());
        }

        socket.close();
    }
}
