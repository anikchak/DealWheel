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
		System.out.println("Post method hit for Booking");
		String pagecontext = request.getContextPath();
		
		HttpSession session = request.getSession();
		System.out.println("session while booking="+session);
		
		if(session!=null && session.getAttribute("username") != null){
			System.out.println("Proceed with booking");
			
			RequestDispatcher rd = request.getRequestDispatcher("/ConfirmBooking.jsp");
			rd.forward(request, response);
		}else{
			session.setAttribute("comingFromPage", "Booking");
			System.out.println("No User hence log in first");
			 response.sendRedirect(pagecontext+"/Login.jsp");
			//RequestDispatcher rd = request.getRequestDispatcher("/Login.jsp");
			//rd.forward(request, response);
		}
	}

}
