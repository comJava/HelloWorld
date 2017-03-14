package com.chinamobile.athena.risk.common.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.commons.lang.StringUtils;


public class DateUtils {

	private DateUtils(){};
	
	public static String getCurDateStr(String pattern) {
		return formatDate(new Date(),pattern);
	}
	
	public static String formatDate(Date date,String pattern) {
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		return sdf.format(date);
	}
	
	public static Date getDateByStrng(String date, String pattern) {
		DateFormat dateFormat = new SimpleDateFormat(pattern);
		try {
			return dateFormat.parse(date);
		} catch (Exception e) {
			return null;
		}
	}
	
	public static String getDateTime(String date,String pattern){
		if(StringUtils.isBlank(date)){
			return null;
		}
		Date d = getDateByStrng(date, pattern);
		if(d!=null)
			return String.valueOf(d.getTime());
		return null;
	}
	
	public static String calcDateTime(Date date,String pattern,int days){
		  SimpleDateFormat sdf = new SimpleDateFormat(pattern);//格式化对象
		  Calendar calendar = Calendar.getInstance();//日历对象
		  calendar.setTime(date);//设置当前日期
		  calendar.add(Calendar.DATE, days);//月份减一
		  return sdf.format(calendar.getTime());
	}
	
   public static boolean validDate(String dateStr,String pattern){
        try{
        	DateFormat formatter = new SimpleDateFormat(pattern);
            Date date = (Date)formatter.parse(dateStr);
            return dateStr.equals(formatter.format(date));
        }catch(Exception e){
           return false;
        }
    } 
	
	public static String getConverseDateTime(String date,String pattern){
		if(StringUtils.isBlank(date)){
			return null;
		}
		Date d = getDateByStrng(date, pattern);
		if(d!=null)
			return String.valueOf(Long.MAX_VALUE-d.getTime());
		return null;
	}
	
}
