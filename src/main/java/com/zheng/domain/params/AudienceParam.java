package com.zheng.domain.params;

import cn.jpush.api.push.model.audience.Audience;
import cn.jpush.api.push.model.audience.AudienceTarget;
import com.zheng.enums.AudienceTagOperatorEnum;

import java.util.List;

/**
 * 用于过滤终端设备，是否按照指定的alias, tag进行过滤
 * Created by zhenglian on 2017/6/4.
 */
public class AudienceParam extends AbstractParam<Audience> {
    private Audience.Builder builder = null;
    public AudienceParam() {
        builder = Audience.newBuilder();
    }

    /**
     * 通过tags限制终端设备
     * @param tagParam
     * @return
     */
    public AudienceParam withTags(AudienceTagParam tagParam) {
        if(null == tagParam || tagParam.getTags().isEmpty()) {
            return this;
        }
        
        AudienceTagOperatorEnum operator = tagParam.getTagOperatorEnum();
        List<String> tags = tagParam.getTags();

        AudienceTarget target;
        if(operator.equals(AudienceTagOperatorEnum.AND)) {
            target = AudienceTarget.tag_and(tags);
        }else {
            target = AudienceTarget.tag(tags);
        }
        
        builder.addAudienceTarget(target);
        setCreate(true);
        
        return this;
    }

    /**
     * 通过别名过滤
     * @param alias
     * @return
     */
    public AudienceParam withAlias(List<String> alias) {
        if(null == alias || alias.isEmpty()) {
            return this;
        }
        
        builder.addAudienceTarget(AudienceTarget.alias(alias));
        setCreate(true);
        
        return this;
    }

    @Override
    protected Audience build() {
        return builder.build();
    }
}
