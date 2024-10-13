package com.atguigu.service.impl;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch._types.FieldValue;
import co.elastic.clients.elasticsearch._types.SortOrder;
import co.elastic.clients.elasticsearch._types.aggregations.Aggregate;
import co.elastic.clients.elasticsearch._types.aggregations.StringRareTermsAggregate;
import co.elastic.clients.elasticsearch._types.query_dsl.BoolQuery;
import co.elastic.clients.elasticsearch._types.query_dsl.NestedQuery;
import co.elastic.clients.elasticsearch._types.query_dsl.Query;
import co.elastic.clients.elasticsearch._types.query_dsl.TermsQueryField;
import co.elastic.clients.elasticsearch.core.SearchRequest;
import co.elastic.clients.elasticsearch.core.SearchResponse;
import co.elastic.clients.elasticsearch.core.search.Hit;
import co.elastic.clients.elasticsearch.core.search.Suggester;
import co.elastic.clients.elasticsearch.core.search.Suggestion;
import com.alibaba.fastjson.JSONObject;
import com.atguigu.AlbumFeignClient;
import com.atguigu.CategoryFeignClient;
import com.atguigu.UserFeignClient;
import com.atguigu.constant.RedisConstant;
import com.atguigu.entity.*;
import com.atguigu.query.AlbumIndexQuery;
import com.atguigu.repository.AlbumRepository;
import com.atguigu.repository.SuggestRepository;
import com.atguigu.service.SearchService;
import com.atguigu.util.PinYinUtils;
import com.atguigu.vo.AlbumInfoIndexVo;
import com.atguigu.vo.AlbumSearchResponseVo;
import com.atguigu.vo.AlbumStatVo;
import com.atguigu.vo.UserInfoVo;
import io.micrometer.common.util.StringUtils;
import jakarta.annotation.Resource;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.data.elasticsearch.core.suggest.Completion;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * @author ShiRongbao
 * @create 2024/7/16 23:44
 * @description:
 */
@Slf4j
@Service
public class SearchServiceImpl implements SearchService {

    @Resource
    private AlbumRepository albumRepository;

    @Resource
    private AlbumFeignClient albumFeignClient;

    @Resource
    private CategoryFeignClient categoryFeignClient;

    @Resource
    private UserFeignClient userFeignClient;

    @Resource
    private ElasticsearchClient elasticsearchClient;

    @Resource
    private SuggestRepository suggestRepository;

