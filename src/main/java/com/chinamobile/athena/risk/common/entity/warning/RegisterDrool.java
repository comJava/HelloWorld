/**
 * 
 */
package com.chinamobile.athena.risk.common.entity.warning;

import java.math.RoundingMode;
import java.text.NumberFormat;
import java.util.HashMap;
import java.util.Map;

import com.chinamobile.athena.risk.common.enumrator.WarningLevel;
import com.chinamobile.athena.risk.common.enumrator.WarningTypeEnum;


/** 
 * @ClassName RegisterDrool
 * @Description TODO
 * @author lihaiyang
 * @date 2016年5月3日 - 下午2:28:46
 * @version 1.0
 */

public class RegisterDrool {
	
	
	private long current;
	
	private double weekAvg;
	
	private String daily;
	
	private String hour;

	private String thresh = "0.5";
	
	public String getThresh() {
		return thresh;
	}

	public void setThresh(String thresh) {
		this.thresh = thresh;
	}

	public String getDaily() {
		return daily;
	}

	public void setDaily(String daily) {
		this.daily = daily;
	}

	public String getHour() {
		return hour;
	}

	public void setHour(String hour) {
		this.hour = hour;
	}

	private WarningTypeEnum type;
	
	private WarningLevel riskLevel = WarningLevel.NORMAL;
	
	private Double percent ;
	
	public Map<String, Object> toModel(){
		Map<String, Object>model = new HashMap<String, Object>();
		model.put("percent", doubleToRation(percent,2));
		model.put("current", current);
		model.put("weekAvg", weekAvg);
		model.put("daily", daily);
		model.put("hour", hour);
		model.put("thresh", thresh);
		return model;
	}
	/**
	 * 
	 * @Description TODO(这里用一句话描述这个方法的作用)
	 * @param value 需要转化的double数字
	 * @param digits  保留的小数位
	 * @return
	 */
	public  String doubleToRation(double value,int digits){
		NumberFormat nf = NumberFormat.getPercentInstance();
		nf.setMinimumFractionDigits(digits);//设置保留小数位
		nf.setRoundingMode(RoundingMode.HALF_UP); //设置舍入模式
		return nf.format(value);
	}
	
	@Override
	public String toString(){
		return "Drools Entity:  [daily: " + daily+",hour: " + hour+",current: " +current+",weekAvg: " + weekAvg +",type: " + type+",riskLevel: "+riskLevel+",percent: " + percent+"]";
	}
	public Double getPercent() {
		return percent;
	}

	public void setPercent(Double percent) {
		this.percent = percent;
	}

	public long getCurrent() {
		return current;
	}

	public void setCurrent(long current) {
		this.current = current;
	}

	public double getWeekAvg() {
		return weekAvg;
	}

	public void setWeekAvg(double weekAvg) {
		this.weekAvg = weekAvg;
	}

	public WarningTypeEnum getType() {
		return type;
	}

	public void setType(WarningTypeEnum type) {
		this.type = type;
	}

	public WarningLevel getRiskLevel() {
		return riskLevel;
	}

	public void setRiskLevel(WarningLevel riskLevel) {
		this.riskLevel = riskLevel;
	}
	
	
}
