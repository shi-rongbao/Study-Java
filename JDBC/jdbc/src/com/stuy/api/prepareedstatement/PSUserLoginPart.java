package com.stuy.api.prepareedstatement;

import java.sql.*;
import java.util.Scanner;

public class PSUserLoginPart {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        // 因为直接使用Statement类在使用动态值语句时可能发生注入攻击,因此改使用PreparedStatement类
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入账号:");
        String inputAccount = sc.nextLine();
        System.out.println("请输入密码:");
        String inputPassword = sc.nextLine();

        // 1. 注册驱动
        Class.forName("com.mysql.cj.jdbc.Driver");

        // 2. 获取连接
        Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/atguigu", "root", "123456");

        // 3.编写SQL语句
        String sql = "SELECT * FROM t_user WHERE account = ? AND password = ?;";

        // 4.创建预编译的statement并设置SQL语句结果

        PreparedStatement preparedStatement = connection.prepareStatement(sql);

        // 5.单独的给占位符进行赋值
        preparedStatement.setObject(1, inputAccount);
        preparedStatement.setObject(2, inputPassword);

        // 6.发送SQL语句并返回结果
        ResultSet resultSet = preparedStatement.executeQuery();

        // 7.结果集解析
        // 因为在这个示例中只有一条语句, 因此使用if语句

        if (resultSet.next()){
            System.out.println("登录成功");
        }else {
            System.out.println("登录失败");
        }

        // 8.关闭资源
        resultSet.close();
        preparedStatement.close();
        connection.close();
    }
}
