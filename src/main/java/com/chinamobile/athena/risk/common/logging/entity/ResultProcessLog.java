package com.chinamobile.athena.risk.common.logging.entity;

/**
 * 
     * ClassName:ResultProcessLog <br/> 
     * Date:     2015年5月3日 下午9:18:39 <br/> 
     * @author   wangbing  
     * @email  wangbingyf@chinamobile.com
     * @version   
     * @since    JDK 1.7
     * @see
 */
public class ResultProcessLog extends ProcessLog {
	
	private final String processResult;
	
	private String errorInfo;

	public ResultProcessLog(String processId, String processType, String processResult) {
		super(processId, processType);
		this.processResult = processResult;
	}

	public String getProcessResult() {
		return processResult;
	}

	public String getErrorInfo() {
		return errorInfo;
	}

	public void setErrorInfo(String errorInfo) {
		this.errorInfo = errorInfo;
	}
}
