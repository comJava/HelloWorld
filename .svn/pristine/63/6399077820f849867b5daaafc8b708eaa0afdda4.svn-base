/**
 * 
 */
package com.chinamobile.athena.risk.common.mail;

import java.util.Calendar;
import java.util.Map;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.mail.Authenticator;
import javax.mail.Message.RecipientType;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.apache.commons.lang.StringUtils;
import org.apache.velocity.app.VelocityEngine;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.velocity.VelocityEngineUtils;

/**
 * @author lihiayang
 * @description 邮件发送工具类 templateName是模板类的名称 通过模板可以配置邮件的内容
 *              使用该类需在bean里配置smtpHost，mailFrom等一些邮件发送参数
 */
public  class SimpleMessageSender {
	private String smtpHost;// 邮件服务器地址
	private String mailFrom; // 发件人
	private String password;// 邮件密码
	private String mailTo; // 收件人
	private String ccTo;// 抄送地址

	private String subJect; // 邮件主题
	private String messageBody; // 邮件内容
	private String messageType; // 邮件类型
	private VelocityEngine velocityEngine;// 模板引擎
	private String templateName;
	private String auth;

	private static final String mailRegex = "^([a-zA-Z0-9_\\-\\.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([a-zA-Z0-9\\-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\\]?)$";
	private static final Pattern PATTERN = Pattern.compile(mailRegex);
	/*private final Logger logger = LogFactory
			.getLogger(SimpleMessageSender.class);*/
	private static final Logger logger = LoggerFactory.getLogger(SimpleMessageSender.class.getSimpleName());
	private Session session;
	private MimeMessage message;
	private String userName;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	private Transport transport;

	/**
	 * 初始化邮件配置
	 */
	public void init() {
		try {
			Properties props = new Properties();
			props.put("mail.smtp.host", smtpHost);
			/* props.put("mail.smtp.starttls.enable", "true"); // 使用starttls安全连接 */
			props.put("mail.smtp.auth", auth);
			logger.info("mail.smtp.auth:  " + auth);
			// props.put("mail.debug", "true"); //打印调试信息
			if (auth == null || auth.equals("false")) {
				session = Session.getInstance(props);
			} else {
				logger.info("auth:  " + auth + " userName: " + userName
						+ " password:  " + password);
				session = Session.getInstance(props, new MyAuthenticator(
						userName, password));
			}
			InternetAddress fromAddress = new InternetAddress(mailFrom);
			message = new MimeMessage(session);
			message.setFrom(fromAddress);
			String[] arrrays = mailTo.split(",");
			// 添加收件人（有可能是多个）
			for (String add : arrrays) {
				// 验证邮箱格式是否正确
				Matcher matcher = PATTERN.matcher(add);
				if (!matcher.matches()) {
					continue;
				}
				InternetAddress toAddress = new InternetAddress(add);
				// 添加收件人
				message.addRecipient(RecipientType.TO, toAddress);
			}
			// 添加抄送地址（有可能是多个）
			if (StringUtils.isNotBlank(ccTo)) {
				String[] ccAddress = ccTo.split(",");
				for (String cc : ccAddress) {
					Matcher matcher = PATTERN.matcher(cc);
					if (!matcher.matches()) {
						continue;
					}
					// 添加抄送人呢
					message.addRecipient(RecipientType.CC, new InternetAddress(
							cc));
				}
			}
			transport = session.getTransport("smtp");
			transport.connect();
		} catch (Exception e) {
			logger.info("Initializing SimpleMessageSender failed!  "
					+ e.getMessage());
		}

	}

	/**
	 * 发送邮件方法
	 * 
	 * @param logMessage
	 *            这是日志预警的实体类 可以根据业务需要进行配置
	 *            当业务比较多时可以通过继承SimpleMessageSender类并且增加sendMessageWithBoby
	 *            ()方法来根据业务需要传递不同的参数
	 */

	/**
	 * 
	 * @param model
	 *            vm文件变量值
	 * @author lihaiyang
	 * @descrption 实现邮件发送逻辑 smtp服务器以及发件人收件人抄送人等信息都是在配置文件中配置
	 *             如果配置文件中的收件人或者抄送人任何一个人的邮件地址格式有误那么将会导致所有人的邮件都收取不到
	 *             所以需要对邮件地址进行正则表达判断，如果不符合邮件地址规则，那么就不构造这个邮件地址对象
	 * 
	 */
	@SuppressWarnings("static-access")
	public int sendMessage(Map<String, Object> model) {
		try {
			// 从vm文件中读取邮件模板
			messageBody = VelocityEngineUtils.mergeTemplateIntoString(this.getVelocityEngine(), this.getTemplateName(), "UTF-8",model);
			message.setSentDate(Calendar.getInstance().getTime());
			// 设置邮件主题
			message.setSubject(subJect);
			// 设置邮件内容
			message.setContent(messageBody, messageType);
			// 发送邮件
			transport.send(message);
			logger.info("Send Message Success!");
		} catch (Exception e) {
			//邮件发送失败
			logger.error("SimpleMessageSender.sendMessage:   " + e.getMessage());
			return -1;
		}
		return 1;
	}

	/**
	 * 销毁方法
	 */
	public void destroy() {
		try {
			transport.close();
		} catch (MessagingException e) {
			logger.info("In SimmeMessageSender close():  " + e.getMessage());
		}
	}

	/**
	 * 
	 * @author lihaiyang 邮件地址身份认证类
	 *
	 */
	class MyAuthenticator extends Authenticator {
		String userName = "";
		String password = "";

		public MyAuthenticator() {

		}

		public MyAuthenticator(String userName, String password) {
			this.userName = userName;
			this.password = password;
		}

		protected PasswordAuthentication getPasswordAuthentication() {
			return new PasswordAuthentication(userName, password);
		}

		public String getSmtpHost() {
			return smtpHost;
		}
	}

	public VelocityEngine getVelocityEngine() {
		return velocityEngine;
	}

	public void setVelocityEngine(VelocityEngine velocityEngine) {
		this.velocityEngine = velocityEngine;
	}

	public void setSmtpHost(String smtpHost) {
		this.smtpHost = smtpHost;
	}

	public String getMailFrom() {
		return mailFrom;
	}

	public void setMailFrom(String mailFrom) {
		this.mailFrom = mailFrom;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getMailTo() {
		return mailTo;
	}

	public void setMailTo(String mailTo) {
		this.mailTo = mailTo;
	}

	public String getSubJect() {
		return subJect;
	}

	public String getTemplateName() {
		return templateName;
	}

	public void setTemplateName(String templateName) {
		this.templateName = templateName;
	}

	public void setSubJect(String subJect) {
		this.subJect = subJect;
	}

	public String getMessageBody() {
		return messageBody;
	}

	public void setMessageBody(String messageBody) {
		this.messageBody = messageBody;
	}

	public String getMessageType() {
		return messageType;
	}

	public void setMessageType(String messageType) {
		this.messageType = messageType;
	}

	public String getCcTo() {
		return ccTo;
	}

	public void setCcTo(String ccTo) {
		this.ccTo = ccTo;
	}

	public String getAuth() {
		return auth;
	}

	public void setAuth(String auth) {
		this.auth = auth;
	}
}
