package com.study.udp;

import java.io.IOException;
import java.net.*;

public class SendMessageDemo1 {
    public static void main(String[] args) throws IOException {
        //创建DatagramSocket对象

        //细节：
        /*
            参数:绑定端口,以后就是通过这个端口往外发送
            空参,所有可用的端口号中随机一个进行使用
            有参,指定端口号进行绑定
         */

        //创建发送数据的对象
        DatagramSocket ds = new DatagramSocket();

        //将要发送的数据转换为byte数组
        String name = "srb";
        byte[] bytes = name.getBytes();

        //获取本机ip地址
        InetAddress ip = InetAddress.getByName("127.0.0.1");

        //创建一个端口号
        int port = 10086;

        //打包数据
        /*
            参数一:byte数组
            参数二:要发送数组中多少的内容
            参数三:要往哪个ip发送数据
            参数四:端口号
         */
        DatagramPacket dp = new DatagramPacket(bytes, bytes.length, ip, port);

        //发送数据包
        ds.send(dp);

        //释放资源
        ds.close();
    }
}