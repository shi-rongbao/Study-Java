package com.atguigu.service;

import com.atguigu.entity.UserInfo;
import com.atguigu.vo.UserPaidRecordVo;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 用户 服务类
 * </p>
 *
 * @author ShiRongbao
 * @since 2024-05-13
 */
public interface UserInfoService extends IService<UserInfo> {

    /**
     * 根据专辑id，需要付费的声音id集合，查询出用户对该专辑声音中还需要付费的标识
     *
     * @param albumId            专辑id
     * @param needPayTrackIdList 需要付费的声音id集合
     * @return 返回一个map集合，key是声音的id，value是是否需要付费标识
     */
    Map<Long, Boolean> getUserShowPaidMarkOrNot(Long albumId, List<Long> needPayTrackIdList);

    /**
     * 根据用户支付记录，更新用户支付记录
     * @param userPaidRecordVo 用户支付记录
     */
    void updateUserPaidInfo(UserPaidRecordVo userPaidRecordVo);
}
