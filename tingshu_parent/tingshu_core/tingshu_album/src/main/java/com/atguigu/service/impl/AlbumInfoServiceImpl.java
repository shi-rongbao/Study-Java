package com.atguigu.service.impl;

import com.atguigu.SearchFeignClient;
import com.atguigu.cache.TingShuCache;
import com.atguigu.constant.KafkaConstant;
import com.atguigu.constant.RedisConstant;
import com.atguigu.constant.SystemConstant;
import com.atguigu.entity.AlbumAttributeValue;
import com.atguigu.entity.AlbumInfo;
import com.atguigu.entity.AlbumStat;
import com.atguigu.mapper.AlbumInfoMapper;
import com.atguigu.service.AlbumAttributeValueService;
import com.atguigu.service.AlbumInfoService;
import com.atguigu.service.AlbumStatService;
import com.atguigu.service.KafkaService;
import com.atguigu.util.AuthContextHolder;
import com.atguigu.util.MongoUtil;
import com.atguigu.util.SleepUtils;
import com.atguigu.vo.AlbumInfoVo;
import com.atguigu.vo.AlbumTempVo;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mongodb.client.MongoClient;
import io.micrometer.common.util.StringUtils;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RBloomFilter;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.BeanUtils;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;


/**
 * <p>
 * 专辑信息 服务实现类
 * </p>
 *
 * @author ShiRongbao
 * @since 2024-05-13
 */
@Service
@Slf4j
public class AlbumInfoServiceImpl extends ServiceImpl<AlbumInfoMapper, AlbumInfo> implements AlbumInfoService {

    @Resource
    private AlbumAttributeValueService albumAttributeValueService;

    @Resource
    private AlbumStatService albumStatService;

    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    @Resource
    private RedissonClient redissonClient;

    @Resource
    private RBloomFilter<Long> rBloomFilter;

    @Resource
    private SearchFeignClient searchFeignClient;

    @Resource
    private KafkaService kafkaService;

    @Resource
    private MongoTemplate mongoTemplate;

    @Override
    @Transactional
    public Boolean saveAlbumInfo(AlbumInfoVo albumInfoVo) {
        /*初始化布隆过滤器
        for (long i = 0; i <= 1577; i++) {
            rBloomFilter.add(i);
        }*/
        // 创建AlbumInfo对象
        AlbumInfo albumInfo = new AlbumInfo();
        // 对象拷贝
        BeanUtils.copyProperties(albumInfoVo, albumInfo);
        // 获取当前UserId
        Long userId = AuthContextHolder.getUserId();
        // 赋值userId
        albumInfo.setUserId(userId);
        // 设置当前专辑状态
        albumInfo.setStatus(SystemConstant.ALBUM_APPROVED);
        // 设置VIP前5集免费
        if (!SystemConstant.FREE_ALBUM.equals(albumInfo.getPayType())) {
            albumInfo.setTracksForFree(5);
        }
        // 保存albumInfo
        boolean save = save(albumInfo);
        // 保存id到布隆过滤器中
        rBloomFilter.add(albumInfo.getId());
        // 保存专辑标签属性
        // 拿到标签集合
        List<AlbumAttributeValue> albumPropertyValueList = albumInfo.getAlbumPropertyValueList();
        boolean saveBatch = true;
        // 如果集合不为空
        if (!CollectionUtils.isEmpty(albumPropertyValueList)) {
            // 遍历所有的标签
            for (AlbumAttributeValue albumAttributeValue : albumPropertyValueList) {
                // 给标签设置专辑id
                // 方法开启事务，这里应当拿不到id
                albumAttributeValue.setAlbumId(albumInfo.getId());
                // 保存标签到数据库
                // 不要在for循环中操作数据库
                // albumAttributeValueService.save(albumAttributeValue);
            }
            // 批量保存
            saveBatch = albumAttributeValueService.saveBatch(albumPropertyValueList);
        }
        // 保存专辑的统计信息
        List<AlbumStat> albumStatList = buildAlbumStatData(albumInfo.getId());
        boolean saveBatchStat = albumStatService.saveBatch(albumStatList);
        // 如果公开专辑，调用searchFeign将专辑上传至ES
        if (SystemConstant.OPEN_ALBUM.equals(albumInfo.getIsOpen())) {
            // searchFeignClient.onSaleAlbum(albumInfo.getId());
            // 使用kafka
            kafkaService.sendMessage(KafkaConstant.ONSALE_ALBUM_QUEUE, String.valueOf(albumInfo.getId()));
        }
        return saveBatch && save && saveBatchStat;
    }

    @TingShuCache("albumInfo")
    @Override
    public AlbumInfo getAlbumInfoById(Long albumId) {
        return getAlbumInfoFromDb(albumId);
        // return getAlbumInfoFromRedis(albumId);
        // return getAlbumInfoFromRedisWithThreadLocal(albumId);
        // return getAlbumInfoFromRedisson(albumId);
    }

