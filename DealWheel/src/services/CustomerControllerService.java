package services;

import static services.utility.GenericConstant.*;

import org.apache.log4j.Logger;

import dao.LoginDAOImpl;
import dao.UserDAOImpl;

import java.math.BigInteger;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query; 

import model.Address;
import model.Bookingshistory;
import model.ListedVehicle;
import model.LoginDetail;
import model.User;
import model.Vehicle;
import services.security.SecurePassword;
import services.utility.CommonUtility;
import services.utility.GenericConstant;
import services.utility.MessageBundle;
import services.utility.QueryConstant;

public class CustomerControllerService {

	EntityManagerFactory emf = Persistence.createEntityManagerFactory("DealWheel");
	EntityManager em = emf.createEntityManager();
	EntityTransaction et = em.getTransaction();
	
	private static Logger logger = Logger.getLogger(CustomerControllerService.class);
	
	/**
	 * This method is invoked when a new user is registering. 
	 * @param: user entered email id(username) and password
	 * @return: true - if the user record is entered successfully
	 * 			false - if there is any exception while entering user record 
	 */
	@SuppressWarnings("unchecked")
	public List<User> inserNewUser(String usr, String pwd,BigInteger mobileNo) {

		logger.info("ControllerService: inserting new user through insertNewUser");
		List returnUserList = null;
		// Secure password
		// get Salt to be used with password
		SecurePassword securePwd = new SecurePassword();
		byte[] salt = securePwd.createSalt();
		// Hashing password with salt
		try {
			pwd = securePwd.createHash(pwd, salt);
		} catch (NoSuchAlgorithmException excep) {
			logger.error("Exception in algorithm");
		} catch (InvalidKeySpecException e) {
			logger.error("Invalid Key Spec Exception");
		}
		try {
			if (em != null) {
				// Verify if the username already exists
				Query usrnmExist = em
						.createNamedQuery(LOGIN_DETAIL_FIND_USING_USER_NAME_AND_TYPE);
				usrnmExist.setParameter(LOGIN_USER_NAME, usr);
				usrnmExist.setParameter(LOGIN_USER_TYPE, USER_TYPE_CUSTOMER);
				List<LoginDetail> resultSet = (List<LoginDetail>) usrnmExist
						.getResultList();
				if (resultSet != null && resultSet.size() > 0) {
					logger.error("Unique username encountered");
					// return null;
				} else {

					// Beginning txn for User table record
					// et.begin();
					User u = new User();
					u.setUserEmail(usr);
					u.setUserName(usr);
					u.setUserPrimaryContact(mobileNo);
					u.setUserType(USER_TYPE_CUSTOMER);
					u.setLastUpdated(new Date());
					u.setLastUpdatedBy(usr);
					User insertedUser = new UserDAOImpl<User>().addNewUser(u);
					// em.persist(u);
					// et.commit();

					// Beginning txn for LoginDetail table record
					// et.begin();
					LoginDetail l = new LoginDetail();
					l.setLognUserId(insertedUser.getUserId());
					l.setLognUserName(usr);
					l.setLognPassword(pwd);
					l.setLastUpdatedBy(usr);
					l.setLognLastLoginDetail(new Date());
					l.setLastUpdated(new Date());
					// em.persist(l);
					// et.commit();
					LoginDetail newLoginDetail = new LoginDAOImpl<LoginDetail>()
							.addNewLogin(l);

					logger.info("User ID=" + insertedUser.getUserId());
					if (insertedUser.getUserId() != null) {
						returnUserList = getValidUserDetails(insertedUser
								.getUserId());
					}
				}
			}
		} catch (Exception e) {
			logger.error("Error while feeding user credentials to DB");
			e.printStackTrace();
		} finally {
			em.close();
		}
		return returnUserList;
	}
	
