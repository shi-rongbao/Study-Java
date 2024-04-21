package com.shirongbao.mybatis.test;

import com.shirongbao.mybatis.mapper.CarMapper;
import com.shirongbao.mybatis.pojo.Car;
import com.shirongbao.mybatis.utils.SqlSessionUtil;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.util.List;
import java.util.Map;

public class CarMapperTest {
    @Test
    public void testSelectTotal(){
        SqlSession sqlSession = SqlSessionUtil.openSession();
        CarMapper mapper = sqlSession.getMapper(CarMapper.class);
        Long total = mapper.selectTotal();
        System.out.println(total);
        sqlSession.close();
    }
    @Test
    public void testSelectAllByMapUnderscoreToCamelCase(){
        SqlSession sqlSession = SqlSessionUtil.openSession();
        CarMapper mapper = sqlSession.getMapper(CarMapper.class);
        List<Car> cars = mapper.selectAllByMapUnderscoreToCamelCase();
        cars.forEach(System.out::println);
        sqlSession.close();
    }
    @Test
    public void testSelectAllByResultMap(){
        SqlSession sqlSession = SqlSessionUtil.openSession();
        CarMapper mapper = sqlSession.getMapper(CarMapper.class);
        List<Car> cars = mapper.selectAllByResultMap();
        cars.forEach(System.out::println);
        sqlSession.close();
    }
    @Test
    public void testSelectAllRetMapMap() {
        SqlSession sqlSession = SqlSessionUtil.openSession();
        CarMapper mapper = sqlSession.getMapper(CarMapper.class);
        Map<Long, Map<Long, Object>> maps = mapper.selectAllRetMapMap();
        maps.entrySet().forEach(System.out::println);
        sqlSession.close();
    }

    @Test
    public void testSelectAllRetListMap() {
        SqlSession sqlSession = SqlSessionUtil.openSession();
        CarMapper mapper = sqlSession.getMapper(CarMapper.class);
        List<Map<String, Object>> list = mapper.selectAllRetListMap();
        list.forEach(map -> {
            map.entrySet().forEach(System.out::println);
            System.out.println();
        });
        sqlSession.close();
    }

    @Test
    public void testSelectByIdRetMap() {
        SqlSession sqlSession = SqlSessionUtil.openSession();
        CarMapper mapper = sqlSession.getMapper(CarMapper.class);
        Map<String, Object> carMap = mapper.selectByIdRetMap(2L);
        carMap.entrySet().forEach(System.out::println);
        sqlSession.close();
    }

    @Test
    public void testSelectAll() {
        SqlSession sqlSession = SqlSessionUtil.openSession();
        CarMapper mapper = sqlSession.getMapper(CarMapper.class);
        List<Car> cars = mapper.selectAll();
        cars.forEach(System.out::println);
        sqlSession.close();
    }

    @Test
    public void testSelectById() {
        SqlSession sqlSession = SqlSessionUtil.openSession();
        CarMapper mapper = sqlSession.getMapper(CarMapper.class);
        Car car = mapper.selectById(2L);
        System.out.println(car);
        sqlSession.close();
    }
}
