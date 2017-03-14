package com.chinamobile.athena.risk.common.logging.log4j;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.spi.LoggingEvent;

import com.chinamobile.athena.risk.common.logging.Log;
import com.chinamobile.athena.risk.common.logging.entity.EndProcessLog;
import com.chinamobile.athena.risk.common.logging.entity.ResultProcessLog;
import com.chinamobile.athena.risk.common.logging.entity.StartProcessLog;


/**
 * 
     * ClassName:Log4jImpl <br/> 
     * Date:     2015年5月3日 下午9:18:50 <br/> 
     * @author   wangbing  
     * @email  wangbingyf@chinamobile.com
     * @version   
     * @since    JDK 1.7
     * @see
 */
public class Log4jImpl implements Log{
	
	private final Logger log;
	
	private final static String FQCN = Log4jImpl.class.getName();
	private final static Logger processLog = Logger.getLogger("process.log");
	
	public Log4jImpl(Class<?> clazz) {
	      log = Logger.getLogger(clazz);
	}

	public void error(Object message, Throwable e) {
		forcedLog(log, Level.ERROR, message, e);
	}

	public void error(Object message) {
		forcedLog(log, Level.ERROR, message);
	}
	
	public void info(Object message) {
		forcedLog(log, Level.INFO, message);
	}

	public void debug(Object message) {
		if (log.isDebugEnabled()) {
			forcedLog(log, Level.DEBUG, message);
		}
	}

	public void warn(Object message) {
		forcedLog(log, Level.WARN, message);
	}

	public void processStart(String processId, String processType, String processInfo) {
		StartProcessLog log = new StartProcessLog(processId, processType);
		log.setProcessInfo(processInfo);
		processLog.debug("start : " + log);
	}

	public void processEnd(String processId, String processType, String processInfo) {
		EndProcessLog log = new EndProcessLog(processId, processType);
		log.setProcessInfo(processInfo);
		processLog.debug("end : " + log);	
	}

	public void processResult(String processId, String processType, String processInfo, 
			String processResult) {
		ResultProcessLog log = new ResultProcessLog(processId, processType, processResult);
		log.setProcessInfo(processInfo);
		processLog.debug("result : " + log);
	}

	public void processResult(String processId, String processType, String processInfo, 
			String processResult, String errorInfo) {
		ResultProcessLog log = new ResultProcessLog(processId, processType, processResult);
		log.setProcessInfo(processInfo);
		log.setErrorInfo(errorInfo);
		processLog.info("result : " + log);
	}
	
	private static void forcedLog(Logger logger, Level level, Object message) {  
        logger.callAppenders(new LoggingEvent(FQCN, logger, level, message, null));  
    }  
  
    private static void forcedLog(Logger logger, Level level, Object message, Throwable t) {  
        logger.callAppenders(new LoggingEvent(FQCN, logger, level, message, t));
    }
	
}
