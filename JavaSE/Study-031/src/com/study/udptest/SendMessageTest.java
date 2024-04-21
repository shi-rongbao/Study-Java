package com.study.udptest;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

public class SendMessageTest {
    public static void main(String[] args) throws IOException, InterruptedException {
        /*
            按照先免得要求实现程序
            UDP发送数据:数据来源于键盘录入,直到输入的数据是886,发送数据结束
            UDP接收数据:因为接收端不知道发送端什么时候停止发送,故采用死循环接受
         */

        //创建 DS对象用于发送数据
        DatagramSocket ds = new DatagramSocket();

        //创建 DP对象用于打包数据
        //创建数组用于存放数据
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("请输入要发送的数据");
            String line = sc.nextLine();
            if ("886".equals(line)) {
                break;
            }
            byte[] bytes = line.getBytes();

            InetAddress address = InetAddress.getByName("127.0.0.1");
            int port = 10086;

            //将数据放入数据包中
            DatagramPacket dp = new DatagramPacket(bytes, bytes.length, address, port);

            //发送数据
            ds.send(dp);
        }

        Thread.sleep(100);
        //释放资源
        ds.close();
    }
}
