package com.free.es.service;

import com.free.es.model.Article;

import java.util.List;

public interface ArticleService {

    Iterable<Article> findAll();

    void save(Article article);

    void save(List<Article> list);

    List<Article> findByAuthor(String text);

    List<Article> findByTitleOrContent(String text);
}
