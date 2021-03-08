package com.free.es.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

@Data
@Document(indexName = "blog", type = "article")
public class Article {

    @Id
    private Long id;

    @Field(analyzer = "ik_smart", type = FieldType.Text)
    private String title;

    @Field(analyzer = "ik_smart", type = FieldType.Text)
    private String content;

    @Field(analyzer = "ik_smart", type = FieldType.Text)
    private String summary;

    @Field(analyzer = "ik_smart", type = FieldType.Text)
    private String author;

    public Article() {
    }

    public Article(Long id, String title, String content, String summary, String author) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.summary = summary;
        this.author = author;
    }

}
