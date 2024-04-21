package com.shirongbao.mybatis.test;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class MyBatisCompleteTest {
    public static void main(String[] args) {
        SqlSession sqlSession = null;
        try {
            SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
            SqlSessionFactory factory = builder.build(Resources.getResourceAsStream("mybatis-config.xml"));
            // 开启会话,底层会开启事务
            sqlSession = factory.openSession();
            int rows = sqlSession.insert("insertCar");
            sqlSession.commit();
            System.out.println(rows);
        } catch (Exception e) {
            // 出现异常回滚
            if (sqlSession != null) {
                sqlSession.rollback();
            }
            e.printStackTrace();
        } finally {
            // 关闭会话
            if (sqlSession != null) {
                sqlSession.close();
            }
        }
    }
}