    @Resource
    private ThreadPoolExecutor threadPoolExecutor;

    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @Override
    public boolean onSaleAlbum(Long albumId) {
        AlbumInfoIndex albumInfoIndex = new AlbumInfoIndex();
        // 使用albumFeign调用album服务查询数据库
        AlbumInfo albumInfo = albumFeignClient.getAlbumInfoById(albumId).getData();
        if (albumInfo != null) {
            BeanUtils.copyProperties(albumInfo, albumInfoIndex);
            List<AlbumAttributeValue> albumPropertyValueList = albumFeignClient.getAlbumPropertyValue(albumId).getData();
            if (!CollectionUtils.isEmpty(albumPropertyValueList)) {
                List<AttributeValueIndex> attributeValueIndexList = albumPropertyValueList.stream().map(albumAttributeValue -> {
                    AttributeValueIndex attributeValueIndex = new AttributeValueIndex();
                    BeanUtils.copyProperties(albumAttributeValue, attributeValueIndex);
                    return attributeValueIndex;
                }).collect(Collectors.toList());
                albumInfoIndex.setAttributeValueIndexList(attributeValueIndexList);
            }
            // 根据三级分类id查询专辑的分类信息
            BaseCategoryView categoryView = categoryFeignClient.getCategoryView(albumInfo.getCategory3Id()).getData();
            if (categoryView != null) {
                albumInfoIndex.setCategory1Id(categoryView.getCategory1Id());
                albumInfoIndex.setCategory2Id(categoryView.getCategory2Id());
            }
            // 根据用户id查询用户信息
            UserInfoVo userInfo = userFeignClient.getUserInfo(albumInfo.getUserId()).getData();
            if (userInfo != null) {
                String announcerName = userInfo.getNickname();
                if (announcerName != null) {
                    albumInfoIndex.setAnnouncerName(announcerName);
                }
            }
            // 还需要查询统计表，这里不查了，模拟数据
            int num1 = new Random().nextInt(1000);
            int num2 = new Random().nextInt(100);
            int num3 = new Random().nextInt(50);
            int num4 = new Random().nextInt(300);
            albumInfoIndex.setPlayStatNum(num1);
            albumInfoIndex.setSubscribeStatNum(num2);
            albumInfoIndex.setBuyStatNum(num3);
            albumInfoIndex.setCommentStatNum(num4);
            // 计算热度公式，未实现
            double hotScore = num1 * 0.2 + num2 * 0.3 + num3 * 0.4 + num4 * 0.1;
            albumInfoIndex.setHotScore(hotScore);
            albumRepository.save(albumInfoIndex);
            // 专辑自动补全的索引
            SuggestIndex suggestIndex = new SuggestIndex();
            suggestIndex.setId(UUID.randomUUID().toString().replaceAll("-", ""));
            suggestIndex.setTitle(albumInfo.getAlbumTitle());
            suggestIndex.setKeyword(new Completion(new String[]{albumInfo.getAlbumTitle()}));
            suggestIndex.setKeywordPinyin(new Completion(new String[]{PinYinUtils.toHanyuPinyin(albumInfo.getAlbumTitle())}));
            suggestIndex.setKeywordSequence(new Completion(new String[]{PinYinUtils.getFirstLetter(albumInfo.getAlbumTitle())}));
            suggestRepository.save(suggestIndex);
            if (!StringUtils.isEmpty(albumInfoIndex.getAnnouncerName())) {
                SuggestIndex announcerSuggestIndex = new SuggestIndex();
                announcerSuggestIndex.setId(UUID.randomUUID().toString().replaceAll("-", ""));
                announcerSuggestIndex.setTitle(albumInfoIndex.getAnnouncerName());
                announcerSuggestIndex.setKeyword(new Completion(new String[]{albumInfoIndex.getAnnouncerName()}));
                announcerSuggestIndex.setKeywordPinyin(new Completion(new String[]{PinYinUtils.toHanyuPinyin(albumInfoIndex.getAnnouncerName())}));
                announcerSuggestIndex.setKeywordSequence(new Completion(new String[]{PinYinUtils.getFirstLetter(albumInfoIndex.getAnnouncerName())}));
                suggestRepository.save(announcerSuggestIndex);
            }
            return true;
        }
        return false;
    }

    @Override
    public boolean offSaleAlbum(Long albumId) {
        albumRepository.deleteById(albumId);
        return true;
    }

    @SneakyThrows
    @Override
    public List<Map<String, Object>> getChannelData(Long category1Id) {
        // 1.拿到三级分类信息
        List<BaseCategory3> category3List = categoryFeignClient.getCategory3ListByCategory1Id(category1Id).getData();
        // 2.建立三级分类id和三级分类对象的映射
        Map<Long, BaseCategory3> category3Map = category3List.stream()
                .collect(Collectors.toMap(BaseCategory3::getId, baseCategory3 -> baseCategory3));
        // 3.把三级分类id转换成List<FieldValue>
        List<FieldValue> category3FieldValue = category3List.stream().map(BaseCategory3::getId).map(FieldValue::of).toList();
        // 搜索ES语句
        SearchResponse<AlbumInfoIndex> response = elasticsearchClient.search(s -> s.index("albuminfo")
                        .query(q -> q.terms(t -> t.field("category3Id")
                                .terms(new TermsQueryField.Builder()
                                        .value(category3FieldValue).build())))
                        .aggregations("category3IdAgg", a -> a.terms(t -> t.field("category3Id"))
                                .aggregations("topSixHotScoreAgg", ag -> ag.topHits(t -> t.size(6)
                                        .sort(so -> so.field(f -> f.field("hotScore").order(SortOrder.Desc)))))),
                AlbumInfoIndex.class);
        // 4.解析数据放到对象中
        Aggregate category3IdAgg = response.aggregations().get("category3IdAgg");
        return category3IdAgg.lterms().buckets().array().stream().map(bucket -> {
            Long category3Id = bucket.key();
            Aggregate topSixHotScoreAgg = bucket.aggregations().get("topSixHotScoreAgg");
            List<AlbumInfoIndex> topAlbumIndexList = topSixHotScoreAgg.topHits()
                    .hits().hits()
                    .stream().map(hit -> {
                        assert hit.source() != null;
                        return JSONObject.parseObject(hit.source().toString(), AlbumInfoIndex.class);
                    }).toList();
            Map<String, Object> retMap = new HashMap<>();
            retMap.put("baseCategory3", category3Map.get(category3Id));
            retMap.put("list", topAlbumIndexList);
            return retMap;
        }).toList();
    }

