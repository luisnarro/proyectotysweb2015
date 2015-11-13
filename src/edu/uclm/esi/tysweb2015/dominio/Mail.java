package edu.uclm.esi.tysweb2015.dominio;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.PasswordAuthentication;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.apache.tomcat.util.codec.binary.Base64;

public class Mail {
	
	public static void main(String args[]){
		try {
			createEmail("macario.polo@gmail.com","publianunciostysweb@gmail.com", "prueba", "Hola.");
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static MimeMessage createEmail(String to, String from, String subject,
		      String bodyText) throws MessagingException {
		    Properties props = new Properties();
		    props.put("mail.smtp.host", "smtp.gmail.com");
		    props.put("mail.smtp.starttls.enable", "true");
		    props.put("mail.smtp.port", "465");
		    props.put("mail.smtp.mail.sender", "publianunciostysweb@gmail.com");
		    props.put("mail.smtp.user", "publianunciostysweb@gmail.com");
		    props.put("mail.smtp.socketFactory.port", "465");
		    props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		    props.put("mail.smtp.socketFactory.fallback", "false");
			Authenticator autentication = new autenticador(from);
			Session session = Session.getInstance(props, autentication );

		    MimeMessage email = new MimeMessage(session);
		    InternetAddress tAddress = new InternetAddress(to);
		    InternetAddress fAddress = new InternetAddress(from);

		    email.setFrom(new InternetAddress(from));
		    email.addRecipient(javax.mail.Message.RecipientType.TO,
		                       new InternetAddress(to));
		    email.setSubject(subject);
		    email.setText(bodyText);
		    Transport.send(email);
		    return email;
	}
	
/*	 public static Message createMessageWithEmail(MimeMessage email)
		      throws MessagingException, IOException {
		    ByteArrayOutputStream bytes = new ByteArrayOutputStream();
		    email.writeTo(bytes);
		    String encodedEmail = Base64.encodeBase64URLSafeString(bytes.toByteArray());
		    Message message = new MimeMessage();
		    message.setRaw(encodedEmail);
		    return message;
	}*/

}
