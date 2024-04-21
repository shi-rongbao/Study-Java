package com.shirongbao.mybatis.mapper;

import com.shirongbao.mybatis.pojo.Car;

import java.util.List;

public interface CarMapper {
    /**
     * 添加汽车信息
     * @param car 要添加的汽车对象
     * @return 返回影响数据库的行数
     */
    int insert(Car car);

    /**
     * 根据id删除Car
     * @param id 汽车的id
     * @return 返回影响数据库的行数
     */
    int deleteById(Long id);

    /**
     * 修改汽车信息
     * @param car 要被修改的汽车对象
     * @return 返回影响数据库的行数
     */
    int update(Car car);

    /**
     * 根据id查询汽车信息
     * @param id 要查询的汽车的ID
     * @return 返回被查询汽车的Car实例
     */
    Car selectById(Long id);

    /**
     * 查询所有的汽车信息
     * @return 将所有汽车对象放入List集合中返回
     */
    List<Car> selectAll();
}
