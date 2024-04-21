package com.study.test3;

import java.io.*;
import java.net.Socket;
import java.util.UUID;

public class MyRunnable implements Runnable {
    Socket socket;

    public MyRunnable(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            //读取文件并写入到本地
            BufferedInputStream bis = new BufferedInputStream(socket.getInputStream());
            String name = UUID.randomUUID().toString().replace("-", "");
            BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream("Study-031\\serverdir\\" + name + ".jpg"));


            byte[] bytes = new byte[1024];
            int len;
            while ((len = bis.read(bytes)) != -1) {
                bos.write(bytes, 0, len);
            }
            //将缓冲区的数据写入到文件中
            bos.flush();

            //写出反馈
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            bw.write("文件接收并写入成功");
            bw.newLine();
            bw.flush();

            //释放资源
            bos.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            if (socket != null) {
                try {
                    socket.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
}