    private AlbumInfo getAlbumInfoFromRedisson(Long albumId) {
        // 拼接redisKey
        String cacheKey = RedisConstant.ALBUM_INFO_PREFIX + albumId;
        // 从redis中查询专辑信息
        AlbumInfo albumInfoRedis = (AlbumInfo) redisTemplate.opsForValue().get(cacheKey);
        // 设置锁的key
        String lockKey = "lock-" + albumId;
        RLock lock = redissonClient.getLock(lockKey);
        // 如果redis中没有数据
        if (albumInfoRedis == null) {
            // 加锁
            lock.lock();
            try {
                // 先走布隆过滤器,如果数据库中存在这个id再查询数据库
                boolean contains = rBloomFilter.contains(albumId);
                if (contains) {
                    // 从数据库中查询
                    AlbumInfo albumInfoFromDb = getAlbumInfoFromDb(albumId);
                    // 将查到的数据放入redis中
                    redisTemplate.opsForValue().set(cacheKey, albumInfoFromDb);
                    // 同时返回redis中的数据
                    return albumInfoFromDb;
                }
            } catch (Exception e) {
                throw new RuntimeException("查询数据库时出现了异常，异常信息为：" + e);
            } finally {
                // 释放锁
                lock.unlock();
            }
        }
        // 如果redis中有数据，直接返回
        return albumInfoRedis;
    }


    ThreadLocal<String> threadLocal = new ThreadLocal<>();

    private AlbumInfo getAlbumInfoFromRedisWithThreadLocal(Long albumId) {
        // 修改锁的粒度
        String lockKey = "lock-" + albumId;
        // 拼接redisKey
        String cacheKey = RedisConstant.ALBUM_INFO_PREFIX + albumId;
        // 从redis中查询专辑信息
        AlbumInfo albumInfoRedis = (AlbumInfo) redisTemplate.opsForValue().get(cacheKey);
        // 如果redis中不存在数据
        if (albumInfoRedis == null) {
            // 从threadLocal中拿token
            String token = threadLocal.get();
            boolean acquireLock = false;
            // 如果拿到了token
            if (StringUtils.isNotBlank(token)) {
                // 设置拿到锁的状态
                acquireLock = true;
            } else {
                // token 为空，生成新的token
                token = UUID.randomUUID().toString();
                acquireLock = Boolean.TRUE.equals(redisTemplate.opsForValue().setIfAbsent(lockKey, token, 3, TimeUnit.SECONDS));
            }
            // 如果拿到锁了
            if (acquireLock) {
                // 从数据库根据id查询albumInfo
                AlbumInfo albumInfoFromDb = getAlbumInfoFromDb(albumId);
                // 存入redis中
                redisTemplate.opsForValue().set(cacheKey, albumInfoFromDb);
                // lua脚本内容
                String luaScript = "if redis.call('get', KEYS[1]) == ARGV[1] then return redis.call('del', KEYS[1]) else return 0 end";
                // 创建DefaultRedisScript对象
                DefaultRedisScript<Long> redisScript = new DefaultRedisScript<>();
                // 设置lua脚本内容
                redisScript.setScriptText(luaScript);
                // 设置脚本内容返回值
                redisScript.setResultType(Long.class);
                // redis执行lua脚本
                redisTemplate.execute(redisScript, List.of(lockKey), token);
                // 清空threadLocal中的内容
                threadLocal.remove();
                return albumInfoFromDb;
            } else {
                // 如果没拿到锁，那么自旋
                while (true) {
                    // 先等待50毫秒
                    SleepUtils.millis(50);
                    // 再尝试获取锁
                    boolean retryAcquireLock = Boolean.TRUE.equals(redisTemplate.opsForValue().setIfAbsent(lockKey, token, 3, TimeUnit.SECONDS));
                    // 如果拿到锁了，设置锁
                    if (retryAcquireLock) {
                        threadLocal.set(token);
                        // 跳出循环
                        break;
                    }
                }
                // 执行程序
                return getAlbumInfoFromRedisWithThreadLocal(albumId);
            }
        }
        // 如果redis中存在数据，直接返回
        return albumInfoRedis;
    }

    private AlbumInfo getAlbumInfoFromRedis(Long albumId) {
        // 拼接redisKey
        String cacheKey = RedisConstant.ALBUM_INFO_PREFIX + albumId;
        // 从redis中查询专辑信息
        AlbumInfo albumInfoRedis = (AlbumInfo) redisTemplate.opsForValue().get(cacheKey);
        // 如果redis中不存在数据
        if (albumInfoRedis == null) {
            // 从数据库中查询
            AlbumInfo albumInfoFromDb = getAlbumInfoFromDb(albumId);
            // 将查到的数据放入redis中
            redisTemplate.opsForValue().set(cacheKey, albumInfoFromDb);
            // 同时返回redis中的数据
            return albumInfoFromDb;
        }
        // 如果redis中存在数据，直接返回
        return albumInfoRedis;
    }

