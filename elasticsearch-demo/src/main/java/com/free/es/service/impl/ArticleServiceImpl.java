package com.free.es.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.free.common.utils.CodeEnum;
import com.free.common.utils.PageResult;
import com.free.es.mapper.ArticleMapper;
import com.free.es.model.Article;
import com.free.es.model.vo.ArticleVO;
import com.free.es.service.ArticleService;
import com.free.es.utils.EsUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.sort.FieldSortBuilder;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.stereotype.Service;

import java.util.*;

@Slf4j
@Service
@RequiredArgsConstructor
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article> implements ArticleService {

    private final EsUtil esUtil;

    @Override
    public PageResult<Article> findAll(ArticleVO articleVO){
        SearchSourceBuilder searchSourceBuilder = getSearchSourceBuilder(articleVO);
        try {
            Page<Article> page = esUtil.search(searchSourceBuilder,articleVO.getPage(),articleVO.getLimit(),Article.class);
            return builderResult(page,page.getRecords());
        } catch (Exception e) {
            log.error("es 查询异常，开始使用数据库做查询，错误为 ：{}", e);
            Page<Article> page = new Page<>(articleVO.getPage(), articleVO.getLimit());
            List<Article> list = baseMapper.selectListFull(page,articleVO);
            return builderResult(page,list);
        }
    }
    private <T> PageResult<T> builderResult(Page<T> pages, List<T> list) {
        long total = pages.getTotal();
        long current = pages.getCurrent();
        long page = pages.getPages();
        long limit = pages.getSize();
        return PageResult.<T>builder()
                .current(current)
                .count(total)
                .page(page)
                .limit(limit)
                .data(list)
                .code(CodeEnum.SUCCESS.getCode())
                .msg("查询成功")
                .build();
    }

    /**
     * 拼接综合查询 查询条件
     * @return
     */
    private SearchSourceBuilder getSearchSourceBuilder(ArticleVO articleVO){
        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
        //分页
        if (articleVO.getPage() == null) {
            articleVO.setPage(1);
        }
        if (articleVO.getLimit() == null) {
            articleVO.setLimit(10);
        }
        sourceBuilder.from((articleVO.getPage() - 1) * articleVO.getLimit());
        sourceBuilder.size(articleVO.getLimit());

        // 符合条件查询
        BoolQueryBuilder boolBuilder = QueryBuilders.boolQuery();

        // 动态条件----keyword
        if (StringUtils.isNotEmpty(articleVO.getKeyword())) {
            boolBuilder.must(QueryBuilders.queryStringQuery(articleVO.getKeyword()));
        }
        // 拼接动态查询条件
        if (StringUtils.isNotEmpty(articleVO.getTitle())) {
            boolBuilder.must(QueryBuilders.termQuery("title", articleVO.getTitle()));
        }

        if (StringUtils.isNotEmpty(articleVO.getSummary())) {
            boolBuilder.must(QueryBuilders.termQuery("summary", articleVO.getSummary()));
        }

        if (StringUtils.isNotEmpty(articleVO.getContent())) {
            boolBuilder.must(QueryBuilders.termQuery("content", articleVO.getContent()));
        }
        if (articleVO.getIsEnd()!= null) {
            boolBuilder.must(QueryBuilders.termQuery("isEnd", articleVO.getIsEnd()));
        }
        if (articleVO.getIsPublish()!= null) {
            boolBuilder.must(QueryBuilders.termQuery("isPublish", articleVO.getIsPublish()));
        }
        sourceBuilder.query(boolBuilder);
        // 排序
        String esOrderField = "createTime";
        FieldSortBuilder fieldSortBuilder = new FieldSortBuilder(esOrderField);
        fieldSortBuilder = fieldSortBuilder.order("orderDesc".equals(articleVO.getOrderSort()) ? SortOrder.DESC : SortOrder.ASC);
        sourceBuilder.sort(fieldSortBuilder);
        return sourceBuilder;
    }

    @Override
    public boolean saveOrUpdateArticle(Article article) {
        article.setCreateTime(new Date());
        return super.saveOrUpdate(article);
    }


    @Override
    public boolean deleteArticle(Long id) {

        return baseMapper.deleteById(id) > 0;
    }

}
