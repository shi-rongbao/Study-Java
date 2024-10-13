package com.atguigu.service.impl;

import com.atguigu.entity.UserAccountDetail;
import com.atguigu.mapper.UserAccountDetailMapper;
import com.atguigu.service.UserAccountDetailService;
import com.atguigu.util.AuthContextHolder;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户账户明细 服务实现类
 * </p>
 *
 * @author 强哥
 * @since 2023-12-16
 */
@Service
public class UserAccountDetailServiceImpl extends ServiceImpl<UserAccountDetailMapper, UserAccountDetail> implements UserAccountDetailService {

}
