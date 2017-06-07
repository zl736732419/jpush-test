package com.zheng.enums;

/**
 * JPush平台枚举类型
 * Created by zhenglian on 2017/6/4.
 */
public enum PlatformEnum {
    IOS(1, "iphone", "苹果终端"),
    ANDROID(2, "android", "android终端"),
    WINPHONE(3, "winphone", "winphone终端"),
    ANDROID_IOS(4, "android_ios", "android与ios终端"),
    ANDROID_WINPHONE(5, "android_winphone", "android与winphone终端"),
    IOS_WINPHONE(6, "ios_winphone", "ios与winphone终端"),
    ALL(7, "all", "所有终端设备");
    
    private Integer key;
    private String value;
    private String description;

    private PlatformEnum(Integer key, String value, String description) {
        this.key = key;
        this.value = value;
        this.description = description;
    }

    public Integer getKey() {
        return key;
    }

    public void setKey(Integer key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
