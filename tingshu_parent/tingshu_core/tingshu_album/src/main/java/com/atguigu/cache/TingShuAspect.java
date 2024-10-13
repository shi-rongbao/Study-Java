package com.atguigu.cache;

import com.atguigu.util.SleepUtils;
import io.micrometer.common.util.StringUtils;
import jakarta.annotation.Resource;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.redisson.api.RBloomFilter;
import org.redisson.api.RedissonClient;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * @author ShiRongbao
 * @create 2024/6/10 13:58
 * @description:
 */
@Aspect
@Component
@Slf4j
public class TingShuAspect {

    @Resource
    private RedissonClient redissonClient;

    @Resource
    private RBloomFilter<Long> rBloomFilter;

    // 4.切面编程+双重检查+本地锁
    @SneakyThrows
    @Around("@annotation(com.atguigu.cache.TingShuCache)")
    public Object cacheAroundAdvice(ProceedingJoinPoint joinPoint) {
        // 1.拿到目标方法上面的参数
        Object[] methodParams = joinPoint.getArgs();
        // 2.拿到目标方法
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        // 3.拿到目标方法上面的注解
        TingShuCache tingShuCache = method.getAnnotation(TingShuCache.class);
        // 4.拿到TingShuCache注解上的值
        String prefix = tingShuCache.value();
        // 拼接redisKey
        Object firstParam = "";
        if (methodParams.length != 0) {
            firstParam = methodParams[0];
        }
        String cacheKey = prefix + ":" + firstParam;
        // 从redis中查询专辑信息
        Object redisObject = redisTemplate.opsForValue().get(cacheKey);
        // 设置锁的key
        String lockKey = "lock-" + firstParam;
        // 双重校验
        // 判断是否需要加锁
        if (redisObject == null) {
            synchronized (lockKey.intern()) {
                // 如果redis中没有数据
                // 判断是否需要从数据库中查询
                redisObject = redisTemplate.opsForValue().get(cacheKey);
                if (redisObject == null) {
                    // 判断是否要使用布隆过滤器
                    boolean enableBloom = tingShuCache.enableBloom();
                    Object objectDB = null;
                    if (enableBloom) {
                        // 先走布隆过滤器,如果数据库中存在这个id再查询数据库
                        boolean contains = rBloomFilter.contains(Long.valueOf(firstParam.toString()));
                        if (contains) {
                            // 从数据库中查询
                            objectDB = joinPoint.proceed();
                        } else {
                            log.error("布隆过滤器过滤掉此id");
                            return null;
                        }
                    } else {
                        // 从数据库中查询
                        objectDB = joinPoint.proceed();
                    }
                    // 将查到的数据放入redis中
                    log.info("更新缓存到redis");
                    redisTemplate.opsForValue().set(cacheKey, objectDB);
                    // 返回查询数据库的数据
                    return objectDB;
                }
                log.info("是用缓存中的数据");
                return redisObject;
            }
        }
        // 如果redis中有数据，直接返回
        return redisObject;
    }

    // 3.切面编程+读写锁
    /*@SneakyThrows
    @Around("@annotation(com.atguigu.cache.TingShuCache)")
    public Object cacheAroundAdvice3(ProceedingJoinPoint joinPoint) {
        // 1.拿到目标方法上面的参数
        Object[] methodParams = joinPoint.getArgs();
        // 2.拿到目标方法
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        // 3.拿到目标方法上面的注解
        TingShuCache tingShuCache = method.getAnnotation(TingShuCache.class);
        // 4.拿到TingShuCache注解上的值
        String prefix = tingShuCache.value();
        // 拼接redisKey
        Object firstParam = methodParams[0];
        String cacheKey = prefix + ":" + firstParam;
        // 从redis中查询专辑信息
        Object redisObject = redisTemplate.opsForValue().get(cacheKey);
        // 设置锁的key
        String lockKey = "lock-" + firstParam;
        // 拿到读写锁
        RReadWriteLock readWriteLock = redissonClient.getReadWriteLock(lockKey);
        // 双重校验
        // 判断是否需要加锁
        // 如果redis中没有数据
        if (redisObject == null) {
            try {
                // 加读锁
                readWriteLock.readLock().lock();
                redisObject = redisTemplate.opsForValue().get(cacheKey);
                // 释放读锁
                readWriteLock.readLock().unlock();
                // 判断是否需要从数据库中查询
                if (redisObject == null) {
                    try {
                        // 加写锁
                        readWriteLock.writeLock().lock();
                        // 先走布隆过滤器,如果数据库中存在这个id再查询数据库
                        boolean contains = rBloomFilter.contains(Long.valueOf(firstParam.toString()));
                        if (contains) {
                            // 从数据库中查询
                            Object objectDB = joinPoint.proceed();
                            // 将查到的数据放入redis中
                            redisTemplate.opsForValue().set(cacheKey, objectDB);
                            // 返回查询数据库的数据
                            return objectDB;
                        }
                    } catch (Exception e) {
                        throw new RuntimeException("查询数据库时出现了异常，异常信息为：" + e);
                    } finally {
                        // 释放写锁
                        readWriteLock.writeLock().unlock();
                    }
                }
                // 上读锁
                readWriteLock.readLock().lock();
            } finally {
                // 最终释放读锁
                readWriteLock.readLock().unlock();
            }
        }
        // 如果redis中有数据，直接返回
        return redisObject;
    }*/

