package com.chinamobile.athena.risk.common.webclient.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;

/**
 * 
 * <p>文件名称: PatternUtils.java</p>
 * 
 * <p>文件功能: </p>
 *
 * <p>编程者: 王兵</p>
 * 
 * <p>初作时间: 2015年3月3日 上午1:17:47</p>
 * 
 * <p>版本: version 1.0 </p>
 *
 * <p>输入说明: </p>
 *
 * <p>输出说明: </p>
 *
 * <p>程序流程: </p>
 * 
 * <p>============================================</p>
 * <p>修改序号:</p>
 * <p>时间:	 </p>
 * <p>修改者:  </p>
 * <p>修改内容:  </p>
 * <p>============================================</p>
 */
public class PatternUtils {
	
    private static final ConcurrentMap<String,Pattern> patternCache;
	
	static{
		patternCache = new ConcurrentHashMap<String,Pattern>();
	}
	
	public static Pattern compile(String regex){
		if (StringUtils.isBlank(regex)) {
			return null;
		}
		Pattern pattern = patternCache.get(regex);
		if(pattern != null){
			return pattern;
		}
		patternCache.putIfAbsent(regex, Pattern.compile(regex));
		return patternCache.get(regex);
	}
	
	public static Pattern compile(String regex, int flags){
        Pattern pattern = patternCache.get(regex);
        if(pattern != null){
            return pattern;
        }
        patternCache.putIfAbsent(regex, Pattern.compile(regex, flags));
        return patternCache.get(regex);
    }
	
	public static boolean match(Pattern pattern, String str){
		Matcher matcher = pattern.matcher(str);
		return matcher.matches();
	}
	
	public static boolean match(String regex, String str){
		Pattern pattern = compile(regex);
		return match(pattern,str);
	}
	
	public static Matcher matcher(String regex, String str){
		Pattern pattern = compile(regex);
		return pattern.matcher(str);
	}
	
	public static Matcher matcher(Pattern pattern, String str){
		return pattern.matcher(str);
	}
	
	public static String group(String str, Pattern pattern, int index){
		if(str == null){
			return StringUtils.EMPTY;
		}
		Matcher matcher = pattern.matcher(str);
		if(matcher.find()){
			return matcher.group(index);
		}
		return StringUtils.EMPTY;
	}
	
	public static String group(String str, String regex, int index){
		Pattern pattern = compile(regex);
		return group(str,pattern,index);
	}
	
	public static List<String> findAll(String str, Pattern pattern, int index){
		if(str == null){
			return Collections.emptyList();
		}
		List<String> results = new ArrayList<String>();
		Matcher matcher = pattern.matcher(str);
		while(matcher.find()){
			results.add(matcher.group(index));
		}
		
		return results;
	}
	
	public static List<String> findAll(String str, String regex, int index){
		Pattern pattern = compile(regex);
		return findAll(str,pattern,index);
	}

}
