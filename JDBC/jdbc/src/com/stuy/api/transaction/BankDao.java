package com.stuy.api.transaction;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class BankDao {

    //加钱的数据库操作方法
    public void add(String account, int money, Connection connection) throws Exception{

        String sql = "UPDATE t_bank set money = money + ? WHERE account = ?;";

        PreparedStatement preparedStatement = connection.prepareStatement(sql);

        preparedStatement.setObject(1, money);
        preparedStatement.setObject(2, account);

        preparedStatement.executeUpdate();

        preparedStatement.close();

        System.out.println("加钱成功");
    }

    //减钱的数据库操作方法
    public void sub(String account, int money, Connection connection) throws Exception{

        String sql = "UPDATE t_bank set money = money - ? WHERE account = ?;";

        PreparedStatement preparedStatement = connection.prepareStatement(sql);

        preparedStatement.setObject(1, money);
        preparedStatement.setObject(2, account);

        preparedStatement.executeUpdate();

        preparedStatement.close();

        System.out.println("减钱成功");
    }
}
