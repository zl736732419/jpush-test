package com.zheng.exception;

import com.zheng.enums.ExceptionCodeEnum;

/**
 * 平台终端设备异常
 * Created by zhenglian on 2017/6/7.
 */
public class JpushAudienceException extends AbstractException {
    public JpushAudienceException() {
        super(ExceptionCodeEnum.JPUSH_AUDIENCE_REQUIRED_ERROR.getCode(),
                ExceptionCodeEnum.JPUSH_AUDIENCE_REQUIRED_ERROR.getName());
    }
    
    public JpushAudienceException(String message) {
        super(ExceptionCodeEnum.JPUSH_AUDIENCE_REQUIRED_ERROR.getCode(), message);
    }
}
