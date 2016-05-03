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
			body = "<p><span style=\"color: #687074; font-size: 13.5px;\">Welcome </span> "
					+ "<span style=\"color: #85b213; font-size: 14px; font-weight: bold; text-transform: uppercase;\">"
					+ "<span style=\"color: #85b213; font-size: 14px; font-weight: bold; text-transform: uppercase;\">"+params.get(0)+", <br /></span></span></p>"
					+ "<p style=\"color: #687074; font-size: 12px;\">Kindly verify your email to register with us by clicking on the link below:<br /> "
					+ "<a style=\"color: #85b213; font-size: 13px;font-weight:bold;\" href=\""+params.get(1)+"\">Verify Me</a> <br /><br /> "
					+ "For any other query kindly drop a mail to <span style=\"color: #85b213; font-size: 13px;\">support@dealwheel.in</span> <br /><br /> "
					+ "Happy Riding,<br /> <span style=\"color: #85b213; font-size: 13px;\">Team DealWheel</span></p>";
			
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
			
			body = "<div style=\"color: #687074;\"><span style=\";font-size: 12px;\">Hello <span style=\";font-size: 14px; color: #85b213;\">vendor@gmail.com , "
					+ "<br /><br /></span></span> <span style=\";font-size: 12px;\">Your vehicle has been booked by "
					+ "<span style=\";font-size: 14px; color: #85b213; font-weight: bold;\">customer@gmail.com</span>. <br /><br /> "
					+ "<span style=\";font-size: 13px;\">Booking Reference# </span><span style=\"font-weight: bold; font-size: 14px; color: #85b213;\">"
					+ "1234567890</span> <br /><br /></span> <br /><table style=\"border-collapse: collapse; width: 100%; border: 1px solid #85b213;\">"
					+ "<tbody><tr><th style=\"text-align: left; padding: 8px; background-color: #85b213; color: white;\">Vehicle Booked</th>"
					+ "<th style=\"text-align: left; padding: 8px; background-color: #85b213; color: white;\">PickUp Date/Time</th>"
					+ "<th style=\"text-align: left; padding: 8px; background-color: #85b213; color: white;\">Drop off Date/Time</th>"
					+ "<th style=\"text-align: left; padding: 8px; background-color: #85b213; color: white;\">Pickup/Drop off Location</th>"
					+ "<th style=\"text-align: left; padding: 8px; background-color: #85b213; color: white;\">User Contact</th>"
					+ "<th style=\"text-align: left; padding: 8px; background-color: #85b213; color: white;\">Amount Paid</th></tr>"
					+ "<tr><td style=\"text-align: left; padding: 8px;\">Hero Honda Splendor</td>"
					+ "<td style=\"text-align: left; padding: 8px;\">28-05-2016 / 9AM</td>"
					+ "<td style=\"text-align: left; padding: 8px;\">28-05-2016 / 7PM</td>"
					+ "<td style=\"text-align: left; padding: 8px;\">We Ride Adventure Bike Rental Services, HSR Layout.</td>"
					+ "<td style=\"text-align: left; padding: 8px;\">9874563210</td>"
					+ "<td style=\"text-align: left; padding: 8px;\">1000/-</td></tr></tbody></table><br /> "
					+ "<span style=\"font-size: 12px;\">Payble Security Deposit = <span style=\"font-weight: bold; font-size: 14px;\">5000/-</span></span> "
					+ "<br /><br /> <span style=\"font-weight: bold; font-size: 12px;\">Terms and Conditions:</span><ul style=\"font-size: 11px;\">"
					+ "<li>Terms and condition 1</li><li>Terms and condition 2</li><li>Terms and condition 3</li></ul><br /><br /> "
					+ "<span style=\";font-size: 12px;\">Happy Riding,</span><br /> <span style=\";font-size: 12px; color: #85b213;\">Team DealWheel</span>"
					+ "<br /> <span style=\";font-size: 11px; color: #85b213;\">support@dealwheel.in</span></div>";
			break;
			
		case FORGOT_PASSWORD:
			/*
			body = "Gotcha!..Seems you forgot your password. No Issues, here are your details \n"
					+ "Username: "+params.get(0)+" \n"
							+ "Password: "+params.get(1)+"\n"
									+ "\n"
									+ "Enjoy You ride !";
			*/
			body = "<div style=\"color: #687074;\">"
					+ "<span style=\";font-size: 12px;\">Hello <span style=\";font-size: 14px; color: #85b213;\">"+params.get(0)+" , <br /><br /></span></span> "
					+ "<span style=\"font-size: 12px;\"> Looks like you have forgotten your password. No worries, here's your new password "
					+ "<span style=\"font-weight: bold; font-size: 14px; color: #85b213;\">"+params.get(1)+"</span>. <br /><br /> "
					+ "After logging in you can change the password to your liking by navigating to <em style=\";font-size: 13.5px;\">My Profile</em> section. <br />"
					+ "<br /> For any other assistance, feel free to drop a note at <span style=\";font-size: 13.5px; color: #85b213;\">support@dealwheel.in</span> "
					+ "</span> <br /><br /> <span style=\";font-size: 12px;\">Happy Riding,</span><br /> <span style=\";font-size: 12px; color: #85b213;\">"
					+ "Team DealWheel</span><br /> <span style=\";font-size: 11px; color: #85b213;\">support@dealwheel.in</span></div>";
			break;
			
		default:
			body = "";
			break;
			
		}
		
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
