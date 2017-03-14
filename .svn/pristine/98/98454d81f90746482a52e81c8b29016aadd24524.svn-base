package com.chinamobile.athena.risk.common.message.impl;

import org.slf4j.Logger;

import com.chinamobile.athena.risk.common.actor.ActorContext;
import com.chinamobile.athena.risk.common.constants.LogCode;
import com.chinamobile.athena.risk.common.logging.LogFactory;
import com.chinamobile.athena.risk.common.message.MessageListener;

/**
 * 
     * ClassName:ActorMessageListener <br/> 
     * Date:     2015年5月3日 下午9:19:11 <br/> 
     * @author   wangbing  
     * @email  wangbingyf@chinamobile.com
     * @version   
     * @since    JDK 1.7
     * @see
 */
public class ActorMessageListener extends MessageListener<Object>{
	
	private ActorContext  actorContext;
	
	private final Logger logger = LogFactory.getLogger(LogCode.RISK_DATA_RECEIVE);

	@Override
	public void processMessage(Object message) {
		if (message != null) {
            logger.debug("receiver a message : " + message.toString());
            actorContext.processMessage(message);
        } else {
        	try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				logger.error(e.getMessage(), e);
			}
        }
	}

	public ActorContext getActorContext() {
		return actorContext;
	}

	public void setActorContext(ActorContext actorContext) {
		this.actorContext = actorContext;
	}
}
