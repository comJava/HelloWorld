package com.chinamobile.athena.risk.common.message.impl;

import java.io.IOException;
import java.net.ConnectException;

import org.slf4j.Logger;
import org.springframework.amqp.AmqpConnectException;
import org.springframework.amqp.AmqpIOException;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

import com.chinamobile.athena.risk.common.constants.LogCode;
import com.chinamobile.athena.risk.common.logging.LogFactory;
import com.chinamobile.athena.risk.common.message.MessageReceiver;

/**
 * ClassName:SimpleMessageReceiver <br/>
 * Date: 2015年5月3日 下午9:19:31 <br/>
 * 
 * @author wangbing
 * @email wangbingyf@chinamobile.com
 * @version
 * @since JDK 1.7
 * @see
 */
public class SimpleMessageReceiver implements MessageReceiver<Object>{

    private final Logger       logger = LogFactory.getLogger(LogCode.RISK_MAIN);

    private final RabbitTemplate template;
    private String             queueName;
    
    public SimpleMessageReceiver(RabbitTemplate template) {
        super();
        this.template = template;
        
    }
    public SimpleMessageReceiver(RabbitTemplate template,String queueName){
    	super();
    	this.template = template;
    	this.queueName = queueName;
    }

    public AmqpTemplate getTemplate() {
        return template;
    }

    

    public String getQueueName() {
        return queueName;
    }

    public void setQueueName(String queueName) {
        this.queueName = queueName;
    }


    public Object receive() {
        try {   
          Object message = template.receiveAndConvert(queueName);
          
            if (message == null) {
                Thread.sleep(500);
            } else {
                logger.info("get  message from queue : " + queueName);
            }

            return message;
        } catch (Exception e) {
            if (e instanceof ConnectException || e instanceof AmqpConnectException || e instanceof IOException
                    || e instanceof AmqpIOException) {
                logger.warn("get ConnectException in message receiver");
            } else {
                logger.error(e.getMessage(), e);
            }
        }
        
        return null;
    }

   
}
