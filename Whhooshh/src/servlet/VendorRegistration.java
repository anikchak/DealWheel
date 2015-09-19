package servlet;

import static services.utility.GenericConstant.NAV_TO_VENDOR_HOME_PAGE;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import services.VendorRegistrationController;
import model.User;

@WebServlet("/vendorRegistration")
public class VendorRegistration extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		Map<String, String> params = new HashMap<String, String>();
		params.put("addrLine1", req.getParameter("addr1"));
		params.put("addrLine2", req.getParameter("addr2"));
		params.put("addrLine3", req.getParameter("addr3"));
		params.put("locality", req.getParameter("locality"));
		params.put("city", req.getParameter("city"));
		params.put("state", req.getParameter("state"));
		params.put("country", req.getParameter("country"));
		params.put("pinCode", req.getParameter("pinCode"));
		
		
		params.put("email", req.getParameter("email"));
		params.put("password", req.getParameter("password"));
		
		params.put("fullName", req.getParameter("fullName"));
		params.put("gender", req.getParameter("gender"));
		params.put("dob", req.getParameter("dob"));
		
		params.put("primaryContact", req.getParameter("primaryContact"));
		params.put("secondaryContact", req.getParameter("secondaryContact"));
		
		VendorRegistrationController controller = new VendorRegistrationController();
		List entities = controller.doProcess(params);
		req.getSession().setAttribute("UserName", ((User)entities.get(0)).getUserName());
		req.getSession().setAttribute("Useremail", ((User)entities.get(0)).getUserEmail());
		getServletContext().getRequestDispatcher(NAV_TO_VENDOR_HOME_PAGE).forward(req,resp);
	}

}
