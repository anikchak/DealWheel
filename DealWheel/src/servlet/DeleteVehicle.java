package servlet;

import static services.utility.GenericConstant.NAV_TO_VENDOR_HOME_PAGE;

import java.io.IOException;
import java.math.BigInteger;

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
//import exception.SomeThingNotRightException;

@WebServlet("/DeleteVehicle")
public class DeleteVehicle extends HttpServlet {

	private static Logger logger = Logger.getLogger(DeleteVehicle.class);
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
		String output= NAV_TO_VENDOR_HOME_PAGE;
			VehicleDAOImpl<Vehicle> vDAO = new VehicleDAOImpl<Vehicle>();
			BookingHistoryDAOImpl<Bookingshistory> bhDAO = new BookingHistoryDAOImpl<Bookingshistory>();
			logger.info("VehicleId="+req.getParameter("selectedVehicleRecordId")+" opCode="+req.getParameter("opCode"));
			BigInteger vehicleId = new BigInteger(req.getParameter("selectedVehicleRecordId"));
			if("Delete".equals(req.getParameter("opCode"))){
				if(!bhDAO.checkFutureBookingAvailable(vehicleId)){
				vDAO.delete(vehicleId);
				}else{
					output = "BOOKINGEXISTDELETE";
				}
			}
			else if("Disable".equals(req.getParameter("opCode"))){
				if(!bhDAO.checkFutureBookingAvailable(vehicleId)){
				vDAO.disable(vehicleId);
				}else{
					output = "BOOKINGEXISTDISABLE";
				}
			}
			else if("Enable".equals(req.getParameter("opCode"))){
					vDAO.enable(vehicleId);
			}else if("Change".equals(req.getParameter("opCode"))){
				String changedCost = req.getParameter("changedCost"+vehicleId);
				String changedDeposit = req.getParameter("changedDeposit"+vehicleId);
				if(changedCost != null && !changedCost.equals("") && changedCost!=""
						&& changedDeposit != null && !changedDeposit.equals("") && changedDeposit!=""){
					vDAO.changeCostAndDeposit(vehicleId,changedCost,changedDeposit);
					output = "CHANGED";
				}else{
					output="NOTCHANGED";
				}
			}
			resp.setContentType("text/html;charset=UTF-8");
			try {
				resp.getWriter().write(output);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doPost(req, resp);
	}
}
