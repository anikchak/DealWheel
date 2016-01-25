package servlet;

import static services.utility.GenericConstant.*;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Address;
import model.User;
import services.VendorLoginController;
import dao.AddressDAOImpl;

@WebServlet("/VendorLoginSignUp")
public class VendorLoginSignUp extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doPost(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		String pagecontext = req.getContextPath();
		String output = null;
		if(req.getParameter("identifier")!=null && "signup".equals(req.getParameter("identifier"))){
			if(req.getParameter("email") ==null){
				output = "USRNMISSUE";
			}
			if(req.getParameter("password").equals(req.getParameter("confirmPassword"))){
				System.out.println("Forwarding to registration");
				System.out.println("Username="+req.getParameter("email") +" Password="+req.getParameter("password"));
				//req.getRequestDispatcher(NAV_TO_VENDORREGISTRATION_PAGE).forward(req, resp);
				req.getSession().setAttribute("password", req.getParameter("password"));
				req.getSession().setAttribute("email", req.getParameter("email"));
				req.getSession().setAttribute("vendorFlow", "vendorFlow");
				output = NAV_TO_VENDORREGISTRATION_PAGE+"?invoke=vendorFlow";
			}
			else{
				output = "SIGNUPPWDMISMATCH";
			}
		}else if(req.getParameter("identifier")!=null && "login".equals(req.getParameter("identifier"))){ 
			VendorLoginController vlc = new VendorLoginController();
			User user= vlc.validateVendor(req.getParameter("loginEmail"), req.getParameter("loginPassword"));
			System.out.println("User details during login = "+user);
			if(user!=null){
				Address address = new AddressDAOImpl<Address>().findAddressByUserIdAndType(user.getUserId(), ADDRESS_TYPE_VENDOR_OFFICE_LOCATION);//Aniket: Location for this line changed as if the validation fails the user object will be null and this line will give NPE
				HttpSession session = req.getSession();
				if(session !=null){
					req.getSession().setAttribute(USER_MODEL, user);
					req.getSession().setAttribute(ADDRESS_MODEL, address);
					req.getSession().setAttribute("vendorFlow", "vendorFlow");
					System.out.println("vendor flow = "+req.getSession().getAttribute("vendorFlow"));
					//resp.sendRedirect(pagecontext+NAV_TO_VENDOR_HOME_PAGE);
					output = NAV_TO_VENDOR_HOME_PAGE;
				}
			}else
				output = "LOGINERROR";
		}
		resp.setContentType("text/plain");
		resp.getWriter().write(output);
	}

}
