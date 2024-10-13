package com.atguigu.service;

import com.atguigu.entity.AlbumInfoIndex;
import com.atguigu.query.AlbumIndexQuery;
import com.atguigu.vo.AlbumSearchResponseVo;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author ShiRongbao
 * @create 2024/7/16 23:44
 * @description:
 */
public interface SearchService {

    /**
     * 根据albumId上架专辑到ES中
     *
     * @param albumId albumId
     * @return 上传成功返回true，否则返回false
     */
    boolean onSaleAlbum(Long albumId);

    /**
     * 根据albumId从ES中下架专辑
     *
     * @param albumId albumId
     * @return 下架成功返回true，否则返回false
     */
    boolean offSaleAlbum(Long albumId);

    /**
     * 根据category1Id获取主频道数据
     *
     * @param category1Id category1Id
     * @return 返回一个List集合，里面装着所有数据，每个List中的数据是一个Map集合，Map集合中，第一关数据是category3的id，第二个数据是3id中的albumInfo信息
     */
    List<Map<String, Object>> getChannelData(Long category1Id);

    /**
     * 根据albumIndexQuery搜索条件，搜索专辑对象
     * @param albumIndexQuery 搜索条件
     * @return 查询到返回结果集，包含一个存储AlbumInfoIndexVo的List，总记录数，分页信息
     */
    AlbumSearchResponseVo search(AlbumIndexQuery albumIndexQuery);

    /**
     * 根据关键字自动补全搜索
     *
     * @param keyword 关键字
     * @return 返回装有标题的set集合
     */
    Set<String> autoCompleteSuggest(String keyword);

    /**
     * 根据专辑id查询专辑详情信息，包括专辑信息，用户基本信息
     *
     * @param albumId albumId
     * @return 拿到专辑各种信息，放入map中
     */
    Map<String, Object> getAlbumDetail(Long albumId);

    /**
     * 更新ES中排行榜信息
     */
    void updateRankingList();

}
