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

@WebServlet("/vendorLoginSignUp")
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
		
		if(req.getParameter("identifier").equals("signup")){
			if(req.getParameter("password").equals(req.getParameter("confirmPassword")))
				req.getRequestDispatcher(NAV_TO_VENDORREGISTRATION_PAGE).forward(req, resp);
			else
				resp.sendRedirect(pagecontext+NAV_TO_VENDORLOGINSIGNUP_PAGE);
		}else{
			VendorLoginController vlc = new VendorLoginController();
			User user= vlc.validateVendor(req.getParameter("loginEmail"), req.getParameter("loginPassword"));
			Address address = new AddressDAOImpl<Address>().findAddressByUserIdAndType(user.getUserId(), ADDRESS_TYPE_VENDOR_OFFICE_LOCATION);
			if(user!=null){
				HttpSession session = req.getSession();
				if(session !=null){
					req.getSession().setAttribute(USER_MODEL, user);
					req.getSession().setAttribute(ADDRESS_MODEL, address);
					resp.sendRedirect(pagecontext+NAV_TO_VENDOR_HOME_PAGE);
				}
			}else
				resp.sendRedirect(pagecontext+NAV_TO_VENDORLOGINSIGNUP_PAGE);
				
		}
		
	}

}
