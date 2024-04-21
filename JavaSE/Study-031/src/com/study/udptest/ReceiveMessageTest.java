package com.study.udptest;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class ReceiveMessageTest {
    public static void main(String[] args) throws IOException {
        //创建 DS对象用于接收数据
        DatagramSocket ds = new DatagramSocket(10086);

        byte[] bytes = new byte[1024];
        int len = bytes.length;
        while (true) {
            //创建 DP对象用于接收数据包
            DatagramPacket dp = new DatagramPacket(bytes, len);

            //接收数据
            ds.receive(dp);

            //解析数据
            byte[] data = dp.getData();
            int port = dp.getPort();
            int length = dp.getLength();
            InetAddress address = dp.getAddress();

            System.out.println("接收到的数据是:" + new String(data, 0, length));
            System.out.println("该数据是从" + address + "端口号为" + port + "接收到的");
            if (new String(data, 0, length).equals("886")){
                break;
            }
        }
        //释放资源
        ds.close();
    }
}
