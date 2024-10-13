package com.study.exception;

public class ExceptionDemo2 {
    public static void main(String[] args) {
        /*
            public String getMessage()          返回此throwable的详细消息字符串
            public String toString()            返回此可抛出的简短描述
            public void printStackTrace()       把异常的错误信息输出在控制台
         */

        int[] arr = {1, 2, 3, 4, 5, 6};

        try {
            System.out.println(arr[10]);
        } catch (ArrayIndexOutOfBoundsException e) {
            e.printStackTrace();
        }

    }
}
