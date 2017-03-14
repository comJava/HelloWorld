package com.chinamobile.athena.risk.common.message;

import org.slf4j.Logger;

import com.chinamobile.athena.risk.common.constants.LogCode;
import com.chinamobile.athena.risk.common.framework.impl.AbstractTaskModule;
import com.chinamobile.athena.risk.common.logging.LogFactory;

/**
 * 
     * ClassName:MessageListener <br/> 
     * Date:     2015年5月3日 下午9:19:42 <br/> 
     * @author   wangbing  
     * @email  wangbingyf@chinamobile.com
     * @version   
     * @since    JDK 1.7
     * @see
 */
public abstract  class MessageListener<T> extends AbstractTaskModule{
	
	private final Logger logger = LogFactory.getLogger(LogCode.RISK_MAIN);
	private MessageReceiver<? extends T> messageReceiver;
	
	@Override
	public void process() {
		try {
			while (!Thread.interrupted() && this.isLive) {
			 try{
				T message = messageReceiver.receive();
				this.processMessage(message);
			 }catch(Exception ex){
				logger.error("Thread process " + this.getMessageReceiver()+ " receiver message or do message error,errorMsg is "+ex.getMessage());
				try {
					   Thread.sleep(2000);
				 } catch (InterruptedException ie) {
					   logger.error(" Thread sleep is error :"+ie.getMessage());
				 }
			 }
			} 
		} catch (Throwable e) {
			try {
				logger.error(e.getMessage(), e);
				Thread.sleep(2000);// Avoid infinite retry
			} catch (InterruptedException e1) {
				logger.error(e1.getMessage(),e1);
			}
		}
	}

	public abstract void processMessage(T message);

	public MessageReceiver<? extends T> getMessageReceiver() {
		return messageReceiver;
	}

	public void setMessageReceiver(MessageReceiver<T> messageReceiver) {
		this.messageReceiver = messageReceiver;
	}	
}
