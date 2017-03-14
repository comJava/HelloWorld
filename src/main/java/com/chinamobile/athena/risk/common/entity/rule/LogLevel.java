package com.chinamobile.athena.risk.common.entity.rule;

public enum LogLevel {
	
    LEVEL_NOT_MAIL(0),
    LEVEL_MAIL(100);
    
    private Integer level;

    private LogLevel(Integer level) {
        this.level = level;
    }

    public Integer getLevel() {
        return this.level;
    }
}
