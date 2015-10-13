package services.utility;

public class QueryConstant {

	public static String CANCEL_BOOKING = "Update bookingshistory set bkng_status = ? where bkng_seq = ?";
	public static String GET_BOOKING_DETAILS = "Select book,bike,pd,adds "
			+ "from Bookingshistory book,Vehicle bike,User us,User pd,Address adds "
			+ " where book.userId = us.userId"
			+ " AND book.bkngVehicle = bike.vhclId"
			+ " AND adds.addrId = bike.vhclAddressId"
			+ " AND pd.userId = adds.userId " + "AND us.userName = :username";
	public static String CLEAN_BOOKINGS = "update bookingshistory b set b.BKNG_STATUS = ? where TIME_TO_SEC(TIMEDIFF(NOW(),b.LAST_UPDATED))> ? and b.BKNG_STATUS in (?)";
	//public static String CLEAN_BOOKINGS = "update Bookingshistory b set b.bkngStatus = :timedout where TIME_TO_SEC(TIMEDIFF(NOW(),b.lastUpdated))> :tickervalue and b.bkngStatus in (:VIEWING)";
	public static String LIST_AVAILABLE_VEHICLES = "SELECT v, a "
			+ "FROM Vehicle v ,  Address a WHERE v.vhclId NOT IN "
			+ "(SELECT bh.bkngVehicle FROM Bookingshistory bh WHERE bh.bkngFromDate <= :toDate AND bh.bkngToDate >= :fromDate "
			+ "AND bh.bkngStatus  IN (:UPCOMING,:VIEWING)) AND a.addrId = v.vhclAddressId AND a.addrType = :addrType GROUP BY v.vhclName,v.vhclAddressId "
			+ "ORDER BY v.vhclPerDayCost,v.vhclName";
}
