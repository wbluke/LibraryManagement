package login.action;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class SendEmail {
	
	private String auth = null;
	
	public SendEmail(String email, String subject, String text) {
		String host = "smtp.naver.com";
		final String user = "pwb0127";
		final String password = "temp1597530";
		
		String to = email;
		
		// Get the session object
		Properties props = new Properties();
		props.put("mail.smtp.host", host);
		props.put("mail.smtp.auth", "true");
		
		Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(user, password);
			}
		});
		
		// Compose the message
		try {
			MimeMessage message = new MimeMessage(session);
			message.setFrom(new InternetAddress(user));
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
			
			// Subject
			message.setSubject(subject);
			
			// Text
			message.setText(text);
			
			// send the message
			Transport.send(message);
			System.out.println("message sent successfully...");
			
		} catch (MessagingException e) {
			e.printStackTrace();
		}
		
	}
	
	public SendEmail(String email, String subject) {
		String host = "smtp.naver.com";
		final String user = "pwb0127";
		final String password = "temp1597530";
		
		String to = email;
		
		// Get the session object
		Properties props = new Properties();
		props.put("mail.smtp.host", host);
		props.put("mail.smtp.auth", "true");
		
		Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(user, password);
			}
		});
		
		// Compose the message
		try {
			MimeMessage message = new MimeMessage(session);
			message.setFrom(new InternetAddress(user));
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
			
			// Subject
			message.setSubject(subject);
			
			// Text
			String auth = "";
			for (int i = 0; i < 6; i++) {
				auth = auth + (int)(Math.random()*10);
			}
			message.setText("������ȣ�� [" + auth + "] �Դϴ�.");
			
			// send the message
			Transport.send(message);
			System.out.println("message sent successfully...");
			
			this.auth = auth;
			
		} catch (MessagingException e) {
			e.printStackTrace();
		}		
	}
	
	public String getAuth() {
		return auth;
	}

	public static void main(String[] args) {
		
		// ����
		
		// 1. �ȳ� ���ڸ� ���� �� : �̸���, ���� ����, �޽��� ������ ���ڸ� �޴� �����ڷ� �߼�
		new SendEmail("pwb0127@gmail.com", "[������ �ȳ�]", "���� [�ڹ��� ����]�� �ݳ����� 1�� ���ҽ��ϴ�. ��ü�� ���� �ʰ� �ݳ� ��Ź�帳�ϴ�.");
		
		// 2. ������ȣ�� ������ ���� ������ȣ ��ȯ���� �� :  �̸���, ���� ���� ������ ���ڸ� �޴� ������ ���� �� getter ȣ��
		String auth = new SendEmail("pwb0127@gmail.com", "������ ������ȣ").getAuth();
		System.out.println("������ȣ : " + auth);
	}
 
}