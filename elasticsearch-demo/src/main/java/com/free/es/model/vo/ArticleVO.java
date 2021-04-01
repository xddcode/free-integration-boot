package com.free.es.model.vo;

import com.free.es.model.Article;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class ArticleVO extends Article {

    private static final long serialVersionUID = 4884856095789201798L;


    /**
     * 分页数
     */
    private Integer page;

    /**
     * 每页记录数
     */
    private Integer limit;

    /**
     * 开始时间
     */
    private String startTime;

    /**
     * 结束时间
     */
    private String endTime;

    /**
     * 关键字
     */
    private String keyword;

    /**
     * 排序方式
     */
    private String orderSort;
}
