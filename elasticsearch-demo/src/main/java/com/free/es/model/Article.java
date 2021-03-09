package com.free.es.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Data
public class Article {

    private Long id;

    private String title;

    private String content;

    private String summary;

    private String author;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date createTime;

    public Article() {
    }

    public Article(Long id, String title, String content, String summary, String author, Date createTime) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.summary = summary;
        this.author = author;
        this.createTime = createTime;
    }

}
