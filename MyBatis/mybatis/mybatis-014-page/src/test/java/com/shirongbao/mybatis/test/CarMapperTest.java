package com.shirongbao.mybatis.test;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.shirongbao.mybatis.mapper.CarMapper;
import com.shirongbao.mybatis.pojo.Car;
import com.shirongbao.mybatis.utils.SqlSessionUtil;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.util.List;

public class CarMapperTest {
    @Test
    public void testSelectAll() {
        SqlSession sqlSession = SqlSessionUtil.openSession();
        CarMapper mapper = sqlSession.getMapper(CarMapper.class);
        PageHelper.startPage(1, 3);
        List<Car> cars = mapper.selectAll();
//        cars.forEach(System.out::println);
        PageInfo<Car> carPageInfo = new PageInfo<>(cars, 3);
        System.out.println(carPageInfo);
        sqlSession.close();
    }

    @Test
    public void testSelectByPage() {
        SqlSession sqlSession = SqlSessionUtil.openSession();
        CarMapper mapper = sqlSession.getMapper(CarMapper.class);
        List<Car> cars = mapper.selectByPage(1, 2);
        cars.forEach(System.out::println);
        sqlSession.close();
    }
}
