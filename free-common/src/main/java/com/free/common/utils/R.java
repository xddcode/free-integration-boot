package com.free.common.utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 封装返回结果对象
 *
 * @author dinghao
 * @date 2021/2/24
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class R<T> implements Serializable {

    private static final long serialVersionUID = -7583882240838909179L;
    private T data;
    private Integer code;
    private String msg;

    public static <T> R<T> succeed(String msg) {
        return of(null, CodeEnum.SUCCESS.getCode(), msg);
    }

    public static <T> R<T> succeed(T model, String msg) {
        return of(model, CodeEnum.SUCCESS.getCode(), msg);
    }

    public static <T> R<T> succeed(T model, Integer code, String msg) {
        return of(model, code, msg);
    }

    public static <T> R<T> succeed(T model) {
        return of(model, CodeEnum.SUCCESS.getCode(), "");
    }

    public static <T> R<T> of(T datas, Integer code, String msg) {
        return new R<>(datas, code, msg);
    }


    public static <T> R<T> failed(String msg) {
        return of(null, CodeEnum.ERROR.getCode(), msg);
    }

    public static <T> R<T> failed(Integer code, String msg) {

        return of(null, code, msg);
    }

    public static <T> R<T> failed(T model, String msg) {
        return of(model, CodeEnum.ERROR.getCode(), msg);
    }
}
