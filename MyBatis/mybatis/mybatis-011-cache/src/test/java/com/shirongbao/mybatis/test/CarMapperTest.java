package com.shirongbao.mybatis.test;

import com.shirongbao.mybatis.mapper.CarMapper;
import com.shirongbao.mybatis.pojo.Car;
import com.shirongbao.mybatis.utils.SqlSessionUtil;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

public class CarMapperTest {
    @Test
    public void testSelectById2() throws Exception {
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(Resources.getResourceAsStream("mybatis-config.xml"));
        SqlSession sqlSession1 = sqlSessionFactory.openSession();
        SqlSession sqlSession2 = sqlSessionFactory.openSession();

        CarMapper mapper1 = sqlSession1.getMapper(CarMapper.class);
        CarMapper mapper2 = sqlSession2.getMapper(CarMapper.class);

        Car car1 = mapper1.selectById2(20L);
        System.out.println(car1);
        sqlSession1.close();

        Car car2 = mapper2.selectById2(20L);
        System.out.println(car2);
        sqlSession2.close();
    }

    @Test
    public void testSelectById() {
        SqlSession sqlSession = SqlSessionUtil.openSession();
        CarMapper mapper = sqlSession.getMapper(CarMapper.class);
        Car car1 = mapper.selectById(19L);
        System.out.println(car1);

        Car car2 = mapper.selectById(19L);
        System.out.println(car2);
        sqlSession.close();
    }
}
