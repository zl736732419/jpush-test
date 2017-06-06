package com.zheng.domain.params;

import cn.jpush.api.push.model.Message;
import org.apache.commons.lang3.StringUtils;

import java.util.Map;

/**
 * jpush短信消息
 * Created by zhenglian on 2017/6/5.
 */
public class MessageParam extends AbstractParam<Message> {
    
    private Message.Builder builder;
    
    public MessageParam() {
        builder = Message.newBuilder();
    }

    /**
     * 设置短信内容
     * @param content
     * @return
     */
    public MessageParam withContent(String content) {
        if(StringUtils.isBlank(content)) {
            return this;
        }
        
        builder.setMsgContent(content);
        setCreate(true);
        
        return this;
    }
    
    public MessageParam withExtras(Map<String, String> extras) {
        if(null == extras || extras.isEmpty()) {
            return this;
        }
        
        builder.addExtras(extras);
        setCreate(true);
        
        return this;
    }
    
    @Override
    protected Message build() {
        return builder.build();
    }
}
