package com.chinamobile.athena.risk.common.entity;

import com.google.gson.annotations.SerializedName;

public class RiskEquipment {
	@SerializedName("A_cpuid")
	private String aCpuId;
	@SerializedName("A_imei")
	private String aImei;
	@SerializedName("A_dev_mac")
	private String aDevMac;
	@SerializedName("A_sim_num")
	private String aSimNum;
	@SerializedName("I_idfa")
	private String iIdfa;
	@SerializedName("I_open_udid")
	private String iOpenUdid;
	@SerializedName("I_push_token")
	private String iPushToken;
	@SerializedName("I_idfv")
	private String iIdfv;

	public String getaCpuId() {
		return aCpuId;
	}

	public void setaCpuId(String aCpuId) {
		this.aCpuId = aCpuId;
	}

	public String getaImei() {
		return aImei;
	}

	public void setaImei(String aImei) {
		this.aImei = aImei;
	}

	public String getaDevMac() {
		return aDevMac;
	}

	public void setaDevMac(String aDevMac) {
		this.aDevMac = aDevMac;
	}

	public String getaSimNum() {
		return aSimNum;
	}

	public void setaSimNum(String aSimNum) {
		this.aSimNum = aSimNum;
	}

	public String getiIdfa() {
		return iIdfa;
	}

	public void setiIdfa(String iIdfa) {
		this.iIdfa = iIdfa;
	}

	public String getiOpenUdid() {
		return iOpenUdid;
	}

	public void setiOpenUdid(String iOpenUdid) {
		this.iOpenUdid = iOpenUdid;
	}

	public String getiPushToken() {
		return iPushToken;
	}

	public void setiPushToken(String iPushToken) {
		this.iPushToken = iPushToken;
	}

	public String getiIdfv() {
		return iIdfv;
	}

	public void setiIdfv(String iIdfv) {
		this.iIdfv = iIdfv;
	}

}
