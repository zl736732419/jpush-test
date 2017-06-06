package com.zheng.exception;

/**
 * Created by zhenglian on 2017/6/6.
 */
public abstract class AbstractException extends RuntimeException {
    protected String code;
    protected String message;

    public AbstractException(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
