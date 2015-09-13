package services.utility;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import services.CustomerControllerService;

public class CommonUtility {
	/**
	 * The method is used to enter records in Bookingdetails table
	 * */
	
	public long lockRecord(HttpServletRequest request){
		System.out.println("Inside lock record");
		CustomerControllerService s = new CustomerControllerService();
		System.out.println("Username="+(String)request.getSession().getAttribute("username"));
		System.out.println("FromDate="+(String)request.getSession().getAttribute("fromDateString"));
		System.out.println("FromDate="+(String)request.getSession().getAttribute("toDateString"));
		System.out.println("VehicleDetails="+(String)request.getSession().getAttribute("selectedVehicleDetails"));
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MMM-dd");
		long tempBookingId=0;
		if(request.getSession().getAttribute("fromDateString")!=null && request.getSession().getAttribute("toDateString")!=null){
			try {
				Date fromDate = sdf.parse((String)request.getSession().getAttribute("fromDateString"));
				Date toDate = sdf.parse((String)request.getSession().getAttribute("toDateString"));
				String vehicleDetails[] = null;
				if((String)request.getSession().getAttribute("selectedVehicleDetails") !=null){
				vehicleDetails  = ((String)request.getSession().getAttribute("selectedVehicleDetails")).split("\\$",-1);
				tempBookingId = s.updateBooking("VWNG",fromDate,toDate,vehicleDetails[0],vehicleDetails[2],(String)request.getSession().getAttribute("username"));
				}
			} catch (ParseException e) {
				System.out.println("Date Parsing error");
				e.printStackTrace();
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
			if (comingFromPage != null && "Booking".equalsIgnoreCase(comingFromPage)) {
				//Inserting the currently viewed vehicle record - locking mechanism
				//lockRecord(request);
				request.getSession().setAttribute("tempBookingSeq", lockRecord(request));
				response.sendRedirect(pagecontext + "/ReviewBooking.jsp");
			} else if (comingFromPage == null || "LandingPage".equalsIgnoreCase(comingFromPage)) {
				response.sendRedirect(pagecontext + "/LandingPage.jsp");
			}
		} catch (Exception e) {
			System.out.println("Exception occured while navigating=");
			e.printStackTrace();
		}
	}
}
