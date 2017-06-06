package com.zheng.domain.params;

import com.google.common.collect.Lists;
import com.zheng.enums.AudienceTagOperatorEnum;

import java.util.List;

/**
 * audience tag参数
 * 用于过滤终端设备
 * Created by zhenglian on 2017/6/5.
 */
public class AudienceTagParam {
    private AudienceTagOperatorEnum tagOperatorEnum = AudienceTagOperatorEnum.OR;
    private List<String> tags = Lists.newArrayList();

    public AudienceTagParam(List<String> tags) {
        this(null, tags);
    }
    
    public AudienceTagParam(AudienceTagOperatorEnum tagOperatorEnum) {
        this(tagOperatorEnum, null);
    }

    public AudienceTagParam(AudienceTagOperatorEnum tagOperatorEnum, List<String> tags) {
        if(null != tagOperatorEnum) {
            this.tagOperatorEnum = tagOperatorEnum;
        }
        if(null != tags) {
            this.tags = tags;
        }
    }

    public AudienceTagOperatorEnum getTagOperatorEnum() {
        return tagOperatorEnum;
    }

    public List<String> getTags() {
        return tags;
    }
}
