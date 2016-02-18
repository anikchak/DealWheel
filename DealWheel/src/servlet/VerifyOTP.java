package servlet;

import static services.utility.GenericConstant.ADDRESS_MODEL;
import static services.utility.GenericConstant.NAV_TO_VENDOR_HOME_PAGE;
import static services.utility.GenericConstant.NAV_TO_VENDORREGISTRATION_PAGE;
import static services.utility.GenericConstant.USER_MODEL;
import static services.utility.GenericConstant.ADDRESS_TYPE_VENDOR_OFFICE_LOCATION;

import java.io.IOException;
import java.math.BigInteger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Address;
import model.User;
import dao.AddressDAOImpl;
import dao.UserDAOImpl;

@WebServlet("/VerifyOTP")
public class VerifyOTP extends HttpServlet{
	private static final long serialVersionUID = -2225196584227042213L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		String otp = req.getParameter("otpVendor");
		String email = (String) req.getSession().getAttribute("email");
		User usr = new UserDAOImpl<User>().findUserByEmailAddress(email);
		BigInteger usrId = new BigInteger(usr.getUserId());
		Address addr = new AddressDAOImpl<Address>().findAddressByUserIdAndType(usrId, ADDRESS_TYPE_VENDOR_OFFICE_LOCATION);
		if(otp.equals(usr.getUserEmailOtp().toString())){
			HttpSession session = req.getSession();
			if(session !=null){
				req.getSession().setAttribute(USER_MODEL, usr);
				req.getSession().setAttribute(ADDRESS_MODEL, addr);
				resp.sendRedirect(req.getContextPath()+NAV_TO_VENDOR_HOME_PAGE);
			}
		}else{
			new UserDAOImpl<User>().delete(usr);
			new AddressDAOImpl<Address>().delete(addr);
			RequestDispatcher rd = req.getRequestDispatcher(NAV_TO_VENDORREGISTRATION_PAGE);
			rd.forward(req, resp);
		}
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doPost(req, resp);
	}

}
