package com.chinamobile.athena.risk.common.message.impl;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Exchange;
import org.springframework.amqp.core.Queue;

/**
 * 
     * ClassName:BindingFactory <br/> 
     * Date:     2015年5月3日 下午9:19:17 <br/> 
     * @author   wangbing  
     * @email  wangbingyf@chinamobile.com
     * @version   
     * @since    JDK 1.7
     * @see
 */
public class BindingFactory {
	public static Binding createBinding(Queue queue, Exchange exchange, String routingKey) {
		 return BindingBuilder.bind(queue).to(exchange).with(routingKey).noargs();
	}
}
