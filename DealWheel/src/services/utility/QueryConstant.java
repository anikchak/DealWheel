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
	public static String LIST_AVAILABLE_VEHICLES = "SELECT v, a, u, lv "
			+ "FROM Vehicle v ,  Address a , User u , ListedVehicle lv WHERE v.vhclId NOT IN "
			+ "(SELECT bh.bkngVehicle FROM Bookingshistory bh WHERE bh.bkngFromDate <= :toDate AND bh.bkngToDate >= :fromDate "
			+ "AND bh.bkngStatus  IN (:UPCOMING,:VIEWING)) AND a.addrId = v.vhclAddressId "
			//+ "AND a.addrType = :addrType "
			+ "AND u.userId = a.userId AND upper(a.addrCity) = :addrCity AND v.listedVhclId = lv.lvclId "
			+ "GROUP BY lv.lvclName,v.vhclAddressId,u.userId "
			+ "ORDER BY v.vhclPerDayCost,lv.lvclName";
	public static String GET_USER_EMAIL = "SELECT USER_EMAIL FROM USERS WHERE USER_NAME = ?";
	
	public static String GET_FULL_VEHICLE_DETAILS_USING_BOOKING_ID = "select v, lv, b, a, u from Vehicle v,ListedVehicle lv,Bookingshistory b, Address a, User u "
			+ "where u.userId = a.userId "
			+ "and a.addrId = v.vhclAddressId "
			+ "and v.listedVhclId = lv.lvclId "
			+ "and v.vhclId = b.bkngVehicle "
			+ "and b.bkngStatus in (:VIEWING,:UPCOMING) "
			+ "and b.bkngSeq = :BOOKINGID";
}
