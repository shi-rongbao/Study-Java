package com.stuy.api.prepareedstatement;

import org.junit.Test;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;

public class PSCURDPart {
        /*
            使用preparedStatement 进行t_user表的curd动作
         */

    @Test
    public void testInsert() throws Exception {
        /*
            t_user 插入一条数据
            account test
            password test
            nickname 法克
         */

        // 注册驱动
        Class.forName("com.mysql.cj.jdbc.Driver");

        // 获取连接
        Connection connection = DriverManager.getConnection("jdbc:mysql:///atguigu", "root", "123456");

        //编写SQL语句
        String sql = "INSERT INTO t_user(account, password, nickname) VALUES(?, ?, ?);";

        //创建preparedStatement对象并传入SQL语句结果
        PreparedStatement preparedStatement = connection.prepareStatement(sql);

        //占位符赋值
        preparedStatement.setObject(1, "test");
        preparedStatement.setObject(2, "test");
        preparedStatement.setObject(3, "法克");

        //发送SQL语句
        int rows = preparedStatement.executeUpdate();

        //输出结果
        if (rows > 0) {
            System.out.println("插入成功");
        } else {
            System.out.println("插入失败");
        }

        //释放资源
        preparedStatement.close();
        connection.close();
    }

    @Test
    public void testUpdate() throws Exception {
        /*
            修改id = 4 的用户nickname 为 法克鱿
         */
        //注册驱动
        Class.forName("com.mysql.cj.jdbc.Driver");

        //获取连接
        Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/atguigu", "root", "123456");

        //编写SQL语句
        String sql = "UPDATE t_user SET nickname = ? WHERE ID = ?;";

        PreparedStatement preparedStatement = connection.prepareStatement(sql);

        preparedStatement.setObject(1, "爱拉乌呦");
        preparedStatement.setObject(2, 4);

        int rows = preparedStatement.executeUpdate();

        if (rows > 0) {
            System.out.println("修改成功");
        } else {
            System.out.println("修改失败");
        }

        //释放资源
        preparedStatement.close();
        connection.close();
    }

    @Test
    public void testDelete() throws Exception {
        /*
            删除id = 4 的用户数据
         */

        Class.forName("com.mysql.cj.jdbc.Driver");

        Connection connection = DriverManager.getConnection("jdbc:mysql:///atguigu", "root", "123456");

        String sql = "DELETE FROM t_user WHERE id = ?;";

        PreparedStatement preparedStatement = connection.prepareStatement(sql);

        preparedStatement.setObject(1, 4);

        int rows = preparedStatement.executeUpdate();
        if (rows > 0) {
            System.out.println("删除成功");
        } else {
            System.out.println("删除失败");
        }

        preparedStatement.close();
        connection.close();
    }

    @Test
    public void testSelect() throws Exception {

        ArrayList<HashMap<String, Object>> list = new ArrayList<>();

        Class.forName("com.mysql.cj.jdbc.Driver");

        Connection connection = DriverManager.getConnection("jdbc:mysql:///atguigu", "root", "123456");

        String sql = "SELECT * FROM t_user;";

        PreparedStatement preparedStatement = connection.prepareStatement(sql);

        ResultSet resultSet = preparedStatement.executeQuery();
        //MetaData方法装的是当前结果集列的信息
        //可以根据列的名称获取下角标,也可以获取列的数量
        ResultSetMetaData metaData = resultSet.getMetaData();
        int columnCount = metaData.getColumnCount();

        while (resultSet.next()) {
            HashMap<String, Object> hm = new HashMap<>();

            /* 不推荐使用硬编码的方式
            hm.put("id", resultSet.getInt("id") + "");
            hm.put("account", resultSet.getString("account"));
            hm.put("password", resultSet.getString("password"));
            hm.put("nickname", resultSet.getString("nickname"));*/

            // 自动遍历列, 注意: 要从1 开始
            for (int i = 1; i <= columnCount; i++) {
                //获取指定列的下角标的值
                Object value = resultSet.getObject(i);
                //获取指定列的下角标的Key
                //metaData.getColumnName() 只能获取列的名字,不能获取列的别名
                String key = metaData.getColumnLabel(i);
                hm.put(key, value);
            }

            list.add(hm);
        }

        System.out.println(list);

        resultSet.close();
        preparedStatement.close();
        connection.close();

    }
}
