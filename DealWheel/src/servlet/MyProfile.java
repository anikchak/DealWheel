package servlet;

import java.io.IOException;
import java.math.BigInteger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import services.CustomerControllerService;
import services.utility.GenericConstant;

/**
 * Servlet implementation class MyProfile
 */
@WebServlet("/MyProfile")
public class MyProfile extends HttpServlet {
	private static final long serialVersionUID = 1L;
	static final Logger logger = Logger.getLogger(MyProfile.class);
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MyProfile() {
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
		
		logger.info("Here Inside MyProfile");
		String pageContext = request.getContextPath();
		String userEmail = request.getParameter("Email");
		String userName = request.getParameter("Name");
		String userPassword = request.getParameter("Password");
		String userPContact = request.getParameter("PContact1");
		String userGender = request.getParameter("Gender");
		BigInteger userId = new BigInteger(request.getParameter("UserId"));
		CustomerControllerService ccs = new CustomerControllerService();
		String[] params = new String[5];
		params[0] = userEmail;
		params[1]=userName;
		params[2]=userPassword;
		params[3]=userPContact;
		params[4]=userGender;
		 ccs.updateUserDetails(userId,params);
		 request.getSession().setAttribute("SetConfirmSuccess", "Yes");
		 
		 response.sendRedirect(pageContext+ GenericConstant.NAV_TO_MYPROFILE_PAGE);
		 
		 
		
	}

}
