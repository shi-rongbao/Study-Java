package com.atguigu.service.impl;

import com.atguigu.constant.SystemConstant;
import com.atguigu.entity.RechargeInfo;
import com.atguigu.entity.UserAccountDetail;
import com.atguigu.mapper.RechargeInfoMapper;
import com.atguigu.mapper.UserAccountMapper;
import com.atguigu.service.RechargeInfoService;
import com.atguigu.service.UserAccountDetailService;
import com.atguigu.util.AuthContextHolder;
import com.atguigu.vo.RechargeInfoVo;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * <p>
 * 充值信息 服务实现类
 * </p>
 *
 * @author 强哥
 * @since 2023-12-16
 */
@Service
public class RechargeInfoServiceImpl extends ServiceImpl<RechargeInfoMapper, RechargeInfo> implements RechargeInfoService {

}
