package com.chinamobile.athena.risk.common.entity.rule;

import java.io.Serializable;

/**
 * 
 * @ClassName OneLevelLocationType
 * @Description 0--常用　１－非常用
 * @author 王兵
 * @date 2015年8月10日 - 下午11:04:31
 * @version 1.0
 */
public enum LevelLocationType implements Serializable {

    NORMAL_LOCATION(0),
    UNNORMAL_LOCATION(1);
    
    private Integer locationType;

    private LevelLocationType(Integer locationType) {
        this.locationType = locationType;
    }

    public Integer getType() {
        return this.locationType;
    }
    
}
