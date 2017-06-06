package com.zheng.domain.params;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 通用参数接口
 * Created by zhenglian on 2017/6/5.
 */
public abstract class AbstractParam<T> {
    protected Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * 判断当前audience是否有效设置了tag/alias
     */
    protected boolean create = false;

    protected void setCreate(boolean create) {
        this.create = create;
    }
    
    /**
     * 生成指定类型参数
     * @return
     */
    public T create() {
        if(!create) {
            return null;
        }
        
        return build();
    }

    /**
     * 具体参数构造过程，需要用户实现
     * @return
     */
    protected abstract T build();
}
