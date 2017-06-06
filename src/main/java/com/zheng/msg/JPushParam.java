package com.zheng.msg;

import com.google.common.base.Throwables;
import com.zheng.domain.JPushMessage;
import com.zheng.domain.params.AudienceParam;
import com.zheng.enums.PlatformEnum;
import com.zheng.exception.PlatformParamException;
import com.zheng.interfaces.Validable;

/**
 * 发送消息所需要配置的参数信息
 * jpushsender通过jpushparam配置发送的消息参数
 * 
 * Created by zhenglian on 2017/6/4.
 */
public class JPushParam implements Validable {
    /**
     * 必须项
     * 推送终端平台
     */
    private PlatformEnum platformEnum;

    /**
     * 必须项
     * 推送的消息实体
     * 封装需要发送的消息内容
     */
    private JPushMessage message;

    /**
     * 可选项
     * 是否需要采用tag或al过滤终端设备
     * 不需要则设置为null
     */
    private AudienceParam audienceParam;

    public JPushParam(PlatformEnum platformEnum, JPushMessage message,
                      AudienceParam audienceParam) {
        this.platformEnum = platformEnum;
        this.message = message;
        this.audienceParam = audienceParam;
    }

    public JPushParam(PlatformEnum platformEnum, JPushMessage message) {
        this.platformEnum = platformEnum;
        this.message = message;
    }

    @Override
    public void valid() throws RuntimeException {
        if(null == platformEnum || null == message) {
            Throwables.propagate(new PlatformParamException());
        }
    }

    public PlatformEnum getPlatformEnum() {
        return platformEnum;
    }

    public void setPlatformEnum(PlatformEnum platformEnum) {
        this.platformEnum = platformEnum;
    }

    public JPushMessage getMessage() {
        return message;
    }

    public void setMessage(JPushMessage message) {
        this.message = message;
    }

    public AudienceParam getAudienceParam() {
        return audienceParam;
    }

    public void setAudienceParam(AudienceParam audienceParam) {
        this.audienceParam = audienceParam;
    }
    
}
