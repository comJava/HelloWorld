package com.chinamobile.athena.risk.common.entity;

import com.google.gson.annotations.SerializedName;

public class RiskLocation {

	@SerializedName("gw_ip")
	private String gwId;
	@SerializedName("wifi_ssid")
	private String wifiSsid;
	@SerializedName("loc_info")
	private String locInfo;
	@SerializedName("A_cell_id")
	private String aCellId;
	@SerializedName("A_lac")
	private String aLac;
	@SerializedName("A_mnc")
	private String aMnc;
	@SerializedName("A_wifi_mac")
	private String aWifiMac;

	public String getGwId() {
		return gwId;
	}

	public void setGwId(String gwId) {
		this.gwId = gwId;
	}

	public String getWifiSsid() {
		return wifiSsid;
	}

	public void setWifiSsid(String wifiSsid) {
		this.wifiSsid = wifiSsid;
	}

	public String getLocInfo() {
		return locInfo;
	}

	public void setLocInfo(String locInfo) {
		this.locInfo = locInfo;
	}

	public String getaCellId() {
		return aCellId;
	}

	public void setaCellId(String aCellId) {
		this.aCellId = aCellId;
	}

	public String getaLac() {
		return aLac;
	}

	public void setaLac(String aLac) {
		this.aLac = aLac;
	}

	public String getaMnc() {
		return aMnc;
	}

	public void setaMnc(String aMnc) {
		this.aMnc = aMnc;
	}

	public String getaWifiMac() {
		return aWifiMac;
	}

	public void setaWifiMac(String aWifiMac) {
		this.aWifiMac = aWifiMac;
	}

}
