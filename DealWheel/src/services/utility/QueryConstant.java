package services.utility;

public class QueryConstant {

	public static String CANCEL_BOOKING = "Update Bookingshistory bh set bh.bkngStatus = :BOOKINGSTATUS where bh.bkngSeq = :BOOKINGSEQ";
	public static String GET_BOOKING_DETAILS = "SELECT b, v, lv, a, u, vProvider FROM Bookingshistory b, Vehicle v, ListedVehicle lv, Address a, User u, User vProvider "
			+ "WHERE u.userId = b.userId "
			+ "AND v.vhclId = b.bkngVehicle "
			+ "AND a.addrId = v.vhclAddressId "
			+ "AND lv.lvclId = v.listedVhclId "
			+ "AND vProvider.userId = a.userId "
			+ "AND b.bkngStatus IN (:UPCOMING,:COMPLETED,:CANCELLED) "
			+ "AND b.userId = :USERID";
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
			+ "and b.bkngStatus in (:UPCOMING,:VIEWING) "
			+ "and b.bkngSeq = :BOOKINGID";
	
	public static String GET_USER_DETAILS = "select u,ld from User u,LoginDetail ld "
	                                        + "Where u.userId = ld.lognUserId"
			                                + " AND u.userId = :USERID";


}
