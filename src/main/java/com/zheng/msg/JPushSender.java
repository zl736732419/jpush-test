package com.zheng.msg;

import cn.jiguang.common.ClientConfig;
import cn.jiguang.common.resp.APIConnectionException;
import cn.jiguang.common.resp.APIRequestException;
import cn.jpush.api.JPushClient;
import cn.jpush.api.push.PushResult;
import cn.jpush.api.push.model.Message;
import cn.jpush.api.push.model.Platform;
import cn.jpush.api.push.model.PushPayload;
import cn.jpush.api.push.model.audience.Audience;
import cn.jpush.api.push.model.notification.Notification;
import com.google.common.base.Throwables;
import com.zheng.constant.JPushConfig;
import com.zheng.domain.JPushMessage;
import com.zheng.domain.params.AudienceParam;
import com.zheng.domain.params.MessageParam;
import com.zheng.domain.params.NotificationParam;
import com.zheng.enums.PlatformEnum;
import com.zheng.exception.JPushParamException;
import com.zheng.exception.PlatformParamException;
import com.zheng.util.PlatformUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Jpush推送中心，负责消息发送
 * Created by zhenglian on 2017/6/4.
 */
public class JPushSender {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    private JPushSender() {
    }

    /**
     * 获取jpush消息发送对象
     * @return
     */
    private JPushClient getJPushClient() {
        return new JPushClient(JPushConfig.MASTER_SECRET, JPushConfig.APP_KEY, 
                null, ClientConfig.getInstance());
    }

    /**
     * 根据message生成PayLoad对象
     * @param jPushParam
     * @return
     */
    private PushPayload createPayLoad(JPushParam jPushParam) {
        if(null == jPushParam) {
            Throwables.propagate(new JPushParamException());
        }
        
        //检查参数是否合法
        jPushParam.valid();
        
        PushPayload.Builder builder = PushPayload.newBuilder();

        //设置推送终端设备类型
        PlatformEnum platformEnum = jPushParam.getPlatformEnum();
        setPlatform(builder, platformEnum);
        
        //设置终端过滤
        AudienceParam audienceParam = jPushParam.getAudienceParam();
        setAudience(builder, audienceParam);
        
        JPushMessage jPushMessage = jPushParam.getMessage();
        jPushMessage.valid();
        //设置通知
        setAlert(builder, jPushMessage, platformEnum);
        
        //设置消息
        setMessage(builder, jPushMessage);
        
        return builder.build();
    }

    /**
     * 设置消息
     * @param builder
     * @param jPushMessage
     */
    private void setMessage(PushPayload.Builder builder, JPushMessage jPushMessage) {
        if (!jPushMessage.isSendMsg()) {
            return;
        }

        MessageParam messageParam = new MessageParam();
        messageParam.withContent(jPushMessage.getMsg()).withExtras(jPushMessage.getMsgExtras());

        Message message = messageParam.create();
        if(null != message) {
            builder.setMessage(message);
        }
    }

    /**
     * 设置通知
     * @param builder
     * @param jPushMessage
     */
    private void setAlert(PushPayload.Builder builder, JPushMessage jPushMessage, PlatformEnum platformEnum) {
        if(!jPushMessage.isSendAlert()) {
            return;
        }
        
        NotificationParam notificationParam = new NotificationParam();
        
        if(PlatformUtil.containsAndroid(platformEnum)) {
            notificationParam.addAndroidNotification(jPushMessage.getAlert(), jPushMessage.getAlertTitle(),
                    jPushMessage.getAlertExtras());
        }
        if (PlatformUtil.containsIos(platformEnum)) {
            notificationParam.addIosNotification(jPushMessage.getAlert(),
                    jPushMessage.getIosAlertSound(), jPushMessage.getIosAlertBadge(), jPushMessage.getAlertExtras());
        }
        if (PlatformUtil.containsWinphone(platformEnum)) {
            notificationParam.addWinphoneNotification(jPushMessage.getAlert(), jPushMessage.getAlertExtras());
        }

        Notification notification = notificationParam.create();
        if(null != notification) {
            builder.setNotification(notification);
        }
    }

    /**
     * 设置终端能过滤
     * @param builder
     * @param audienceParam
     */
    private void setAudience(PushPayload.Builder builder, AudienceParam audienceParam) {
        if(null == audienceParam) {
            return;
        }

        Audience audience = audienceParam.create();
        if(null != audience) {
            builder.setAudience(audience);
        }
    }

    /**
     * 设置终端平台类型
     * @param builder
     * @param platformEnum
     */
    private void setPlatform(PushPayload.Builder builder, PlatformEnum platformEnum) {
        if(null == platformEnum) {
            Throwables.propagate(new PlatformParamException());
        }
        
        Platform platform = PlatformUtil.getPlatform(platformEnum);
        builder.setPlatform(platform);
    }

    /**
     * 根据jpushParam生成audience过滤
     * @param jPushParam
     * @return
     */
    private Audience createAudience(JPushParam jPushParam) {
        AudienceParam audienceParam = jPushParam.getAudienceParam();
        Audience audience = null;
        if(null != audienceParam) {
            audience = audienceParam.create();
        }
        
        return audience;
    }


    /**
     * 发送消息的主入口
     * 只需要构造一个jpushParam参数即可进行消息通知的发送操作
     * @param jPushParam
     * @return
     */
    public PushResult sendMessage(JPushParam jPushParam) {
        JPushClient client = getJPushClient();
        PushPayload payload = createPayLoad(jPushParam);
        PushResult result = null;
        try {
            result = client.sendPush(payload);
        } catch (APIConnectionException e) {
            logger.error(":::发送消息失败，发生APIConnectionException异常{}", e.getMessage());
            Throwables.propagate(e);
        } catch (APIRequestException e) {
            logger.error(":::发送消息失败，发生APIRequestException异常{}", e.getMessage());
            Throwables.propagate(e);
        }

        return result;
    }
}
