package com.tingyu.kjyjcxt.common;

import lombok.Data;

/**
 * @Author essionshy
 * @Create 2021/7/7 22:37
 * @Version kjyjcxt
 */
@Data
public class PublicResult<T> {

    private int code;

    private String msg;

    private T data;

    public PublicResult(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public PublicResult(int code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public static PublicResult success() {
        return new PublicResult(200, "操作成功", null);
    }

    public static PublicResult success(Object obj) {
        return new PublicResult(200, "操作成功", obj);
    }


}
