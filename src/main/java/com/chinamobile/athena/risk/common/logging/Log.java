package com.chinamobile.athena.risk.common.logging;

/**
 * 
     * ClassName:Log <br/> 
     * Date:     2015年5月3日 下午9:18:55 <br/> 
     * @author   wangbing  
     * @email  wangbingyf@chinamobile.com
     * @version   
     * @since    JDK 1.7
     * @see
 */
public interface Log {

	void error(Object message, Throwable e);

	void error(Object message);
	
	void info(Object message);

	void debug(Object message);

	void warn(Object message);
	
	void processStart(String processId, String processType, String processInfo);
	
	void processEnd(String processId, String processType, String processInfo);
	
	void processResult(String processId, String processType, String processInfo, String processResult);
	
	void processResult(String processId, String processType, String processInfo, String processResult, String errorInfo);
}


