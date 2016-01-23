package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import services.CustomerControllerService;

/**
 * Servlet implementation class Logout
 */
@WebServlet("/Logout")
public class Logout extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Logout() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("POst method hit in Logout");
		response.setContentType("text/html");
        String pagecontext = request.getContextPath();
        String initiateVendorFlow = (String)request.getParameter("invoke");
        System.out.println("initiateFlow = "+initiateVendorFlow);
        
        String pageId = (String)request.getSession().getAttribute("currentPage");
        System.out.println("Logout.java: tempBookingId="+(String)request.getParameter("fetchSelectedVehicle"));
        //If logout flow is invoked from Review Page then clearing the held vehicle first 
        if("ReviewBooking".equalsIgnoreCase(pageId)){
        	if((String)request.getParameter("fetchSelectedVehicle") != null){
        	new CustomerControllerService().cleanBookingUsingTempBookingId((String)request.getParameter("fetchSelectedVehicle"));
        	}
        }
        //invalidate the session if exists
        HttpSession session = request.getSession(false);
        if(session != null){
        	session.removeAttribute("vendorFlow");
            session.invalidate();
        }
        if(initiateVendorFlow!=null && "vendorFlow".equalsIgnoreCase(initiateVendorFlow)){
        	response.sendRedirect(pagecontext+"/VendorLoginSignUp.jsp?invoke=vendorFlow");
        }
        else{
         response.sendRedirect(pagecontext+"/LandingPage.jsp");
        }
        
        response.setHeader("Cache-Control","no-cache"); //Forces caches to obtain a new copy of the page from the origin server
        response.setHeader("Cache-Control","no-store"); //Directs caches not to store the page under any circumstance
        response.setDateHeader("Expires", 0); //Causes the proxy cache to see the page as "stale"
        response.setHeader("Pragma","no-cache"); //HTTP 1.0 backward compatibility
    }
}
