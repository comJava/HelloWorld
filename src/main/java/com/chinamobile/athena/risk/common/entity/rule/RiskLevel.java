package com.chinamobile.athena.risk.common.entity.rule;

/**
 * 
 * @ClassName RiskLevel
 * @Description  风险等级定义
 * @author 王兵
 * @date 2015年8月10日 - 下午11:32:58
 * @version 1.0
 */
public enum RiskLevel {

    LEVEL_5(5),
    LEVEL_4(4),
    LEVEL_3(3),
    LEVEL_2(2),
    LEVEL_1(1),
    LEVEL_0(0),
    LEVEL_DEFULT(100);
    
    private Integer level;

    private RiskLevel(Integer level) {
        this.level = level;
    }

    public Integer getLevel() {
        return this.level;
    }
    
}
