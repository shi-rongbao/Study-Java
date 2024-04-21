package com.stuy.api.statement;

import com.mysql.cj.jdbc.Driver;

import java.sql.*;

public class StatementQueryPart {
    public static void main(String[] args) throws SQLException {
        // 注册驱动

        DriverManager.registerDriver(new Driver());

        // 获取连接

        /*
            Java程序,连接数据库,肯定是要调用某个方法,方法也需要填入连接数据库的基本信息:
                数据库ip地址 : 127.0.0.1
                数据库端口号 : 3306
                账户 :
                密码 :
                连接的数据库名称 : atguigu
         */

        // 参数一: url -> 数据库ip地址 : 127.0.0.1 格式: jdbc:数据库厂商名://ip地址:port/数据库名
        //                                            jdbc:mysql://127.0.0.1:3306/atguigu
        // 参数二: 账户 :
        // 参数三: 密码 :
        Connection connection =
                DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/atguigu", "root", "123456");

        // 创建statement
        Statement statement = connection.createStatement();

        // 发送sql语句,并且获取返回结果
        String sql = "SELECT * FROM t_user;";
        ResultSet resultSet = statement.executeQuery(sql);

        // 进行结果解析
        //
        while (resultSet.next()) {
            int id = resultSet.getInt("id");
            String account = resultSet.getString("account");
            String password = resultSet.getString("password");
            String nickname = resultSet.getString("nickname");
            System.out.println(id + " = " +account + password + nickname);
        }

        // 关闭资源
        resultSet.close();
        statement.close();
        connection.close();
    }
}
