package com.stuy.api.transaction;

import org.junit.Test;

import java.sql.Connection;
import java.sql.DriverManager;

public class BankService {

    @Test
    public void testTransfer() throws Exception {
        /*
            由于加钱和减钱的操作不在同一个链接中,因此可能会出现一定的问题
            现在在业务端创建连接,保证了加钱与减钱在同一个链接中,避免了一定的问题
         */
        transfer("srb", "lvdandan", 1000);

    }

    //银行卡业务方法,调用dao方法
    public void transfer(String addCount, String subCount, int money) throws Exception {
        BankDao bk = new BankDao();

        Class.forName("com.mysql.cj.jdbc.Driver");

        Connection connection = DriverManager.getConnection("jdbc:mysql:///atguigu", "root", "123456");

        try {
            //开启事物,关闭自动提交后,在手动提交之前,将所有数据库动作全部放入到一个事物中,等待提交
            connection.setAutoCommit(false);

            bk.add(addCount, money, connection);
            System.out.println("=================");
            bk.sub(subCount, money, connection);
            //提交事物
            connection.commit();
        } catch (Exception e) {
            //如果报错,则回滚,不会执行数据库动作
            connection.rollback();

            throw e;
        } finally {
            connection.close();
        }


    }
}
