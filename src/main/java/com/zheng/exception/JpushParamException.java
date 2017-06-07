package com.zheng.exception;

import com.zheng.enums.ExceptionCodeEnum;

/**
 * JPushParam异常
 * Created by zhenglian on 2017/6/6.
 */
public class JpushParamException extends AbstractException {
    
    public JpushParamException() {
        super(ExceptionCodeEnum.JPUSH_PARAM_REQUIRED_ERROR.getCode(),
                ExceptionCodeEnum.JPUSH_PARAM_REQUIRED_ERROR.getName());
    }
    
    public JpushParamException(String message) {
        super(ExceptionCodeEnum.JPUSH_PARAM_REQUIRED_ERROR.getCode(), message);
    }
}
