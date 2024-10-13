package com.hmdp.service.impl;

import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hmdp.dto.Result;
import com.hmdp.entity.ShopType;
import com.hmdp.mapper.ShopTypeMapper;
import com.hmdp.service.IShopTypeService;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

import static com.hmdp.utils.RedisConstants.CACHE_SHOP_TYPE_KEY;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author 虎哥
 * @since 2021-12-22
 */
@Service
public class ShopTypeServiceImpl extends ServiceImpl<ShopTypeMapper, ShopType> implements IShopTypeService {

    @Resource
    private ShopTypeMapper shopTypeMapper;

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @Override
    public Result queryAll() {
        // 1. 查询redis缓存中的所有店铺类型
        // opsForList.range方法，p1 传入key  p2 传入起始索引  p3 传入最终索引 0  -1 则获取所有数据
        List<String> cacheList = stringRedisTemplate.opsForList().range(CACHE_SHOP_TYPE_KEY, 0, -1);
        // 2. 判断是否存在
        if (cacheList.size() != 0 && cacheList != null) {
            // 3. 存在，直接返回
            // 将JSON转换为对象
            List<ShopType> typeList = new ArrayList<>();
            cacheList.forEach(e -> typeList.add(JSONUtil.toBean(e, ShopType.class)));
            return Result.ok(typeList);
        }
        // 4. 不存在，查询数据库中的所有店铺类型
        List<ShopType> typeList = shopTypeMapper.queryForList();
        // 5. 不存在，直接返回错误
        if (typeList == null || typeList.size() == 0) {
            return Result.fail("店铺类型不存在");
        }
        // 6. 存在，写会redis
        // 将typeList中的泛型转换为JSON
        List<String> typeListJson = new ArrayList<>();
        typeList.forEach(e -> typeListJson.add(JSONUtil.toJsonStr(e)));
        stringRedisTemplate.opsForList().leftPushAll(CACHE_SHOP_TYPE_KEY, typeListJson);
        // 7. 返回
        return Result.ok(typeListJson);
    }

}
