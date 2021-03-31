package com.free.es.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.free.es.model.Article;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ArticleMapper extends BaseMapper<Article> {
}
