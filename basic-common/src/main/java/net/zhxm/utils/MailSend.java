package net.zhxm.utils;

import java.util.Date;
import java.util.Properties;
import javax.mail.*;
import javax.mail.Message.RecipientType;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;


/**
 * 邮件发送工具类 SendMail
 * 
 * @author zhengxingmiao
 * @date Nov 23, 2012
 */
public class MailSend {

	private static MailSend a = null;

	public static MailSend getInstance() {
		if (a == null) {
			a = new MailSend();
		}
		return a;
	}

	/**
	 * 普通的通过构造函数传入身份验证信息的 Authenticator 子类 SmtpAuthenticator
	 * 
	 * @author zhengxingmiao
	 * @date Nov 23, 2012
	 */
	class SmtpAuthenticator extends Authenticator {

		String username;
		String password;

		public SmtpAuthenticator(String username, String password) {
			this.username = null;
			this.password = null;
			this.username = username;
			this.password = password;
		}

		public PasswordAuthentication getPasswordAuthentication() {
			return new PasswordAuthentication(username, password);
		}

	}

	/**
	 * 发送电子邮件
	 * @author zhengxingmiao
	 * @param mailInfo
	 */
	public String sendMail(MailConfigInfo mailInfo){
		String return_str = "ok";
		
		Properties props = mailInfo.getProperties();
		SmtpAuthenticator sa = null;
		if (mailInfo.isValidate()) {
			sa = new SmtpAuthenticator(mailInfo.getUserName(), mailInfo.getPassword());
		}
		// 根据邮件会话属性和密码验证器构造一个发送邮件的session 
		Session sendMailSession = Session.getInstance(props, sa);
		
		try {
			// 根据session创建一个邮件消息
			Message newMessage = new MimeMessage(sendMailSession);
			// 设置邮件消息的发送者
			newMessage.setFrom(new InternetAddress(mailInfo.getFromAddress()));
			// 创建邮件的接收者地址，并设置到邮件消息中
			newMessage.setRecipient(Message.RecipientType.TO,new InternetAddress(mailInfo.getToAddress()));
			// 设置邮件消息的主题
			newMessage.setSubject(mailInfo.getSubject());
			// 设置邮件消息发送的时间
			newMessage.setSentDate(new Date());
			
			if (mailInfo.isContentText()) {
				// 以文本格式发送邮件
				String mailContent = mailInfo.getContent();
				newMessage.setText(mailContent);
			}else{
				
				// MiniMultipart类是一个容器类，包含MimeBodyPart类型的对象
				Multipart mainPart = new MimeMultipart();
				// 创建一个包含HTML内容的MimeBodyPart
				BodyPart html = new MimeBodyPart();
				// 设置HTML内容
				html.setContent(mailInfo.getContent(), "text/html; charset=utf-8");
				mainPart.addBodyPart(html);
				// 将MiniMultipart对象设置为邮件内容
				newMessage.setContent(mainPart);
				
			}
			
			Transport.send(newMessage);
			
		} catch (MessagingException e) {
			//e.printStackTrace();
			String result = "系统提示：发送邮件失败，原因：" + e.toString();
			System.out.println(result);
			return_str = "fail";
		}
		return return_str;
	}

	/**
	 * 批量发送电子邮件
	 * @author zhengxingmiao
	 * @param smtpServer
	 * @param smtpUser
	 * @param smtpPwd
	 * @param from
	 * @param to
	 * @param subject
	 * @param body
	 * @throws MessagingException
	 */
	public void sendMailMore(MailConfigInfo mailInfo)
			throws MessagingException {
		
		Properties props = mailInfo.getProperties();
		SmtpAuthenticator sa = null;
		if (mailInfo.isValidate()) {
			sa = new SmtpAuthenticator(mailInfo.getUserName(), mailInfo.getPassword());
		}
		// 根据邮件会话属性和密码验证器构造一个发送邮件的session 
		Session sendMailSession = Session.getInstance(props, sa);
		
		try {
			// 根据session创建一个邮件消息
			Message newMessage = new MimeMessage(sendMailSession);
			// 设置邮件消息的发送者
			newMessage.setFrom(new InternetAddress(mailInfo.getFromAddress()));
			
			// 邮件的接收者数组
			InternetAddress[] adress = new InternetAddress[mailInfo.getToArrayAddress().length];
			for (int i = 0; i < adress.length; i++) {
				adress[i] = new InternetAddress(mailInfo.getToArrayAddress()[i]);
			}
			newMessage.setRecipients(RecipientType.TO, adress);
			
			// 设置邮件消息的主题
			newMessage.setSubject(mailInfo.getSubject());
			// 设置邮件消息发送的时间
			newMessage.setSentDate(new Date());
			
			if (mailInfo.isContentText()) {
				// 以文本格式发送邮件
				String mailContent = mailInfo.getContent();
				newMessage.setText(mailContent);
			}else{
				
				// MiniMultipart类是一个容器类，包含MimeBodyPart类型的对象
				Multipart mainPart = new MimeMultipart();
				// 创建一个包含HTML内容的MimeBodyPart
				BodyPart html = new MimeBodyPart();
				// 设置HTML内容
				html.setContent(mailInfo.getContent(), "text/html; charset=utf-8");
				mainPart.addBodyPart(html);
				// 将MiniMultipart对象设置为邮件内容
				newMessage.setContent(mainPart);
				
			}
			
			Transport.send(newMessage);
			
		} catch (MessagingException e) {
			//e.printStackTrace();
			String result = "系统提示：发送邮件失败，原因：" + e.toString();
			System.out.println(result);
		}
		
	}

	/**
	 * 测试Main方法
	 * 
	 * @author：zhengxingmiao
	 * @method: main
	 * @param args
	 * @time: 2011-3-7 上午11:31:55
	 */
	public static void main(String args[]) {
		
		MailConfigInfo mailInfo = new MailConfigInfo();
		mailInfo.setValidate(true);							//是否需要身份验证
		mailInfo.setToAddress("zhengxingmiao@tycmc.net");	//收件人邮件
		mailInfo.setSubject("[D1CM]找回您的帐户密码");		//邮件标题
		mailInfo.setContent("测试测试测试测试测试测试");
		
		MailSend.getInstance().sendMail(mailInfo);
		
	}
	
}