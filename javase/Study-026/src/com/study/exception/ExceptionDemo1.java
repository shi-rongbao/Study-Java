package com.study.exception;

public class ExceptionDemo1 {
    public static void main(String[] args) {
        int[] arr = {1,2,3,4,5};
        try{
            arr[10] = 5;
            System.out.println(1/0);
            System.out.println(1);
        }catch(ArrayIndexOutOfBoundsException |ArithmeticException e){
            System.out.println("fuck");
        }
    }
}
