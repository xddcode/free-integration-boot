package com.free.es.annotation;

import java.lang.annotation.*;

/**
 * 用于标识使用 该字段作为ES数据中的id
 *
 * @author dinghao
 * @date 2021/4/1
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Documented
@Inherited
public @interface EsId {
}

