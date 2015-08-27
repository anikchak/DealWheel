package servlet;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import services.TestService;
import services.utility.CommonUtility;

/**
 * Servlet implementation class Login
 */
@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Login() {
		super();
		// TODO Auto-generated constructor stub
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
		TestService s = new TestService();
		
		String pagecontext = request.getContextPath();
		String uName = request.getParameter("username");
		String pwd = request.getParameter("password");
		String optionSelected = request.getParameter("option");
		String comingFromPage = (String) request.getSession().getAttribute(
				"comingFromPage");
		HttpSession session = null;
		System.out.println("option selected=" + optionSelected);
		System.out.println("Coming from page = " + comingFromPage);

		if ("oldRegistration".equalsIgnoreCase(optionSelected)) {
			Boolean validUser = s.validateUser(uName, pwd);
			if (validUser) {
				session = generateSession(request, uName);
				session.setAttribute("LOGIN_ERROR",null );
				new CommonUtility().pageNavigation(pagecontext, comingFromPage, request, response);
			}else{
				System.out.println("Invalid username or password");
				request.getSession().setAttribute("LOGIN_ERROR","Invalid username or password" );
				response.sendRedirect(pagecontext + "/Login.jsp");
				}
		} else if ("newRegistration".equalsIgnoreCase(optionSelected)) {
			Boolean status = s.inserNewUser(uName, pwd);
			System.out.println("status post registration=" + status);
			if (status) {
				session = generateSession(request, uName);
				new CommonUtility().pageNavigation(pagecontext, comingFromPage, request, response);
			}
		}
	}

	private HttpSession generateSession(HttpServletRequest request, String uName) {
		HttpSession session = request.getSession();
		System.out.println("New Session created=" + session.getId());
		session.setAttribute("username", uName.toUpperCase());
		session.setAttribute("sessionID", session.getId());
		// setting session to expiry in 30 mins
		session.setMaxInactiveInterval(30 * 60);
		return session;
	}

	
	
	
}
