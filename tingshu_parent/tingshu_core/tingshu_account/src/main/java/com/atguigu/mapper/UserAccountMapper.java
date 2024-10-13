package com.atguigu.mapper;

import com.atguigu.entity.UserAccount;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;

/**
 * <p>
 * 用户账户 Mapper 接口
 * </p>
 *
 * @author 强哥
 * @since 2023-12-16
 */
public interface UserAccountMapper extends BaseMapper<UserAccount> {


    /**
     * 根据userId和amount查询用户余额是否充足
     * @param userId userId
     * @param amount amount
     * @return 返回一个UserAccount对象
     */
    UserAccount checkUserAccount(@Param("userId") Long userId, @Param("amount") BigDecimal amount);

    /**
     * 修改userAccount表，修改锁定金额
     * @param userId userId
     * @param amount amount
     * @return 返回更新后影响数据库的行数
     */
    Integer lockUserAccount(@Param("userId") Long userId, @Param("amount") BigDecimal amount);

    /**
     * 解除锁定金额，也是修改用户金额表
     * @param userId userId
     * @param amount amount
     * @return 返回影响数据库的行数
     */
    int deductLockAccount(@Param("userId") Long userId, @Param("amount") BigDecimal amount);

    /**
     * 发生异常解锁账户余额
     * @param userId userId
     * @param amount amount
     * @return 返回影响数据库的行数
     */
    int unlockUserAccount(@Param("userId") Long userId, @Param("amount") BigDecimal amount);
}
