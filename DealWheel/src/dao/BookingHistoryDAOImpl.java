package dao;

import static services.utility.GenericConstant.BOOKING_HISTORY_FOR_VENDOR;
import static services.utility.GenericConstant.BOOKING_HISTORY_BY_ID;
import static services.utility.GenericConstant.CANCELLED;
import static services.utility.GenericConstant.BOOKING_HISTORY_FOR_ID_BY_DATE;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Query;

import model.Bookingshistory;

import org.apache.log4j.Logger;

public class BookingHistoryDAOImpl<T>  extends  BaseDAOImpl<Bookingshistory> implements BookingHistoryDAO {

	private static Logger logger = Logger.getLogger(BookingHistoryDAOImpl.class);

	@Override
	public List<Object[]> getBookingDetailsForVendorId(BigInteger vendorId) {
		logger.debug("Fetching all the Booking detail for Vendor Id "+vendorId);
		List<String> bookingStatusList = new ArrayList<String>();
		bookingStatusList.add("UPCOMING");
		bookingStatusList.add("COMPLETED");
		bookingStatusList.add("CANCELLED");
		bookingStatusList.add("VENDORCANCELLED");
		Query q = em.createNamedQuery(BOOKING_HISTORY_FOR_VENDOR);
		q.setParameter("vendorId", vendorId);
		q.setParameter("bookingstatus", bookingStatusList);
		List<Object[]> bookingDetails = q.getResultList();
		return bookingDetails;
	}

	public Bookingshistory findBookingHistoryById(String bookingId) {
		logger.debug("Fetching all the Booking detail for Booking Id "+ bookingId);
		Query q = em.createNamedQuery(BOOKING_HISTORY_BY_ID);
		q.setParameter("bookingId", bookingId);
		Bookingshistory bookingDetail = (Bookingshistory) q.getSingleResult();
		return bookingDetail;
	}
	
	public void cancelBooking(String bookingId){
		logger.debug("Cancelling booking with Id "+bookingId);
		Bookingshistory bkngHistory = findBookingHistoryById(bookingId);
		bkngHistory.setBkngStatus("VENDORCANCELLED");
		update(bkngHistory);
	}

	public boolean checkFutureBookingAvailable(BigInteger vehicleId) {
		boolean isAvailable = true;
		logger.debug("Checking if booking for Vehicle Id "+vehicleId+" is available after "+new Date());
			Query q = em.createNamedQuery(BOOKING_HISTORY_FOR_ID_BY_DATE);
			q.setParameter("vehicleId", vehicleId);
			q.setParameter("today", new Date());
			Bookingshistory bookingDetail = (Bookingshistory)(q.getResultList().size()!=0?q.getResultList().get(0):null);
			if(bookingDetail == null || bookingDetail.getBkngSeq().isEmpty())
				isAvailable = false;
			return isAvailable;
	}
}
