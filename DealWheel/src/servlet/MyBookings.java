package servlet;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import services.CustomerControllerService;
import services.utility.GenericConstant;

/**
 * Servlet implementation class MyBookings
 */
@WebServlet("/MyBookings")
public class MyBookings extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MyBookings() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("DoGet");
		
	}
	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String pagecontext = request.getContextPath();
		
		if (request.getParameter("tempBookingName")!=null){
			System.out.println("tempBookingId = "+request.getParameter("tempBookingName"));
			String bookingIdToCancel = (String)request.getParameter("tempBookingName");
			CustomerControllerService s = new CustomerControllerService();
			int status = s.cancelBooking(bookingIdToCancel);
			
			if(status>0){
				response.sendRedirect(pagecontext+ GenericConstant.NAV_TO_MYBOOKINGS_PAGE);
			}else{
				response.sendRedirect(pagecontext+"/BookingError.jsp");
				
			}
		}
	}
}
