package com.chinamobile.athena.risk.common.webclient.impl;

import java.io.IOException;

import com.chinamobile.athena.risk.common.webclient.Response;

/**
 * 
 * <p>文件名称: ExceptionRetryHandler.java</p>
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
 * <p>时间:	 </p>
 * <p>修改者:  </p>
 * <p>修改内容:  </p>
 * <p>============================================</p>
 */
public class ExceptionRetryHandler extends AbstractRetryHandler {


    /**
     * only retry on io exception happen
     * @param response
     * @param e
     * @return
     */
    public boolean needRetry(Response response, Exception e) {
       return e != null && e instanceof IOException;
    }    
    
}
