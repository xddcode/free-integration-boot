package com.free.es.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.free.common.utils.PageResult;
import com.free.es.model.Article;
import com.free.es.model.vo.ArticleVO;

public interface ArticleService extends IService<Article> {

    /**
     * 搜索所有文章-es查询
     */
    PageResult<Article> findAll(ArticleVO articleVO);

    /**
     * 插入或修改文章
     */
    boolean saveOrUpdateArticle(Article article);

    /**
     * 删除文章
     */
    boolean deleteArticle(Long id);

}
