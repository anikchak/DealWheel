package servlet;

import static services.utility.GenericConstant.NAV_TO_ADD_LOCATION_PAGE;
import static services.utility.GenericConstant.NAV_TO_HOME_PAGE;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import services.LocationOfOperationController;

@WebServlet("/addLocation")
public class Location extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private static Logger logger = Logger.getLogger(Location.class);
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try{
			String city = req.getParameter("city");
			String state = req.getParameter("state");
			String country = req.getParameter("country");
			
			if(city != null && state != null && country != null && !"".equals(city) && !"".equals(state) && !"".equals(country)){
				LocationOfOperationController loc = new LocationOfOperationController();
				loc.addToXML(country, state, city);
				req.getRequestDispatcher(NAV_TO_HOME_PAGE).forward(req, resp);
			}
		}catch(Exception e){
			logger.error("error adding locations");
			e.printStackTrace();
			resp.sendRedirect(req.getContextPath()+NAV_TO_ADD_LOCATION_PAGE);
		}
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}
}
