package com.free.es.mapper;

import com.free.es.model.Article;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ArticleMapper extends CrudRepository<Article, Long> {

    List<Article> findByAuthor(String author);

    List<Article> findByTitleOrContent(String text);
}
