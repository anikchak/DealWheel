package servlet;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import services.mail.EmailType;
import services.mail.SendMail;

/**
 * Servlet implementation class TriggerEmail
 */
@WebServlet("/TriggerEmail")
public class TriggerEmail extends HttpServlet {
	private static final long serialVersionUID = 1L;
	static final Logger logger = Logger.getLogger(TriggerEmail.class);
       
    public TriggerEmail() {
        super();
    }
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		logger.info("Trigger Email: Post Hit");
		String emailType = (String)request.getParameter("emailType");
		String list = request.getParameter("list");
		List<String> params = Arrays.asList(list.split(","));
		String emailAddress = request.getParameter("emailAddress");
		if(EmailType.CONFIRM_BOOKING_TO_USER.toString().equals(emailType)){
			SendMail.sendEmailNotification(EmailType.CONFIRM_BOOKING_TO_USER,emailAddress, params);
		}
		else if(EmailType.CONFIRM_BOOKING_TO_VENDOR.toString().equals(emailType)){
			SendMail.sendEmailNotification(EmailType.CONFIRM_BOOKING_TO_VENDOR,emailAddress, params);
		}
		else if(EmailType.CANCEL_BOOKING_BY_USER.toString().equals(emailType)){
			SendMail.sendEmailNotification(EmailType.CANCEL_BOOKING_BY_USER,emailAddress, params);
		}
		else if(EmailType.CANCEL_BOOKING_TO_USER.toString().equals(emailType)){
			SendMail.sendEmailNotification(EmailType.CANCEL_BOOKING_TO_USER,emailAddress, params);
		}
		else if(EmailType.CANCEL_BOOKING_BY_VENDOR.toString().equals(emailType)){
			SendMail.sendEmailNotification(EmailType.CANCEL_BOOKING_BY_VENDOR,emailAddress, params);
		}
		else if(EmailType.CANCEL_BOOKING_TO_VENDOR.toString().equals(emailType)){
			SendMail.sendEmailNotification(EmailType.CANCEL_BOOKING_TO_VENDOR,emailAddress, params);
		}
		else if(EmailType.FORGOT_PASSWORD.toString().equals(emailType)){
			SendMail.sendEmailNotification(EmailType.FORGOT_PASSWORD,emailAddress, params);
		}
		else if(EmailType.VERIFY_VENDOR.toString().equals(emailType)){
			SendMail.sendEmailNotification(EmailType.VERIFY_VENDOR,emailAddress, params);
		}
		else if(EmailType.VERIFY_USER.toString().equals(emailType)){
			SendMail.sendEmailNotification(EmailType.VERIFY_USER,emailAddress, params);
		}
	}
}
