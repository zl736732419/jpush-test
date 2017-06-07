package com.zheng.exception;

import com.zheng.enums.ExceptionCodeEnum;

/**
 * 平台异常
 * Created by zhenglian on 2017/6/6.
 */
public class JpushPlatformParamException extends AbstractException {
    public JpushPlatformParamException() {
        super(ExceptionCodeEnum.PLATFORM_REQUIRED_ERROR.getCode(), ExceptionCodeEnum.PLATFORM_REQUIRED_ERROR.getName());
    }
    
    public JpushPlatformParamException(String message) {
        super(ExceptionCodeEnum.PLATFORM_REQUIRED_ERROR.getCode(), message);
    }
    
}
