package services.mail;

import java.util.List;

public class GetMailInformation {
	
	static String getBodyForMailType(EmailType type, List<String> params){
		String body = null;
		
		switch(type){
		
		case VERIFY_VENDOR:
			body = "<h1 style=\"color:Red;\">Welcome </h1>"+params.get(0)+" to DealWheel.in "
					+ " Your OTP to verify your email is "+params.get(1)+" "
							+ " Please verify your email to register a vehicle with us.";
			break;
			
		case VERIFY_USER:
			body = "Welcome "+params.get(0)+" to DealWheel.in "
					+ " Your OTP to verify your email is "+params.get(1)+" "
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
			subject = "DealWheel | OTP to verify email address";
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
