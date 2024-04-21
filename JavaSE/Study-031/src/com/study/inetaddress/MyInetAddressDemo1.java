package com.study.inetaddress;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class MyInetAddressDemo1 {
    public static void main(String[] args) throws UnknownHostException {
        //获取电脑的对象
        InetAddress address = InetAddress.getByName("丿时");
        System.out.println(address);

        //通过对象获取ip与设备名称  注:如果没有网络连接,获取设备名称返回ip值
        String ip = address.getHostAddress();
        String name = address.getHostName();

        System.out.println(ip);
        System.out.println(name);
    }
}
