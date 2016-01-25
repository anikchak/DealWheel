package servlet;

import static services.utility.GenericConstant.NAV_TO_DISPLAY_VEHICLE_PAGE;
import static services.utility.GenericConstant.NAV_TO_VENDOR_HOME_PAGE;

import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Bookingshistory;
import model.Vehicle;

import org.apache.log4j.Logger;

import dao.BookingHistoryDAOImpl;
import dao.VehicleDAOImpl;

@WebServlet("/DeleteVehicle")
public class DeleteVehicle extends HttpServlet {

	private static Logger logger = Logger.getLogger(DeleteVehicle.class);
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		try{
			VehicleDAOImpl<Vehicle> vDAO = new VehicleDAOImpl<Vehicle>();
			BookingHistoryDAOImpl<Bookingshistory> bhDAO = new BookingHistoryDAOImpl<Bookingshistory>();
			Object[] list=  req.getParameterValues("arrayList");
			if("Delete".equals(req.getParameter("identifier"))){
				for (int i = 0; i < list.length; i++) {
					BigInteger id = new BigInteger(list[i].toString());
					if("Yes".equals(req.getParameter("check" + i)))
						if(!bhDAO.checkFutureBookingAvailable(id))
						vDAO.delete(id);
					//TODO - else error msg
					}
			}
			else if("Disable".equals(req.getParameter("identifier"))){
				List<BigInteger> listIds = new ArrayList<BigInteger>();
				for (int i = 0; i < list.length; i++) {
					BigInteger id = new BigInteger(list[i].toString());
					if("Yes".equals(req.getParameter("check" + i)))
						if(!bhDAO.checkFutureBookingAvailable(id))
							listIds.add(id);
					//TODO - else error msg
					}
				vDAO.disable(listIds);
			}
			else {
				vDAO.enable(new BigInteger(req.getParameter("identifier")));
			}
			resp.sendRedirect(req.getContextPath()+NAV_TO_DISPLAY_VEHICLE_PAGE);
		}catch(Exception e){
			logger.error("ERROR"+e);
			resp.sendRedirect(req.getContextPath()+NAV_TO_VENDOR_HOME_PAGE);
		}
		
		
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doPost(req, resp);
	}
}
