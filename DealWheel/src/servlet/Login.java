package servlet;

import java.io.IOException;
import java.math.BigInteger;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.User;

import org.apache.log4j.Logger;

import services.CustomerControllerService;
import services.utility.CommonUtility;
import services.utility.GenericConstant;

/**
 * Servlet implementation class Login
 */
@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
	static final Logger logger = Logger.getLogger(Login.class);

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
		
		String uName = request.getParameter(GenericConstant.USERNAME);
		String pwd = request.getParameter(GenericConstant.PASSWORD);
		
		String authType = request.getParameter(GenericConstant.AUTH_TYPE);
		String comingFromPage = (String) request.getSession().getAttribute("currentPage");
		HttpSession session = null;
		String msg=null;
		logger.info("uName="+uName);
		logger.info("authType="+authType);
		logger.info("Coming from page = " + comingFromPage);
		
		if((uName !=null && "".equals(uName.trim())) || (pwd !=null && "".equals(pwd.trim()))){
			msg = "emptyFields";
			logger.info("empty fields");
		}else if(uName!=null && !CommonUtility.validateEmail(uName)){
			msg = "invalidEmail";
		}
		else{
		if ("login".equalsIgnoreCase(authType)) {
			List<User> validUserDetails = s.validateUser(uName, pwd);
			if (validUserDetails!=null && validUserDetails.size()==1) {
				session = generateSession(request, uName);
				session.setAttribute("LoggedInUserDetailsObject",validUserDetails );
				msg = CommonUtility.getPageName(comingFromPage);
			}else{
				logger.info("Invalid username or password");
				request.getSession().removeAttribute("LoggedInUserDetailsObject");
				msg = "authenticationFailed";
				}
		} else if ("signup".equalsIgnoreCase(authType)) {
			String mobileNumber = "0"+request.getParameter("mobileNumber");
			String regex = "\\d+";
			if(pwd!=null && !"".equals(pwd) && (mobileNumber!=null && !"".equals(mobileNumber) && mobileNumber.matches(regex) && mobileNumber.length()==11)){
				String contextpath = request.getContextPath();
			List<User> newUserEntryList = s.inserNewUser(uName, pwd,BigInteger.valueOf(Long.parseLong(mobileNumber)),request.getServerName(),String.valueOf(request.getServerPort()));
			logger.info("status post registration=" + newUserEntryList);
			if (newUserEntryList!=null && newUserEntryList.size()==1) {
				//session = generateSession(request, uName);
				//session.setAttribute("LoggedInUserDetailsObject",newUserEntryList );
				//new CommonUtility().pageNavigation(pagecontext, comingFromPage, request, response);
				//response.setContentType("text/plain");
		        //response.getWriter().write("/LandingPage.jsp");
				msg = "CheckyourMail";
				//msg = CommonUtility.getPageName(comingFromPage);
			}else{
				logger.info("Username already exists. Error..!!");
				request.getSession().removeAttribute("LoggedInUserDetailsObject");
				msg = "userNameExists";
			}
			}else if(mobileNumber!=null && "".equals(mobileNumber)){
				msg = "emptyFields";
			}
			else if(!mobileNumber.matches(regex) || mobileNumber.length()!=11){
				msg = "mobileNaN";
			}else{
				msg = "emptyFields";
			}
		}else if ("forgotPassword".equalsIgnoreCase(authType)){
			String mobileNumber = "0"+request.getParameter("mobileNumber");
			String regex = "\\d+";
			if((mobileNumber!=null && !"".equals(mobileNumber) && mobileNumber.matches(regex) && mobileNumber.length()==11)){
				String resetPwd = s.resetPassword(uName, BigInteger.valueOf(Long.parseLong(mobileNumber)));
				logger.info("resetPwd = "+resetPwd);
				if(resetPwd!=null){
					msg = "/LandingPage.jsp#"+resetPwd;
				}
			}
			else if(mobileNumber!=null && "".equals(mobileNumber)){
				msg = "emptyFields";
			}
			else if(!mobileNumber.matches(regex) || mobileNumber.length()!=11){
				msg = "mobileNaN";
			}else{
				msg = "emptyFields";
			}
		}
	}
		response.setContentType("text/plain");
        response.getWriter().write(msg);
	}

	private HttpSession generateSession(HttpServletRequest request, String uName) {
		HttpSession session = request.getSession();
		logger.info("New Session created=" + session.getId());
		session.setAttribute(GenericConstant.USERNAME, uName.toUpperCase());
		session.setAttribute(GenericConstant.SESSIONID, session.getId());
		return session;
	}

	
	
	
}
