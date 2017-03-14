package com.chinamobile.athena.risk.common.entity.rule;

import java.io.Serializable;

/**
* 事件  1-密码错误 2-验证码错误
* @ClassName: EventType
* @author zing
* @date Oct 13, 2015 1:45:27 PM
*/
public enum EventType implements Serializable {

    PASSWORD_ERROR(1),
    VERIFICATE_ERROR(2);
    
    private Integer eventType;

    private EventType(Integer eventType) {
        this.eventType = eventType;
    }

    public Integer getType() {
        return this.eventType;
    }
    
}
