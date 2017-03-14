package com.chinamobile.athena.risk.common.webclient.impl;

import com.chinamobile.athena.risk.common.webclient.RetryHandler;

/**
 * 
 * <p>文件名称: AbstractRetryHandler.java</p>
 * 
 * <p>文件功能: </p>
 *
 * <p>编程者: 王兵</p>
 * 
 * <p>初作时间: 2015年3月3日 上午1:18:57</p>
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
public abstract class AbstractRetryHandler implements RetryHandler {

    private int maxRetryCount = 3;
    private int noProxyIndex = 0; 

    public  AbstractRetryHandler() {
    }

    public int getMaxRetryCount() {
        return maxRetryCount;
    }

    public void setMaxRetryCount(int maxRetryCount) {
        this.maxRetryCount = maxRetryCount;
    }
    
	public int getNoProxyIndex() {
		return noProxyIndex;
	}

	public void setNoProxyIndex(int noProxyIndex) {
		this.noProxyIndex = noProxyIndex;
	}
}
