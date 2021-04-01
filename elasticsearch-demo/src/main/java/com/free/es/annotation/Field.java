package com.free.es.annotation;

import com.free.es.model.eunm.AnalyzerType;
import com.free.es.model.eunm.FieldType;

import java.lang.annotation.*;

/**
 * 作用在字段上，用于定义类型，映射关系
 *
 * @author dinghao
 * @date 2021/4/1
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Documented
@Inherited
public @interface Field {

    FieldType type() default FieldType.TEXT;

    /**
     * 指定分词器
     *
     * @return
     */
    AnalyzerType analyzer() default AnalyzerType.STANDARD;

}
