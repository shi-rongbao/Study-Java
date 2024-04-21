package com.study.printstream;

import java.io.FileNotFoundException;
import java.io.PrintStream;

public class PrintStreamDemo1 {
    public static void main(String[] args) throws FileNotFoundException {
        PrintStream pr = new PrintStream("Study-028\\print.txt");

        pr.println("fuck");
        pr.println("my");
        pr.println("roommate");

        // System.out.close();
        System.out.println("fuck");
        pr.close();
    }
}
