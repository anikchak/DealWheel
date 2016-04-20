package services.mail;

import java.util.List;

public class GetMailInformation {
	
	static String getBodyForMailType(EmailType type, List<String> params){
		String body = null;
		
		switch(type){
		
		case VERIFY_VENDOR:
			body = "<span style=\"color: #687074;font-size:13.5px;\">Welcome </span>  "
					+ "<span style=\"color: #85b213;font-size:14px;font-weight:bold;text-transform:uppercase;\">"+params.get(0)+",</<span> "
							+ " <br> <p style=\"color: #687074;font-size:12px;\"> Kindly verify your email to register with us using the provided One Time Password (OTP).<br>"
							+ " One Time Password (OTP): <span style=\"color: #85b213;font-size:15px;font-weight:bold;\">"+params.get(1)+"</span> <br><br>"
							+ " For any other query kindly drop a mail to  <span style=\"color: #85b213;font-size:13px;\">support@dealwheel.in</span> <br><br> Happy Riding,<br> "
							+ "<span style=\"color: #85b213;font-size:13px;\">Team DealWheel</span><br></p>";
			
			break;
			
		case VERIFY_USER:
			body = "Welcome "+params.get(0)+" to DealWheel.in "
					+ " Confirm your registeration by clicking on the link "+params.get(1)+" "
							+ " Please verify your email to book a vehicle with us.";
			break;
			
		case CANCEL_BOOKING_BY_USER:
			body = "You have cancelled the booking For Vehicle number "+params.get(0)+" "
					+ " which was booked from "+params.get(1)+" to "+params.get(2)+""
							+ " from Vendor "+params.get(3)+ "";
			break;
			
		case CANCEL_BOOKING_TO_VENDOR:
			body = "Your booking cancelled the booking For Vehicle number "+params.get(0)+" "
					+ " which was booked from "+params.get(1)+" to "+params.get(2)+""
							+ " from Vendor "+params.get(3)+ "";
			break;
		
		case CANCEL_BOOKING_BY_VENDOR:
			body = "You have cancelled the booking For Vehicle number "+params.get(0)+" "
					+ " which was booked from "+params.get(1)+" to "+params.get(2)+""
						+ " for User "+params.get(3)+ "";
			break;
			
		case CANCEL_BOOKING_TO_USER:
			body = "Your booking for Vehicle No  "+params.get(0)+" "
					+ " which was booked from "+params.get(1)+" to "+params.get(2)+""
						+" has been cancelled by the Vendor "+params.get(3);
			break;
			
		case CONFIRM_BOOKING_TO_USER:
			body = "Congratulations! You have successfully booked Vehicle "+params.get(0) +" "
					+ " from "+params.get(1)+" to "+params.get(2)+ ""
							+ " from Vendor "+params.get(3)+" ";
			break;
		
		case CONFIRM_BOOKING_TO_VENDOR:
			body = "Congratulations! Your Vehicle "+params.get(0) +" is successfully booked "
					+ " from "+params.get(1)+" to "+params.get(2)+ ""
							+ " by User "+params.get(3)+" ";
			break;
			
		case FORGOT_PASSWORD:
			body = "Gotcha!..Seems you forgot your password. No Issues, here are your details \n"
					+ "Username: "+params.get(0)+" \n"
							+ "Password: "+params.get(1)+"\n"
									+ "\n"
									+ "Enjoy You ride !";
			break;
			
		default:
			body = "";
			break;
			
		}
		
		body = body +"\n\n\n\n\n\n"+ "Thank you."
				+ "DealWheel Team";
		return body;
	}

	
	public static String getSubjectForMail(EmailType type) {
		String subject= null;
		
		switch(type){
		
		case VERIFY_VENDOR:
			subject = "DealWheel | OTP to verify email address";
			break;
		
		case VERIFY_USER:
			subject = "DealWheel | Verify email address";
			break;
			
		case CANCEL_BOOKING_BY_USER:
			subject = "DealWheel | Cancelled Booking Notification";
			break;
			
		case CANCEL_BOOKING_TO_USER:
			subject = "DealWheel | Cancelled Booking Notification";
			break;
			
		case CANCEL_BOOKING_TO_VENDOR:
			subject = "DealWheel | Cancelled Booking Notification";
			break;
			
		case CANCEL_BOOKING_BY_VENDOR:
			subject = "DealWheel | Cancelled Booking Notification";
			break;
			
		case CONFIRM_BOOKING_TO_USER:
			subject = "DealWheel | Confirm Booking Notification";
			break;
		
		case CONFIRM_BOOKING_TO_VENDOR:
			subject = "DealWheel | Confirm Booking Notification";
			break;
			
		case FORGOT_PASSWORD:
			subject = "DealWheel | Forgot Password";
			break;
			
		default:
			subject = "";
			break;
			
		}
		return subject;
	}
}
