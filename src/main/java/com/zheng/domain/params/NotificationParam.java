package com.zheng.domain.params;


import cn.jpush.api.push.model.notification.AndroidNotification;
import cn.jpush.api.push.model.notification.IosNotification;
import cn.jpush.api.push.model.notification.Notification;
import cn.jpush.api.push.model.notification.WinphoneNotification;
import org.apache.commons.lang3.StringUtils;

import java.util.Map;

/**
 * 通知参数
 * 具体各个平台的通知参数需要根据
 * AndroidNotification,
 * IosNotification,
 * WinPhoneNotification
 * 类进行构造,创建了该类意味着必须添加对应平台通知
 * Created by zhenglian on 2017/6/4.
 */
public class NotificationParam extends AbstractParam<Notification> {
    private Notification.Builder builder;
    private boolean create = false;

    public NotificationParam() {
        builder = Notification.newBuilder();
    }

    /**
     * 构造android平台通知
     * @param alert 消息提示内容
     * @param title 消息标题
     * @param extras 消息附加信息
     * @return
     */
    public NotificationParam addAndroidNotification(String alert, String title, Map<String, String> extras) {
        if(StringUtils.isBlank(alert)) {
            logger.warn(":::构造android notification失败:::alert消息为空");
            return this;
        }

        AndroidNotification.Builder androidBuilder = AndroidNotification.newBuilder()
                .setAlert(alert);
        if(!StringUtils.isBlank(title)) {
            androidBuilder.setTitle(title);
        }
        if(null != extras && !extras.isEmpty()) {
            androidBuilder.addExtras(extras);
        }
        
        AndroidNotification notification = androidBuilder.build();
        builder.addPlatformNotification(notification);

        setCreate(true);
        
        return this;
    }

    /**
     * 添加苹果平台通知
     * @param alert 消息提示内容
     * @param sound 声音
     * @param badge 徽章
     * @param extras 附加信息
     * @return
     */
    public NotificationParam addIosNotification(String alert, String sound, Integer badge, 
                                                Map<String, String> extras) {
        if(StringUtils.isBlank(alert)) {
            logger.warn(":::构造ios notification失败:::alert消息为空");
            return this;
        }
        
        IosNotification.Builder iosBuilder = IosNotification.newBuilder()
                .setAlert(alert);
        
        if(null == badge) {
            iosBuilder.autoBadge();
        } else {
            iosBuilder.setBadge(badge);  
        }
        if(!StringUtils.isBlank(sound)) {
            iosBuilder.setSound(sound);
        }
        if(null != extras && !extras.isEmpty()) {
            iosBuilder.addExtras(extras);
        }
        
        IosNotification notification = iosBuilder.build();
        builder.addPlatformNotification(notification);

        setCreate(true);
        
        return this;
    }

    /**
     * 添加winphone平台通知
     * @param alert 消息提示内容
     * @param extras 附加信息
     * @return
     */
    public NotificationParam addWinphoneNotification(String alert, Map<String, String> extras) {
        if(StringUtils.isBlank(alert)) {
            logger.warn(":::构造winphone notification失败:::alert消息为空");
            return this;
        }

        WinphoneNotification.Builder winphoneBuilder = WinphoneNotification.newBuilder()
                .setAlert(alert);

        if(null != extras && !extras.isEmpty()) {
            winphoneBuilder.addExtras(extras);
        }

        WinphoneNotification notification = winphoneBuilder.build();
        builder.addPlatformNotification(notification);

        setCreate(true);
        
        return this;
    }

    @Override
    protected Notification build() {
        return builder.build();
    }
}
