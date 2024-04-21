package com.shirongbao.mybatis.test;

import com.shirongbao.mybatis.mapper.CarMapper;
import com.shirongbao.mybatis.pojo.Car;
import com.shirongbao.mybatis.utils.SqlSessionUtil;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.util.List;

public class CarMapperTest {
    @Test
    public void testSelectAll(){
        SqlSession sqlSession = SqlSessionUtil.openSession();
        CarMapper mapper = sqlSession.getMapper(CarMapper.class);
        List<Car> cars = mapper.selectAll();
        cars.forEach(System.out::println);
        sqlSession.close();
    }
    @Test
    public void testDeleteByPrimaryKey(){
        SqlSession sqlSession = SqlSessionUtil.openSession();
        CarMapper mapper = sqlSession.getMapper(CarMapper.class);
        int rows = mapper.deleteByPrimaryKey(19L);
        System.out.println(rows);
        sqlSession.commit();
        sqlSession.close();
    }
}
