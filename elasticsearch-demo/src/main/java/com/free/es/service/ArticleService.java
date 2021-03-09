package com.free.es.service;

import com.free.es.model.Article;

import java.util.List;

public interface ArticleService {

    /**
     * 搜索所有文章
     */
    List<Article> findAll();

    /**
     * 批量插入文章
     */
    void saveBatch();

    /**
     * 根据作者名称条件搜索
     *
     * @param text 关键字
     */
    List<Article> findByAuthor(String text);

    /**
     * 多个字段匹配text
     *
     * @param text 关键字
     */
    List<Article> findMultiMatchQuery(String text);

    /**
     * 匹配多条件搜索
     *
     * @param text 关键字
     */
    List<Article> findByConditions(String text);

    /**
     * 删除索引库
     */
    void deleteIndex();
}
