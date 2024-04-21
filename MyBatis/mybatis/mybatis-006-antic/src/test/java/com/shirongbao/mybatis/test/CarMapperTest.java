package com.shirongbao.mybatis.test;

import com.shirongbao.mybatis.mapper.CarMapper;
import com.shirongbao.mybatis.pojo.Car;
import com.shirongbao.mybatis.utils.SqlSessionUtil;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.util.List;

public class CarMapperTest {

    @Test
    public void testInsertCarUseGeneratedKeys() {
        SqlSession sqlSession = SqlSessionUtil.openSession();
        CarMapper mapper = sqlSession.getMapper(CarMapper.class);
        Car car = new Car(null, "1112", "特斯拉FuckS", 98.99, "2024-01-25", "新能源");
        int rows = mapper.insertCarUseGeneratedKeys(car);
        System.out.println(car.getId());
        System.out.println(rows);
        sqlSession.commit();
        sqlSession.close();
    }

    @Test
    public void testSelectByBrandLike() {
        SqlSession sqlSession = SqlSessionUtil.openSession();
        CarMapper mapper = sqlSession.getMapper(CarMapper.class);
        List<Car> cars = mapper.selectByBrandLike("特斯拉");
        cars.forEach(System.out::println);
        sqlSession.close();
    }

    @Test
    public void testDeleteBatch() {
        SqlSession sqlSession = SqlSessionUtil.openSession();
        CarMapper mapper = sqlSession.getMapper(CarMapper.class);
        int rows = mapper.deleteBatch("13,15,17");
        System.out.println(rows);
        sqlSession.commit();
        sqlSession.close();
    }

    @Test
    public void testSelectByCarType() {
        SqlSession sqlSession = SqlSessionUtil.openSession();
        CarMapper mapper = sqlSession.getMapper(CarMapper.class);
        List<Car> cars = mapper.selectByCarType("新能源");
        cars.forEach(System.out::println);
        sqlSession.close();
    }

    @Test
    public void testSelectAllByAscOrDesc() {
        SqlSession sqlSession = SqlSessionUtil.openSession();
        CarMapper mapper = sqlSession.getMapper(CarMapper.class);
        List<Car> cars = mapper.selectAllByAscOrDesc("DESC");
        cars.forEach(System.out::println);
        sqlSession.close();
    }
}
