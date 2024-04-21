package com.hmdp.service;

import com.hmdp.dto.Result;
import com.hmdp.entity.Shop;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 虎哥
 * @since 2021-12-22
 */
public interface IShopService extends IService<Shop> {

    /**
     * 根据商户id查询商户信息
     * @param id 要查询的商户id
     * @return 返回结果集
     */
    Result queryById(Long id);

    /**
     * 更新数据库中的数据
     * @param shop 要更新的商铺信息
     * @return 返回结果集
     */
    Result update(Shop shop);
}