	/**
	 * This method is used to validate the entered user credentials 
	 */
	@SuppressWarnings("unchecked")
	public List<User> validateUser(String usrEntered, String pwdEntered) {
		logger.info("ControllerService:Validate user");

		SecurePassword securePwd = new SecurePassword();
		List<User> validatedUserDetails = null;
		boolean validationStatus = false;
		BigInteger loginUserId = null;
		// Query to fetch hashed password and salt
		try {
			if (em != null) {
				Query q = em
						.createNamedQuery(LOGIN_DETAIL_FIND_USING_USER_NAME_AND_TYPE);
				q.setParameter(LOGIN_USER_NAME, usrEntered);
				q.setParameter(LOGIN_USER_TYPE, USER_TYPE_CUSTOMER);
				List<LoginDetail> resultSet = (List<LoginDetail>) q
						.getResultList();
				if (resultSet != null && resultSet.size() == 1) {
					logger.info("Result fetched");
					LoginDetail details = (LoginDetail) resultSet.get(0);

					try {
						loginUserId = details.getLognUserId();
						validationStatus = securePwd.validatePassword(
								pwdEntered, details.getLognPassword());
					} catch (Exception e) {
						logger.error("Error while validating password");
					}
				} else {
					return null;
				}
			}
		} catch (Exception e) {
			logger.error("Exception while password verification");
			e.printStackTrace();
		}

		logger.info("validationStatus = " + validationStatus);
		if (validationStatus) {
			try {
				if (em != null) {
					et.begin();
					Query q = em
							.createNamedQuery(LOGIN_DETAIL_UPDATE_LAST_LOGIN);
					q.setParameter(LAST_LOGIN_DETAIL, new Date());
					q.setParameter(LOGIN_USER_NAME, usrEntered);
					int updateStatus = q.executeUpdate();
					et.commit();
					if (updateStatus > 0) {
						validatedUserDetails = getValidUserDetails(loginUserId);
					}

				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				em.close();
			}
			return validatedUserDetails;
		} else
			em.close();
		return null;
	}
	
	public List getValidUserDetails(BigInteger loginUserId) {
		logger.info("Update Successfull..fetching user details for ="
				+ loginUserId);
		Query fetchUserDetails = null;
		try {
			if (em != null) {
				fetchUserDetails = em.createNamedQuery(USER_FIND_BY_ID);
				fetchUserDetails.setParameter(USERID, loginUserId);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return fetchUserDetails.getResultList();
	}
	/**
	 * This method is used to prepare a map which will hold the related vehicle data (vehicle details and pickup locations) for the user entered date range 
	 * @param from
	 * @param to
	 * @return Map: key - holds the vehicle details, value - holds location details for the vehicles
	 */
	@SuppressWarnings({"unchecked", "rawtypes" })
	public Map fetchSearchResult(Date from, Date to,String selectedLocation) {
		logger.info("Inside Fetch Search Result");
		Map displayResultMap = null;
		try {
			if (em != null) {
				
				Query q = em
						.createQuery(QueryConstant.LIST_AVAILABLE_VEHICLES);
				q.setParameter(TODATE, to);
				q.setParameter(FROMDATE, from);
				q.setParameter(UPCOMING, UPCOMING);
				q.setParameter(VIEWING, VIEWING);
				q.setParameter(ADDR_TYPE, PICKUP);
				q.setParameter("addrCity", selectedLocation.toUpperCase());

				List<Object[]> searchResultSet = (List<Object[]>) q
						.getResultList();
				logger.info("Execution successful"
						+ searchResultSet.size());
				if (searchResultSet != null && searchResultSet.size() > 0) {
					displayResultMap = prepareSearchResultDisplay(searchResultSet);

				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			em.close();
		}
		return displayResultMap;
	}
	
	/**
	 * This method prepares the Map to be returned by fetchSearchResult()
	 * @param searchResultSet
	 * @return Map: key - holds the vehicle details, value - holds location details for the vehicles
	 */
	 @SuppressWarnings({ "unchecked", "rawtypes" })
	public Map prepareSearchResultDisplay(List<Object[]> searchResultSet) {
		logger.info("prepareSearchResultDisplay method invoked");

		Map displaySearchResultMap = new HashMap();
		int len = searchResultSet.size();
		for (Object[] o : searchResultSet) {
			// Vehicle v = (Vehicle)searchResultSet.get(i);
			Vehicle v = (Vehicle) o[0];
			Address a = (Address) o[1];
			User u  = (User) o[2];
			ListedVehicle lv = (ListedVehicle) o[3];
			if (displaySearchResultMap != null) {
				String key = v.getVhclAddressId()+"#"+lv.getLvclName();
				String value = "vehcileImageURL"+"#"+
								lv.getLvclName()+"#"+
								lv.getLvclMake()+"#"+
								v.getVhclPerDayCost()+"#"+
								v.getVhclSecurityDeposit()+"#"+
								u.getUserName()+"#"+
								a.getAddrLocality()+" ,"+a.getAddrCity()+"#"+
								a.getAddrLine1()+"\n"+
								((a.getAddrLine2()!=null && (!a.getAddrLine2().isEmpty() || "null".equalsIgnoreCase(a.getAddrLine2())))?(a.getAddrLine2()+"\n"):"")+
								((a.getAddrLine2()!=null && (!a.getAddrLine2().isEmpty() || "null".equalsIgnoreCase(a.getAddrLine2()) ))?(a.getAddrLine3()+"\n"):"")+
								a.getAddrLocality()+"\n"+a.getAddrCity()+" - "+a.getAddrPinCode()+"\n"+a.getAddrState()+"\n"+a.getAddrCountry();
				
				if (!displaySearchResultMap.containsKey(key)) {
					displaySearchResultMap.put(key, value);
				}
			}
			/*
			if (displaySearchResultMap != null) {
				String locationValue = null;
				String key = v.getVhclName() + DOLLAR + v.getVhclMake() + DOLLAR
						+ v.getVhclPerDayCost() + DOLLAR
						+ v.getVhclSecurityDeposit();
				if (displaySearchResultMap.containsKey(key)) {
					locationValue = (String) displaySearchResultMap.get(key);
					locationValue = locationValue + DOLLAR + a.getAddrLocality();
				} else {
					locationValue = a.getAddrLocality();
				}
				locationValue = locationValue + PERCENT + v.getVhclId();
				displaySearchResultMap.put(key, locationValue);
			}
*/
		}
		logger.info("Map Values= " + displaySearchResultMap);
		logger.info("Map size= " + displaySearchResultMap.size());
		return displaySearchResultMap;
	}
	 
	 /**
	  * This method is used to lock the records while the user is in booking process for a specified time-duration to maintain concurrency
	  * @param lockingcode
	  * @param fromDate
	  * @param toDate
	  * @param pickupLocation
	  * @param vehicleId
	  * @param usernm
	  * @return temporary booking id
	  */
	public long updateBooking(String lockingcode, Date fromDate, Date toDate,
			String vehicleName, String usernm, long userId) {
		System.out.println("lockingcode=" + lockingcode + "\nfromDate="
				+ fromDate + "\ntoDate=" + toDate + "\nvehicleName="
				+ vehicleName + "\nusernm=" + usernm);
		String lastRowId = null;
		try {
			if (em != null) {
				et.begin();
				Query q = em
						.createNativeQuery("insert into bookingshistory (BKNG_STATUS,BKNG_FROM_DATE,BKNG_TO_DATE,BKNG_VEHICLE,BKNG_CREATION_DATE,LAST_UPDATED_BY,LAST_UPDATED,USER_ID) "
								+ "select ?1,?2,?3,v.vhcl_id,now(),?4,now(),?5 "
								+ "from vehicles v where v.vhcl_name=?6 and v.vhcl_id not in ( "
								+ "SELECT bh.BKNG_VEHICLE FROM bookingshistory bh WHERE "
								+ "bh.BKNG_FROM_DATE <= ?3 AND bh.BKNG_TO_DATE >= ?2 AND bh.BKNG_STATUS  IN (?7,?8)) limit 1");

						
				q.setParameter(1, lockingcode);
				q.setParameter(2, fromDate);
				q.setParameter(3, toDate);
				q.setParameter(4, usernm);
				q.setParameter(5, userId);
				q.setParameter(6, vehicleName);

				q.setParameter(7, toDate);
				q.setParameter(8, fromDate);

				int entryUpdate = q.executeUpdate();
				logger.info("value post update=" + entryUpdate);
				et.commit();
				if (entryUpdate == 1) {
					Query seqQuery = em
							.createNamedQuery(BOOKING_HISTORY_FIND_BOOKING_BY_SEQ);
					List rowIdList = (List) seqQuery.setMaxResults(1)
							.getResultList();
					if (rowIdList != null && rowIdList.size() > 0) {
						lastRowId = (String) rowIdList.get(0);
					}
					logger.info("Last Row=" + lastRowId);
				}
			}
		} catch (Exception e) {
			logger.error("Exception occured while inserting record in updateBooking()");
			e.printStackTrace();
		} finally {
			em.close();
		}
		return (lastRowId != null) ? Long.parseLong(lastRowId) : 0L;
	}
	 
	 /**
	  * This method will be executed on every page load. Its is used to clear the records which have been timed out  
	  */
	public void cleanBookings() {
		logger.info("cleanBookings called..!!!");
		int cleanStatus = 0;
		try {
			if (em != null) {
				et.begin();
				Query q = em
						.createNativeQuery(QueryConstant.CLEAN_BOOKINGS);
				q.setParameter(1, TIMEDOUT);
				q.setParameter(2, MessageBundle.TICKERVALUE);
				q.setParameter(3, VIEWING);
				cleanStatus = q.executeUpdate();
				et.commit();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			em.close();
		}
		logger.info("Clean Status=" + cleanStatus);
	}
	 
	public boolean updateBookingWithOrderIdonSuccess(long tempBookingSeq,String generatedOrderId,String uName) {
		logger.info("Inside method updateBookingWithOrderIdonSuccess");
		CommonUtility cu = new CommonUtility();
		int updateStatus = 0;String uMail;
		try {
			if (em != null) {
				
				et.begin();
				Query q = em.createNamedQuery(BOOKING_HISTORY_UPDATE);
				// q.setParameter(1, "UPCMNG");
				q.setParameter(BKNG_STATUS, GenericConstant.UPCOMING);
				q.setParameter(BKNG_NUMBER, generatedOrderId);
				q.setParameter(BKNG_SEQ, String.valueOf(tempBookingSeq));
				q.setParameter(BKNG_STATUS_WHERE_CLAUSE, GenericConstant.VIEWING);
				updateStatus = q.executeUpdate();
				et.commit();
				Query q1= em.createNativeQuery(QueryConstant.GET_USER_EMAIL);
				q1.setParameter(1,uName);
				uMail = (String) q1.getSingleResult();
				System.out.println("User Email"+uMail);
				cu.sendEmailNotification(generatedOrderId,uMail);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			logger.info("Finally invoked");
		    em.close();
		}
		return (updateStatus == 1) ? true : false;
	}
	
	/*
	 @SuppressWarnings("unchecked")
	public Map fetchStaticData() {
		logger.info("Inside method fetchStaticData");
		Map staticVehicleDetails = new HashMap();
		try {
			if (em != null) {
				Query q = em.createNamedQuery(VEHICLE_FIND_ALL);
				List<Vehicle> resultList = (List<Vehicle>) q.getResultList();
				if (resultList != null & resultList.size() > 0) {
					for (Vehicle veh : resultList) {
						staticVehicleDetails.put(
								veh.getVhclName(),
								veh.getVhclMake() + GenericConstant.HASH
										+ veh.getVhclPerDayCost() + GenericConstant.HASH
										+ veh.getVhclSecurityDeposit());
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			logger.info("Finally invoked");
			em.close();
		}

		return staticVehicleDetails;
	}
	 */
	 @SuppressWarnings("unchecked")
	public List<Object[]> getBookings(String uName) {
		logger.info("Inside getbookings()");
		
		em.getEntityManagerFactory().getCache().evictAll();
		Query q = em
				.createQuery(QueryConstant.GET_BOOKING_DETAILS,Bookingshistory.class);
		q.setParameter(USERNAME, uName);
		List<Object[]> searchResultSet = (List<Object[]>) q.getResultList();

		return searchResultSet;
} 
	 
	public void cancelbooking(String uBookId) {
		logger.info("Inside cancelbooking");
		logger.info("Bookingid passed="+uBookId);
		et.begin();
		Query q = em
				.createNativeQuery(QueryConstant.CANCEL_BOOKING);
		q.setParameter(1, CANCELLED);
		q.setParameter(2, uBookId);
		int cleanStatus = q.executeUpdate();
		et.commit();
	} 
	 
}
