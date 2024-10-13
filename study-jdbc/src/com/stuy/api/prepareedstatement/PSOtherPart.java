package com.stuy.api.prepareedstatement;

import org.junit.Test;

import java.sql.*;

public class PSOtherPart {
    /*
        主键回显
        当往一个表中插入数据时,自增长的主键往往我们不进行操作,但是有必要获取到这个自增长的值
        这就上主键回显
     */

    @Test
    public void returnPrimaryKey() throws Exception {
        Class.forName("com.mysql.cj.jdbc.Driver");

        Connection connection = DriverManager.getConnection("jdbc:mysql:///atguigu", "root", "123456");

        String sql = "INSERT INTO t_user(account, password, nickname) VALUES (?, ?, ?);";

        PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

        preparedStatement.setObject(1, "srb");
        preparedStatement.setObject(2, "7447664226");
        preparedStatement.setObject(3, "丿时");


        int rows = preparedStatement.executeUpdate();

        if (rows > 0) {
            System.out.println("插入成功");
            //在这里可以获取回显的主键
            //获取装主键的结果集对象,对象是固定的一行一列
            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            //因为这里是插入成功的情况,因此一定存在一行的值,直接获取该行的主键就行
            generatedKeys.next();
            int id = generatedKeys.getInt(1);
            System.out.println("id = " + id);
        } else {
            System.out.println("插入失败");
        }

        preparedStatement.close();
        connection.close();
    }

    //使用普通的方式插入10000条数据
    @Test
    public void testInsert() throws Exception {
        long startTime = System.currentTimeMillis();

        Class.forName("com.mysql.cj.jdbc.Driver");

        Connection connection = DriverManager.getConnection("jdbc:mysql:///atguigu", "root", "123456");

        String sql = "INSERT INTO t_user(account, password, nickname) VALUES (?, ?, ?);";

        PreparedStatement preparedStatement = connection.prepareStatement(sql);


        for (int i = 0; i < 10000; i++) {
            preparedStatement.setObject(1, "srb" + i);
            preparedStatement.setObject(2, "7447664226" + i);
            preparedStatement.setObject(3, "丿时" + i);
            preparedStatement.executeUpdate();
        }

        preparedStatement.close();
        connection.close();

        long endTime = System.currentTimeMillis();
        System.out.println(endTime - startTime);
        //24267毫秒
    }

    //使用批量插入的方式插入10000条数据
    @Test
    public void testBatchInsert() throws Exception {
        long startTime = System.currentTimeMillis();

        Class.forName("com.mysql.cj.jdbc.Driver");

        Connection connection = DriverManager.getConnection("jdbc:mysql:///atguigu?rewriteBatchedStatements=true", "root", "123456");

        String sql = "INSERT INTO t_user(account, password, nickname) VALUES (?, ?, ?)";

        PreparedStatement preparedStatement = connection.prepareStatement(sql);


        for (int i = 0; i < 10000; i++) {
            preparedStatement.setObject(1, "srbdd" + i);
            preparedStatement.setObject(2, "7447664226dd" + i);
            preparedStatement.setObject(3, "丿时dd" + i);

            // 不直接执行,先添加到VALUES()后面
            preparedStatement.addBatch();
        }
        //全部追加完毕后执行
        preparedStatement.executeUpdate();

        preparedStatement.close();
        connection.close();

        long endTime = System.currentTimeMillis();
        System.out.println(endTime - startTime);
        //761毫秒
    }
}
