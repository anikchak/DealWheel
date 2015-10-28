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
		String authType = request.getParameter(GenericConstant.AUTH_TYPE);
		String comingFromPage = (String) request.getSession().getAttribute(GenericConstant.COMINGFROMPAGE);
		HttpSession session = null;
		String msg=null;
		System.out.println("uName="+uName);
		System.out.println("authType="+authType);
		System.out.println("Coming from page = " + comingFromPage);
		
		if(uName !=null && "".equals(uName.trim()) || pwd !=null && "".equals(pwd.trim())){
			msg = "emptyFields";
			System.out.println("empty fields");
		}
		else{
		if ("login".equalsIgnoreCase(authType)) {
			List<User> validUserDetails = s.validateUser(uName, pwd);
			//System.out.println("user from query="+validUserDetails.get(0).getUserId());
			if (validUserDetails!=null && validUserDetails.size()==1) {
				session = generateSession(request, uName);
				session.setAttribute("LoggedInUserDetailsObject",validUserDetails );
				msg = new CommonUtility().getPageName(comingFromPage);
				//new CommonUtility().pageNavigation(pagecontext, comingFromPage, request, response);
			}else{
				System.out.println("Invalid username or password");
				request.getSession().removeAttribute("LoggedInUserDetailsObject");
				msg = "authenticationFailed";
				//response.setContentType("text/plain");
		        //response.getWriter().write("authenticationFailed");
				//request.getSession().setAttribute(GenericConstant.LOGINERROR,MessageBundle.LOGIN_ERROR_MSG);
				//response.sendRedirect(pagecontext + GenericConstant.NAV_TO_LOGIN_PAGE);
				}
		} else if ("signup".equalsIgnoreCase(authType)) {
			String confirmPassword = request.getParameter("confirmPassword");
			if(confirmPassword!=null && !"".equals(confirmPassword) && pwd.equals(confirmPassword)){
			List<User> newUserEntryList = s.inserNewUser(uName, pwd);
			System.out.println("status post registration=" + newUserEntryList);
			if (newUserEntryList!=null && newUserEntryList.size()==1) {
				session = generateSession(request, uName);
				session.setAttribute("LoggedInUserDetailsObject",newUserEntryList );
				//new CommonUtility().pageNavigation(pagecontext, comingFromPage, request, response);
				//response.setContentType("text/plain");
		        //response.getWriter().write("/LandingPage.jsp");
				msg = new CommonUtility().getPageName(comingFromPage);
			}else{
				System.out.println("Username already exists. Error..!!");
				request.getSession().removeAttribute("LoggedInUserDetailsObject");
				msg = "userNameExists";
				//response.setContentType("text/plain");
		        //response.getWriter().write("userNameExists");
				//request.getSession().setAttribute(GenericConstant.LOGINERROR,MessageBundle.NOT_UNIQUE_USERNAME_ERROR_MSG);
				//response.sendRedirect(pagecontext + GenericConstant.NAV_TO_LOGIN_PAGE);
			}
			}else if(confirmPassword!=null && "".equals(confirmPassword)){
				msg = "emptyFields";
			}
			else{
				msg = "passwordMismatch";
			}
		}
	}
		response.setContentType("text/plain");
        response.getWriter().write(msg);
	}

	private HttpSession generateSession(HttpServletRequest request, String uName) {
		HttpSession session = request.getSession();
		System.out.println("New Session created=" + session.getId());
		session.setAttribute(GenericConstant.USERNAME, uName.toUpperCase());
		session.setAttribute(GenericConstant.SESSIONID, session.getId());
		return session;
	}

	
	
	
}
