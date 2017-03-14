package com.chinamobile.athena.risk.common.entity.rule;

import java.util.HashMap;
import java.util.Map;

/**
 * 
 * @author lihaiyang
 *
 */
public class LogMessage {
	/**
	 * 规则：当实时数据分析中存在【%p】 级别为ERROR和FATAL时，触发预警。
	 * 当实时数据分析中存在【%p】 级别为WARN时,则需要对【%m】输出内容进行正则判断，如果匹配则触发预警。(正则表达式提前根据WARN级别的业务规则配置)
	 * 触发的邮件内容中含：异常抛出时间、线程名(可用来根据jdk工具导出的线程栈信息进行问题的定位)、全路径的类名及异常代码行号、异常提示信息
	 */
	private String logTime;   //日志打印时间
	private String threadName; //线程名
	private String priority;// 线程优先级  debug，info，warn，error，fatal等
	private String className;//类名
	private String output;  //按NDC（Nested Diagnostic Context，线程堆栈）顺序输出日志
	private String logMessage;//输出代码中指定的消息
	private Integer logLevel ; 
	private String regex;
	
	public String getRegex() {
		return regex;
	}
	public void setRegex(String regex) {
		this.regex = regex;
	}
	public Integer getLogLevel() {
		return logLevel;
	}
	public void setLogLevel(Integer logLevel) {
		this.logLevel = logLevel;
	}
	public String getLogTime() {
		return logTime;
	}
	public void setLogTime(String logTime) {
		this.logTime = logTime;
	}
	public String getThreadName() {
		return threadName;
	}
	public void setThreadName(String threadName) {
		this.threadName = threadName;
	}
	public String getPriority() {
		return priority;
	}
	public void setPriority(String priority) {
		this.priority = priority;
	}
	public String getClassName() {
		return className;
	}
	public void setClassName(String className) {
		this.className = className;
	}
	public String getOutput() {
		return output;
	}
	public void setOutput(String output) {
		this.output = output;
	}
	public String getLogMessage() {
		return logMessage;
	}
	public void setLogMessage(String logMessage) {
		this.logMessage = logMessage;
	}
	
	public Map<String, Object> toModel(){
		Map<String, Object>model = new HashMap<String, Object>();
		model.put("LogLevel", logLevel);
		model.put("LogTime", logTime);
		model.put("LogThreadName", threadName);
		model.put("LogThreadClass", className);
		return model;
	}
	
}
