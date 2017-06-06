package com.zheng.enums;

/**
 * audience tag聚合操作枚举
 * Created by zhenglian on 2017/6/5.
 */
public enum AudienceTagOperatorEnum {
    /**
     * tag之间做与操作，取交集
     */
    AND,
    /**
     * tag之间做并操作,取并集
     */
    OR;
}
