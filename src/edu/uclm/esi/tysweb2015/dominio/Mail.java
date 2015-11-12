package edu.uclm.esi.tysweb2015.dominio;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.apache.tomcat.util.codec.binary.Base64;

public class Mail {
	public static MimeMessage createEmail(String to, String from, String subject,
		      String bodyText) throws MessagingException {
		    Properties props = new Properties();
		    Session session = Session.getDefaultInstance(props, null);

		    MimeMessage email = new MimeMessage(session);
		    InternetAddress tAddress = new InternetAddress(to);
		    InternetAddress fAddress = new InternetAddress(from);

		    email.setFrom(new InternetAddress(from));
		    email.addRecipient(javax.mail.Message.RecipientType.TO,
		                       new InternetAddress(to));
		    email.setSubject(subject);
		    email.setText(bodyText);
		    return email;
	}
	
	 public static Message createMessageWithEmail(MimeMessage email)
		      throws MessagingException, IOException {
		    ByteArrayOutputStream bytes = new ByteArrayOutputStream();
		    email.writeTo(bytes);
		    String encodedEmail = Base64.encodeBase64URLSafeString(bytes.toByteArray());
		    Message message = new Message();
		    message.setRaw(encodedEmail);
		    return message;
	}

}