    private AlbumInfo getAlbumInfoFromDb(Long albumId) {
        // 根据主键专辑id在专辑信息表中查询专辑信息
        AlbumInfo albumInfo = getById(albumId);
        // 创建过滤条件对象
        LambdaQueryWrapper<AlbumAttributeValue> wrapper = new LambdaQueryWrapper<>();
        // 设置过滤条件
        wrapper.eq(AlbumAttributeValue::getAlbumId, albumId);
        // 根据过滤条件查询专辑分类表
        List<AlbumAttributeValue> albumAttributeValueList = albumAttributeValueService.list(wrapper);
        // 设置专辑分类属性
        albumInfo.setAlbumPropertyValueList(albumAttributeValueList);
        return albumInfo;
    }

    @Override
    public boolean updateAlbumInfo(AlbumInfo albumInfo) {
        // 修改专辑基本信息
        boolean update = updateById(albumInfo);
        // 删除原来的专辑分类
        // 创建过滤条件对象
        LambdaQueryWrapper<AlbumAttributeValue> wrapper = new LambdaQueryWrapper<>();
        // 设置过滤条件
        wrapper.eq(AlbumAttributeValue::getAlbumId, albumInfo.getId());
        albumAttributeValueService.remove(wrapper);
        // 保存专辑标签属性
        // 拿到标签集合
        List<AlbumAttributeValue> albumPropertyValueList = albumInfo.getAlbumPropertyValueList();
        boolean saveBatch = true;
        // 如果集合不为空
        if (!CollectionUtils.isEmpty(albumPropertyValueList)) {
            // 遍历所有的标签
            for (AlbumAttributeValue albumAttributeValue : albumPropertyValueList) {
                // 给标签设置专辑id
                // 方法开启事务，这里应当拿不到id
                albumAttributeValue.setAlbumId(albumInfo.getId());
                // 保存标签到数据库
                // 不要在for循环中操作数据库
                // albumAttributeValueService.save(albumAttributeValue);
            }
            // 批量保存
            saveBatch = albumAttributeValueService.saveBatch(albumPropertyValueList);
        }
        // 如果公开专辑，调用searchFeign将专辑上传至ES
        if (SystemConstant.OPEN_ALBUM.equals(albumInfo.getIsOpen())) {
            kafkaService.sendMessage(KafkaConstant.ONSALE_ALBUM_QUEUE, String.valueOf(albumInfo.getId()));
        } else {
            // 如果改为私密，则将专辑从ES中移除
            kafkaService.sendMessage(KafkaConstant.OFFSALE_ALBUM_QUEUE, String.valueOf(albumInfo.getId()));
        }
        log.info("删除缓存，单删");
        String cacheKey = RedisConstant.ALBUM_INFO + albumInfo.getId();
        redisTemplate.delete(cacheKey);
        return update && saveBatch;
    }

    @Override
    public boolean deleteAlbumInfo(Long albumId) {
        // 删除专辑基本信息
        boolean delete = removeById(albumId);
        // 删除专辑分类信息
        // 创建过滤条件对象
        LambdaQueryWrapper<AlbumAttributeValue> wrapper = new LambdaQueryWrapper<>();
        // 设置过滤条件
        wrapper.eq(AlbumAttributeValue::getAlbumId, albumId);
        albumAttributeValueService.remove(wrapper);
        // 删除专辑统计信息
        boolean removeStat = albumStatService.remove(new LambdaQueryWrapper<AlbumStat>().eq(AlbumStat::getAlbumId, albumId));
        // 从ES中移除专辑信息
        kafkaService.sendMessage(KafkaConstant.OFFSALE_ALBUM_QUEUE, String.valueOf(albumId));
        return delete && removeStat;
    }

    @Override
    public boolean isSubscribe(Long albumId) {
        Long userId = AuthContextHolder.getUserId();
        Query query = Query.query(Criteria.where("userId").is(userId).and("albumId").is(albumId));
        long count = mongoTemplate.count(query, MongoUtil.getCollectionName(MongoUtil.MongoCollectionEnum.USER_SUBSCRIBE, userId));
        if (count > 0) return true;
        return false;
    }

    @Override
    public List<AlbumTempVo> getAlbumTempList(List<Long> albumIdList) {
        List<AlbumInfo> albumInfoList = listByIds(albumIdList);
        return albumInfoList.stream().map(albumInfo -> {
            AlbumTempVo albumTempVo = new AlbumTempVo();
            BeanUtils.copyProperties(albumInfo, albumTempVo);
            albumTempVo.setAlbumId(albumInfo.getId());
            return albumTempVo;
        }).toList();
    }

    // 初始化专辑状态
    private List<AlbumStat> buildAlbumStatData(Long albumId) {
        List<AlbumStat> albumStatList = new ArrayList<>();
        initAlbumStat(albumId, albumStatList, SystemConstant.PLAY_NUM_ALBUM);
        initAlbumStat(albumId, albumStatList, SystemConstant.SUBSCRIBE_NUM_ALBUM);
        initAlbumStat(albumId, albumStatList, SystemConstant.BUY_NUM_ALBUM);
        initAlbumStat(albumId, albumStatList, SystemConstant.COMMENT_NUM_ALBUM);
        return albumStatList;
    }

    private static void initAlbumStat(Long albumId, List<AlbumStat> albumStatList, String statType) {
        albumStatList.add(AlbumStat.builder()
                .albumId(albumId)
                .statType(statType)
                .statNum(0)
                .build());
    }
}
