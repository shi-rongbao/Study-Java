package com.shirongbao.godbatis.test;

import com.shirongbao.godbatis.pojo.User;
import org.god.ibatis.core.SqlSession;
import org.god.ibatis.core.SqlSessionFactory;
import org.god.ibatis.core.SqlSessionFactoryBuilder;
import org.god.ibatis.utils.Resources;
import org.junit.Test;

public class UserMapperTest {
    @Test
    public void testInsertUser() {
        SqlSessionFactoryBuilder sqlSessionFactoryBuilder = new SqlSessionFactoryBuilder();
        SqlSessionFactory sqlSessionFactory = sqlSessionFactoryBuilder.build(Resources.getResourceAsStream("godbatis-config.xml"));
        SqlSession sqlSession = sqlSessionFactory.openSession();
        User user = new User("1002", "李四", "24");
        int rows = sqlSession.insert("fuckUser.insertUser", user);
        System.out.println(rows);
        sqlSession.commit();
        sqlSession.close();
    }

    @Test
    public void testSelectById() {
        SqlSessionFactoryBuilder sqlSessionFactoryBuilder = new SqlSessionFactoryBuilder();
        SqlSessionFactory sqlSessionFactory = sqlSessionFactoryBuilder.build(Resources.getResourceAsStream("godbatis-config.xml"));
        SqlSession sqlSession = sqlSessionFactory.openSession();
        User user = (User) sqlSession.selectOne("fuckUser.selectById", "1002");
        System.out.println(user);
        sqlSession.close();
    }
}
