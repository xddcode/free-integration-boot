package com.free.es.model;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.free.common.constant.EsIndexConstant;
import com.free.es.annotation.Document;
import com.free.es.annotation.EsId;
import com.free.es.annotation.Field;
import com.free.es.model.eunm.AnalyzerType;
import com.free.es.model.eunm.FieldType;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

@Data
@TableName("es_article")
@EqualsAndHashCode(callSuper = true)
@Document(index = EsIndexConstant.ARTICLE_INDEX, type = "")
public class Article extends Model<Article> {

    private static final long serialVersionUID = -9022654944696245392L;

    @EsId
    @Field(type = FieldType.KEYWORD)
    @TableId
    private Long id;

    @Field(type = FieldType.TEXT, analyzer = AnalyzerType.IK_SMART)
    private String title;

    @Field(type = FieldType.TEXT, analyzer = AnalyzerType.IK_SMART)
    private String content;

    @Field(type = FieldType.TEXT, analyzer = AnalyzerType.IK_SMART)
    private String summary;

    @Field(type = FieldType.TEXT, analyzer = AnalyzerType.IK_SMART)
    private String author;

    @Field(type = FieldType.INTEGER)
    private Integer isEnd;

    @Field(type = FieldType.INTEGER)
    private Integer is_publish;

    @Field(type = FieldType.DATE)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;

}
