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

import org.apache.log4j.Logger;

import model.Address;
import model.Bookingshistory;
import model.ListedVehicle;
import model.User;
import model.Vehicle;
import services.CustomerControllerService;
import services.mail.SendMail;

public class CommonUtility {
	static final Logger logger = Logger.getLogger(CommonUtility.class);
	/**
	 * The method is used to enter records in Bookingdetails table
	 * */

	@SuppressWarnings("unchecked")
	public static long lockRecord(HttpServletRequest request) {
		logger.info("Inside lock record");
		CustomerControllerService s = new CustomerControllerService();
		logger.info("Username="
				+ (String) request.getSession().getAttribute("username"));
		logger.info("FromDate="
				+ (String) request.getSession().getAttribute("fromDateString"));
		logger.info("FromDate="
				+ (String) request.getSession().getAttribute("toDateString"));
		logger.info("VehicleDetails="
				+ (String) request.getSession().getAttribute(
						"selectedVehicleDetails"));
		long loggedInUserId = 0L;
		// Logged in user details
		logger.info(request.getSession().getAttribute("LoggedInUserDetailsObject"));

		if (request.getSession().getAttribute("LoggedInUserDetailsObject") != null) {
			List<User> validUserDetails = (List<User>) request.getSession().getAttribute("LoggedInUserDetailsObject");
			if (validUserDetails != null & validUserDetails.size() > 0) {
				for (User u : validUserDetails) {
					loggedInUserId =Long.parseLong(u.getUserId());
				}
			}
		}

		// loggedInUserId = (loggedInUserId!=null)?loggedInUserId:0;
		logger.info("LoggedInUserId in lockRecord()=" + loggedInUserId);

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
						//Vehicle details contains vehicleAddress and listedVehicleId in vehicleAddress#listedVehicleId format
						vehicleDetails = ((String) request.getSession().getAttribute("selectedVehicleDetails")).split("#", -1);
						tempBookingId = s.updateBooking("VIEWING", fromDate,
								toDate, vehicleDetails[0],vehicleDetails[1], (String) request
										.getSession().getAttribute("username"),
								loggedInUserId);
					}
				} catch (ParseException e) {
					logger.info("Date Parsing error");
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
				logger.info("navigate");
				response.sendRedirect(pagecontext + "/LandingPage.jsp");
			}
		} catch (Exception e) {
			logger.info("Exception occured while navigating=");
			e.printStackTrace();
		}
	}
	


	
	public static String getPageName(String comingFromPage){
		if("LandingPage".equalsIgnoreCase(comingFromPage)){
			return "/LandingPage.jsp";
		}else if("SearchResult".equalsIgnoreCase(comingFromPage)){
			return "/SearchResult.jsp";
		}else if("ReviewBooking".equalsIgnoreCase(comingFromPage)){
			return "/ReviewBooking.jsp";
		}
		else{
			return "/LandingPage.jsp";
		}
	}
	
	public static boolean validateEmail(final String hex) {
		Pattern pattern  = null;
		Matcher matcher = null;
		pattern = Pattern.compile(GenericConstant.EMAIL_PATTERN);
		matcher = pattern.matcher(hex);
		logger.info("validateEmail return result="+matcher.matches());
		return matcher.matches();

	}
	
	public static String[] activeCities(){
		
		String activeCities[] = null;
		String value = getValuesFromProperties("activeCities");
		if(value!=null){
			activeCities =value.split("#",-1);
		}
		
		return activeCities;
	}
	
	public static String getValuesFromProperties(String searchKey){
		String value = null;
		//Reading city values from resource bundle
		ResourceBundle rb = ResourceBundle.getBundle("properties.applicationpropertybundle");
		if(rb.containsKey(searchKey)){
			value = rb.getString(searchKey);
		}
		//logger.info("Value from resource bndl="+value);
		return value;
	}
	
	@SuppressWarnings("unchecked")
	public static List fetchVehicleUsingTempBooking(String tempBookingId){
		logger.info("Inside fetchVehicleUsingTempBooking");
		//logger.info("Temp booking Id="+tempBookingId);
		CustomerControllerService s = new CustomerControllerService();
		List<Object[]> vehicleDetailsUsingTempBookingId = null;
		if(tempBookingId!=null){
			vehicleDetailsUsingTempBookingId = (List<Object[]>)s.fetchVehicleUsingTempBooking(tempBookingId);
		}
		return vehicleDetailsUsingTempBookingId;
	}
	
	public static List fetchMyBookingsHistory(BigInteger userId){
		logger.info("Inside fetchMyBookingsHistory");
		CustomerControllerService s = new CustomerControllerService();
		List<Object[]> myBookingsHistoryList= s.getMyBookingsHistory (userId);
		return myBookingsHistoryList;
	}
	
	public static List fetchMyProfile(BigInteger userId){
		logger.info("Inside Fetch My Profile");
		CustomerControllerService s = new CustomerControllerService();
		List<Object[]> myprofiledetails = s.getMyProfile(userId);
		return myprofiledetails;
	}
}
