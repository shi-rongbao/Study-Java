package com.shirongbao.cloud.service;

import com.shirongbao.cloud.entities.Pay;

import java.util.List;

/**
 * @author ShiRongbao
 * @create 2024/3/10 15:06
 * @description:
 */
public interface PayService {

    /**
     * 添加Pay
     * @param pay 要添加的数据
     * @return 返回影响数据库的行数
     */
    int add(Pay pay);

    /**
     * 根据主键id删除一条记录
     * @param id id
     * @return 返回影响数据库的行数
     */
    int delete(Integer id);

    /**
     * 更新pay
     * @param pay 要更新的数据
     * @return 返回影响数据库的行数
     */
    int update(Pay pay);

    /**
     * 根据主键id查询一条Pay记录
     * @param id id
     * @return 返回Pay对象
     */
    Pay getById(Integer id);

    /**
     * 查询所有的Pay放入集合返回
     * @return 装着所有的pay
     */
    List<Pay> getAll();
}
