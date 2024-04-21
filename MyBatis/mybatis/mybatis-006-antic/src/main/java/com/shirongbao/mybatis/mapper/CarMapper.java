package com.shirongbao.mybatis.mapper;

import com.shirongbao.mybatis.pojo.Car;

import java.util.List;

public interface CarMapper {

    int insertCarUseGeneratedKeys(Car car);

    List<Car> selectByBrandLike(String brand);

    int deleteBatch(String ids);

    List<Car> selectAllByAscOrDesc(String ascOrDesc);

    List<Car> selectByCarType(String carType);
}
