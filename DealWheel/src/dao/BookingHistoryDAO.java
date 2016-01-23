package dao;

import java.math.BigInteger;
import java.util.List;

import model.Bookingshistory;

public interface BookingHistoryDAO  extends BaseDAO<Bookingshistory>{

	public List<Object[]> getBookingDetailsForVendorId(BigInteger userId);
	
	public void cancelBooking(String bookingId);
	
}
