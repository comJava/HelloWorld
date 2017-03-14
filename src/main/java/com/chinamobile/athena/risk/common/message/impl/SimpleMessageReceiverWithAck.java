/**
 * 
 */
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
import com.chinamobile.athena.risk.common.message.ChannelWrappedMessage;
import com.chinamobile.athena.risk.common.message.MessageReceiver;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.QueueingConsumer;
import com.rabbitmq.client.QueueingConsumer.Delivery;

/** 
 * @author: lihaiyang
 * @email:  lihaiyang@chinamobile.com
 * @Tel  :  18867103799
 */
public class SimpleMessageReceiverWithAck implements MessageReceiver<Object> {
    private final Logger       logger = LogFactory.getLogger(LogCode.RISK_MAIN);
    private final RabbitTemplate template;
    private String             queueName;
    private Channel            channel;
    private QueueingConsumer   consumer;
    
    public SimpleMessageReceiverWithAck(RabbitTemplate template){
	super();
	this.template = template;
    }


    public Object receive() {
        try {
            if(channel == null){
                channel = template.getConnectionFactory().createConnection().createChannel(false);
                consumer = new QueueingConsumer(channel);
                channel.queueDeclare(queueName, true, false, false, null);
                channel.basicConsume(queueName, false,consumer);
            }
            
            Delivery delivery = consumer.nextDelivery();
            ChannelWrappedMessage object  = new ChannelWrappedMessage();
            object.setDelivery(delivery);
            object.setChannel(channel);
            return object;
        }catch (Exception e) {
            if (e instanceof ConnectException || e instanceof AmqpConnectException || e instanceof IOException
                    || e instanceof AmqpIOException) {
                logger.warn("get ConnectException in message receiver");
            } else {
                logger.error(e.getMessage(), e);
            }
        }
        return null;
    }

    public String getQueueName() {
        return queueName;
    }

    public void setQueueName(String queueName) {
        this.queueName = queueName;
    }
    
    public AmqpTemplate getTemplate() {
        return template;
    }
    

}
