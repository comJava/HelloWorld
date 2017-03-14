package com.chinamobile.athena.risk.common.framework.impl;

import com.chinamobile.athena.risk.common.framework.Executor;


/**
 * 
     * ClassName:AbstractExecutor <br/> 
     * Date:     2015年5月3日 下午9:16:08 <br/> 
     * @author   wangbing  
     * @email  wangbingyf@chinamobile.com
     * @version   
     * @since    JDK 1.7
     * @see
 */
public abstract class AbstractExecutor implements Executor{
	
	private String name = this.getClass().getSimpleName();
	private boolean mutex = false;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isMutex() {
		return this.mutex;
	}

	public void setMutex(boolean mutex) {
		this.mutex = mutex;
	}
}
