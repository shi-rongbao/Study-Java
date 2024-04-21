package com.shirongbao.mybatis.test;

import com.shirongbao.mybatis.mapper.CarMapper;
import com.shirongbao.mybatis.pojo.Car;
import com.shirongbao.mybatis.utils.SqlSessionUtil;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class CarMapperTest {
    @Test
    public void testDeleteByIds2(){
        SqlSession sqlSession = SqlSessionUtil.openSession();
        CarMapper mapper = sqlSession.getMapper(CarMapper.class);
        Long[] ids = {10L, 18L};
        int rows = mapper.deleteByIds2(ids);
        System.out.println(rows);
        sqlSession.commit();
        sqlSession.close();
    }
    @Test
    public void testInsertBatch() {
        SqlSession sqlSession = SqlSessionUtil.openSession();
        CarMapper mapper = sqlSession.getMapper(CarMapper.class);
        List<Car> cars = new ArrayList<>();
        cars.add(new Car(null, "1116", "特斯拉Fuck3", 29.3, "2024-01-26", "新能源"));
        cars.add(new Car(null, "1117", "宝马X5I", 56.9, "2024-01-16", "燃油车"));
        cars.add(new Car(null, "1118", "奔驰S530", 129.3, "2024-01-22", "燃油车"));
        cars.add(new Car(null, "1119", "保时捷918", 133.3, "2024-02-26", "燃油车"));
        int rows = mapper.insertBatch(cars);
        System.out.println(rows);
        sqlSession.commit();
        sqlSession.close();
    }

    @Test
    public void testDeleteByIds() {
        SqlSession sqlSession = SqlSessionUtil.openSession();
        CarMapper mapper = sqlSession.getMapper(CarMapper.class);
        Long[] ids = {2L, 4L};
        int rows = mapper.deleteByIds(ids);
        System.out.println(rows);
        sqlSession.commit();
        sqlSession.close();
    }

    @Test
    public void testSelectByChoose() {
        SqlSession sqlSession = SqlSessionUtil.openSession();
        CarMapper mapper = sqlSession.getMapper(CarMapper.class);
        List<Car> cars = mapper.selectByChoose("特斯拉", 10.0, "新能源");
        cars.forEach(System.out::println);
        sqlSession.close();
    }

    @Test
    public void testUpdateBySet() {
        SqlSession sqlSession = SqlSessionUtil.openSession();
        CarMapper mapper = sqlSession.getMapper(CarMapper.class);
        Car car = new Car(10L, null, "特斯拉FuckY", null, null, "新能源");
        mapper.updateBySet(car);
        sqlSession.commit();
        sqlSession.close();
    }

    @Test
    public void testUpdateById() {
        SqlSession sqlSession = SqlSessionUtil.openSession();
        CarMapper mapper = sqlSession.getMapper(CarMapper.class);
        Car car = new Car(10L, "1115", "特斯拉FuckY", 23.38, "2024-01-26", "新能源");
        int rows = mapper.updateById(car);
        System.out.println(rows);
        sqlSession.commit();
        sqlSession.close();
    }

    @Test
    public void testSelectByMultiConditionWithTrim() {
        SqlSession sqlSession = SqlSessionUtil.openSession();
        CarMapper mapper = sqlSession.getMapper(CarMapper.class);
        List<Car> cars = mapper.selectByMultiConditionWithTrim("特斯拉", null, "新能源");
        cars.forEach(System.out::println);
        sqlSession.close();
    }

    @Test
    public void testSelectByMultiConditionWithWhere() {
        SqlSession sqlSession = SqlSessionUtil.openSession();
        CarMapper mapper = sqlSession.getMapper(CarMapper.class);
        List<Car> cars = mapper.selectByMultiConditionWithWhere("", 10D, "新能源");
        cars.forEach(System.out::println);
        sqlSession.close();
    }

    @Test
    public void testSelectByMultiCondition() {
        SqlSession sqlSession = SqlSessionUtil.openSession();
        CarMapper mapper = sqlSession.getMapper(CarMapper.class);
        List<Car> cars = mapper.selectByMultiCondition("特斯拉", 10D, "新能源");
        cars.forEach(System.out::println);
        sqlSession.close();
    }
}
