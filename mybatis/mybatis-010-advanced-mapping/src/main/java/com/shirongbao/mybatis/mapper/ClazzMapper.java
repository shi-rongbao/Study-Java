package com.shirongbao.mybatis.mapper;

import com.shirongbao.mybatis.pojo.Clazz;

public interface ClazzMapper {

    /**
     * 分布查询第一步:根据班级编号获取班级信息
     * @param cid 班级编号
     * @return 班级信息
     */
    Clazz selectByStep1(Integer cid);

    /**
     * 根据班级编号查询班级信息.
     * @param cid 班级编号
     * @return 班级对象
     */
    Clazz selectByCollection(Integer cid);

    /**
     * 分布查询第二步,根据cid获取班级信息
     * @param cid 班级id
     * @return 班级对象
     */
    Clazz selectByIdStep2(Integer cid);
}
