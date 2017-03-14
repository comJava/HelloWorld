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
public class KsRule implements Serializable {

	private static final long serialVersionUID = -5767983336315502296L;

	private DeviceType deviceType;
	private TrustedType trustedType;
	private LevelLocationType oneLevelLocationType;
	private LevelLocationType twoLevelLocationType;
	private LoginType loginType;

	private Integer deviceTypeValue;
	private Integer trustedTypeValue;
	private Integer oneLevelLocationTypeValue;
	private Integer twoLevelLocationTypeValue;
	private Integer loginTypeValue;
	private Integer riskLevel;
	/**
	 * 风控规则编号
	 */
	private String riskEvent;

	public TrustedType getTrustedType() {
		return trustedType;
	}

	public void setTrustedType(TrustedType trustedType) {
		this.trustedType = trustedType;
		this.setTrustedTypeValue(trustedType.getType());
	}

	public LevelLocationType getOneLevelLocationType() {
		return oneLevelLocationType;
	}

	public void setOneLevelLocationType(LevelLocationType oneLevelLocationType) {
		this.oneLevelLocationType = oneLevelLocationType;
		this.setOneLevelLocationTypeValue(oneLevelLocationType.getType());
	}

	public LevelLocationType getTwoLevelLocationType() {
		return twoLevelLocationType;
	}

	public void setTwoLevelLocationType(LevelLocationType twoLevelLocationType) {
		this.twoLevelLocationType = twoLevelLocationType;
		this.setTwoLevelLocationTypeValue(twoLevelLocationType.getType());
	}

	public Integer getRiskLevel() {
		return riskLevel;
	}

	public void setRiskLevel(Integer riskLevel) {
		this.riskLevel = riskLevel;
	}

	public Integer getTrustedTypeValue() {
		return trustedTypeValue;
	}

	public Integer getOneLevelLocationTypeValue() {
		return oneLevelLocationTypeValue;
	}

	public void setOneLevelLocationTypeValue(Integer oneLevelLocationTypeValue) {
		this.oneLevelLocationTypeValue = oneLevelLocationTypeValue;
	}

	public Integer getTwoLevelLocationTypeValue() {
		return twoLevelLocationTypeValue;
	}

	public void setTwoLevelLocationTypeValue(Integer twoLevelLocationTypeValue) {
		this.twoLevelLocationTypeValue = twoLevelLocationTypeValue;
	}

	public DeviceType getDeviceType() {
		return deviceType;
	}

	public void setDeviceType(DeviceType deviceType) {
		this.deviceType = deviceType;
		this.setDeviceTypeValue(deviceType.getType());
	}

	public Integer getDeviceTypeValue() {
		return deviceTypeValue;
	}

	public void setDeviceTypeValue(Integer deviceTypeValue) {
		this.deviceTypeValue = deviceTypeValue;
	}

	public void setTrustedTypeValue(Integer trustedTypeValue) {
		this.trustedTypeValue = trustedTypeValue;
	}

	public LoginType getLoginType() {
		return loginType;
	}

	public void setLoginType(LoginType loginType) {
		this.loginType = loginType;
		this.setLoginTypeValue(loginType.getType());
	}

	public Integer getLoginTypeValue() {
		return loginTypeValue;
	}

	public void setLoginTypeValue(Integer loginTypeValue) {
		this.loginTypeValue = loginTypeValue;
	}

	public String getRiskEvent() {
		return riskEvent;
	}

	public void setRiskEvent(String riskEvent) {
		this.riskEvent = riskEvent;
	}

}
