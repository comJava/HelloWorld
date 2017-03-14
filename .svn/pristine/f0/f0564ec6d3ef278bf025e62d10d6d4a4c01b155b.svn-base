package com.chinamobile.athena.risk.common.entity.rule;

import java.io.Serializable;

/**
 * 
 * @ClassName KsRule
 * @Description Ks协商规则模型实体类
 * @author 王兵
 * @date 2015年8月10日 - 下午11:22:00
 * @version 1.0
 */
public class OperateRule implements Serializable {

	private static final long serialVersionUID = -5767983336315502296L;

	private OperateType operateType;
	private EventType eventType;
	private Integer count;
	private LevelLocationType oneLevelLocationType;

	private Integer operateTypeValue;
	private Integer eventTypeValue;
	private Integer oneLevelLocationTypeValue;
	private Integer riskLevel;
	/**
	 * 风控规则编号
	 */
	private String riskEvent;

	public void setOperateType(OperateType operateType) {
		this.operateType = operateType;
		this.setOperateTypeValue(operateType.getType());
	}

	public void setOneLevelLocationType(LevelLocationType oneLevelLocationType) {
		this.oneLevelLocationType = oneLevelLocationType;
		this.setOneLevelLocationTypeValue(oneLevelLocationType.getType());
	}

	public EventType getEventType() {
		return eventType;
	}

	public void setEventType(EventType eventType) {
		this.eventType = eventType;
		this.setEventTypeValue(eventType.getType());
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public Integer getOperateTypeValue() {
		return operateTypeValue;
	}

	public void setOperateTypeValue(Integer operateTypeValue) {
		this.operateTypeValue = operateTypeValue;
	}

	public OperateType getOperateType() {
		return operateType;
	}

	public LevelLocationType getOneLevelLocationType() {
		return oneLevelLocationType;
	}

	public Integer getEventTypeValue() {
		return eventTypeValue;
	}

	public Integer getRiskLevel() {
		return riskLevel;
	}

	public void setRiskLevel(Integer riskLevel) {
		this.riskLevel = riskLevel;
	}

	public Integer getOneLevelLocationTypeValue() {
		return oneLevelLocationTypeValue;
	}

	public void setOneLevelLocationTypeValue(Integer oneLevelLocationTypeValue) {
		this.oneLevelLocationTypeValue = oneLevelLocationTypeValue;
	}

	public void setEventTypeValue(Integer eventTypeValue) {
		this.eventTypeValue = eventTypeValue;
	}

	public String getRiskEvent() {
		return riskEvent;
	}

	public void setRiskEvent(String riskEvent) {
		this.riskEvent = riskEvent;
	}

}
