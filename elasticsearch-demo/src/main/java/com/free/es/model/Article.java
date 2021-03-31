package com.free.es.model;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

@Data
@TableName("es_article")
@EqualsAndHashCode(callSuper = true)
public class Article extends Model<Article>{

    private static final long serialVersionUID = -9022654944696245392L;

    @TableId
    private Long id;

    private String title;

    private String content;

    private String summary;

    private String author;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;

}
