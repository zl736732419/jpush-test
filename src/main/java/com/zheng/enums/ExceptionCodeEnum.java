package com.zheng.enums;

/**
 * 异常枚举
 * Created by zhenglian on 2017/6/6.
 */
public enum ExceptionCodeEnum {
    /**
     * 平台参数异常
     */
    PLATFORM_REQUIRED_ERROR("1000", "终端平台必选"),
    /**
     * JPushParam参数异常
     */
    JPUSH_PARAM_REQUIRED_ERROR("1001", "JpushParam参数必选"),
    /**
     * JPushMessage对象异常
     */
    JPUSH_MESSAGE_INVALID_ERROR("1002", "JpushMessage对象不合法，至少需要消息或通知其中一项"),
    /**
     * JpushAudience终端平台异常
     */
    JPUSH_AUDIENCE_REQUIRED_ERROR("1003", "JpushAudience对象必选");

    private String code;
    private String name;

    private ExceptionCodeEnum(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
