package servlet;

import static services.utility.GenericConstant.ADDRESS_MODEL;
import static services.utility.GenericConstant.NAV_TO_VENDOR_HOME_PAGE;
import static services.utility.GenericConstant.USER_MODEL;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Address;
import model.User;
import model.Vehicle;

import org.apache.log4j.Logger;

import services.AddVehicleController;

@WebServlet("/AddVehicle")
public class AddVehicle extends HttpServlet {
	static final Logger logger = Logger.getLogger(AddVehicle.class);
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		try{
			User u = null;
			Address add = null ;
			HttpSession session = req.getSession();
			if(session !=null){
				u = (User) req.getSession().getAttribute(USER_MODEL);
				add = (Address) req.getSession().getAttribute(ADDRESS_MODEL);
			}
			Map<String, String> params = new HashMap<String, String>();
			params.put("userName",u.getUserName());
			params.put("vehicleName",req.getParameter("vehicleName"));
			params.put("vehicleManufacturer",req.getParameter("vehicleManufacturer"));
			params.put("registrationNo",req.getParameter("registrationNo"));
			params.put("yearOfManufacture",req.getParameter("yearOfManufacture"));
			params.put("perDayCost",req.getParameter("perDayCost"));
			params.put("securityDeposit",req.getParameter("securityDeposit"));
			
			if("true".equals(req.getParameter("useDifferentAddress"))){
				params.put("useDifferentAddress", "true");
				params.put("addrLine1", req.getParameter("addr1Add"));
				params.put("addrLine2", req.getParameter("addr2Add")!=null?req.getParameter("addr2Add"):"");
				params.put("addrLine3", req.getParameter("addr3Add")!=null?req.getParameter("addr3Add"):"");
				params.put("locality", req.getParameter("localityAdd"));
				params.put("city", req.getParameter("cityAdd"));
				params.put("state", req.getParameter("stateAdd"));
				params.put("country", "India");
				params.put("pinCode", req.getParameter("pinCodeAdd"));
			}else{
				params.put("useDifferentAddress", "No");
				params.put("addrressId", add!=null?add.getAddrId():"");
			}

			params.put("userId", u.getUserId().toString());
			
			AddVehicleController controller = new AddVehicleController();
			Vehicle addedVehicle = controller.addNewVehicle(params);
			resp.setContentType("text/html;charset=UTF-8");
			if(addedVehicle.getVhclId() != null){
				resp.getWriter().write(NAV_TO_VENDOR_HOME_PAGE);
			}else{
				resp.getWriter().write("ADDVEHICLEERROR");
			}
		}catch(Exception e){
			logger.info("ERROR"+e);
		}
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doPost(req, resp);
	}
}
