package dao;

import java.math.BigInteger;
import java.util.List;

import model.Bookingshistory;

public interface BookingHistoryDAO  extends BaseDAO<Bookingshistory>{

	public List<Object[]> getBookingDetailsForVendorId(String userId);
	
	public Bookingshistory cancelBooking(String bookingId);
	
	public boolean checkFutureBookingAvailable(BigInteger id);	
}
