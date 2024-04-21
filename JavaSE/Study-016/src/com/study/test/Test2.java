package com.study.test;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Test2 {
    public static void main(String[] args) {
        JFrame jf = new JFrame();
        jf.setSize(500, 500);
        jf.setTitle("事件监听");
        jf.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        jf.setLayout(null);
        jf.setLocationRelativeTo(null);

        JButton jb = new JButton("点我啊");
        jb.setBounds(0, 0, 100, 50);
        jb.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        System.out.println("我被点了");
                    }
                });
        jf.getContentPane().add(jb);

        jf.setVisible(true);
    }
}
