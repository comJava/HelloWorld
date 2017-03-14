package com.chinamobile.athena.risk.common.framework.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;

import org.slf4j.Logger;

import com.chinamobile.athena.risk.common.constants.LogCode;
import com.chinamobile.athena.risk.common.framework.ScheduledTaskModule;
import com.chinamobile.athena.risk.common.framework.TaskModule;
import com.chinamobile.athena.risk.common.framework.TransientTaskModule;
import com.chinamobile.athena.risk.common.logging.LogFactory;

/**
 * 
     * ClassName:SimpleExecutor <br/> 
     * Date:     2015年5月3日 下午9:16:31 <br/> 
     * @author   wangbing  
     * @email  wangbingyf@chinamobile.com
     * @version   
     * @since    JDK 1.7
     * @see
 */
@SuppressWarnings("rawtypes")
public class SimpleExecutor extends AbstractExecutor{
	
	private final Logger logger = LogFactory.getLogger(LogCode.RISK_MAIN);
	
	private ExecutorService  executor;
	private ScheduledExecutorService scheduledExecutors;
	private List<TaskModule> tasks;
	private List<Future> futures;
	
	public SimpleExecutor() {
		super();
		this.executor = Executors.newCachedThreadPool();
		this.scheduledExecutors = Executors.newScheduledThreadPool(5);
		futures = new ArrayList<Future>();
	}

	public void start() {
		try {
			for (TaskModule module : tasks) {
				
				if (module instanceof TransientTaskModule) {
					executor.submit(module);
					continue;
				}
				
				Future<?> future = null;
				if (module instanceof ScheduledTaskModule) {
					ScheduledTaskModule scheduledModule = (ScheduledTaskModule) module;
					future = scheduledExecutors.scheduleAtFixedRate(module, 0, scheduledModule.getInterval(), scheduledModule.getTimeUnit());
				} else {
					future = executor.submit(module);
				}
				futures.add(future);
			}
			
		}catch(Throwable e) {
			logger.error(e.getMessage(), e);
		}
	}
	
	public void shutdown() {
		executor.shutdownNow();
		scheduledExecutors.shutdownNow();
		for (Future future : futures) {
			future.cancel(true);
		}
		
		for (TaskModule task : tasks) {
			task.showdown();
		}
 	}

	public ExecutorService getExecutor() {
		return executor;
	}

	public void setExecutor(ExecutorService executor) {
		this.executor = executor;
	}

	public ScheduledExecutorService getScheduledExecutors() {
		return scheduledExecutors;
	}

	public void setScheduledExecutors(ScheduledExecutorService scheduledExecutors) {
		this.scheduledExecutors = scheduledExecutors;
	}

	public List<TaskModule> getTasks() {
		return tasks;
	}

	public void setTasks(List<TaskModule> tasks) {
		this.tasks = tasks;
	}
	
}
