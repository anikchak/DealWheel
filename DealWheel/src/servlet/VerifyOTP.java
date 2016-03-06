package servlet;

import static services.utility.GenericConstant.ADDRESS_MODEL;
import static services.utility.GenericConstant.ADDRESS_TYPE_VENDOR_OFFICE_LOCATION;
import static services.utility.GenericConstant.NAV_TO_VENDOR_HOME_PAGE;
import static services.utility.GenericConstant.USER_MODEL;
import static services.utility.GenericConstant.USER_TYPE_VENDOR;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import services.utility.LoginUtil;
import model.Address;
import model.LoginDetail;
import model.User;
import dao.AddressDAOImpl;
import dao.LoginDAOImpl;
import dao.UserDAOImpl;

@WebServlet("/VerifyOTP")
public class VerifyOTP extends HttpServlet{
	private static final long serialVersionUID = -2225196584227042213L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String identifier = req.getParameter("identifier");
		if("ResendOTP".equals(identifier)){
			User usr = new UserDAOImpl<User>().findUserByEmailAddress(req.getParameter("email"));
			usr.setUserEmailOtp(LoginUtil.generateOTP());
			new UserDAOImpl<User>().update(usr);
			resp.setContentType("text/html;charset=UTF-8");
			resp.getWriter().write(usr.getUserEmail()+","+usr.getUserEmailOtp());
		}else if("Cancel".equals(identifier)){
			User usr = new UserDAOImpl<User>().findUserByEmailAddress(req.getParameter("email"));
			Address addr = new AddressDAOImpl<Address>().findAddressByUserIdAndType(usr.getUserId(), ADDRESS_TYPE_VENDOR_OFFICE_LOCATION);
			LoginDetail ld = new LoginDAOImpl<LoginDetail>().findLoginDetailForUserNameAndType(usr.getUserId(),USER_TYPE_VENDOR);
			new UserDAOImpl<User>().delete(usr.getUserId());
			new AddressDAOImpl<Address>().delete(addr.getAddrId());
			new LoginDAOImpl<LoginDetail>().delete(ld.getLognId());
			req.getSession().invalidate();
		}else{
			String otp = req.getParameter("otpVendor");
			String email = (String) req.getSession().getAttribute("email");
			User usr = new UserDAOImpl<User>().findUserByEmailAddress(email);
			String usrId = usr.getUserId();
			Address addr = new AddressDAOImpl<Address>().findAddressByUserIdAndType(usrId, ADDRESS_TYPE_VENDOR_OFFICE_LOCATION);
			if(otp.equals(usr.getUserEmailOtp().toString())){
				usr.setUserEmailOtp(null);
				new UserDAOImpl<User>().update(usr);
				HttpSession session = req.getSession();
				if(session !=null){
					req.getSession().setAttribute(USER_MODEL, usr);
					req.getSession().setAttribute(ADDRESS_MODEL, addr);
					resp.sendRedirect(req.getContextPath()+NAV_TO_VENDOR_HOME_PAGE);
				}
			}else{
				resp.setContentType("text/html;charset=UTF-8");
				resp.getWriter().write("OTPWRONG");
			}
		}
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doPost(req, resp);
	}

}
