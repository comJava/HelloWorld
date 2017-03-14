package com.chinamobile.athena.risk.common.util;

import java.lang.reflect.Type;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * 
     * ClassName:GsonUtils <br/> 
     * Date:     2015年5月3日 下午9:42:36 <br/> 
     * @author   wangbing  
     * @email  wangbingyf@chinamobile.com
     * @version   
     * @since    JDK 1.7
     * @see
 */
public class GsonUtils {

	private static final Gson gson;

	static {
		GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder.setDateFormat("yyyy-MM-dd HH:mm:ss");
		gson = gsonBuilder.create();
	}

	public static String toJson(Object object) {
		return gson.toJson(object);
	}

	public static Object fromJson(String json, Class<?> type) {
		return gson.fromJson(json, type);
	}

	public static Object fromJson(String json, Type type) {
		return gson.fromJson(json, type);
	}

}
