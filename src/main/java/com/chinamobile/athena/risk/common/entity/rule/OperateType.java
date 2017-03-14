package com.chinamobile.athena.risk.common.entity.rule;

import java.io.Serializable;

/**
 * 操作
* @ClassName: OperateType
* @Description: 1-UP登录 2-DUP登录 3-重置密码
* @author zing
* @date Oct 13, 2015 1:43:26 PM
 */
public enum OperateType implements Serializable {

    UP_LOGIN(1),
    DUP_LOGIN(2),
    RESET_PASSWORD(3);
    
    private Integer operateType;

    private OperateType(Integer operateType) {
        this.operateType = operateType;
    }

    public Integer getType() {
        return this.operateType;
    }
    
}
