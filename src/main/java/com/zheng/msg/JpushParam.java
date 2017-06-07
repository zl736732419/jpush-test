package com.zheng.msg;

import com.google.common.base.Throwables;
import com.zheng.domain.JpushMessage;
import com.zheng.domain.params.AudienceParam;
import com.zheng.enums.PlatformEnum;
import com.zheng.exception.JpushAudienceException;
import com.zheng.exception.JpushMessageException;
import com.zheng.exception.JpushPlatformParamException;
import com.zheng.interfaces.Validable;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * 发送消息所需要配置的参数信息
 * jpushsender通过jpushparam配置发送的消息参数
 * 
 * Created by zhenglian on 2017/6/4.
 */
public class JpushParam implements Validable {
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
    private JpushMessage message;

    /**
     * 必选项
     * 是否需要采用tag或al过滤终端设备
     */
    private AudienceParam audienceParam;

    public JpushParam(PlatformEnum platformEnum, JpushMessage message,
                      AudienceParam audienceParam) {
        this.platformEnum = platformEnum;
        this.message = message;
        this.audienceParam = audienceParam;
    }

    public JpushParam(PlatformEnum platformEnum, JpushMessage message) {
        this.platformEnum = platformEnum;
        this.message = message;
        this.audienceParam = new AudienceParam();
    }

    @Override
    public void valid() throws RuntimeException {
        if(null == platformEnum) {
            Throwables.propagate(new JpushPlatformParamException());
        }
        
        if(null == message) {
            Throwables.propagate(new JpushMessageException());
        }
        
        if(null == audienceParam) {
            Throwables.propagate(new JpushAudienceException());
        }
    }

    public PlatformEnum getPlatformEnum() {
        return platformEnum;
    }

    public void setPlatformEnum(PlatformEnum platformEnum) {
        this.platformEnum = platformEnum;
    }

    public JpushMessage getMessage() {
        return message;
    }

    public void setMessage(JpushMessage message) {
        this.message = message;
    }

    public AudienceParam getAudienceParam() {
        return audienceParam;
    }

    public void setAudienceParam(AudienceParam audienceParam) {
        this.audienceParam = audienceParam;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("platform:", this.platformEnum)
                .append("message:", this.message).build();
    }
}
