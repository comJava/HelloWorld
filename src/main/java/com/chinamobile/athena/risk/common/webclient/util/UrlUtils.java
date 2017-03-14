package com.chinamobile.athena.risk.common.webclient.util;

import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLDecoder;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;

/**
 * 
 * <p>文件名称: UrlUtils.java</p>
 * 
 * <p>文件功能: </p>
 *
 * <p>编程者: 王兵</p>
 * 
 * <p>初作时间: 2015年3月3日 上午1:18:04</p>
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
public class UrlUtils {
	
	private static final Pattern URL_NAME_PATTERN = PatternUtils.compile("(https?://)?(\\w+\\.)?(([^/\\s]+)\\.\\w+)(/.*)?");
	private static final Pattern URL_PATTERN = PatternUtils.compile("https?(:|&#58;)//([-\\w]+\\.)+[\\w-]+(:\\d+)?(/[^'<>,、，\\s\\\"]*)?");	
	public static String extractUrlName(String url) {
		return PatternUtils.group(url, URL_NAME_PATTERN, 4);
	}
	
	public static String extractUrlDomain(String url) {
		return PatternUtils.group(url, URL_NAME_PATTERN, 3);
	}
	
	public static String fixUrl(String url) {
		if (!StringUtils.isBlank(url) && !url.startsWith("http://") && !url.startsWith("https://")) {
			url = "http://" + url;
		}
		
		try {
			url = URLDecoder.decode(url, "UTF-8");
			return encodeUrl(url);
		} catch (Exception e){
		}
		
		return url;
	}
	
	public static String encodeUrl(String urlStr) throws URISyntaxException, MalformedURLException {
    	URL url = new URL(urlStr);
    	URI uri = new URI(url.getProtocol(), url.getUserInfo(), url.getHost(), url.getPort(), url.getPath(), url.getQuery(), url.getRef());
    	url = uri.toURL();
    	return url.toString();
 
	}
	
	public static String encodeURI(String url) throws UnsupportedEncodingException {
		return URLDecoder.decode(url, "UTF-8");
	}
	
	public static boolean checkUrlSyntax(String url) {
		if (!StringUtils.isBlank(url)) {
			return PatternUtils.match(URL_PATTERN, url);
		}
		
		return false;
	}
	
}
