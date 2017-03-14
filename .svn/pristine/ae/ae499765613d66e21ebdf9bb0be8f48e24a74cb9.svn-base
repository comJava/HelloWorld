package com.chinamobile.athena.risk.common.message.impl;

import java.net.ConnectException;

import org.slf4j.Logger;
import org.springframework.amqp.AmqpConnectException;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.core.ChannelCallback;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

import com.chinamobile.athena.risk.common.constants.LogCode;
import com.chinamobile.athena.risk.common.logging.LogFactory;
import com.chinamobile.athena.risk.common.message.MessageSender;
import com.rabbitmq.client.AMQP.Queue.DeclareOk;
import com.rabbitmq.client.Channel;

/**
 * 
     * ClassName:SimpleMessageSender <br/> 
     * Date:     2015年5月3日 下午9:19:36 <br/> 
     * @author   wangbing  
     * @email  wangbingyf@chinamobile.com
     * @version   
     * @since    JDK 1.7
     * @see
 */
public class SimpleMessageSender implements MessageSender<Object> {
	
	private final Logger logger = LogFactory.getLogger(LogCode.RISK_MAIN);
	
	private final AmqpTemplate template;
	private String exchangeName;
	private String routingKey;
	private CachingConnectionFactory mqConnFactory;
	
	public CachingConnectionFactory getMqConnFactory() {
		return mqConnFactory;
	}

	public SimpleMessageSender(AmqpTemplate template) {
		super();
		this.template = template;
	}

	public SimpleMessageSender(AmqpTemplate template,CachingConnectionFactory mqConnFactory){
		super();
		this.template = template;
		this.mqConnFactory = mqConnFactory;
	}
	
	public AmqpTemplate getTemplate() {
		return template;
	}

	public void sendMessage(Object message) {
		try {
			template.convertAndSend(exchangeName,routingKey,message);
		} catch (Exception e) {
			if (e instanceof ConnectException || e instanceof AmqpConnectException) {
				logger.info("get IO ConnectException in message sender, going to stop dbpc executor");
			} 
			logger.error(e.getMessage(), e);
		}
		
	}
	
	public int getMessageNum() {
		try {
			DeclareOk declareOk = ((RabbitTemplate)template).execute(new ChannelCallback<DeclareOk>() {
		        public DeclareOk doInRabbit(Channel channel) throws Exception {
		            return channel.queueDeclarePassive(routingKey);
		        }
		    });
		    return declareOk.getMessageCount();
		} catch (Exception ex) {
			logger.error(ex.getMessage());
			throw new RuntimeException(ex);
		}
	}

	public String getExchangeName() {
		return exchangeName;
	}

	public void setExchangeName(String exchangeName) {
		this.exchangeName = exchangeName;
	}

	public String getRoutingKey() {
		return routingKey;
	}

	public void setRoutingKey(String routingKey) {
		this.routingKey = routingKey;
	}
}
