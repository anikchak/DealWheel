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

import model.Vehicle;

import org.apache.log4j.Logger;

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
			//Object[] list=  req.getParameterValues("arrayList");
			System.out.println("VehicleId="+req.getParameter("selectedVehicleRecordId")+" opCode="+req.getParameter("opCode"));
			String vehicleRecord = (String)req.getParameter("selectedVehicleRecordId");
			if("Delete".equals(req.getParameter("opCode"))){
				/*
				for (int i = 0; i < list.length; i++) {
					if("Yes".equals(req.getParameter("check" + i)))
						vDAO.delete(new BigInteger(list[i].toString()));
					}
				*/
				vDAO.delete(new BigInteger(vehicleRecord));
			}
			else if("Disable".equals(req.getParameter("opCode"))){
				/*
				List<BigInteger> listIds = new ArrayList<BigInteger>();;
				for (int i = 0; i < list.length; i++) {
					if("Yes".equals(req.getParameter("check" + i)))
						listIds.add(new BigInteger(list[i].toString()));
					}
				vDAO.disable(listIds);
				*/
				vDAO.disable(new BigInteger(vehicleRecord));
				
			}
			else if("Enable".equals(req.getParameter("opCode"))){
				vDAO.enable(new BigInteger(vehicleRecord));
			}
			resp.sendRedirect(req.getContextPath()+NAV_TO_VENDOR_HOME_PAGE);
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
