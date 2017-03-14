package com.chinamobile.athena.risk.common.framework.impl;

import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;

import com.chinamobile.athena.risk.common.constants.LogCode;
import com.chinamobile.athena.risk.common.framework.LifeCycle;
import com.chinamobile.athena.risk.common.framework.ScheduledTaskModule;
import com.chinamobile.athena.risk.common.logging.LogFactory;

/**
 * 
     * ClassName:AbstractScheduledTask <br/> 
     * Date:     2015年5月3日 下午9:16:13 <br/> 
     * @author   wangbing  
     * @email  wangbingyf@chinamobile.com
     * @version   
     * @since    JDK 1.7
     * @see
 */
public abstract class AbstractScheduledTask implements ScheduledTaskModule, LifeCycle{

	
	private final Logger logger = LogFactory.getLogger(LogCode.RISK_MAIN);
	private String name = this.getClass().getSimpleName();
	protected final long interval;
	protected TimeUnit timeUnit;
	protected volatile boolean isLive = true;
	private volatile boolean isFirst = false;
	private long initialDelay;
	
	public AbstractScheduledTask(long interval) {
		super();
		this.interval = interval;
		this.timeUnit = TimeUnit.SECONDS;
	}
	
	public AbstractScheduledTask(long interval, TimeUnit timeUnit) {
		super();
		this.interval = interval;
		this.timeUnit = timeUnit;
	}

	public void run() {
		try {
			logger.info("scheduled task module : " + this.getName() + " start");
			if(!this.init()){
				TimeUnit.SECONDS.sleep(initialDelay);
			}
			if (this.isLive) {
				process();
			}
			
		} catch(Throwable e) {
			logger.error("exception occur when task module : " + this.getName() + " stopped \n" + e.getMessage(), e);
		}finally {
			try {
				this.destroy();
			} catch (Throwable e) {
				logger.error("exception occur when task module : " + this.getName() + " destroy \n" + e.getMessage(), e);
			}
			
			logger.info("scheduled task module : " + this.getName() + " stopped");
		}
	}
	
	public abstract void process();
	
	/**
	 * 
	 * @Description 如果想每次启动的时候就执行任务，那么重写该方法返回-1即可
	 * @return
	 */
	public abstract long getInitialDelay();
	
	public boolean init() {
		initialDelay = getInitialDelay();
		if(!isFirst && initialDelay < 0){
			isFirst = true;
		}
		return isFirst;
	}

	public void destroy() {
		
	}
	
	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return this.name;
	}

	public long getInterval() {
		return interval;
	}

	public TimeUnit getTimeUnit() {
		return timeUnit;
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
