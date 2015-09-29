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
	public static final String NAV_TO_VENDOR_HOME_PAGE = "/VendorHome.jsp";
	public static final String NAV_TO_ADD_VEHICLE_PAGE = "/AddVehicle.jsp";
	public static final String NAV_TO_MYBOOKINGS_PAGE = "/MyBookings.jsp";
	public static final String NAV_TO_HOME_PAGE = "/LandingPage.jsp";
	public static final String NAV_TO_ADD_LOCATION_PAGE = "/AddNewLocation.jsp";
	
	//NAMED QUERIES
	public static final String LOGIN_DETAIL_FIND_ALL = "LoginDetail.findAll";
	public static final String LOGIN_DETAIL_FIND_USING_USER_NAME ="LoginDetail.findDetailUsingUserName";
	public static final String LOGIN_DETAIL_UPDATE_LAST_LOGIN = "LoginDetail.UpdateLastLoginDetail";
	public static final String LOGIN_DETAIL_FIND_USING_USER_NAME_AND_TYPE ="LoginDetail.findDetailUsingUserName";
	
	public static final String USER_FIND_ALL = "User.findAll";
	public static final String USER_FIND_BY_ID = "User.findByUserId";
	public static final String USER_FIND_BY_EMAIL = "User.findByEmail";
	
	public static final String VEHICLE_GET_NAMES = "Vehicle.getAllNames";
	public static final String VEHICLE_FIND_ALL = "Vehicle.findAll";
	public static final String VEHICLE_GET_VEHICLE_DETAILS_FOR_USER = "Vehicle.findVehicleDetailForUserId";
	
	public static final String BOOKING_HISTORY_FIND_ALL = "Bookingshistory.findAll";
	public static final String BOOKING_HISTORY_FIND_BOOKING_BY_SEQ = "Bookingshistory.findBookingSeq";
	public static final String BOOKING_HISTORY_UPDATE = "Bookingshistory.UpdateBooking";
	
	public static final String ADDRESS_FIND_ALL = "Address.findAll";
	public static final String ADDRESS_FIND_FOR_USER_ID_AND_TYPE = "Address.findAddressForUserId";
	
	// Entities Model
	public static final String USER_MODEL = "UserModel";
	public static final String ADDRESS_MODEL = "AddressModel";
	
	//	User Types
	public static final String USER_TYPE_USER = "USER";
	public static final String USER_TYPE_VENDOR = "VENDOR";
	public static final String USER_TYPE_ADMIN = "ADMIN";
	
	// Address Type
	public static final String ADDRESS_TYPE_VENDOR_OFFICE_LOCATION = "OFFICE";
	public static final String ADDRESS_TYPE_PICKUP_LOCAION = "PICKUP";
	
	// Vehicle Type
	public static final String VEHICLE_TYPE_TWO_WHEELER = "2-WHEELER";
	public static final String VEHICLE_TYPE_FOUR_WHEELER = "4-WHEELER";
	
	// LOCATION OF OPERATIONS
	public static final String COUNTRY_OF_OPERATIONS_FILE = "/LocationOfOperation.xml";
	public static final String XML_OBJECT_COUNTRY = "COUNTRY";
	public static final String XML_OBJECT_STATE = "STATE";
	public static final String XML_OBJECT_CITY = "CITY";
	public static final String XML_ELEMENT_NAME = "name";
}
