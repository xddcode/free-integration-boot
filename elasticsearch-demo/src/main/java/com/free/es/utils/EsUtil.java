package com.free.es.utils;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.json.JSONUtil;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.support.master.AcknowledgedResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.client.HttpAsyncResponseConsumerFactory;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.CreateIndexResponse;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.*;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Slf4j
@Component
@AllArgsConstructor
public class EsUtil<T> {

    private RestHighLevelClient client;

    private static final RequestOptions COMMON_OPTIONS;

    static {
        RequestOptions.Builder builder = RequestOptions.DEFAULT.toBuilder();
        // 默认缓冲限制为100MB，此处修改为30MB。
        builder.setHttpAsyncResponseConsumerFactory(new HttpAsyncResponseConsumerFactory.HeapBufferedResponseConsumerFactory(30 * 1024 * 1024));
        COMMON_OPTIONS = builder.build();
    }

    /**
     * 创建索引库
     *
     * @param index
     */
    public void createIndexRequest(String index) {
        CreateIndexRequest createIndexRequest = new CreateIndexRequest(index).settings(Settings.builder().put("index.number_of_shards", 3).put("index.number_of_replicas", 0));
        try {
            CreateIndexResponse response = client.indices().create(createIndexRequest, COMMON_OPTIONS);
            log.info(" 所有节点确认响应 : {}", response.isAcknowledged());
            log.info(" 所有分片的复制未超时 :{}", response.isShardsAcknowledged());
        } catch (IOException e) {
            log.error("创建索引库【{}】失败", index, e);
        }
    }

    /**
     * 删除索引库
     *
     * @param index
     */
    public void deleteIndexRequest(String index) {
        DeleteIndexRequest request = new DeleteIndexRequest(index);
        try {
            AcknowledgedResponse response = client.indices().delete(request, COMMON_OPTIONS);
            System.out.println(response.isAcknowledged());
        } catch (IOException e) {
            log.error("删除索引库【{}】失败", index, e);
        }
    }

    /**
     * 更新索引文档
     *
     * @param index
     * @param id
     * @param object
     */
    public void updateRequest(String index, String id, Object object) {
        UpdateRequest updateRequest = new UpdateRequest(index, id);
        updateRequest.doc(BeanUtil.beanToMap(object), XContentType.JSON);
        try {
            client.update(updateRequest, COMMON_OPTIONS);
        } catch (IOException e) {
            log.error("更新索引文档 {" + index + "} 数据 {" + object + "} 失败", e);
        }
    }

    /**
     * 新增索引文档
     *
     * @param index
     * @param id
     * @param object
     */
    public void insertRequest(String index, String id, Object object) {
        IndexRequest indexRequest = new IndexRequest(index).id(id).source(BeanUtil.beanToMap(object), XContentType.JSON);
        try {
            client.index(indexRequest, COMMON_OPTIONS);
        } catch (IOException e) {
            log.error("创建索引文档 {" + index + "} 数据 {" + object + "} 失败", e);
        }
    }

    /**
     * 删除索引文档
     *
     * @param index
     * @param id
     */
    public void deleteRequest(String index, String id) {
        DeleteRequest deleteRequest = new DeleteRequest(index, id);
        try {
            client.delete(deleteRequest, COMMON_OPTIONS);
        } catch (IOException e) {
            log.error("删除索引文档 {" + index + "} 数据id {" + id + "} 失败", e);
        }
    }

    /**
     * 搜索索引文档
     *
     * @param index
     * @param queryBuilder
     * @param sort
     */
    public SearchResponse search(String index, QueryBuilder queryBuilder) {
        SearchRequest searchRequest = new SearchRequest(index);
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.query(queryBuilder);
        //bool符合查询
        //BoolQueryBuilder boolQueryBuilder = new BoolQueryBuilder()
        //        .filter(QueryBuilders.matchQuery("name", "盖伦"))
        //        .must(QueryBuilders.matchQuery("desc", "部落"))
        //        .should(QueryBuilders.matchQuery("realName", "光辉"));

        //分页
        //searchSourceBuilder.from(1).size(2);
        // 排序
        searchSourceBuilder.sort("createTime", SortOrder.DESC);

        ////误拼写时的fuzzy模糊搜索方法 2表示允许的误差字符数
        //QueryBuilders.fuzzyQuery("title", "ceshi").fuzziness(Fuzziness.build("2"));
        searchRequest.source(searchSourceBuilder);
        System.out.println(searchSourceBuilder.toString());
        System.out.println(JSONUtil.parseObj(searchSourceBuilder.toString()).toStringPretty());
        SearchResponse searchResponse = null;
        try {
            searchResponse = client.search(searchRequest, COMMON_OPTIONS);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return searchResponse;
    }
}
