package com.atguigu.service;

import com.atguigu.entity.UserAccount;
import com.atguigu.result.RetVal;
import com.atguigu.vo.AccountLockVo;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 用户账户 服务类
 * </p>
 *
 * @author 强哥
 * @since 2023-12-16
 */
public interface UserAccountService extends IService<UserAccount> {

    /**
     * 锁定账户金额，先查询，如果够了就锁定，如果不够就返回金额不够
     *
     * @param accountLockVo 锁定账户需要的信息
     * @return 直接返回数据
     */
    RetVal<Object> checkAndLock(AccountLockVo accountLockVo);

    /**
     * 扣减锁定金额，根据orderNo锁定user_account表
     * @param orderNo orderNo
     */
    void deductLockAccount(String orderNo);

    /**
     * 发生异常解锁账户
     * @param orderNo orderNo
     */
    void unlockAccount(String orderNo);
}
