package com.chinamobile.athena.risk.common.message;

import java.io.Serializable;

import org.springframework.amqp.core.Message;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.QueueingConsumer.Delivery;

public class ChannelWrappedMessage  implements Serializable {
    /**
     * @author lihaiyang
     * @email  lihaiyang@chinamobile.com
     * @tel    18867103799
     */
    private static final long serialVersionUID = 71876010112534155L;

    private Channel channel;
    
    private Delivery delivery;

    public Channel getChannel() {
        return channel;
    }

    public void setChannel(Channel channel) {
        this.channel = channel;
    }

    public Delivery getDelivery() {
        return delivery;
    }

    public void setDelivery(Delivery delivery) {
        this.delivery = delivery;
    }

  
    
    
}
