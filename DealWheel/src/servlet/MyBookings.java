package servlet;

import java.io.IOException;
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
		System.out.println("MyBookings:DoGet");
		String uName = (String) request.getSession().getAttribute(GenericConstant.USERNAME);
		System.out.println("USER NAME "+uName);
		CustomerControllerService s = new CustomerControllerService();
		List<Object[]> MyBookList= s.getMyBookings(uName);
		System.out.println("Size"+MyBookList.size());
		request.getSession().setAttribute("MyBookingHistory", MyBookList);
		String pagecontext = request.getContextPath();
		response.sendRedirect(pagecontext+ GenericConstant.NAV_TO_MYBOOKINGS_PAGE);
		
	}
	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
