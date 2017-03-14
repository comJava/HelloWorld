package com.chinamobile.athena.risk.common.logging.entity;

import com.chinamobile.athena.risk.common.util.GsonUtils;


/**
 * 
     * ClassName:ProcessLog <br/> 
     * Date:     2015年5月3日 下午9:18:34 <br/> 
     * @author   wangbing  
     * @email  wangbingyf@chinamobile.com
     * @version   
     * @since    JDK 1.7
     * @see
 */
public abstract class ProcessLog {
	
	private final String processId;
	private final String processType;
	private String processInfo;
	
	public ProcessLog(String processId, String processType) {
		super();
		this.processId = processId;
		this.processType = processType;
	}
	
	
	public String getProcessInfo() {
		return processInfo;
	}

	public void setProcessInfo(String processInfo) {
		this.processInfo = processInfo;
	}

	public String getProcessId() {
		return processId;
	}

	public String getProcessType() {
		return processType;
	}

	public String toString() {
		return GsonUtils.toJson(this);
	}
	
}
