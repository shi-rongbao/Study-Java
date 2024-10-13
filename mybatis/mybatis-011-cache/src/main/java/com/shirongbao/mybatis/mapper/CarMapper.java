package com.shirongbao.mybatis.mapper;

import com.shirongbao.mybatis.pojo.Car;

public interface CarMapper {

    /**
     * 测试二级缓存
     * @param id 汽车的id
     * @return 汽车对象
     */
    Car selectById2(Long id);


    /**
     * 根据id获取Car信息.
     *
     * @param id car的id
     * @return car对象
     */
    Car selectById(Long id);
}
