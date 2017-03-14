package com.chinamobile.athena.risk.common.webclient.util;

/**
 * 
 * <p>文件名称: URIBuilder.java</p>
 * 
 * <p>文件功能: </p>
 *
 * <p>编程者: 王兵</p>
 * 
 * <p>初作时间: 2015年3月3日 上午1:17:54</p>
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
public class URIBuilder {
	
	private StringBuffer sb;
	
	public URIBuilder(String url) {
		sb = new StringBuffer(url);
	}

	public void addParameter(String name, String value) {
		if (sb.indexOf("?") == -1) {
			sb.append("?");
		} else {
			sb.append("&");
		}
		sb.append(name);
		sb.append("=");
		sb.append(value);
	}
	
	public String build() {
		return sb.toString();
	}

}
