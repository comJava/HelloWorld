package com.chinamobile.athena.risk.common.entity.rule;

import java.io.Serializable;

/**
 * 
 * @ClassName TrustedType
 * @Description 设备可信度　０－可信　１－非可信　－１－黑名单
 * @author 王兵
 * @date 2015年8月10日 - 下午11:10:19
 * @version 1.0
 */
public enum TrustedType implements Serializable {

    TRUSTED_TYPE(0),
    UNTRUSTED_TYPE(1),
    BLACKLIST_TYPE(-1);
    
    private Integer deviecType;

    private TrustedType(Integer deviecType) {
        this.deviecType = deviecType;
    }

    public Integer getType() {
        return this.deviecType;
    }
    
}