    /*if (redisObject == null) {
        try{
            // 上读锁
            readWriteLock.readLock().lock();
            // 从redis中获取数据
            redisObject = redisTemplate.opsForValue().get(cacheKey);
            // 释放读锁
            readWriteLock.readLock().unlock();
            // 如果redis中没有数据
            if (redisObject == null) {
                // 上写锁
                readWriteLock.writeLock().lock();
                // 如果布隆过滤器中没有这个id
                boolean contains = rBloomFilter.contains(Long.valueOf(firstParam.toString()));
                // 执行从数据库中查询的方法
                Object objectDB = joinPoint.proceed();
                // 写入到redis中
                redisTemplate.opsForValue().set(cacheKey, objectDB);
                // 释放写锁
                readWriteLock.writeLock().unlock();
                // 上读锁
                readWriteLock.readLock().lock();
                // 返回查到的数据
                return objectDB;
            }
        } finally {
            // 释放读锁
            readWriteLock.readLock().unlock();
        }
    }*/


    // 2.切面编程+redisson+分布式+双重检查
    /*@SneakyThrows
    @Around("@annotation(com.atguigu.cache.TingShuCache)")
    public Object cacheAroundAdvice(ProceedingJoinPoint joinPoint) {
        // 1.拿到目标方法上面的参数
        Object[] methodParams = joinPoint.getArgs();
        // 2.拿到目标方法
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        // 3.拿到目标方法上面的注解
        TingShuCache tingShuCache = method.getAnnotation(TingShuCache.class);
        // 4.拿到TingShuCache注解上的值
        String prefix = tingShuCache.value();
        // 拼接redisKey
        Object firstParam = methodParams[0];
        String cacheKey = prefix + ":" + firstParam;
        // 从redis中查询专辑信息
        Object redisObject = redisTemplate.opsForValue().get(cacheKey);
        // 双重校验
        // 判断是否需要加锁
        if (redisObject == null) {
            // 设置锁的key
            String lockKey = "lock-" + firstParam;
            RLock lock = redissonClient.getLock(lockKey);
            // 如果redis中没有数据
            // 判断是否需要从数据库中查询
            redisObject = redisTemplate.opsForValue().get(cacheKey);
            if (redisObject == null) {
                // 加锁
                lock.lock();
                try {
                    // 先走布隆过滤器,如果数据库中存在这个id再查询数据库
                    boolean contains = rBloomFilter.contains(Long.valueOf(firstParam.toString()));
                    if (contains) {
                        // 从数据库中查询
                        Object objectDB = joinPoint.proceed();
                        // 将查到的数据放入redis中
                        redisTemplate.opsForValue().set(cacheKey, objectDB);
                        // 返回查询数据库的数据
                        return objectDB;
                    }
                } catch (Exception e) {
                    throw new RuntimeException("查询数据库时出现了异常，异常信息为：" + e);
                } finally {
                    // 释放锁
                    lock.unlock();
                }
            }
        }
        // 如果redis中有数据，直接返回
        return redisObject;
    }*/


    // 1.切面编程+redis+分布式锁+threadLocal
    @Resource
    private RedisTemplate<String, Object> redisTemplate;
    private final ThreadLocal<String> threadLocal = new ThreadLocal<>();

    @SneakyThrows
    //@Around("@annotation(com.atguigu.cache.TingShuCache)")
    public Object cacheAroundAdvice1(ProceedingJoinPoint joinPoint) {
        // 1.拿到目标方法上面的参数
        Object[] methodParams = joinPoint.getArgs();
        // 2.拿到目标方法
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        // 3.拿到目标方法上面的注解
        TingShuCache tingShuCache = method.getAnnotation(TingShuCache.class);
        // 4.拿到TingShuCache注解上的值
        String prefix = tingShuCache.value();
        // 拼接redisKey
        Object firstParam = methodParams[0];
        String cacheKey = prefix + ":" + firstParam;
        // 从redis缓存中拿数据
        Object redisObject = redisTemplate.opsForValue().get(cacheKey);
        // 锁的key
        String lockKey = "lock-" + firstParam;
        if (redisObject == null) {
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
                Object objectDb = joinPoint.proceed();
                // 存入redis中
                redisTemplate.opsForValue().set(cacheKey, objectDb);
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
                return objectDb;
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
                return cacheAroundAdvice1(joinPoint);
            }
        }
        // 如果redis中存在数据，直接返回
        return redisObject;
    }
}
