package services.utility;

import java.math.BigInteger;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;
import java.util.List;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.User;
import services.CustomerControllerService;

public class CommonUtility {
	/**
	 * The method is used to enter records in Bookingdetails table
	 * */

	public long lockRecord(HttpServletRequest request) {
		System.out.println("Inside lock record");
		CustomerControllerService s = new CustomerControllerService();
		System.out.println("Username="
				+ (String) request.getSession().getAttribute("username"));
		System.out.println("FromDate="
				+ (String) request.getSession().getAttribute("fromDateString"));
		System.out.println("FromDate="
				+ (String) request.getSession().getAttribute("toDateString"));
		System.out.println("VehicleDetails="
				+ (String) request.getSession().getAttribute(
						"selectedVehicleDetails"));
		long loggedInUserId = 0L;
		// Logged in user details
		System.out.println(request.getSession().getAttribute(
				"LoggedInUserDetailsObject"));

		if (request.getSession().getAttribute("LoggedInUserDetailsObject") != null) {
			List<User> validUserDetails = (List<User>) request.getSession()
					.getAttribute("LoggedInUserDetailsObject");
			if (validUserDetails != null & validUserDetails.size() > 0) {
				for (User u : validUserDetails) {
					loggedInUserId = u.getUserId().longValue();
				}
			}
		}

		// loggedInUserId = (loggedInUserId!=null)?loggedInUserId:0;
		System.out.println("LoggedInUserId in lockRecord()=" + loggedInUserId);

		SimpleDateFormat sdf = new SimpleDateFormat(GenericConstant.DATEFORMAT);
		long tempBookingId = 0L;
		if (loggedInUserId > 0L) {
			if (request.getSession().getAttribute("fromDateString") != null
					&& request.getSession().getAttribute("toDateString") != null) {
				try {
					Date fromDate = sdf.parse((String) request.getSession()
							.getAttribute("fromDateString"));
					Date toDate = sdf.parse((String) request.getSession()
							.getAttribute("toDateString"));
					String vehicleDetails[] = null;
					if ((String) request.getSession().getAttribute(
							"selectedVehicleDetails") != null) {
						vehicleDetails = ((String) request.getSession()
								.getAttribute("selectedVehicleDetails")).split(
								"\\$", -1);
						tempBookingId = s.updateBooking("VIEWING", fromDate,
								toDate, vehicleDetails[0], (String) request
										.getSession().getAttribute("username"),
								loggedInUserId);
					}
				} catch (ParseException e) {
					System.out.println("Date Parsing error");
					e.printStackTrace();
				}

			}
		}
		return tempBookingId;
	}

	/**
	 * The method has logic for page navigation
	 * */
	public void pageNavigation(String pagecontext, String comingFromPage,
			HttpServletRequest request, HttpServletResponse response) {
		try {
			if (comingFromPage != null
					&& "Booking".equalsIgnoreCase(comingFromPage)) {
				// Inserting the currently viewed vehicle record - locking
				// mechanism
				long tempBookingSeq = lockRecord(request);
				if (tempBookingSeq > 0) {
					request.getSession().setAttribute("tempBookingSeq",
							tempBookingSeq);
					response.sendRedirect(pagecontext + "/ReviewBooking.jsp");
				} else {
					response.sendRedirect(pagecontext + "/BookingError.jsp");
				}
			} else if (comingFromPage == null
					|| "LandingPage".equalsIgnoreCase(comingFromPage)) {
				System.out.println("navigate");
				response.sendRedirect(pagecontext + "/LandingPage.jsp");
			}
		} catch (Exception e) {
			System.out.println("Exception occured while navigating=");
			e.printStackTrace();
		}
	}
	
	public void sendEmailNotification(String bookingNo,String uEmail) {
		final String username = "bala@doctordekhoo.in";
		final String password = "Sriramajayam1";


		Properties props = new Properties();

		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");

		props.put("mail.smtp.host", "mail.doctordekhoo.in");
		props.put("mail.smtp.port", "25");


		Session session = Session.getInstance(props,
				new javax.mail.Authenticator() {
					protected PasswordAuthentication getPasswordAuthentication() {
						return new PasswordAuthentication(username, password);
					}
				});

		try {

			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress("admin@doctordekhoo.in"));
			message.setRecipients(Message.RecipientType.TO,
			InternetAddress.parse("anikchak@gmail.com"));
			
			message.setSubject("Confirmation: Booking Id - "+bookingNo);

			message.setText("Dear Guest,"
					+ "\n\n Your booking was successful! Booking reference id="
					+ bookingNo
					+ "\n\n This mail is auto-generated using Java Code.");

			Transport.send(message);

			System.out.println("Done Email");

		} catch (MessagingException e) {
			System.out.println("Error in Email");
			throw new RuntimeException(e);
			
		} 
		   
		 } 

	
	public static String getPageName(String comingFromPage){
		if("LandingPage".equalsIgnoreCase(comingFromPage)){
			return "/LandingPage.jsp";
		}
		return "/LandingPage.jsp";
	}
	
	public static boolean validateEmail(final String hex) {
		Pattern pattern  = null;
		Matcher matcher = null;
		pattern = Pattern.compile(GenericConstant.EMAIL_PATTERN);
		matcher = pattern.matcher(hex);
		return matcher.matches();

	}
	
	public String[] activeCities(){
		
		String activeCities[] = null;
		String value = getValuesFromProperties("activeCities");
		System.out.println("Value: " + value);
		if(value!=null){
			activeCities =value.split("#",-1);
		}
		
		return activeCities;
	}
	
	public String getValuesFromProperties(String searchKey){
		String value = null;
		//Reading city values from resource bundle
		ResourceBundle rb = ResourceBundle.getBundle("properties.applicationpropertybundle");
		if(rb.containsKey(searchKey)){
			value = rb.getString(searchKey);
		}
		return value;
	}
}
