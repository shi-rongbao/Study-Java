package com.stuy.api.statement;

import java.sql.*;
import java.util.Properties;
import java.util.Scanner;

public class StatementUserLoginPart {
    /*
        模拟用户登录
            1.明确jdbc的使用流程和详细疆内内部设计api方法
            2.发现问题,引出preparedStatement

            1.输入账户和密码
            2.进行数据库信息查询(t_user)
            3.反馈登录成功还是失败

            1.键盘输入事件,收集账号和密码信息
            2.注册驱动
            3.获取连接
            4.创建statement对象
            5.发送查询SQL语句,并获取返回结果
            6.结果判断,显示登录成功还是失败
            7.关闭资源
     */

    public static void main(String[] args) throws SQLException, ClassNotFoundException {

        //1. 获取用户输入信息
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入账号:");
        String inputAccount = sc.nextLine();
        System.out.println("请输入密码:");
        String inputPassword = sc.nextLine();

        //2. 注册驱动
        /*
            使用DriverManager.registerDriver(new Driver());语句注册驱动会有一些问题
            该语句会注册两个驱动
            在Driver类中的静态代码块中,会调用一次DriverManager.registerDriver(new Driver())方法,注册一个驱动
            同时,在DriverManager.registerDriver()方法中也会注册一个驱动
            这样会注册两个驱动,浪费资源
            因此我们想只调用一次DriverManager.registerDriver()方法注册驱动
            这里我们想办法触发Driver类的静态代码块中的注册驱动
            如果直接使用new Driver(); 该语句可以触发静态代码块中的语句,但是不够好
            因为如果后期需要修改数据库软件,必须要修改源代码,同时创建其他的对象

            可以考虑使用反射来加载类
            使用Class.forName();该方法将类名以字符串的形式传递,后续可以将该字符串信息配置到配置文件中
            后期修改数据库软件时,可以只修改配置文件,而不用修改源码

         */

        //使用反射的方法加载类
        Class.forName("com.mysql.cj.jdbc.Driver");

        //3.获取数据库连接
        /*
            getConnection方法有三个重载的方法,分别对应三个参数

            核心属性:
                1.数据库软件所在的主机的ip地址:127.0.0.1
                2.数据库软件所在的主机的端口号:3306
                3.连接的具体数据库: atguigu
                4.连接的账号与密码: root 123456
                5.可选信息 这个例子中没有

            getConnection 的三个参数:
                String url: (其余全部填入第一个参数中)
                    语法:jdbc:数据库管理软件的名称[mysql,oracle]://ip地址:prot/数据库名?key = value & key = value 可选信息!
                    具体:jdbc:mysql://127.0.0.1:3306/atguigu
                        jdbc:mysql://localhost:3306/atguigu
                    本机的省略写法:如果你的数据库软件安装到本机,可以进行一些省略
                        //jdbc:mysql://127.0.0.1:3306/atguigu -> jdbc:mysql:///atguigu
                String user: root
                String password: 123456

            getConnection 的两个参数:
                String url : 与三个参数中的信息完全一致
                Properties info : 存放账号密码,将账号密码封装为Properties对象

            getConnection 的一个参数:
                将账户密码放入可选信息中
                String url : 数据库管理软件的名称[mysql,oracle]://ip地址:prot/数据库名?key = value & key = value 可选信息!
                jdbc:mysql://127.0.0.1:3306/atguigu?user=root&password=123456"
         */
        Connection connection1 = DriverManager.getConnection("jdbc:mysql:///atguigu", "root", "123456");

        Properties info = new Properties();
        info.put("user", "root");
        info.put("password", "123456");

        //Connection connection2 = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/atguigu", info);

        //Connection connection3 = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/atguigu?user=root&password=123456");

        //4. 创建发送SQL语句的statement对象
        Statement statement = connection1.createStatement();

        //5. 发送SQL语句并获取返回结果集
        String sql = "SELECT * FROM t_user WHERE account = '" + inputAccount + "' AND password = '" + inputPassword + "';";
        ResultSet resultSet = statement.executeQuery(sql);
        //int i = statement.executeUpdate(sql);

        //6. 查询结果解析
        while (resultSet.next()) {
            int id = resultSet.getInt(1);
            String account = resultSet.getString("account");
            String password = resultSet.getString("password");
            String nickname = resultSet.getString("nickname");
            System.out.println(id + " : " + account + " : " + password + " : " + nickname);
        }

        //7. 关闭资源

        resultSet.close();
        statement.close();
        connection1.close();
    }
}
