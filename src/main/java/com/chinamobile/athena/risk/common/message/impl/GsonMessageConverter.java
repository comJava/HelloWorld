package com.chinamobile.athena.risk.common.message.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;
import org.slf4j.Logger;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.amqp.support.converter.SimpleMessageConverter;
import org.springframework.util.Assert;

import com.chinamobile.athena.risk.common.constants.LogCode;
import com.chinamobile.athena.risk.common.logging.LogFactory;
import com.chinamobile.athena.risk.common.util.GsonUtils;

/**
 * 
 * ClassName:GsonMessageConverter <br/>
 * Date: 2015年5月3日 下午9:19:25 <br/>
 * 
 * @author wangbing
 * @email wangbingyf@chinamobile.com
 * @version
 * @since JDK 1.7
 * @see
 */
public class GsonMessageConverter implements MessageConverter {

  private final Logger logger = LogFactory.getLogger(LogCode.RISK_MAIN);
  private final SimpleMessageConverter converter;
  private static final String GSON_TYPE = "GSON_TYPE";
  private final ObjectMapper mapper = new ObjectMapper();

  public GsonMessageConverter() {
    this(new SimpleMessageConverter());
  }

  public GsonMessageConverter(SimpleMessageConverter convertor) {
    super();
    this.converter = convertor;
  }

  public Message toMessage(Object object, MessageProperties messageProperties) {
    String content = GsonUtils.toJson(object);
    messageProperties.setHeader(GSON_TYPE, object.getClass().getName());
    return this.converter.toMessage(content, messageProperties);
  }

  public Object fromMessage(Message message) {
    return message;
  }

  public Object fromMessage1(Message message) {
    logger.info("GsonMessageConverter fromMessage()");
    Assert.notNull(message.getMessageProperties().getHeaders().get("systemtime"),
        "header systemtime parameter is must not null");
    String systemtime = (String) message.getMessageProperties().getHeaders().get("systemtime");
    logger.info("systemtime:" + systemtime);
    try {
    	return
          mapper.readValue(new String(message.getBody()),
              new TypeReference<List<HashMap<String, HashMap<String, String>>>>() {});
    } catch (Exception e) {
      logger.info("invalid message : " + message.toString());
      logger.error(e.getMessage(), e);
    }
    return null;
  }

  public Object fromMessage2(Message message) {
    logger.info("GsonMessageConverter fromMessage()");
    Assert.notNull(message.getMessageProperties().getHeaders().get("systemtime"),
        "header systemtime parameter is must not null");
    String systemtime = (String) message.getMessageProperties().getHeaders().get("systemtime");
    logger.info("systemtime:" + systemtime);
    List<HashMap<String, String>> map = new ArrayList<HashMap<String, String>>();
    try {
      return map;
    } catch (Exception e) {
      logger.info("invalid message : " + message.toString());
      logger.error(e.getMessage(), e);
    }
    return null;
  }
}
