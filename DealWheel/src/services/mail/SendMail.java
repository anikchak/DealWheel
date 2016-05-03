package services.mail;

import java.util.List;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.apache.log4j.Logger;

public class SendMail {
	static final Logger logger = Logger.getLogger(SendMail.class);
	
	public static void sendEmailNotification(EmailType type, String emailAddress, List<String> params) {
		try {
			logger.info("Sending Email to "+emailAddress);
			String FROM = "admin@dealwheel.in";
			String TO = emailAddress;
			// TODO
			// Need to add disclaimer and phone number details

			// Supply your SMTP credentials below. Note that your SMTP
			// credentials are different from your AWS credentials.
			String SMTP_USERNAME = "AKIAIIIQEUDPG6JSMHZA"; // Replace with your SMTP username.
			String SMTP_PASSWORD = "AqX9xiLD8tEEPknMEew3dHXSDBHIvC6RlgZF4Z2ki1Ca"; // Replace with your SMTP password.

			// Amazon SES SMTP host name. This example uses the US West (Oregon) region.
			String HOST = "email-smtp.us-west-2.amazonaws.com";

			// Port we will connect to on the Amazon SES SMTP endpoint. We are
			// choosing port 25 because we will use
			// STARTTLS to encrypt the connection.
			int PORT = 25;

			// Create a Properties object to contain connection configuration
			// information.
			Properties props = System.getProperties();
			props.put("mail.transport.protocol", "smtp");
			props.put("mail.smtp.port", PORT);

			// Set properties indicating that we want to use STARTTLS to encrypt
			// the connection.
			// The SMTP session will begin on an unencrypted connection, and
			// then the client
			// will issue a STARTTLS command to upgrade to an encrypted
			// connection.
			props.put("mail.smtp.auth", "true");
			props.put("mail.smtp.starttls.enable", "true");
			// props.put("mail.smtp.starttls.required", "true");

			// Create a Session object to represent a mail session with the
			// specified properties.
			Session session = Session.getDefaultInstance(props);

			// Create a message with the specified information.
			MimeMessage msg = new MimeMessage(session);
			msg.setFrom(new InternetAddress(FROM));
			msg.setRecipient(Message.RecipientType.TO, new InternetAddress(TO));
			msg.setSubject(GetMailInformation.getSubjectForMail(type));
			msg.setContent(GetMailInformation.getBodyForMailType(type, params), "text/html; charset=utf-8");

			// Create a transport.
			Transport transport = session.getTransport();

			// Send the message.
			try {
				logger.info("SendMail : Attempting to send an email through the Amazon SES SMTP interface...");

				// Connect to Amazon SES using the SMTP username and password
				// you specified above.
				transport.connect(HOST, SMTP_USERNAME, SMTP_PASSWORD);

				// Send the email.
				transport.sendMessage(msg, msg.getAllRecipients());
				logger.info("Email sent!");
			} catch (Exception ex) {
				logger.info("The email was not sent.");
				logger.info("Error message: " + ex.getMessage());
			} finally {
				// Close and terminate the connection.
				transport.close();
			}
		} catch (MessagingException e) {
			logger.info("Error in Email");
			throw new RuntimeException(e);

		}

	}

}
