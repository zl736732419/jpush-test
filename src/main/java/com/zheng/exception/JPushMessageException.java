package com.zheng.exception;

import com.zheng.enums.ExceptionCodeEnum;

/**
 * JpushMessage对象参数异常
 * Created by zhenglian on 2017/6/6.
 */
public class JPushMessageException extends AbstractException {
    
    public JPushMessageException() {
        super(ExceptionCodeEnum.JPUSH_MESSAGE_INVALID_ERROR.getCode(), 
                ExceptionCodeEnum.JPUSH_MESSAGE_INVALID_ERROR.getName());
    }
    
    public JPushMessageException(String message) {
        super(ExceptionCodeEnum.JPUSH_MESSAGE_INVALID_ERROR.getCode(), message);
    }
    
}
