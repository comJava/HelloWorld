package com.chinamobile.athena.risk.common.webclient.impl;


import org.apache.http.client.CookieStore;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;

import com.chinamobile.athena.risk.common.webclient.WebContext;

/**
 * 
 * <p>文件名称: WebContextImpl.java</p>
 * 
 * <p>文件功能: </p>
 *
 * <p>编程者: 王兵</p>
 * 
 * <p>初作时间: 2015年3月3日 上午1:21:41</p>
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
public class WebContextImpl implements HttpContext, WebContext {
	
	private final HttpContext httpContext;

	public WebContextImpl() {
		super();
		this.httpContext = new BasicHttpContext();
		CookieStore cookieStore = new BasicCookieStore();
		this.httpContext.setAttribute(HttpClientContext.COOKIE_STORE, cookieStore);
	}

	public Object getAttribute(String arg0) {
		return httpContext.getAttribute(arg0);
	}

	public Object removeAttribute(String arg0) {
		return httpContext.removeAttribute(arg0);
	}

	public void setAttribute(String arg0, Object arg1) {
		httpContext.setAttribute(arg0, arg1);
	}

	public HttpContext getHttpContext() {
		return httpContext;
	}
}