    @SneakyThrows
    @Override
    public AlbumSearchResponseVo search(AlbumIndexQuery albumIndexQuery) {
        // 1.生成DSL语句
        SearchRequest request = buildQueryDsl(albumIndexQuery);
        // 2.实现对DSL语句的调用
        SearchResponse<AlbumInfoIndex> response = elasticsearchClient.search(request, AlbumInfoIndex.class);
        // 3.对结果进行解析
        AlbumSearchResponseVo responseVo = parseSearchResult(response);
        // 4.设置其他参数
        responseVo.setPageNo(albumIndexQuery.getPageNo());
        responseVo.setPageSize(albumIndexQuery.getPageSize());
        // 5.设置总页数
        long totalPages = (responseVo.getTotal() + responseVo.getPageSize() - 1) / responseVo.getPageSize();
        responseVo.setTotalPages(totalPages);
        return responseVo;
    }

    @SneakyThrows
    @Override
    public void updateRankingList() {
        List<BaseCategory1> category1List = categoryFeignClient.getCategory1().getData();
        if (!CollectionUtils.isEmpty(category1List)) {
            for (BaseCategory1 category1 : category1List) {
                String[] rankingTypeList = new String[]{"hotScore", "playStatNum", "subscribeStatNum", "buyStatNum", "commentStatNum"};
                for (String rankingType : rankingTypeList) {
                    SearchResponse<AlbumInfoIndex> response = elasticsearchClient
                            .search(s -> s
                                            .index("albuminfo")
                                            .query(q -> q.bool(b -> b.filter(f -> f.term(t -> t.field("category1Id").value(category1.getId())))))
                                            .size(10).sort(t -> t.field(f -> f.field(rankingType).order(SortOrder.Desc)))
                                    , AlbumInfoIndex.class);
                    // List<AlbumInfoIndex> albumList = new ArrayList<>();
                    List<AlbumInfoIndex> albumList = response.hits().hits().stream().map(Hit::source).collect(Collectors.toList());
                    redisTemplate.boundHashOps(RedisConstant.RANKING_KEY_PREFIX + category1.getId()).put(rankingType, albumList);
                    // String jsonString = JSONObject.toJSONString(albumList);
                    // stringRedisTemplate.opsForHash().put(RedisConstant.RANKING_KEY_PREFIX + category1.getId(), rankingType, jsonString);
                }
            }
        }
    }

