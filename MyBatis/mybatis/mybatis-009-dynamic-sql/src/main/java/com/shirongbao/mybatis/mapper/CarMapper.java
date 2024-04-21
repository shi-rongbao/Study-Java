package com.shirongbao.mybatis.mapper;

import com.shirongbao.mybatis.pojo.Car;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CarMapper {
    int deleteByIds2(@Param("ids") Long[] ids);
    int insertBatch(@Param("cars") List<Car> cars);
    int deleteByIds(@Param("ids") Long[] ids);
    List<Car> selectByChoose(@Param("brand") String brand, @Param("guidePrice") Double guidePrice, @Param("carType") String carType);

    int updateBySet(Car car);

    int updateById(Car car);

    List<Car> selectByMultiConditionWithTrim(@Param("brand") String brand, @Param("guidePrice") Double guidePrice, @Param("carType") String carType);

    List<Car> selectByMultiConditionWithWhere(@Param("brand") String brand, @Param("guidePrice") Double guidePrice, @Param("carType") String carType);

    List<Car> selectByMultiCondition(@Param("brand") String brand, @Param("guidePrice") Double guidePrice, @Param("carType") String carType);
}
