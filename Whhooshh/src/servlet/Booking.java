package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import services.utility.CommonUtility;
import services.utility.GenericConstant;

/**
 * Servlet implementation class Booking
 */
@WebServlet("/ConfirmBooking")
public class Booking extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Booking() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("Post method hit for Booking");
		String pagecontext = request.getContextPath();

		HttpSession session = request.getSession();
		System.out.println("session while booking=" + session);
		
		String selectedVehicleDetails = request.getParameter(GenericConstant.SELECTEDVEHICLEDETAILS);
		System.out.println("selectedVehicleDetails = " + selectedVehicleDetails);
		session.setAttribute(GenericConstant.SELECTEDVEHICLEDETAILS,selectedVehicleDetails);

		if (session != null
				&& session.getAttribute(GenericConstant.USERNAME) != null) {
			System.out.println("Proceed with booking");
			request.getSession().setAttribute(GenericConstant.TEMPBOOKINGSEQ, new CommonUtility().lockRecord(request));
			
			response.sendRedirect(pagecontext+GenericConstant.NAV_TO_CONFIRMBOOKING_PAGE);
			//RequestDispatcher rd = request.getRequestDispatcher(GenericConstant.NAV_TO_CONFIRMBOOKING_PAGE);
			//rd.forward(request, response);
		} else {
			session.setAttribute(GenericConstant.COMINGFROMPAGE, GenericConstant.BOOKING);
			System.out.println("No User hence log in first");
			response.sendRedirect(pagecontext+ GenericConstant.NAV_TO_LOGIN_PAGE);

		}

	}

}
