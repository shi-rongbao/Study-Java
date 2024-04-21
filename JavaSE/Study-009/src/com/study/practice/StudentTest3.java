package com.study.practice;

public class StudentTest3 {
    public static void main(String[] args) {
        Student[] arr = new Student[3];
        Student stu1 = new Student("rongbao001", "时荣宝", 19);
        Student stu2 = new Student("rongbao002", "时宝荣", 20);
        Student stu3 = new Student("rongbao003", "宝荣时", 21);

        arr[0] = stu1;
        arr[1] = stu2;
        arr[2] = stu3;

        int index = getIndex(arr, "rongbao002");

        if (index >= 0) {
            arr[index] = null;
        } else {
            System.out.println("当前id不存在,删除失败!");
        }

        print(arr);

    }

    public static int getIndex(Student[] arr, String id) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] != null) {
                if (id.equals(arr[i].getId())) {
                    return i;
                }
            }
        }
        return -1;
    }

    public static void print(Student[] arr) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] != null) {
                System.out.println(arr[i].toString());
            }
        }
    }
}
