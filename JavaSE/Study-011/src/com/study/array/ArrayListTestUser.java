package com.study.array;

import java.util.ArrayList;

public class ArrayListTestUser {
    public static void main(String[] args) {

        /*
            需求:
                main方法中定义一个集合,存入三个用户对象.
                用户的属性为:id,username,password
            要求:定义一个方法,根据id查找对应的用户信息
                如果存在,返回true
                如果不存在,返回false
            需求:再定义一个方法,根据id查找对应的用户信息.
                如果存在,返回索引
                如果不存在,返回-1
         */

        // 创建一个ArrayList来存储User对象
        ArrayList<User> list = new ArrayList<>();

        // 创建用户对象
        User u1 = new User(1,"user1","password1");
        User u2 = new User(2,"user2","password2");
        User u3 = new User(3,"user3","password3");

        // 将用户对象添加到ArrayList中
        list.add(u1);
        list.add(u2);
        list.add(u3);

        // 调用"contains"方法，并打印结果
        System.out.println(contains(0, list));
    }

    // 方法用于根据ID在列表中查找对应的用户信息，返回是否存在
    public static boolean contains(int id, ArrayList<User> list){
        // 遍历列表
        for (int i = 0; i < list.size(); i++) {
            // 检查ID是否匹配
            if (id == list.get(i).getId()){
                return true; // 如果找到匹配的ID，则返回true
            }
        }
        return false; // 如果未找到匹配的ID，则返回false
    }

    // 方法用于根据ID在列表中查找对应的用户信息，返回索引
    public static int getIndex(int id, ArrayList<User> list){
        // 遍历列表
        for (int i = 0; i < list.size(); i++) {
            // 检查ID是否匹配
            if (id == list.get(i).getId()){
                return i; // 如果找到匹配的ID，则返回索引
            }
        }
        return -1; // 如果未找到匹配的ID，则返回-1
    }
}
