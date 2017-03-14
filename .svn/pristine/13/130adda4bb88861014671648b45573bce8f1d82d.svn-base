package com.chinamobile.athena.risk.common.framework.impl;

import org.slf4j.Logger;

import com.chinamobile.athena.risk.common.constants.LogCode;
import com.chinamobile.athena.risk.common.framework.LifeCycle;
import com.chinamobile.athena.risk.common.framework.TaskModule;
import com.chinamobile.athena.risk.common.logging.LogFactory;

/**
 * 
     * ClassName:AbstractTaskModule <br/> 
     * Date:     2015年5月3日 下午9:16:19 <br/> 
     * @author   wangbing  
     * @email  wangbingyf@chinamobile.com
     * @version   
     * @since    JDK 1.7
     * @see
 */
public abstract class AbstractTaskModule implements TaskModule, LifeCycle{
	
	private final Logger logger = LogFactory.getLogger(LogCode.RISK_MAIN);
	private String name = this.getClass().getSimpleName();
	protected volatile boolean isLive = true;
	
	public void run() {
		try {
			logger.info("task module : " + this.getName() + " start");
			if (this.init() && this.isLive) {
				process();
			}
			
		} catch(Throwable e) {
			logger.error(e.getMessage(), e);
		} finally {
			try {
				this.destroy();
			} catch (Throwable e) {
				logger.error("exception occur when task module : " + this.getName() + " stopped \n" + e.getMessage(), e);
			}
			
			logger.info("task module : " + this.getName() + " stopped");
		}
	}
	
	public abstract void process();

	public boolean init() {
		return true;
	}

	public void destroy() {
		
	}
	
	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return this.name;
	}

	public void showdown() {
		isLive = false;
	}

	public boolean isLive() {
		return isLive;
	}

	public void setLive(boolean isLive) {
		this.isLive = isLive;
	}
	
}
