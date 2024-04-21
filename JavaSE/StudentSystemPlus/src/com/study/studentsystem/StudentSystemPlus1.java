package com.study.studentsystem;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class StudentSystemPlus1 {
    public static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        ArrayList<User> list = new ArrayList<>();
        while (true) {
            System.out.println("-----欢迎来到学生管理系统-----");
            System.out.println("请选择你的操作:");
            System.out.println("1.登录");
            System.out.println("2.注册");
            System.out.println("3.忘记密码");
            System.out.println("4.退出");
            String choose = sc.next();
            switch (choose) {
                case "1":
                    loginMethod(list);
                    break;
                case "2":
                    registerMethod(list);
                    break;
                case "3":
                    forgetPasswordMethod(list);
                    break;
                case "4":
                    System.out.println("谢谢使用!");
                    System.exit(0);
                default:
                    System.out.println("没有这个选项!");
            }
        }
    }

    private static void forgetPasswordMethod(ArrayList<User> list) {
        System.out.println("请输入用户名:");
        String inputUsername = sc.next();
        boolean flag = contains(list, inputUsername);
        if (!flag) {
            System.out.println("当前用户未注册,请先注册");
            return;
        }
        System.out.println("请输入身份证号:");
        String inputIdCard = sc.next();
        System.out.println("请输入手机号:");
        String inputPhoneNumber = sc.next();
        int index = findIndex(list, inputUsername);
        String rightIdCard = list.get(index).getIdCard();
        String rightPhoneNumber = list.get(index).getPassword();
        if (!(inputIdCard.equalsIgnoreCase(rightIdCard) && inputPhoneNumber.equals(rightPhoneNumber))) {
            System.out.println("身份证号或手机号错误,请重试!");
            return;
        }
        while (true) {
            System.out.println("请输入新的密码:");
            String newPassword = sc.next();
            System.out.println("请再次输入新的密码:");
            String newPasswordAgain = sc.next();
            if (newPassword.equals(newPasswordAgain)) {
                list.get(index).setPassword(newPassword);
                System.out.println("密码修改成功!");
                break;
            } else {
                System.out.println("两次密码输入不一致,请重新输入!");
            }
        }
    }

    private static int findIndex(ArrayList<User> list, String username) {
        for (int i = 0; i < list.size(); i++) {
            if (username.equals(list.get(i).getUsername())) {
                return i;
            }
        }
        return -1;
    }

    private static void registerMethod(ArrayList<User> list) {
        String inputUsername;
        while (true) {
            System.out.println("请输入用户名:");
            inputUsername = sc.next();
            //开发过程中一般先校验格式,再校验唯一性
            //使用方法校验
            boolean flag1 = checkUsername(inputUsername);
            if (!flag1) {
                System.out.println("用户名不满足格式条件!请重新输入");
            }

            boolean flag2 = contains(list, inputUsername);
            if (flag2) {
                System.out.println("用户名已经存在,请重新输入");
            } else {
                break;
            }
        }
        String inputPassword1;
        while (true) {
            System.out.println("请输入你的密码:");
            inputPassword1 = sc.next();
            System.out.println("请再输入一次你的密码:");
            String inputPassword2 = sc.next();
            if (!inputPassword1.equals(inputPassword2)) {
                System.out.println("两次密码输入不一致,请重新输入!");
            } else {
                break;
            }
        }
        String idCard;
        while (true) {
            System.out.println("请输入身份证号码:");
            idCard = sc.next();
            boolean flag = checkIdCard(idCard);
            if (flag) {
                break;
            } else {
                System.out.println("身份证号码输入错误!请重新输入!");
            }
        }
        String inputPhoneNumber;
        while (true) {
            System.out.println("请输入手机号码:");
            inputPhoneNumber = sc.next();
            boolean flag = checkPhoneNumber(inputPhoneNumber);
            if (flag) {
                break;
            } else {
                System.out.println("手机号格式有误,请重新输入!");
            }
        }
        User user = new User(inputUsername, inputPassword1, idCard, inputPhoneNumber);
        list.add(user);
        System.out.println("注册成功!");
        printList(list);
    }

    private static void printList(ArrayList<User> list) {
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i));
        }
    }

    private static boolean checkPhoneNumber(String inputPhoneNumber) {
        int len = inputPhoneNumber.length();
        if (len != 11) {
            return false;
        }
        if (inputPhoneNumber.startsWith("0")) {
            return false;
        }
        for (int i = 0; i < len; i++) {
            char c = inputPhoneNumber.charAt(i);
            if (!(c >= '0' && c <= '9')) {
                return false;
            }
        }
        return true;
    }

    private static boolean checkIdCard(String idCard) {
        int len = idCard.length();
        if (len != 18) {
            return false;
        }
        if (idCard.startsWith("0")) {//startsWith 判断字符串开头字符是否为"0",返回布尔值
            return false;//如果是0,则返回false
        }
        for (int i = 0; i < len - 1; i++) {
            char c1 = idCard.charAt(i);
            if (!(c1 >= '0' && c1 <= '9')) {
                return false;
            }
        }
        char endChar = idCard.charAt(len - 1);
        if (endChar == 'x' || endChar == 'X' || endChar >= '0' && endChar <= '9') {
            return true;
        } else {
            return false;
        }
    }

    private static boolean contains(ArrayList<User> list, String inputUsername) {
        for (int i = 0; i < list.size(); i++) {
            if (inputUsername.equals(list.get(i).getUsername())) {
                return true;
            }
        }
        return false;
    }

    private static boolean checkUsername(String username) {
        int len = username.length();
        if (len < 3 || len > 15) {
            return false;
        }
        //当代码执行到这里,说明username的长度符合要求,继续判断
        for (int i = 0; i < len; i++) {
            char c = username.charAt(i);
            if (!(c >= 'a' && c <= 'z' || c >= 'A' && c <= 'Z' || c >= '0' && c <= '9'))
                return false;
        }
        int count = 0;
        for (int i = 0; i < len; i++) {
            char c = username.charAt(i);
            if (c >= 'a' && c <= 'z' || c >= 'A' && c <= 'Z') {
                count++;
                break;
            }
        }
        return count > 0;
    }


    private static void loginMethod(ArrayList<User> list) {
        for (int i = 0; i < 3; i++) {
            System.out.println("请输入用户名:");
            String inputUsername = sc.next();
            if (!contains(list, inputUsername)) {
                System.out.println("用户名尚未注册,请先注册!");
                return;
            }
            System.out.println("请输入密码:");
            String inputPassword = sc.next();
            while (true) {
                String rightCode = getCode();
                System.out.println("验证码为:" + rightCode);
                System.out.println("请输入验证码:");
                String inputCode = sc.next();
                if (inputCode.equalsIgnoreCase(rightCode)) {
                    break;
                } else {
                    System.out.println("验证码错误!请重新输入");
                }
            }
            //定义一个方法,验证用户名与密码是否正确
            User userInfo = new User(inputUsername, inputPassword, null, null);
            boolean result = checkUserInfo(userInfo, list);
            if (result) {
                System.out.println("登录成功!欢迎使用学生管理系统");
                StudentSystem.startStudentSystem();
                break;
            } else {
                System.out.println("用户名或密码错误,请重新登录!");
                if ((2 - i) == 0) {
                    break;
                }
                System.out.println("现在还剩下" + (2 - i) + "次机会");
            }
        }
    }

    private static boolean checkUserInfo(User userInfo, ArrayList<User> list) {
        for (int i = 0; i < list.size(); i++) {
            if (userInfo.getUsername().equals(list.get(i).getUsername()) && userInfo.getPassword().equals(list.get(i).getPassword())) {
                return true;
            }
        }
        return false;
    }

    //生成一个验证码
    public static String getCode() {
        ArrayList<Character> list = new ArrayList<>();
        for (int i = 0; i < 26; i++) {
            list.add((char) ('a' + i));
            list.add((char) ('A' + i));
        }
        StringBuilder sb = new StringBuilder();
        Random r = new Random();
        for (int i = 0; i < 4; i++) {
            int index = r.nextInt(list.size());
            char c = list.get(index);
            sb.append(c);
        }
        int number = r.nextInt(10);
        sb.append(number);
        char[] arr = sb.toString().toCharArray();
        int len = arr.length;
        int randomIndex = r.nextInt(len);
        char temp = arr[randomIndex];
        arr[randomIndex] = arr[len - 1];
        arr[len - 1] = temp;
        String code = "";
        for (int i = 0; i < len; i++) {
            code += arr[i];
        }
        return code;
    }
}