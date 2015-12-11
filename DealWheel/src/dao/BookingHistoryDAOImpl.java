package dao;

import static services.utility.GenericConstant.BOOKING_HISTORY_FOR_VENDOR;

import java.math.BigInteger;
import java.util.List;

import javax.persistence.Query;

import model.Bookingshistory;

import org.apache.log4j.Logger;

public class BookingHistoryDAOImpl<T>  extends  BaseDAOImpl<Bookingshistory> implements BookingHistoryDAO {

	private static Logger logger = Logger.getLogger(BookingHistoryDAOImpl.class);

	@Override
	public List<Object[]> getBookingDetailsForVendorId(BigInteger vendorId) {
		logger.debug("Fetching all the Booking detail for Vendor Id "+vendorId);
		Query q = em.createNamedQuery(BOOKING_HISTORY_FOR_VENDOR);
		q.setParameter("vendorId", vendorId);
		List<Object[]> bookingDetails = q.getResultList();
		return bookingDetails;
	}
	
}
