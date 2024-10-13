package com.atguigu.service.impl;

import com.alibaba.csp.sentinel.util.StringUtil;
import com.alibaba.fastjson.JSON;
import com.atguigu.constant.SystemConstant;
import com.atguigu.entity.UserAccount;
import com.atguigu.entity.UserAccountDetail;
import com.atguigu.execption.GuiguException;
import com.atguigu.mapper.UserAccountMapper;
import com.atguigu.result.ResultCodeEnum;
import com.atguigu.result.RetVal;
import com.atguigu.service.UserAccountDetailService;
import com.atguigu.service.UserAccountService;
import com.atguigu.vo.AccountLockResultVo;
import com.atguigu.vo.AccountLockVo;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import jakarta.annotation.Resource;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.concurrent.TimeUnit;

/**
 * <p>
 * 用户账户 服务实现类
 * </p>
 *
 * @author 强哥
 * @since 2023-12-16
 */
@Service
public class UserAccountServiceImpl extends ServiceImpl<UserAccountMapper, UserAccount> implements UserAccountService {

    @Resource
    private UserAccountDetailService userAccountDetailService;

    @Resource
    private RedisTemplate<String, String> redisTemplate;

    @Override
    @Transactional
    public RetVal<Object> checkAndLock(AccountLockVo accountLockVo) {
        String orderNo = accountLockVo.getOrderNo();
        String checkAndLockKey = "check:lock:" + orderNo;
        String lockAccountKey = "lock:account:" + orderNo;
        // 查询这个订单号是否已经存在redis
        Boolean isExist = redisTemplate.opsForValue().setIfAbsent(checkAndLockKey, orderNo, 20, TimeUnit.SECONDS);
        // 再次做重复提交订单校验
        if (Boolean.FALSE.equals(isExist)) {
            // 如果存在重复提交，从redis中获取数据直接返回就可以
            String data = redisTemplate.opsForValue().get(lockAccountKey);
            if (StringUtil.isNotBlank(data)) {
                AccountLockResultVo accountLockResultVo = JSON.parseObject(data, AccountLockResultVo.class);
                return RetVal.ok(accountLockResultVo);
            } else {
                return RetVal.build(null, ResultCodeEnum.ACCOUNT_LOCK_REPEAT);
            }
        }
        BigDecimal amount = accountLockVo.getAmount();
        Long userId = accountLockVo.getUserId();
        // 查询用户账户表，将用户账户基本信息查出来
        UserAccount userAccount = baseMapper.checkUserAccount(userId, amount);
        if (userAccount == null) {
            // 没查到这个用户，或者金额不够，那么就抛出异常，金额不够，同时删除校验重复提交订单的锁
            redisTemplate.delete(checkAndLockKey);
            return RetVal.build(null, ResultCodeEnum.ACCOUNT_BALANCES_NOT_ENOUGH);
        }
        // 更新用户余额表，将锁定余额确定
        Integer row = baseMapper.lockUserAccount(userId, amount);
        // 如果没有成功锁定余额，也抛出异常
        if (row == 0) {
            // 同时也要删除校验重复提交订单的锁
            redisTemplate.delete(checkAndLockKey);
            return RetVal.build(null, ResultCodeEnum.ACCOUNT_LOCK_ERROR);
        }
        // 创建账户明细
        UserAccountDetail userAccountDetail = new UserAccountDetail();
        userAccountDetail.setUserId(userId);
        userAccountDetail.setTitle("锁定：" + accountLockVo.getContent());
        userAccountDetail.setTradeType(SystemConstant.ACCOUNT_TRADE_TYPE_LOCK);
        userAccountDetail.setAmount(amount);
        userAccountDetail.setOrderNo("lock:" + orderNo);
        userAccountDetailService.save(userAccountDetail);
        // 返回锁定对象
        AccountLockResultVo accountLockResultVo = new AccountLockResultVo();
        accountLockResultVo.setUserId(userId);
        accountLockResultVo.setAmount(amount);
        accountLockResultVo.setContent(accountLockVo.getContent());
        redisTemplate.opsForValue().set(lockAccountKey, JSON.toJSONString(accountLockResultVo), 30, TimeUnit.SECONDS);
        redisTemplate.delete(checkAndLockKey);
        return RetVal.ok(accountLockResultVo);
    }

    @Override
    public void deductLockAccount(String orderNo) {
        String deductAccountKey = "deduct:account:" + orderNo;
        String lockAccountKey = "lock:account:" + orderNo;
        // 加锁，防止重复消费
        Boolean isExist = redisTemplate.opsForValue().setIfAbsent(deductAccountKey, orderNo, 20, TimeUnit.SECONDS);
        if (Boolean.FALSE.equals(isExist)) return;
        String data = redisTemplate.opsForValue().get(lockAccountKey);
        if (StringUtil.isNotBlank(data)) {
            AccountLockResultVo accountLockResultVo = JSON.parseObject(data, AccountLockResultVo.class);
            // 解除锁定金额，也是修改用户金额表
            int rows = baseMapper.deductLockAccount(accountLockResultVo.getUserId(), accountLockResultVo.getAmount());
            if (rows == 0) {
                redisTemplate.delete(deductAccountKey);
                throw new GuiguException(ResultCodeEnum.ACCOUNT_MINUSLOCK_ERROR);
            }
            UserAccountDetail userAccountDetail = new UserAccountDetail();
            userAccountDetail.setUserId(accountLockResultVo.getUserId());
            userAccountDetail.setTitle("扣减：" + accountLockResultVo.getContent());
            userAccountDetail.setTradeType(SystemConstant.ACCOUNT_EXPENSE);
            userAccountDetail.setAmount(accountLockResultVo.getAmount());
            userAccountDetail.setOrderNo(orderNo);
            // 保存用户余额细节表
            userAccountDetailService.save(userAccountDetail);
            // 移除防止重复消费的锁
            redisTemplate.delete(deductAccountKey);
        }
    }

    @Override
    public void unlockAccount(String orderNo) {
        String unlockKey = "unlock:" + orderNo;
        String lockAccountKey = "lock:account:" + orderNo;
        // 加锁，防止重复消费
        Boolean isExist = redisTemplate.opsForValue().setIfAbsent(unlockKey, orderNo, 20, TimeUnit.SECONDS);
        if (Boolean.FALSE.equals(isExist)) return;
        String data = redisTemplate.opsForValue().get(lockAccountKey);
        if (StringUtil.isNotBlank(data)) {
            AccountLockResultVo accountLockResultVo = JSON.parseObject(data, AccountLockResultVo.class);
            // 发生异常解锁账户余额，余额变回锁定余额之前的
            int rows = baseMapper.unlockUserAccount(accountLockResultVo.getUserId(), accountLockResultVo.getAmount());
            if (rows == 0) {
                redisTemplate.delete(unlockKey);
                throw new GuiguException(ResultCodeEnum.ACCOUNT_UNLOCK_ERROR);
            }
            UserAccountDetail userAccountDetail = new UserAccountDetail();
            userAccountDetail.setUserId(accountLockResultVo.getUserId());
            userAccountDetail.setTitle("解锁：" + accountLockResultVo.getContent());
            userAccountDetail.setTradeType(SystemConstant.UNLOCK_ACCOUNT);
            userAccountDetail.setAmount(accountLockResultVo.getAmount());
            userAccountDetail.setOrderNo(orderNo);
            // 保存用户余额细节表
            userAccountDetailService.save(userAccountDetail);
            // 移除锁
            redisTemplate.delete(lockAccountKey);
        }
    }

}
