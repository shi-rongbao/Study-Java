package com.study.udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class ReceiveMessageDemo1 {
    public static void main(String[] args) throws IOException {
        /*
            创建DatagramSocket对象

            细节:
                在接收的时候,一定要绑定端口
                而且绑定的端口一定要跟发送的端口保持一致
         */

        DatagramSocket ds = new DatagramSocket(10086);

        //创建数据包对象

        //创建用于接收数据的数组
        byte[] bytes = new byte[1024];
        //参数一:用于接收数据的数组
        //参数二:用该数组的多少空间来接收数据
        DatagramPacket dp = new DatagramPacket(bytes, bytes.length);

        //获取数据包
        //该方法是阻塞的
        //当程序运行到这一步,会在这里死等
        //直到获取到发送端发送的消息
        ds.receive(dp);

        //获取数据包中的数据
        byte[] data = dp.getData();
        //获取发送数据的ip
        InetAddress address = dp.getAddress();
        //获取数据的长度
        int len = dp.getLength();
        //获取端口号
        int port = dp.getPort();

        System.out.println("接收到的数据是:" + new String(data, 0, len));
        System.out.println("该数据是从:" + address + "这台电脑的" + port + "端口号接收的");

        ds.close();
    }
}
