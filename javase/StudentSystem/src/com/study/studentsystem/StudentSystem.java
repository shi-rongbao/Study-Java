package com.study.studentsystem;

import java.util.ArrayList;
import java.util.Scanner;

public class StudentSystem {
    public static Scanner sc = new Scanner(System.in);//keyboard entry creates Scanner objects   // public static
    public static void startStudentSystem() {
        ArrayList<Student> list = new ArrayList<>();//存储学生信息的集合
        while (true) {
            System.out.println("-----欢迎来到学生管理系统-----");
            System.out.println("1.添加学生");
            System.out.println("2.删除学生");
            System.out.println("3.修改学生");
            System.out.println("4.查询学生");
            System.out.println("5.退出");
            System.out.println("请输入您的选择:");
            int choose = sc.nextInt();
            switch (choose) {
                case 1:
                    addStudent(list);
                    break;
                case 2:
                    deleteStudent(list);
                    break;
                case 3:
                    updateStudent(list);
                    break;
                case 4:
                    queryStudent(list);
                    break;
                case 5:
                    System.out.println("退出成功!");
                    System.exit(0);
            }
        }
    }

    //"add" method into set
    public static void addStudent(ArrayList<Student> list) {
        System.out.println("请输入你要添加学生的学号:");
        String inputId = sc.next();
        boolean flag = contains(inputId, list);
        if (!flag) {
            System.out.println("请输入你要添加学生的姓名:");
            String inputName = sc.next();
            System.out.println("请输入你要添加学生的年龄:");
            int inputAge = sc.nextInt();
            System.out.println("请输入你要添加学生的地址:");
            String inputAddress = sc.next();
            Student inputStudent = new Student(inputId, inputName, inputAge, inputAddress);
            list.add(inputStudent);
            System.out.println("学生信息添加成功!");
        } else {
            System.out.println("您输入的学号已经存在!");
        }
    }

    public static void deleteStudent(ArrayList<Student> list) {
        System.out.println("请输入你要删除的学生学号:");
        String inputId = sc.next();
        if (contains(inputId,list)){
            int index = getIndex(inputId, list);
            list.remove(index);
            System.out.println("删除成功!");
        }else {
            System.out.println("你要删除的学号不存在!");
        }
    }

    public static void updateStudent(ArrayList<Student> list) {
        System.out.println("请输入你要更新的学生学号:");
        String inputId = sc.next();
        if (contains(inputId,list)){
            int index = getIndex(inputId, list);
            System.out.println("请输入你要修改的学生姓名:");
            String updateName = sc.next();
            System.out.println("请输入你要修改学生的年龄:");
            int updateAge = sc.nextInt();
            System.out.println("请输入你要修改学生的地址:");
            String updateAddress = sc.next();
            Student updateStudent = new Student(inputId,updateName,updateAge,updateAddress);
            list.set(index,updateStudent);
        }else {
            System.out.println("你要修改的学号不存在!");
        }
    }

    public static void queryStudent(ArrayList<Student> list) {
        if (list.size() == 0){
            System.out.println("当前无学生信息,请先添加!");
        }else{
            System.out.println("id\t\tname\t\tage\t\taddress");
            for (int i = 0; i < list.size(); i++) {
                System.out.println(list.get(i));
            }
        }
    }

    //check whether the student number exists
    public static boolean contains(String id, ArrayList<Student> list) {
        for (int i = 0; i < list.size(); i++) {
            if (id.equals(list.get(i).getId())) {
                return true; //if the student number exist, return true
            }
        }
        return false;//when all student numbers in the set do not exist, return false
    }
    //gets the index corresponding to the id in the collection
    public static int getIndex(String id, ArrayList<Student> list){
        //iterate over all the elements in the collection
        for (int i = 0; i < list.size(); i++) {
            if (id.equals(list.get(i).getId())){
                return i;//return index
            }
        }
        return -1;//return -1
    }
}