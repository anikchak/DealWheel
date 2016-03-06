package servlet;

import java.io.IOException;
import java.text.SimpleDateFormat;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Bookingshistory;
import model.User;
import model.Vehicle;

import org.apache.log4j.Logger;

import dao.BookingHistoryDAOImpl;
import dao.UserDAOImpl;
import dao.VehicleDAOImpl;

@WebServlet("/CancelBookingForVendor")
public class CancelBookingForVendor extends HttpServlet {

	private static Logger logger = Logger.getLogger(CancelBookingForVendor.class);
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
			BookingHistoryDAOImpl<Bookingshistory> bkngDAO = new BookingHistoryDAOImpl<Bookingshistory>();
			logger.info("bookingToCancel="+req.getParameter("bookingToCancel"));
			Bookingshistory bh = bkngDAO.cancelBooking(req.getParameter("bookingToCancel"));
			if(bh!=null && bh.getBkngSeq()!=null){
				SimpleDateFormat dt = new SimpleDateFormat("EEE MMM dd ''yy");
				Vehicle v = new VehicleDAOImpl<Vehicle>().findById(bh.getBkngVehicle());
				User u = new UserDAOImpl<User>().findById(bh.getUserId());
				resp.setContentType("text/html;charset=UTF-8");
				resp.getWriter().write(bh.getLastUpdatedBy()+","+v.getVhclRegistrationNo()+","+dt.format(bh.getBkngFromDate())+","+dt.format(bh.getBkngToDate())+","+u.getUserName()+","+u.getUserEmail());
			}
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doPost(req, resp);
	}
}
