package servlet;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.User;
import services.CustomerControllerService;
import services.utility.CommonUtility;
import services.utility.GenericConstant;
import services.utility.MessageBundle;

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
		CustomerControllerService s = new CustomerControllerService();
		
		String pagecontext = request.getContextPath();
		String uName = request.getParameter(GenericConstant.USERNAME);
		String pwd = request.getParameter(GenericConstant.PASSWORD);
		String optionSelected = request.getParameter(GenericConstant.OPTION);
		String comingFromPage = (String) request.getSession().getAttribute(GenericConstant.COMINGFROMPAGE);
		HttpSession session = null;
		System.out.println("option selected=" + optionSelected);
		System.out.println("Coming from page = " + comingFromPage);

		if (GenericConstant.OLDREGISTRATION.equalsIgnoreCase(optionSelected)) {
			List<User> validUserDetails = s.validateUser(uName, pwd);
			if (validUserDetails!=null && validUserDetails.size()==1) {
				session = generateSession(request, uName);
				session.setAttribute("LoggedInUserDetailsObject",validUserDetails );
				session.setAttribute(GenericConstant.LOGINERROR,null );
				new CommonUtility().pageNavigation(pagecontext, comingFromPage, request, response);
			}else{
				System.out.println("Invalid username or password");
				request.getSession().removeAttribute("LoggedInUserDetailsObject");
				request.getSession().setAttribute(GenericConstant.LOGINERROR,MessageBundle.LOGIN_ERROR_MSG);
				response.sendRedirect(pagecontext + GenericConstant.NAV_TO_LOGIN_PAGE);
				}
		} else if (GenericConstant.NEWREGISTRATION.equalsIgnoreCase(optionSelected)) {
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
		session.setAttribute(GenericConstant.USERNAME, uName.toUpperCase());
		session.setAttribute(GenericConstant.SESSIONID, session.getId());
		return session;
	}

	
	
	
}
