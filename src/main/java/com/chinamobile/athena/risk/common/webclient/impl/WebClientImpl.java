package com.chinamobile.athena.risk.common.webclient.impl;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.Header;
import org.apache.http.HeaderElement;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.CookieSpecs;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.GzipDecompressingEntity;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.chinamobile.athena.risk.common.webclient.Response;
import com.chinamobile.athena.risk.common.webclient.RetryHandler;
import com.chinamobile.athena.risk.common.webclient.WebClient;
import com.chinamobile.athena.risk.common.webclient.WebContext;
import com.chinamobile.athena.risk.common.webclient.util.URIBuilder;
import com.chinamobile.athena.risk.common.webclient.util.UrlUtils;

/**
 * 
 * <p>文件名称: WebClientImpl.java</p>
 * 
 * <p>文件功能: </p>
 *
 * <p>编程者: 王兵</p>
 * 
 * <p>初作时间: 2015年3月3日 上午1:19:05</p>
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
 * <p>时间:    </p>
 * <p>修改者:  </p>
 * <p>修改内容:  </p>
 * <p>============================================</p>
 */
public class WebClientImpl implements WebClient {

	private static final Logger logger = LoggerFactory.getLogger(WebClientImpl.class);

    public static final Map<String, String> defaultHeaders;

