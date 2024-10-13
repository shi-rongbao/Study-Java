package com.shirongbao.proxy.service;

public class UserService {
    public boolean login(String username, String password) {
        System.out.println("系统正在验证身份");
        try {
            Thread.sleep(1234);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if ("admin".equalsIgnoreCase(username) && "123".equalsIgnoreCase(password)) {
            return true;
        }
        return false;
    }


    public void logout() {
        System.out.println("系统正在退出");
        try {
            Thread.sleep(231);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("退出成功!");
    }
}
