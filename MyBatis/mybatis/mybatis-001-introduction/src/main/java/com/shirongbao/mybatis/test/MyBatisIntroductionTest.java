package com.shirongbao.mybatis.test;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

public class MyBatisIntroductionTest {
    public static void main(String[] args) throws IOException {
        // 第一步获取SqlSessionFactoryBuilder对象
        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
        // 第二步获取SqlSessionFactory对象
        // InputStream is = Resources.getResourceAsStream("mybatis-config.xml");// 该方法默认从类的根路径下开始查找资源
        // 一般情况一个数据库一个SqlSessionFactory对象
        SqlSessionFactory factory = builder.build(Resources.getResourceAsStream("mybatis-config.xml"));

        // 第三步获取SqlSession对象
        SqlSession sqlSession = factory.openSession();
        // 第四步执行SQL语句
        int rows = sqlSession.insert("insertCar");// 该方法传入SQL语句的ID      返回影响数据库表的行数
        System.out.println(rows);

        // 由于sqlSession对象不支持自动提交,因此要手动提交
        sqlSession.commit();
    }
}
