
package servlet;

import static services.utility.GenericConstant.ADDRESS_MODEL;
import static services.utility.GenericConstant.USER_MODEL;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Address;
import model.User;

import org.apache.log4j.Logger;

import services.VendorRegistrationController;

@WebServlet("/VendorRegistration")
public class VendorRegistration extends HttpServlet {

	private static final long serialVersionUID = 1L;
	static final Logger logger = Logger.getLogger(VendorRegistration.class);
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String password =  (String)req.getSession().getAttribute("password");
		@SuppressWarnings("rawtypes")
		List entities = null;
		Map<String, String> params = new HashMap<String, String>();
		params.put("addrLine1", req.getParameter("addr1"));
		params.put("addrLine2", req.getParameter("addr2")!=null?req.getParameter("addr2"):"");
		params.put("addrLine3", req.getParameter("addr3")!=null?req.getParameter("addr3"):"");
		params.put("locality", req.getParameter("locality"));
		params.put("city", req.getParameter("city"));
		params.put("state", req.getParameter("state"));
		params.put("country", "India");
		params.put("pinCode", req.getParameter("pinCode"));
		
		logger.info("Email id = "+req.getSession().getAttribute("email"));
		String emailUserName = (String)req.getSession().getAttribute("email");
		if( emailUserName!=null)
			params.put("email", emailUserName);
		else
			params.put("email", ((User)req.getSession().getAttribute(USER_MODEL)).getUserEmail());
		
		params.put("password", password!=null?password:"");
		
		params.put("fullName", req.getParameter("fullName")!=null?req.getParameter("fullName"):"");
		params.put("gender", req.getParameter("gender")!=null?req.getParameter("gender"):"");
		params.put("dob", req.getParameter("dob")!=null?req.getParameter("dob"):"");
		
		params.put("primaryContact", req.getParameter("primaryContact"));
		params.put("secondaryContact", req.getParameter("secondaryContact"));
		
		VendorRegistrationController controller = new VendorRegistrationController();
		logger.info("Map params = "+params);
		if(password!=null)
			entities = controller.registerVendor(params);
		else
			entities = controller.updateVendor(params);
		
		if(entities!=null && entities.size() > 0){
			HttpSession session = req.getSession();
			if(session !=null){
				req.getSession().setAttribute(USER_MODEL, (User)entities.get(0));
				req.getSession().setAttribute(ADDRESS_MODEL, (Address)entities.get(1));
				resp.setContentType("text/html;charset=UTF-8");
				String userEmailOTP = ((User)entities.get(0)).getUserEmailOtp() !=null ? ((User)entities.get(0)).getUserEmailOtp().toString() : "";
				resp.getWriter().write(((User)entities.get(0)).getUserEmail()+","+userEmailOTP);
			}
		}
	}

}
