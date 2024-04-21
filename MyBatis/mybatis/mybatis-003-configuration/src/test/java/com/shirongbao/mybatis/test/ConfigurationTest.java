package com.shirongbao.mybatis.test;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

public class ConfigurationTest {
    @Test
    public void testEnvironment() throws  Exception{
        SqlSessionFactoryBuilder sqlSessionFactoryBuilder = new SqlSessionFactoryBuilder();
        // 创建SqlSessionFactory对象,但是使用默认的环境
        SqlSessionFactory sqlSessionFactory = sqlSessionFactoryBuilder.build(Resources.getResourceAsStream("mybatis-config.xml"));
        SqlSession sqlSession = sqlSessionFactory.openSession();
        int rows = sqlSession.insert("fuck.insertCar");
        System.out.println(rows);
        sqlSession.commit();
        sqlSession.close();
        // 创建SqlSessionFactory对象,但是使用指定的环境environment id 来创建
        SqlSessionFactory sqlSessionFactory1 = sqlSessionFactoryBuilder.build(Resources.getResourceAsStream("mybatis-config.xml"),"mybatis2");
    }
}

