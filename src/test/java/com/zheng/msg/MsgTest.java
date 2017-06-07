package com.zheng.msg;

import cn.jpush.api.push.PushResult;
import com.google.common.collect.Maps;
import com.zheng.domain.JpushMessage;
import com.zheng.enums.PlatformEnum;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

/**
 * Created by zhenglian on 2017/6/7.
 */
public class MsgTest {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    
    private String alert;
    private String alertTitle;
    private Map<String, String> extras;
    
    private String msg;
    private Map<String, String> msgExtras;
    @Before
    public void init() {
        extras = Maps.newHashMap();
        extras.put("msg", "jpush test");
        extras.put("girlfriend", "where are you?");
        
        alert = "this is a test msg!";
        alertTitle = "alert title";
        
        msg = "this is a msg string";
        msgExtras = extras;
    }
    
    /**
     * 只发送alert
     */
    @Test
    public void sendAlert() {
        PlatformEnum platformEnum = PlatformEnum.ANDROID;
        JpushMessage message = JpushMessage.newAlertBuilder()
                .withAlert(alert)
                .withAlertTitle(alertTitle)
                .withAlertExtras(extras).build();
        
        sendMessage(platformEnum, message);
    }

    /**
     * 只发送msg
     */
    @Test
    public void sendMsg() {
        PlatformEnum platformEnum = PlatformEnum.ALL;
        JpushMessage message = JpushMessage.newMsgBuilder()
                .withMsg(msg)
                .withMsgExtras(msgExtras).build();
        
        sendMessage(platformEnum, message);
        
    }

    /**
     * 同时发送msg, alert
     */
    @Test
    public void sendMsgAlert() {
        PlatformEnum platformEnum = PlatformEnum.ANDROID_IOS;
        JpushMessage message = JpushMessage.newBuilder()
                .withAlert(alert)
                .withAlertTitle(alertTitle)
                .withAlertExtras(extras)
                .withIosAlertBadge(2)
                .withIosAlertSound("happy")
                .withMsg(msg)
                .withMsgExtras(msgExtras).build();
        sendMessage(platformEnum, message);
    }

    private void sendMessage(PlatformEnum platformEnum, JpushMessage message) {
        JpushParam params = new JpushParam(platformEnum,  message);
        logger.debug("发送消息: {}", params.toString());
        JpushSender sender = JpushSender.getSender();
        
        PushResult result = sender.sendMessage(params);
        
        logger.debug("消息发送状态：{}", result);
    }
    
}
