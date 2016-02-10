package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

/**
 * Servlet implementation class LocationSetting
 */
@WebServlet("/LocationSetting")
public class LocationSetting extends HttpServlet {
	private static final long serialVersionUID = 1L;
	static final Logger logger = Logger.getLogger(LocationSetting.class);
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LocationSetting() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		logger.info("Location setting invoked");
		String selectedLocation = request.getParameter("locationSelected");
		logger.info("Location Selected = "+selectedLocation);
		request.getSession().setAttribute("selectedLocation", selectedLocation);
		logger.info("selectedLocation ="+request.getSession().getAttribute("selectedLocation")+"--");
	}

}
