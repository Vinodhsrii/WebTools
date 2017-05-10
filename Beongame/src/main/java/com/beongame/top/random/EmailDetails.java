package com.beongame.top.random;
import java.util.Properties;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.*;
import com.beongame.top.pojo.Email;

public class EmailDetails {

	public boolean sendMail(Email email) throws NoSuchProviderException{
	    Properties properties = System.getProperties();
	    properties.put("mail.smtp.auth", "true");
	    properties.put("mail.smtp.starttls.enable", "true");
	    properties.put("mail.smtp.host", "smtp.gmail.com");
	    properties.put("mail.smtp.port", "587");

        Authenticator auth = new SMTPAuthenticator();
        	
        Session mailSession = Session.getDefaultInstance(properties, auth);
        String from="vinodhsrii@gmail.com";
        MimeMessage message = new MimeMessage(mailSession);

        try {
			  message.setFrom(new InternetAddress(from));		
		      message.addRecipient(Message.RecipientType.TO, new InternetAddress(email.getToEmail()));
		      message.setSubject(email.getSubject());
		      message.setContent(email.getMessage(), "text/html");
		      Transport.send(message);
		      return true;
		
		} catch (MessagingException e) {
			e.printStackTrace();
		}
        return false;
    }
}
     class SMTPAuthenticator extends javax.mail.Authenticator {
        public PasswordAuthentication getPasswordAuthentication() {
        	String username="vinodhsrii@gmail.com";
        	String password="Rahul_12";
           return new PasswordAuthentication(username, password);
        }
    

}