    @SneakyThrows
    @Override
    public Set<String> autoCompleteSuggest(String keyword) {
        Suggester suggester = new Suggester.Builder()
                .suggesters("suggestionKeyword", s -> s
                        .prefix(keyword)
                        .completion(c -> c.field("keyword")))
                .suggesters("suggestionKeywordPinyin", s -> s
                        .prefix(keyword)
                        .completion(c -> c.field("keywordPinyin")))
                .suggesters("suggestionKeywordSequence", s -> s
                        .prefix(keyword)
                        .completion(c -> c.field("keywordSequence"))).build();
        log.info(suggester.toString());
        SearchResponse<SuggestIndex> suggestResponse = elasticsearchClient.search(s -> s.index("suggestinfo").suggest(suggester), SuggestIndex.class);
        // 解析自动补全结果
        Set<String> suggestTitleSet = analysisResponse(suggestResponse);
        // 如果自动补全结果有点少，那么根据关键词再搜索
        if (suggestTitleSet.size() < 5) {
            SearchResponse<SuggestIndex> searchResponse = elasticsearchClient.search(s -> s.index("suggestinfo").size(10)
                    .query(q -> q.match(m -> m.field("title").query(keyword))), SuggestIndex.class);
            List<Hit<SuggestIndex>> suggestHitList = searchResponse.hits().hits();
            for (Hit<SuggestIndex> suggestHit : suggestHitList) {
                if (suggestHit.source() != null) {
                    suggestTitleSet.add(suggestHit.source().getTitle());
                    // 自动补全不超过10个
                    if (suggestTitleSet.size() > 10) break;
                }
            }
        }
        return suggestTitleSet;
    }

    @Override
    public Map<String, Object> getAlbumDetail(Long albumId) {
        Map<String, Object> retMap = new HashMap<>();
        CompletableFuture<AlbumInfo> future = CompletableFuture.supplyAsync(() -> {
            // 专辑基本信息
            AlbumInfo albumInfo = albumFeignClient.getAlbumInfoById(albumId).getData();
            retMap.put("albumInfo", albumInfo);
            log.info(Thread.currentThread().getName());
            return albumInfo;
        }, threadPoolExecutor);
        CompletableFuture<Void> future1 = CompletableFuture.runAsync(() -> {
            // 获取专辑统计信息
            AlbumStatVo albumStatVo = albumFeignClient.getAlbumStatInfo(albumId);
            retMap.put("albumStatVo", albumStatVo);
            log.info(Thread.currentThread().getName());
        }, threadPoolExecutor);
        CompletableFuture<Void> future2 = future.thenAcceptAsync((albumInfo) -> {
            // 专辑分类信息
            BaseCategoryView categoryView = categoryFeignClient.getCategoryView(albumInfo.getCategory3Id()).getData();
            retMap.put("baseCategoryView", categoryView);
            log.info(Thread.currentThread().getName());
        }, threadPoolExecutor);
        CompletableFuture<Void> future3 = future.thenAcceptAsync((albumInfo) -> {
            // 用户基本信息
            UserInfoVo userInfoVo = userFeignClient.getUserInfo(albumInfo.getUserId()).getData();
            retMap.put("announcer", userInfoVo);
            log.info(Thread.currentThread().getName());
        }, threadPoolExecutor);
        // 等待所有的 CompletableFuture 完成
        CompletableFuture.allOf(future1, future2, future3).join();
        return retMap;
    }

    private Set<String> analysisResponse(SearchResponse<SuggestIndex> suggestResponse) {
        Set<String> suggestTitleSet = new HashSet<>();
        Map<String, List<Suggestion<SuggestIndex>>> suggestMap = suggestResponse.suggest();
        suggestMap.forEach((key, suggestValueList) -> suggestValueList.forEach(suggestValue -> {
            List<String> suggestTitleList = suggestValue.completion().options().stream().map(option -> {
                assert option.source() != null;
                return option.source().getTitle();
            }).toList();
            suggestTitleSet.addAll(suggestTitleList);
        }));
        return suggestTitleSet;
    }

