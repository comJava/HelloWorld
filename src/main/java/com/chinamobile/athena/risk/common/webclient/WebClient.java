package com.chinamobile.athena.risk.common.webclient;


import java.util.Map;

/**
 * 
 * <p>文件名称: WebClient.java</p>
 * 
 * <p>文件功能: </p>
 *
 * <p>编程者: 王兵</p>
 * 
 * <p>初作时间: 2015年3月3日 上午1:17:09</p>
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
public interface WebClient {

    Response getUrl(String url, Map<String,String> params, Map<String,String> headers, WebContext cookie, RetryHandler handler) throws Exception;

    Response getUrl(String url, Map<String,String> params, Map<String,String> headers, WebContext cookie) throws Exception;

    Response getUrl(String url, Map<String,String> params, WebContext cookie, RetryHandler handler) throws Exception;

    Response getUrl(String url, Map<String,String> params, WebContext cookie) throws Exception;

    Response getUrl(String url, Map<String,String> params, RetryHandler handler) throws Exception;

    Response getUrl(String url, Map<String,String> params) throws Exception;

    Response getUrl(String url, WebContext cookie, RetryHandler handler) throws Exception;

    Response getUrl(String url, WebContext cookie) throws Exception;

    Response getUrl(String url, RetryHandler handler) throws Exception;

    Response getUrl(String url) throws Exception;

    Response postUrl(String url, Map<String,String> params, Map<String,String> headers, WebContext cookie, RetryHandler handler) throws Exception;

    Response postUrl(String url, Map<String,String> params, Map<String,String> headers, WebContext cookie) throws Exception;

    Response postUrl(String url, Map<String,String> params, WebContext cookie) throws Exception;

    Response postUrl(String url, Map<String,String> params, Map<String,String> headers, RetryHandler handler) throws Exception;

    Response postUrl(String url, Map<String,String> params, Map<String,String> headers) throws Exception;

    Response postUrl(String url, Map<String,String> params, RetryHandler handler) throws Exception;

    Response postUrl(String url, Map<String,String> params) throws Exception;

    Response postUrlWithRequestBody(String url, String requestBody) throws Exception;
    
    Response postUrlWithRequestBody(String url, Map<String,String> headers ,String requestBody) throws Exception;
    
}
