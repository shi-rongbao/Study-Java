package com.atguigu.controller;

import com.atguigu.constant.RedisConstant;
import com.atguigu.entity.AlbumInfoIndex;
import com.atguigu.query.AlbumIndexQuery;
import com.atguigu.result.RetVal;
import com.atguigu.service.SearchService;
import com.atguigu.vo.AlbumSearchResponseVo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author ShiRongbao
 * @create 2024/7/16 23:42
 * @description:
 */
@Tag(name = "搜索专辑管理")
@RestController
@RequestMapping("/api/search/albumInfo")
public class SearchController {

    @Resource
    private SearchService searchService;

    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @Operation(summary = "上架专辑")
    @GetMapping("/onSaleAlbum/{albumId}")
    public RetVal<String> onSaleAlbum(@PathVariable Long albumId) {
        boolean flag = searchService.onSaleAlbum(albumId);
        if (flag) {
            return RetVal.ok();
        } else {
            return RetVal.fail("上传失败");
        }
    }

    @Operation(summary = "下架专辑")
    @GetMapping("/offSaleAlbum/{albumId}")
    public RetVal<String> offSaleAlbum(@PathVariable Long albumId) {
        boolean flag = searchService.offSaleAlbum(albumId);
        if (flag) {
            return RetVal.ok();
        } else {
            return RetVal.fail("下架失败");
        }
    }

    @Operation(summary = "获取主频道数据")
    @GetMapping("/getChannelData/{category1Id}")
    public RetVal<List<Map<String, Object>>> getChannelData(@PathVariable Long category1Id) {
        return RetVal.ok(searchService.getChannelData(category1Id));
    }

    @Operation(summary = "专辑搜索")
    @PostMapping
    public RetVal<AlbumSearchResponseVo> search(@RequestBody AlbumIndexQuery albumIndexQuery) {
        AlbumSearchResponseVo albumSearchResponseVo = searchService.search(albumIndexQuery);
        return RetVal.ok(albumSearchResponseVo);
    }

    @Operation(summary = "关键字自动补全")
    @GetMapping("/autoCompleteSuggest/{keyword}")
    public RetVal<Set<String>> autoCompleteSuggest(@PathVariable String keyword) {
        Set<String> suggestTitleSet = searchService.autoCompleteSuggest(keyword);
        return RetVal.ok(suggestTitleSet);
    }


    @Operation(summary = "获取专辑详情")
    @GetMapping("/getAlbumDetail/{albumId}")
    public RetVal<Map<String, Object>> getAlbumDetail(@PathVariable Long albumId) {
        Map<String, Object> retMap = searchService.getAlbumDetail(albumId);
        return RetVal.ok(retMap);
    }

    @Operation(summary = "获取排行榜列表")
    @GetMapping("/getRankingList/{category1Id}/{rankingType}")
    public RetVal<List<AlbumInfoIndex>> getRankingList(@PathVariable Long category1Id, @PathVariable String rankingType) {
        List<AlbumInfoIndex> albumList = (List<AlbumInfoIndex>) redisTemplate.boundHashOps(RedisConstant.RANKING_KEY_PREFIX + category1Id).get(rankingType);
        return RetVal.ok(albumList);
    }


    /*
    @Operation(summary = "批量上架专辑")
    @GetMapping("/batchOnSaleAlbum")
    public String batchOnSaleAlbum() {
        for (long i = 1; i <= 1577; i++) {
            searchService.onSaleAlbum(i);
        }
        return "success";
    }
    */

    @Operation(summary = "更新排行榜")
    @GetMapping("/updateRankingList")
    public RetVal<Object> updateRankingList() {
        searchService.updateRankingList();
        return RetVal.ok();
    }

    /*@Operation(summary = "测试redis")
    @GetMapping("/testRedis")
    public RetVal testRedis() {
        List<Integer> list = List.of(1, 2, 3, 4, 5);
        redisTemplate.boundHashOps("123").put("ceshi", list);
        return RetVal.ok();
    }

    @Operation(summary = "测试redis2")
    @GetMapping("/testRedis2")
    public RetVal testRedis2() {
        redisTemplate.boundHashOps("123").get("ceshi");
        return RetVal.ok();
    }*/

}
