package com.zheng.domain;

import com.google.common.base.Throwables;
import com.google.common.collect.Maps;
import com.zheng.exception.JPushMessageException;
import com.zheng.interfaces.Validable;
import org.apache.commons.lang3.StringUtils;

import java.util.Map;

/**
 * jpush需要发送的消息对象(包含两种：通知alert和消息MSG)
 * 
 * Created by zhenglian on 2017/6/5.
 */
public class JPushMessage implements Validable {
    
    //通知alert=====================================
    /**
     * 通知内容
     */
    private String alert;
    /**
     * 通知标题
     */
    private String alertTitle;

    // ios专用
    /**
     * 徽章
     */
    private Integer iosAlertBadge;

    /**
     * 提示声音
     */
    private String iosAlertSound;
    // ios专用end
    
    /**
     * 通知附加信息
     */
    private Map<String, String> alertExtras = Maps.newHashMap();
    //通知alert=====================================end
    
    
    //消息msg=====================================
    /**
     * 消息内容
     */
    private String msg;
    
    private Map<String, String> msgExtras = Maps.newHashMap();

    //消息msg=====================================end

    /**
     * 是否发送消息
     */
    private boolean sendMsg = true;
    /**
     * 是否发送通知
     */
    private boolean sendAlert = true;

    /**
     * 只能通过Builder构造器生成对象实例，确保alert/msg至少存在其中一项
     */
    private JPushMessage() {
    }

    @Override
    public void valid() throws RuntimeException {
        if(!sendAlert && !sendMsg) {
            Throwables.propagate(new JPushMessageException());
        }
        
        if(sendAlert && StringUtils.isBlank(alert)) {
            Throwables.propagate(new JPushMessageException("发送通知时，通知内容不能为空"));
        }
        
        if(sendMsg && StringUtils.isBlank(msg)) {
            Throwables.propagate(new JPushMessageException("发送消息时，消息内容不能为空"));
        }
        
    }

    public String getAlert() {
        return alert;
    }

    public void setAlert(String alert) {
        this.alert = alert;
    }

    public String getAlertTitle() {
        return alertTitle;
    }

    public void setAlertTitle(String alertTitle) {
        this.alertTitle = alertTitle;
    }

    public Integer getIosAlertBadge() {
        return iosAlertBadge;
    }

    public void setIosAlertBadge(Integer iosAlertBadge) {
        this.iosAlertBadge = iosAlertBadge;
    }

    public String getIosAlertSound() {
        return iosAlertSound;
    }

    public void setIosAlertSound(String iosAlertSound) {
        this.iosAlertSound = iosAlertSound;
    }

    public Map<String, String> getAlertExtras() {
        return alertExtras;
    }

    public void setAlertExtras(Map<String, String> alertExtras) {
        this.alertExtras = alertExtras;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Map<String, String> getMsgExtras() {
        return msgExtras;
    }

    public void setMsgExtras(Map<String, String> msgExtras) {
        this.msgExtras = msgExtras;
    }

    public boolean isSendMsg() {
        return sendMsg;
    }

    public void setSendMsg(boolean sendMsg) {
        this.sendMsg = sendMsg;
    }

    public boolean isSendAlert() {
        return sendAlert;
    }

    public void setSendAlert(boolean sendAlert) {
        this.sendAlert = sendAlert;
    }

    /**
     * 构造发送alert/msg的jpushmessage对象实例
     */
    public class Builder {
        private JPushMessage message;
        public Builder() {
            message = new JPushMessage();
            message.setSendAlert(true);
            message.setSendMsg(true);
        }
        
        public Builder withAlertTitle(String title) {
            message.setAlertTitle(title);
            return this;
        }

        public Builder withAlert(String alert) {
            message.setAlert(alert);
            return this;
        }
        
        public Builder withAlertExtras(Map<String, String> extras) {
            message.setAlertExtras(extras);
            return this;
        }

        public Builder withMsg(String msg) {
            message.setMsg(msg);
            return this;
        }

        public Builder withMsgExtras(Map<String, String> extras) {
            message.setMsgExtras(extras);
            return this;
        }

        public JPushMessage build() {
            return message;
        }
    }

    /**
     * 构造发送alert的jpushmessage对象实例
     */
    public class AlertBuilder {
        private JPushMessage message;
        public AlertBuilder() {
            message = new JPushMessage();
            message.setSendAlert(true);
            message.setSendMsg(false);
        }

        public AlertBuilder withAlertTitle(String title) {
            message.setAlertTitle(title);
            return this;
        }

        public AlertBuilder withAlert(String alert) {
            message.setAlert(alert);
            return this;
        }

        public AlertBuilder withAlertExtras(Map<String, String> extras) {
            message.setAlertExtras(extras);
            return this;
        }

        public JPushMessage build() {
            return message;
        }
    }

    /**
     * 构造发送msg的jpushmessage对象实例
     */
    public class MsgBuilder {
        private JPushMessage message;
        public MsgBuilder() {
            message = new JPushMessage();
            message.setSendAlert(false);
            message.setSendMsg(true);
        }

        public MsgBuilder withMsg(String msg) {
            message.setMsg(msg);
            return this;
        }

        public MsgBuilder withMsgExtras(Map<String, String> extras) {
            message.setMsgExtras(extras);
            return this;
        }

        public JPushMessage build() {
            return message;
        }
    }
}
