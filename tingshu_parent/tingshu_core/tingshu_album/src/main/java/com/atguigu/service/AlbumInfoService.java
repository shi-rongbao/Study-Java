package com.atguigu.service;

import com.atguigu.entity.AlbumInfo;
import com.atguigu.vo.AlbumInfoVo;
import com.atguigu.vo.AlbumTempVo;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 专辑信息 服务类
 * </p>
 *
 * @author ShiRongbao
 * @since 2024-05-13
 */
public interface AlbumInfoService extends IService<AlbumInfo> {


    /**
     * 保存新的专辑
     * @param albumInfoVo 专辑信息
     * @return 保存成功返回true，否则返回false
     */
    Boolean saveAlbumInfo(AlbumInfoVo albumInfoVo);

    /**
     * 根据专辑id查询专辑信息
     * @param albumId 专辑id
     * @return 返回专辑信息
     */
    AlbumInfo getAlbumInfoById(Long albumId);

    /**
     * 根据传入的专辑信息更新专辑
     * @param albumInfo 专辑信息
     * @return 更新成功返回true，失败返回false
     */
    boolean updateAlbumInfo(AlbumInfo albumInfo);


    /**
     * 根据专辑id删除专辑信息
     * @param albumId 专辑id
     * @return 删除成功返回true，否则返回false
     */
    boolean deleteAlbumInfo(Long albumId);

    /**
     * 从MongoDB中查询是否订阅信息
     * @param albumId 专辑id
     * @return 订阅返回true，否则返回false
     */
    boolean isSubscribe(Long albumId);

    /**
     * 根据albumIdList查询albumTemp信息
     * @param albumIdList albumIdList
     * @return 返回查到的所有albumTemp信息，返回一个List集合
     */
    List<AlbumTempVo> getAlbumTempList(List<Long> albumIdList);
}
