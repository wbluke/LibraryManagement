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
			message.setText("인증번호는 [" + auth + "] 입니다.");
			
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
		
		// 사용법
		
		// 1. 안내 문자만 보낼 때 : 이메일, 메일 제목, 메시지 순으로 인자를 받는 생성자로 발송
		new SendEmail("pwb0127@gmail.com", "[도서관 안내]", "도서 [자바의 정석]의 반납일이 1일 남았습니다. 연체가 되지 않게 반납 부탁드립니다.");
		
		// 2. 인증번호를 보내고 보낸 인증번호 반환받을 때 :  이메일, 메일 제목 순으로 인자를 받는 생성자 생성 후 getter 호출
		String auth = new SendEmail("pwb0127@gmail.com", "도서관 인증번호").getAuth();
		System.out.println("인증번호 : " + auth);
	}
 
}