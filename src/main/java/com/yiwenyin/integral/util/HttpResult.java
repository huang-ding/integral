package com.yiwenyin.integral.util;

import lombok.Data;

/**
 * @author huangding
 * @description
 * @date 2018/11/2 15:14
 */
@Data
public class HttpResult {

    private int code;

    private String body;

    public HttpResult(int code, String body) {
        this.code = code;
        this.body = body;
    }
}
