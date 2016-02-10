package servlet;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import services.CustomerControllerService;
import services.utility.GenericConstant;

/**
 * Servlet implementation class Search
 */
@WebServlet("/Search")
public class Search extends HttpServlet {
	private static final long serialVersionUID = 1L;
	static final Logger logger = Logger.getLogger(Search.class);
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Search() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		logger.info("Post method hit for Search");
		
		CustomerControllerService s = new CustomerControllerService();
		
		@SuppressWarnings("rawtypes")
		Map displaySearchResultMap = null;
		String pagecontext = request.getContextPath();
		String fromDateString = request.getParameter(GenericConstant.FROMDATE);
		String toDateString = request.getParameter(GenericConstant.TODATE);
		String selectedLocation = (String)request.getSession().getAttribute("selectedLocation");
		String comingFromPage = (String)request.getSession().getAttribute("currentPage");
		
		//If comingFromPage = "ReviewBooking", then the selected record must be unlocked.
		if(comingFromPage!=null && "ReviewBooking".equalsIgnoreCase(comingFromPage)){
		String tempLockedVehicleId = (String)request.getSession().getAttribute("tempLockedVehicle");
		s.cleanBookingUsingTempBookingId(tempLockedVehicleId);
		logger.info("Coming from page = "+comingFromPage+" tempLockedVehicle="+tempLockedVehicleId);
		}
		try{
			logger.info("fromDate = "+fromDateString+" endDate="+toDateString+" Selected Location="+selectedLocation);
			SimpleDateFormat sdf = new SimpleDateFormat(GenericConstant.DATEFORMAT);
			if(fromDateString!=null && toDateString!=null){
				Date fromDate = sdf.parse(fromDateString);
				Date toDate = sdf.parse(toDateString);
				logger.info("Date:"+fromDate+"....."+toDate);
				displaySearchResultMap = s.fetchSearchResult(fromDate, toDate,selectedLocation);
				
			}
			
		}catch(Exception e){
			logger.info("Exception while searching vehicles");
		}
		
		HttpSession session = request.getSession();
		if(session!=null){
			session.setAttribute(GenericConstant.FROMDATESTRING, fromDateString);
			session.setAttribute(GenericConstant.TODATESTRING, toDateString);
		}
		 
		if(displaySearchResultMap !=null){
			if(session !=null){
				session.setAttribute(GenericConstant.DISPLAYSEARCHRESULTMAP, displaySearchResultMap);
				response.sendRedirect(pagecontext+GenericConstant.NAV_TO_SEARCHRESULT_PAGE);
			}
		}else {
			if(session !=null){
				session.setAttribute(GenericConstant.DISPLAYSEARCHRESULTMAP, null);
				response.sendRedirect(pagecontext+GenericConstant.NAV_TO_SEARCHRESULT_PAGE);
		}
	}
  }
}
