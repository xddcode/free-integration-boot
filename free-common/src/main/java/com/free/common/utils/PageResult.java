package com.free.common.utils;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * 分页结果对象,这里以layui框架的table为标准
 *
 * @author dinghao
 * @date 2020/5/14
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PageResult<T> implements Serializable {

    private static final long serialVersionUID = -8010306148649509461L;
    private int code;

    private String msg;

    private long count;

    private long page;

    private long limit;

    private long current;

    private List<T> data;

}
