package services.utility;

public class GenericConstant {

	//GENERIC STRING NAMES
	public static String SELECTEDVEHICLEDETAILS = "selectedVehicleDetails";
	public static String USERNAME = "username";
	public static String TEMPBOOKINGSEQ = "tempBookingSeq";
	public static String COMINGFROMPAGE = "comingFromPage";
	public static String BOOKING = "Booking";
	public static String FROMDATESTRING = "fromDateString";
	public static String TODATESTRING = "toDateString";
	public static String DISPLAYSEARCHRESULTMAP = "displaySearchResultMap";
	public static String DOLLARFORSPLIT = "\\$";
	public static String PASSWORD = "password";
	public static String OPTION = "option";
	public static String OLDREGISTRATION = "oldRegistration";
	public static String NEWREGISTRATION = "newRegistration";	
	public static String LOGINERROR = "LOGIN_ERROR";
	public static String SESSIONID = "sessionID";
	public static String FROMDATE = "fromDate";
	public static String TODATE = "toDate";
	public static String DATEFORMAT = "yyyy-MMM-dd";
	
	//Page Navigations String
	public static String NAV_TO_CONFIRMBOOKING_PAGE= "/ConfirmBooking.jsp";
	public static String NAV_TO_LOGIN_PAGE = "/Login.jsp";
	public static String NAV_TO_SEARCHRESULT_PAGE = "/SearchResult.jsp";
	
	public static final String NAV_TO_VENDORLOGINSIGNUP_PAGE = "/VendorLoginSignUp.jsp";
	public static final String NAV_TO_VENDORREGISTRATION_PAGE = "/VendorRegister.jsp";
	public static final String NAV_TO_VENDOR_HOME_PAGE = "/AddVehicle.jsp";
	public static final String NAV_TO_ADD_VEHICLE_PAGE = "/AddVehicle.jsp";
	public static final String NAV_TO_MYBOOKINGS_PAGE = "/MyBookings.jsp";
	
	//NAMED QUERIES
	public static final String LOGIN_DETAIL_FIND_ALL = "LoginDetail.findAll";
	public static final String LOGIN_DETAIL_FIND_USING_USER_NAME ="LoginDetail.findDetailUsingUserName";
	public static final String LOGIN_DETAIL_UPDATE_LAST_LOGIN = "LoginDetail.UpdateLastLoginDetail";
	public static final String USER_FIND_ALL = "User.findAll";
	public static final String USER_FIND_BY_ID = "User.findByUserId";
	public static final String VEHICLE_GET_NAMES = "Vehicle.getAllNames";
	public static final String VEHICLE_FIND_ALL = "Vehicle.findAll";
}
