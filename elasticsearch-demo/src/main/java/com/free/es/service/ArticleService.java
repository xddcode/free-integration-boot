package com.free.es.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.free.es.model.Article;

import java.util.List;

public interface ArticleService extends IService<Article> {

    /**
     * 搜索所有文章-es查询
     */
    List<Article> findAll();

    /**
     * 插入或修改文章
     */
    boolean saveOrUpdateArticle(Article article);

    /**
     * 批量插入文章
     */
   // boolean saveBatch();

    /**
     * 删除文章
     */
    boolean deleteArticle(Long id);

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

}