    private static  String ENCODE;
    static {
        Map<String, String> headers = new HashMap<String, String>();
        headers.put("User-Agent", "Mozilla/5.0 (Ubuntu; X11; Linux i686; rv:8.0) Gecko/20100101 Firefox/8.0");
        headers.put("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
        headers.put("Accept-Language", "cs,en-us;q=0.7,en;q=0.3");
        headers.put("Accept-Charset", "windows-1250,"+ENCODE+";q=0.7,*;q=0.7");
        defaultHeaders = Collections.unmodifiableMap(headers);
    }

    private final CloseableHttpClient httpClient;
    private final RequestConfig requestconfig;
    private long maxLength = 2097152; //max length 2M

    public WebClientImpl() {
        this(500, 5, 10000, 10000,"utf-8");
    }
    
    public WebClientImpl(String encode) {
    	this(500, 5, 10000, 10000,encode);
    }

    public WebClientImpl(int maxConnections, int maxPreRoute, int coTimeOut, int soTimeOut,String encode) {
    	ENCODE=encode;
        PoolingHttpClientConnectionManager cm = new PoolingHttpClientConnectionManager();
        cm.setMaxTotal(maxConnections);//连接池最大并发连接数
        cm.setDefaultMaxPerRoute(maxPreRoute);//单路由最大并发数
        httpClient = HttpClients.custom().setConnectionManager(cm).build();
        requestconfig = RequestConfig.custom().setSocketTimeout(soTimeOut).setConnectTimeout(coTimeOut).setCookieSpec(CookieSpecs.BEST_MATCH).build();
    }

    public Response getUrl(String url, Map<String, String> params, Map<String, String> headers, WebContext cookie) throws Exception {
        logger.info("get url : " + url + " with prarms :" + params );
        url = this.fromatUrl(this.resolveGetParams(url, params));
        logger.debug("execute url : " + url);
        HttpGet httpGet = this.buildGetMethod(url, headers);
        httpGet.setConfig(requestconfig);
        try {
            HttpContext httpContext = (HttpContext) cookie;
            HttpResponse httpResponse = httpClient.execute(httpGet, httpContext);
            logger.info("response after getting url : " + url + " with prarms :" + params);
            Response response = this.resloveHttpResponse(httpGet, httpResponse);
            logger.debug("get response: \n" + response + " \nwith url: " + url);
            return response;
        } finally {
        	httpGet.abort();
        }
    }
    
    public Response getUrl(String url, Map<String, String> params, Map<String, String> headers, WebContext cookie, RetryHandler handler) throws Exception {
        if (handler != null) {
            for (int i = 0; i < handler.getMaxRetryCount(); i++) {
                Response response = null;
                Exception exception = null;
                try {
                    response = this.getUrl(url, params, headers, cookie);
                } catch (Exception e) {
                    logger.error(e.getMessage(), e);
                    exception = e;
                }

                if (handler.needRetry(response, exception)) {
                    logger.info("retry url: " + url + " with response: " + response + " and exception : " + exception);
                } else {
                    return response;
                }
            }
        }
        
        return this.getUrl(url, params, headers, cookie);
    }

    public Response getUrl(String url, Map<String, String> params, WebContext cookie, RetryHandler handler) throws Exception {
        return this.getUrl(url, params, null, cookie, handler);
    }

    public Response getUrl(String url, Map<String, String> params, WebContext cookie) throws Exception {
        return this.getUrl(url, params, null, cookie);
    }

    public Response getUrl(String url, Map<String, String> params, RetryHandler handler) throws Exception {
        return this.getUrl(url, params, null, new WebContextImpl(), handler);
    }

    public Response getUrl(String url, Map<String, String> params) throws Exception {
        return this.getUrl(url, params, null, new ExceptionRetryHandler());
    }

    public Response getUrl(String url, WebContext cookie, RetryHandler handler) throws Exception {
        return this.getUrl(url, null, null, cookie, handler);  
    }

    public Response getUrl(String url, WebContext cookie) throws Exception {
        return this.getUrl(url, null, null, cookie); 
    }

    public Response getUrl(String url, RetryHandler handler) throws Exception {
        return this.getUrl(url, null, null, new WebContextImpl(), handler);  
    }

    public Response getUrl(String url) throws Exception {
        return this.getUrl(url,null,null,null,null);
    }

    public Response postUrl(String url, Map<String, String> params, Map<String, String> headers, WebContext cookie, RetryHandler handler) throws Exception {
        if (handler != null) {
            for (int i = 0; i < handler.getMaxRetryCount(); i++) {
                Response response = null;
                Exception exception = null;
                try {
                    response = this.postUrl(url, params, headers, cookie);
                } catch (Exception e) {
                    logger.error(e.getMessage(), e);
                    exception = e;
                }

                if (handler.needRetry(response, exception)) {
                    logger.info("retry url: " + url + " with response: " + response + " and exception : " + exception);
                } else {
                    return response;
                }

            }
        }
        return this.postUrl(url, params, headers, cookie);
    }

    public Response postUrl(String url, Map<String, String> params, Map<String, String> headers, WebContext cookie) throws Exception {
    	url = this.fromatUrl(url);
        logger.info("post url : " + url + " with prarms :" + params);
        HttpPost httpPost = this.buildPostMethod(url, headers);
        httpPost.setConfig(requestconfig);
        try {
        	this.resolvePostParams(httpPost, params);
            HttpContext httpContext = (HttpContext) cookie;
            HttpResponse httpResponse = this.httpClient.execute(httpPost, httpContext);
            logger.info("reponse after posting url : " + url + " with prarms :" + params );
            Response response = this.resloveHttpResponse(httpPost, httpResponse);
            logger.debug("post response: \n" + response + " \nwith url: " + url);
            return response;
        } finally {
        	httpPost.abort();
        }
    }

    public Response postUrl(String url, Map<String, String> params, WebContext cookie) throws Exception {
        return this.postUrl(url,params,null,cookie);
    }

    public Response postUrl(String url, Map<String, String> params, Map<String, String> headers, RetryHandler handler) throws Exception {
        return this.postUrl(url, params, headers, null, handler);
    }

    public Response postUrl(String url, Map<String, String> params, Map<String, String> headers) throws Exception {
        return this.postUrl(url, params, headers, new ExceptionRetryHandler());
    }

    public Response postUrl(String url, Map<String, String> params, RetryHandler handler) throws Exception {
        return this.postUrl(url, params, null, handler);
    }

    public Response postUrl(String url, Map<String, String> params) throws Exception {
        return this.postUrl(url, params, new ExceptionRetryHandler());
    }

    public Response postUrlWithRequestBody(String url, String requestBody) throws Exception {
    	url = this.fromatUrl(url);
        HttpPost httpPost = this.buildPostMethod(url, null);
        httpPost.setHeader("Content-Type", "text/plain");
        httpPost.setConfig(requestconfig);
        this.resolveRequestBody(httpPost, requestBody);
        HttpResponse httpResponse = httpClient.execute(httpPost); 
        return this.resloveHttpResponse(httpPost, httpResponse);
    }
    
  
    
    
    /* (non-Javadoc)
	 * @see cn.vobile.cyberlocker.common.webclient.WebClient#postUrlWithRequestBody(java.lang.String, java.util.Map, java.lang.String)
	 */
	public Response postUrlWithRequestBody(String url,Map<String, String> headers, String requestBody) throws Exception {
		url = this.fromatUrl(url);
		HttpPost httpPost = this.buildPostMethod(url, headers);
		httpPost.setHeader("Content-Type", "text/plain");
		httpPost.setConfig(requestconfig);
		this.resolveRequestBody(httpPost, requestBody);
		HttpResponse httpResponse = httpClient.execute(httpPost);
		return this.resloveHttpResponse(httpPost, httpResponse);
	}
    

    private String fromatUrl(String url) throws UnsupportedEncodingException {
        return  UrlUtils.fixUrl(url);
    }

    private boolean checkHttpResponse(HttpEntity httpEntity) {

        Header contentType = httpEntity.getContentType();
        if (contentType != null) {
            String[] validType = new String[]{"text", "xml", "json", "html"};
            for (String item : validType) {
                if (contentType.getValue().contains(item)) {
                	// only accept text, xml, json, html type and length less than 2M
                	long contentLength = httpEntity.getContentLength();
                	if (contentLength <= this.maxLength) {
                		return true;
                	}
                }
            }
        }

        return false;
    }

    private Response resloveHttpResponse(HttpRequestBase httpRequest, HttpResponse httpResponse) throws IOException {
    	this.handleGzip(httpResponse);
        HttpEntity httpEntity = httpResponse.getEntity();
        if (this.checkHttpResponse(httpEntity)) {
            String body = EntityUtils.toString(httpEntity, ENCODE);
            body = body != null ? body.trim() : ""; 
            int statusCode = httpResponse.getStatusLine() != null ? httpResponse.getStatusLine().getStatusCode() : 0;
            String location = httpRequest.getURI().toString();
            Response response = new Response(statusCode, body);
            response.setLocation(location);
            return response;
        }
        return null;
    }
    
    private void handleGzip(HttpResponse response) {
    	HttpEntity entity = response.getEntity();
    	Header header = entity.getContentEncoding();    	
        if (header != null) {
            HeaderElement[] codecs = header.getElements();
            for (int i = 0; i < codecs.length; i++) {
                if (codecs[i].getName().equalsIgnoreCase("gzip")) {
                    response.setEntity(
                            new GzipDecompressingEntity(entity));
                    return;
                }
            }
        }
    }

	private HttpGet buildGetMethod(String url, Map<String, String> headers) {
        HttpGet httpGet = new HttpGet(url);
        this.resolveHeaders(httpGet, defaultHeaders);
        this.resolveHeaders(httpGet, headers);
        return httpGet;
    }

    private HttpPost buildPostMethod(String url, Map<String, String> headers) {
        HttpPost httpPost = new HttpPost(url);
        this.resolveHeaders(httpPost, defaultHeaders);
        this.resolveHeaders(httpPost, headers);
        return httpPost;
    }

    private void resolveHeaders(HttpRequestBase httpRequest, Map<String, String> headers) {
        if (headers != null && !headers.isEmpty()) {
            for (Map.Entry<String, String> entry : headers.entrySet()) {
                httpRequest.setHeader(entry.getKey(), entry.getValue());
            }
        }
    }

	private String resolveGetParams(String url, Map<String, String> params)
			throws URISyntaxException {
		if (params != null && !params.isEmpty()) {
			URIBuilder uriBuilder = new URIBuilder(url);
			for (Map.Entry<String, String> entry : params.entrySet()) {
				uriBuilder.addParameter(entry.getKey(), entry.getValue());
			}
			return uriBuilder.build();
		}
		return url;
	}

	private void resolvePostParams(HttpPost httpPost, Map<String, String> params) throws UnsupportedEncodingException {
        if (params != null && !params.isEmpty()) {
            List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(params.size());
            for (Map.Entry<String, String> entry : params.entrySet()) {
                nameValuePairs.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
            }
            httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
        }
    }

    private void resolveRequestBody(HttpPost httpPost, String requestBody) throws UnsupportedEncodingException {
        StringEntity entity = new StringEntity(requestBody, ENCODE);
        httpPost.setEntity(entity);
    }

    public long getMaxLength() {
        return maxLength;
    }

    public void setMaxLength(long maxLength) {
        this.maxLength = maxLength;
    }

}
