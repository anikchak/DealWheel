package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.ListedVehicle;
import dao.ListedVehicleDAOImpl;

@WebServlet("/LoadVehicleNames")
public class LoadVehicleNames extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		ListedVehicleDAOImpl<ListedVehicle> lvDAO = new ListedVehicleDAOImpl<ListedVehicle>();
		List<String> list = lvDAO.getVehiclesForMakers(req.getParameter("vehicleMake"));
		req.setAttribute("list", list);
		resp.getWriter().write(list.get(0));
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doPost(req, resp);
	}

}
