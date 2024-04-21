package com.study.practice;

public class StudentTest {
    public static void main(String[] args) {
        Student[] arr = new Student[3];
        Student stu1 = new Student("rongbao001", "时荣宝", 19);
        Student stu2 = new Student("rongbao002", "时宝荣", 20);
        Student stu3 = new Student("rongbao003", "宝荣时", 21);

        arr[0] = stu1;
        arr[1] = stu2;
        arr[2] = stu3;

        Student stu4 = new Student("rongbao004", "srb", 22);

        boolean flag = contains(arr, stu4.getId());
        if (flag) {
            System.out.println("已存在该学号,添加失败!");
        } else {
            int count = getCount(arr);
            if (count == arr.length) {
                Student[] newArr = creatNewArr(arr);
                newArr[count] = stu4;
                print(newArr);
            } else {
                arr[count] = stu4;
                print(arr);
            }
        }
    }

    public static boolean contains(Student[] arr, String id) {
        for (int i = 0; i < arr.length; i++) {
            Student stu = arr[i];
            if (stu != null) {
                if (id.equals(stu.getId())) {
                    return true;
                }
            }
        }
        return false;
    }

    public static int getCount(Student[] arr) {
        int count = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] != null) {
                count++;
            }
        }
        return count;
    }

    public static Student[] creatNewArr(Student[] arr) {
        Student[] newArr = new Student[arr.length + 1];
        for (int i = 0; i < arr.length; i++) {
            newArr[i] = arr[i];
        }
        return newArr;
    }

    public static void print(Student[] arr) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] != null) {
                System.out.println(arr[i].toString());
            }
        }
    }
}