    private AlbumSearchResponseVo parseSearchResult(SearchResponse<AlbumInfoIndex> response) {
        AlbumSearchResponseVo responseVo = new AlbumSearchResponseVo();
        // 获取总记录数
        assert response.hits().total() != null;
        responseVo.setTotal(response.hits().total().value());
        // 获取专辑列表信息
        List<Hit<AlbumInfoIndex>> searchAlbumInfoHitsList = response.hits().hits();
        List<AlbumInfoIndexVo> albumInfoIndexVoList = new ArrayList<>();
        for (Hit<AlbumInfoIndex> albumInfoIndexHit : searchAlbumInfoHitsList) {
            AlbumInfoIndexVo albumInfoIndexVo = new AlbumInfoIndexVo();
            assert albumInfoIndexHit.source() != null;
            BeanUtils.copyProperties(albumInfoIndexHit.source(), albumInfoIndexVo);
            List<String> albumTitleHigltList = albumInfoIndexHit.highlight().get("albumTitle");
            if (!CollectionUtils.isEmpty(albumTitleHigltList)) {
                albumInfoIndexVo.setAlbumTitle(albumTitleHigltList.get(0));
            }
            albumInfoIndexVoList.add(albumInfoIndexVo);
        }
        responseVo.setList(albumInfoIndexVoList);
        return responseVo;
    }

    private SearchRequest buildQueryDsl(AlbumIndexQuery albumIndexQuery) {
        // 构造一个bool
        BoolQuery.Builder boolQuery = new BoolQuery.Builder();
        String keyword = albumIndexQuery.getKeyword();
        // 构造should关键字查询
        if (!StringUtils.isEmpty(keyword)) {
            boolQuery.should(s -> s.match(m -> m.field("albumTitle").query(keyword)));
            boolQuery.should(s -> s.match(m -> m.field("albumIntro").query(keyword)));
            boolQuery.should(s -> s.match(m -> m.field("announcerName").query(keyword)));
        }
        // 根据一级分类查询
        Long category1Id = albumIndexQuery.getCategory1Id();
        if (category1Id != null) {
            boolQuery.filter(f -> f.term(t -> t.field("category1Id").value(category1Id)));
        }
        // 根据二级分类查询
        Long category2Id = albumIndexQuery.getCategory2Id();
        if (category2Id != null) {
            boolQuery.filter(f -> f.term(t -> t.field("category2Id").value(category2Id)));
        }
        // 根据一级分类查询
        Long category3Id = albumIndexQuery.getCategory3Id();
        if (category3Id != null) {
            boolQuery.filter(f -> f.term(t -> t.field("category3Id").value(category3Id)));
        }
        // 根据分类属性嵌套过滤
        List<String> propertyList = albumIndexQuery.getAttributeList();
        if (!CollectionUtils.isEmpty(propertyList)) {
            for (String property : propertyList) {
                String[] propertySplit = property.split(":");
                if (propertySplit.length == 2) {
                    Query nestedQuery = NestedQuery.of(o -> o.path("attributeValueIndexList")
                            .query(q -> q.bool(b -> b
                                    .must(m -> m.term(t -> t.field("attributeValueIndexList.attributeId").value(propertySplit[0])))
                                    .must(m -> m.term(t -> t.field("attributeValueIndexList.valueId").value(propertySplit[0])))
                            )))._toQuery();
                    boolQuery.filter(nestedQuery);
                }
            }
        }
        // 构造分页与高亮
        Query query = boolQuery.build()._toQuery();
        Integer pageNo = albumIndexQuery.getPageNo();
        Integer pageSize = albumIndexQuery.getPageSize();
        SearchRequest.Builder searchRequest = new SearchRequest.Builder().index("albuminfo")
                .query(query)
                .from((pageNo - 1) * pageSize)
                .size(pageSize)
                .highlight(h -> h.fields("albumTitle",
                        p -> p.preTags("<font color='red'>").postTags("</font>")));
        // 构造排序
        String order = albumIndexQuery.getOrder();
        if (!StringUtils.isEmpty(order)) {
            String[] orderSplit = order.split(":");
            String orderFiled = "";
            if (orderSplit.length == 2) {
                orderFiled = switch (orderSplit[0]) {
                    case "1" -> "hotScore";
                    case "2" -> "playStatNum";
                    case "3" -> "createTime";
                    default -> orderFiled;
                };
            }
            String sortType = orderSplit[1];
            String finalOrderFiled = orderFiled;
            searchRequest.sort(s -> s.field(f -> f.field(finalOrderFiled)
                    .order("asc".equals(sortType) ? SortOrder.Asc : SortOrder.Desc)));
        }

        return searchRequest.build();
    }

}
