package com.chinamobile.athena.risk.common.actor;

import com.chinamobile.athena.risk.common.framework.InitCycle;

/**
 * 
     * ClassName:ActorContext <br/> 
     * Date:     2015年5月3日 下午9:15:51 <br/> 
     * @author   wangbing  
     * @email  wangbingyf@chinamobile.com
     * @version   
     * @since    JDK 1.7
     * @see
 */
public abstract class ActorContext   implements InitCycle {
	
	private String dispatcher;
	
	public abstract void processMessage(Object message);

	public String getDispatcher() {
		return dispatcher;
	}

	public void setDispatcher(String dispatcher) {
		this.dispatcher = dispatcher;
	}
	
}
