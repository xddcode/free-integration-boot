package com.free.common.utils;

/**
 * 全局返回码
 * @author dinghao
 * @date 2021/2/24
 */
public enum CodeEnum {
    /**成功返回码**/
    SUCCESS(200,""),
    //错误返回吗
    /**错误返回码**/
    ERROR(500,"");

    private Integer code;
    private String msg;

    CodeEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
