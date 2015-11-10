package services.utility;

public class GenericConstant {

	//GENERIC STRING NAMES
	public static final String SELECTEDVEHICLEDETAILS = "selectedVehicleDetails";
	public static final String USERNAME = "username";
	public static final String TEMPBOOKINGSEQ = "tempBookingSeq";
	public static final String COMINGFROMPAGE = "comingFromPage";
	public static final String BOOKING = "Booking";
	public static final String FROMDATESTRING = "fromDateString";
	public static final String TODATESTRING = "toDateString";
	public static final String DISPLAYSEARCHRESULTMAP = "displaySearchResultMap";
	public static final String DOLLARFORSPLIT = "\\$";
	public static final String HASH = "#";
	public static final String PERCENT = "%";
	public static final String DOLLAR = "$";
	public static final String PASSWORD = "password";
	public static final String OPTION = "option";
	public static final String OLDREGISTRATION = "oldRegistration";
	public static final String NEWREGISTRATION = "newRegistration";	
	public static final String AUTH_TYPE = "authType";
	public static final String LOGINERROR = "LOGIN_ERROR";
	public static final String SESSIONID = "sessionID";
	public static final String FROMDATE = "fromDate";
	public static final String TODATE = "toDate";
	public static final String DATEFORMAT = "dd/MM/yyyy";
	public static final String CANCELLED = "CANCELLED";
	public static final String UPCOMING = "UPCOMING";
	public static final String VIEWING = "VIEWING";
	public static final String PICKUP = "PICKUP";
	public static final String BKNG_STATUS = "bkngStatus";
	public static final String BKNG_NUMBER = "bkngNumber";
	public static final String BKNG_SEQ = "bkngSeq";
	public static final String BKNG_STATUS_WHERE_CLAUSE = "bkngStatusWhereClause";
	public static final String TICKER_VALUE = "tickervalue";
	public static final String TIMEDOUT = "TIMEDOUT";
	public static final String LOCKING_CODE = "lockingcode";
	public static final String USERID = "userId";
	public static final String VEHICLE_NAME = "vehicleName";
	public static final String ADDR_TYPE = "addrType";
	public static final String LAST_LOGIN_DETAIL = "lastLoginDetail";
	public static final String LOGIN_USER_NAME = "loginUserName";
	public static final String LOGIN_USER_TYPE = "loginUserType";
	public static final String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
												+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
	
	
	//Page Navigations String
	public static final String NAV_TO_CONFIRMBOOKING_PAGE= "/ConfirmBooking.jsp";
	public static final String NAV_TO_LOGIN_PAGE = "/Login.jsp";
	public static final String NAV_TO_SEARCHRESULT_PAGE = "/SearchResult.jsp";
	
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
	
	public static final String LISTED_VEHICLE_GET_MAKES = "ListedVehicle.getAllMakersNames";
	public static final String LISTED_VEHICLE_GET_NAMES = "ListedVehicle.getAllNames";
	public static final String LISTED_VEHICLE_GET_DETAILS = "ListedVehicle.findAll";
	
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
	public static final String USER_TYPE_CUSTOMER = "CUSTOMER";
	
	// Address Type
	public static final String ADDRESS_TYPE_VENDOR_OFFICE_LOCATION = "OFFICE";
	public static final String ADDRESS_TYPE_PICKUP_LOCAION = "PICKUP";
	
	// Vehicle Type
	public static final String VEHICLE_TYPE_TWO_WHEELER = "2-WHEELER";
	public static final String VEHICLE_TYPE_FOUR_WHEELER = "4-WHEELER";
	
	// LOCATION OF OPERATIONS
	public static final String COUNTRY_OF_OPERATIONS_FILE = "../LocationOfOperation.xml";
	public static final String XML_OBJECT_COUNTRY = "COUNTRY";
	public static final String XML_OBJECT_STATE = "STATE";
	public static final String XML_OBJECT_CITY = "CITY";
	public static final String XML_ELEMENT_NAME = "name";
}
