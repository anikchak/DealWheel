package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import services.mail.SendMail;
import model.ListedVehicle;
import dao.ListedVehicleDAOImpl;

@WebServlet("/LoadVehicleNames")
public class LoadVehicleNames extends HttpServlet {

	private static final long serialVersionUID = 1L;
	static final Logger logger = Logger.getLogger(LoadVehicleNames.class);

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		ListedVehicleDAOImpl<ListedVehicle> lvDAO = new ListedVehicleDAOImpl<ListedVehicle>();
		List<String> list = lvDAO.getVehiclesForMakers(req.getParameter("vehicleMake"));
		StringBuilder sb = new StringBuilder();
		for(String name : list){
			sb.append(name);
			sb.append(":");
		}
		sb.deleteCharAt(sb.length()-1);
		resp.getWriter().write(sb.toString());
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doPost(req, resp);
	}

}
