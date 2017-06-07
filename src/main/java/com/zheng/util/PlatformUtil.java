package com.zheng.util;

import cn.jpush.api.push.model.Platform;
import com.zheng.enums.PlatformEnum;

/**
 * 平台工具类，根据PlatformEnum获取对应的平台对象
 * Created by zhenglian on 2017/6/4.
 */
public class PlatformUtil {
    
    private PlatformUtil() {
    }

    /**
     * 获取平台对象
     * @param platformEnum
     * @return
     */
    public static Platform getPlatform(PlatformEnum platformEnum) {
        if(platformEnum == null) {
            return null;
        }
        
        Platform platform = null;
        switch (platformEnum) {
            case ANDROID:
                platform = Platform.android();
                break;
            case IOS:
                platform = Platform.ios();
                break;
            case WINPHONE:
                platform = Platform.winphone();
                break;
            case ANDROID_IOS:
                platform = Platform.android_ios();
                break;
            case ANDROID_WINPHONE:
                platform = Platform.android_winphone();
                break;
            case IOS_WINPHONE:
                platform = Platform.ios_winphone();
                break;
            case ALL:
                platform = Platform.all();
                break;
            default:
                platform = null;
        }
        
        return platform;
    }

    /**
     * 是否包含android终端设备
     * @param platformEnum
     * @return
     */
    public static boolean containsAndroid(PlatformEnum platformEnum) {
        return platformEnum.equals(PlatformEnum.ANDROID)
                || platformEnum.equals(PlatformEnum.ANDROID_IOS)
                || platformEnum.equals(PlatformEnum.ANDROID_WINPHONE)
                || platformEnum.equals(PlatformEnum.ALL);
    }

    /**
     * 是否包含android终端设备
     * @param platformEnum
     * @return
     */
    public static boolean containsIos(PlatformEnum platformEnum) {
        return platformEnum.equals(PlatformEnum.IOS)
                || platformEnum.equals(PlatformEnum.ANDROID_IOS)
                || platformEnum.equals(PlatformEnum.IOS_WINPHONE)
                || platformEnum.equals(PlatformEnum.ALL);
    }

    /**
     * 是否包含android终端设备
     * @param platformEnum
     * @return
     */
    public static boolean containsWinphone(PlatformEnum platformEnum) {
        return platformEnum.equals(PlatformEnum.WINPHONE)
                || platformEnum.equals(PlatformEnum.ANDROID_WINPHONE)
                || platformEnum.equals(PlatformEnum.IOS_WINPHONE)
                || platformEnum.equals(PlatformEnum.ALL);
    }
}
