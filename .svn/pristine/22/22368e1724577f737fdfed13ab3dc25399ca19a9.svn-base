package com.chinamobile.athena.risk.common.logging.entity;

/**
 * 
     * ClassName:StartProcessLog <br/> 
     * Date:     2015年5月3日 下午9:18:44 <br/> 
     * @author   wangbing  
     * @email  wangbingyf@chinamobile.com
     * @version   
     * @since    JDK 1.7
     * @see
 */
public class StartProcessLog extends ProcessLog {
	
	private final long startTime;

	public StartProcessLog(String processId, String processType) {
		super(processId, processType);
		startTime = System.currentTimeMillis();
	}

	public long getStartTime() {
		return startTime;
	}
}
