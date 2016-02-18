package servlet;

import static services.utility.GenericConstant.ADDRESS_MODEL;
import static services.utility.GenericConstant.ADDRESS_TYPE_VENDOR_OFFICE_LOCATION;
import static services.utility.GenericConstant.NAV_TO_VENDORREGISTRATION_PAGE;
import static services.utility.GenericConstant.NAV_TO_VENDOR_HOME_PAGE;
import static services.utility.GenericConstant.USER_MODEL;

import java.io.IOException;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Address;
import model.User;

import org.apache.log4j.Logger;

import services.VendorLoginController;
import dao.AddressDAOImpl;

@WebServlet("/VendorLoginSignUp")
public class VendorLoginSignUp extends HttpServlet {

	private static Logger logger = Logger.getLogger(VendorLoginSignUp.class);
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doPost(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		VendorLoginController vlc = new VendorLoginController();
		String output = null;
		if(req.getParameter("identifier")!=null && "signup".equals(req.getParameter("identifier"))){
			req.getSession().setAttribute("vendorFlow", "vendorFlow");
			if(req.getParameter("email") == null || isValidEmailAddress(req.getParameter("email"))){
				output = "USRNMISSUE";
			}
			if(req.getParameter("password").equals(req.getParameter("confirmPassword"))){
				logger.info("Forwarding "+ req.getParameter("email")+ " to Registration Page");
				req.getSession().setAttribute("password", req.getParameter("password"));
				req.getSession().setAttribute("email", req.getParameter("email"));
				if(vlc.userNamexists(req.getParameter("email"))){
					output = "USERALREADYPRESENT";
				}else{
				output = NAV_TO_VENDORREGISTRATION_PAGE+"?invoke=vendorFlow";
				}
			}
			else{
				output = "SIGNUPPWDMISMATCH";
			}
		}else if(req.getParameter("identifier")!=null && "login".equals(req.getParameter("identifier"))){ 
			logger.info("User "+ req.getParameter("loginEmail")+" trying to login..");
			if(!isValidEmailAddress(req.getParameter("loginEmail")))
				output = "USRNMISSUE";
			else{
				if(vlc.userNamexists(req.getParameter("loginEmail"))){
					User user= vlc.validateVendor(req.getParameter("loginEmail"), req.getParameter("loginPassword"));
					if(user!=null){
						logger.info("User "+ req.getParameter("loginEmail")+" exists..");
						Address address = new AddressDAOImpl<Address>().findAddressByUserIdAndType(user.getUserId(), ADDRESS_TYPE_VENDOR_OFFICE_LOCATION);//Aniket: Location for this line changed as if the validation fails the user object will be null and this line will give NPE
						logger.info("Address for User "+ req.getParameter("loginEmail")+" exists with Id "+ address.getAddrId());
						HttpSession session = req.getSession();
						if(session !=null){
							req.getSession().setAttribute(USER_MODEL, user);
							req.getSession().setAttribute(ADDRESS_MODEL, address);
							req.getSession().setAttribute("vendorFlow", "vendorFlow");
							logger.info("vendor flow = "+req.getSession().getAttribute("vendorFlow"));
							output = NAV_TO_VENDOR_HOME_PAGE;
						}
						
					}else
						output = "WRONGPASSWORD";
				}else{
					output = "NOLOGIN";
				}
			}
		}
		resp.setContentType("text/plain");
		resp.getWriter().write(output);
	}

	private boolean isValidEmailAddress(String emailAddress){
		boolean result = true;
	   try {
		      InternetAddress emailAddr = new InternetAddress(emailAddress);
		      emailAddr.validate();
		   } catch (AddressException ex) {
		      result = false;
		   }
		   return result;}
}
