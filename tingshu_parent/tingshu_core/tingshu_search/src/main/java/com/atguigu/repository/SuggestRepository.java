package com.atguigu.repository;

import com.atguigu.entity.AlbumInfoIndex;
import com.atguigu.entity.SuggestIndex;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * @author ShiRongbao
 * @create 2024/7/16 23:45
 * @description:
 */

public interface SuggestRepository extends ElasticsearchRepository<SuggestIndex, Long> {
}
