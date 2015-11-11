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

import services.CustomerControllerService;
import services.utility.GenericConstant;

/**
 * Servlet implementation class Search
 */
@WebServlet("/Search")
public class Search extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
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
		System.out.println("Post method hit for Search");
		
		CustomerControllerService s = new CustomerControllerService();
		
		Map displaySearchResultMap = null;
		String pagecontext = request.getContextPath();
		String fromDateString = request.getParameter(GenericConstant.FROMDATE);
		String toDateString = request.getParameter(GenericConstant.TODATE);
		String selectedLocation = (String)request.getSession().getAttribute("selectedLocation");
		
		try{
			
			System.out.println("fromDate = "+fromDateString+" endDate="+toDateString+" Selected Location="+selectedLocation);
			SimpleDateFormat sdf = new SimpleDateFormat(GenericConstant.DATEFORMAT);
			if(fromDateString!=null && toDateString!=null){
				Date fromDate = sdf.parse(fromDateString);
				Date toDate = sdf.parse(toDateString);
				System.out.println("Date:"+fromDate+"....."+toDate);
				displaySearchResultMap = s.fetchSearchResult(fromDate, toDate,selectedLocation);
				
			}
			
		}catch(Exception e){
			System.out.println("Exception while searching vehicles");
		}
		
		if(displaySearchResultMap !=null){
			HttpSession session = request.getSession();
			if(session !=null){
				session.setAttribute(GenericConstant.DISPLAYSEARCHRESULTMAP, displaySearchResultMap);
				session.setAttribute(GenericConstant.FROMDATESTRING, fromDateString);
				session.setAttribute(GenericConstant.TODATESTRING, toDateString);
				response.sendRedirect(pagecontext+GenericConstant.NAV_TO_SEARCHRESULT_PAGE);
			}
		}
	}
	
}
