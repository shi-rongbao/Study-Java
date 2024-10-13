package com.study.test1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerTest1 {
    public static void main(String[] args) throws IOException {
        ServerSocket ss = new ServerSocket(9999);

        Socket accept = ss.accept();

        BufferedReader br = new BufferedReader(new InputStreamReader(accept.getInputStream()));

        int b;
        while ((b = br.read()) != -1){
            System.out.print((char) b);
        }
        accept.close();
        ss.close();
    }
}
