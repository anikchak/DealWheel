package services;
import static services.utility.GenericConstant.BKNG_NUMBER;
import static services.utility.GenericConstant.BKNG_SEQ;
import static services.utility.GenericConstant.BKNG_STATUS;
import static services.utility.GenericConstant.BKNG_STATUS_WHERE_CLAUSE;
import static services.utility.GenericConstant.BOOKING_HISTORY_FIND_BOOKING_BY_SEQ;
import static services.utility.GenericConstant.BOOKING_HISTORY_UPDATE;
import static services.utility.GenericConstant.CANCELLED;
import static services.utility.GenericConstant.FROMDATE;
import static services.utility.GenericConstant.LAST_LOGIN_DETAIL;
import static services.utility.GenericConstant.LOGIN_DETAIL_FIND_USING_USER_NAME_AND_TYPE;
import static services.utility.GenericConstant.LOGIN_DETAIL_UPDATE_LAST_LOGIN;
import static services.utility.GenericConstant.LOGIN_USER_NAME;
import static services.utility.GenericConstant.LOGIN_USER_TYPE;
import static services.utility.GenericConstant.TIMEDOUT;
import static services.utility.GenericConstant.TODATE;
import static services.utility.GenericConstant.UPCOMING;
import static services.utility.GenericConstant.USERID;
import static services.utility.GenericConstant.USER_FIND_BY_ID;
import static services.utility.GenericConstant.USER_TYPE_CUSTOMER;
import static services.utility.GenericConstant.USER_UPDATE_QUERY;
import static services.utility.GenericConstant.VIEWING;

import java.math.BigInteger;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import model.Address;
import model.Bookingshistory;
import model.ListedVehicle;
import model.LoginDetail;
import model.Payment;
import model.User;
import model.Vehicle;

import org.apache.log4j.Logger;

import services.mail.EmailType;
import services.mail.SendMail;
import services.security.SecurePassword;
import services.utility.CommonUtility;
import services.utility.GenericConstant;
import services.utility.MessageBundle;
import services.utility.QueryConstant;
import dao.LoginDAOImpl;
import dao.UserDAOImpl;

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
	public List<User> inserNewUser(String usr, String pwd,BigInteger mobileNo,String serverName,String serverPort) {

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
					String randString = generateRandomString();
					u.setUserEmailOtp(randString);
					List<String> params = new ArrayList<String>();
					params.add(0,usr);
					String url = "http://"+serverName+":"+serverPort+"/DealWheel/UserRegisterationConfirmation.jsp?confirmationurl="+randString;
					params.add(1,url);
					SendMail s = new SendMail();
					SendMail.sendEmailNotification(EmailType.VERIFY_USER, usr, params);
					//SecureRandom rands = new SecureRandom();
					//String s = BigInteger(130, rands).toString(32);
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
						returnUserList = getValidUserDetails(insertedUser.getUserId());
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
		String loginUserId = null;
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
	
	public List getValidUserDetails(String loginUserId) {
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
	public Map<String, String> fetchSearchResult(Date from, Date to,String selectedLocation) {
		logger.info("Inside Fetch Search Result");
		Map<String, String> displayResultMap = null;
		try {
			if (em != null) {
				
				Query q = em
						.createQuery(QueryConstant.LIST_AVAILABLE_VEHICLES);
				q.setParameter(TODATE, to);
				q.setParameter(FROMDATE, from);
				q.setParameter(UPCOMING, UPCOMING);
				q.setParameter(VIEWING, VIEWING);
				//q.setParameter(ADDR_TYPE, PICKUP);
				logger.info(selectedLocation);
				q.setParameter("addrCity", selectedLocation.toUpperCase());

				List<Object[]> searchResultSet = (List<Object[]>) q
						.getResultList();
				logger.info("Execution successful"
						+ searchResultSet.size());
				if (searchResultSet != null && searchResultSet.size() > 0) {
					displayResultMap = prepareSearchResultDisplay(searchResultSet);
				}else if (searchResultSet != null && searchResultSet.size() == 0){
					displayResultMap = null;
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
	public Map<String, String> prepareSearchResultDisplay(List<Object[]> searchResultSet) {
		logger.info("prepareSearchResultDisplay method invoked");

		Map<String, String> displaySearchResultMap = new HashMap<String, String>();
		int len = searchResultSet.size();
		for (Object[] o : searchResultSet) {
			// Vehicle v = (Vehicle)searchResultSet.get(i);
			Vehicle v = (Vehicle) o[0];
			Address a = (Address) o[1];
			User u  = (User) o[2];
			ListedVehicle lv = (ListedVehicle) o[3];
			if (displaySearchResultMap != null) {
				String key = v.getVhclAddressId()+"#"+lv.getLvclId();
				String value =  lv.getLvclImgUrl()+"#"+
								lv.getLvclName()+"#"+
								lv.getLvclMake()+"#"+
								v.getVhclPerDayCost()+"#"+
								v.getVhclSecurityDeposit()+"#"+
								u.getUserName()+"#"+
								a.getAddrLocality()+" ,"+a.getAddrCity()+"#"+
								a.getAddrLine1()+"\n"+
								((a.getAddrLine2()!=null && (!a.getAddrLine2().isEmpty() || "null".equalsIgnoreCase(a.getAddrLine2())))?(a.getAddrLine2()+"\n"):"")+
								((a.getAddrLine3()!=null && (!a.getAddrLine3().isEmpty() || "null".equalsIgnoreCase(a.getAddrLine3()) ))?(a.getAddrLine3()+"\n"):"")+
								a.getAddrLocality()+"\n"+a.getAddrCity()+" - "+a.getAddrPinCode()+"\n"+a.getAddrState()+"\n"+a.getAddrCountry();
				
				if (!displaySearchResultMap.containsKey(key)) {
					displaySearchResultMap.put(key, value);
				}
			}

		}
		//logger.info("Map Values= " + displaySearchResultMap);
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
			String vehicleAddress, String listedVehicleId, String usernm, String userId) {
			logger.info("lockingcode=" + lockingcode + "\nfromDate="
				+ fromDate + "\ntoDate=" + toDate + "\nlistedVehicleId="
				+ listedVehicleId + "\nvehicleAddress="+vehicleAddress+"\nusernm=" + usernm);
		String lastRowId = null;
		List<BigInteger> bookingVehicleList = new ArrayList<BigInteger>();
		try {
			if (em != null) {
				Query getVehicleDetail = em.createQuery("select v,lv from Vehicle v, ListedVehicle lv where lv.lvclId = v.listedVhclId and "
							+ "v.listedVhclId = :listedVechileId and v.vhclAddressId = :vehicleAddressId and v.vhclId NOT IN (SELECT bh.bkngVehicle FROM Bookingshistory bh WHERE "
							+ "bh.bkngFromDate <= :toDate AND bh.bkngToDate >= :fromDate AND bh.bkngStatus IN (:UPCOMING,:VIEWING)) ");
				
				getVehicleDetail.setParameter(TODATE, toDate);
				getVehicleDetail.setParameter(FROMDATE, fromDate);
				getVehicleDetail.setParameter(UPCOMING, UPCOMING);
				getVehicleDetail.setParameter(VIEWING, VIEWING);
				getVehicleDetail.setParameter("listedVechileId", new BigInteger(listedVehicleId));
				getVehicleDetail.setParameter("vehicleAddressId", new BigInteger(vehicleAddress));
				List<Object[]> vehicleDetailList = (List<Object[]>) getVehicleDetail.setMaxResults(1).getResultList();
				logger.info("Update Booking: vehicle details list="+vehicleDetailList);
				if(vehicleDetailList!=null){
					for(Object[] o : vehicleDetailList){
						logger.info("Update Booking: Starting insertion into Bookingshistory");
						Vehicle v = (Vehicle)o[0];
						ListedVehicle lv = (ListedVehicle)o[1];
						et.begin();
						Bookingshistory bh = new Bookingshistory();
						bh.setBkngStatus(VIEWING);
						bh.setBkngFromDate(fromDate);
						bh.setBkngToDate(toDate);
						bh.setBkngVehicle(v.getVhclId());
						bh.setBkngPerDayCost(v.getVhclPerDayCost());
						bh.setBkngSecurityDeposit(v.getVhclSecurityDeposit());
						bh.setBkngCreationDate(new Date());
						bh.setLastUpdatedBy(usernm);
						bh.setLastUpdated(new Date());
						bh.setUserId(userId);
						em.persist(bh);
						et.commit();
					}
				}
				
					Query seqQuery = em
							.createNamedQuery(BOOKING_HISTORY_FIND_BOOKING_BY_SEQ);
					List rowIdList = (List) seqQuery.setMaxResults(1)
							.getResultList();
					if (rowIdList != null && rowIdList.size() > 0) {
						lastRowId = (String) rowIdList.get(0);
					}
					logger.info("Last Row=" + lastRowId);
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
		int cleanStatus = 0,deletedRecStatus = 0;
		try {
			if (em != null) {
				et.begin();
				Query q = em
						.createNativeQuery(QueryConstant.CLEAN_BOOKINGS);
				q.setParameter(1, TIMEDOUT);
				q.setParameter(2, MessageBundle.TICKERVALUE);
				q.setParameter(3, VIEWING);
				cleanStatus = q.executeUpdate();
				
				if(cleanStatus>0){
					Query deleteTimedOutRecs = em.createNativeQuery("delete from bookingshistory where bkng_status = ?1");
					deleteTimedOutRecs.setParameter(1, TIMEDOUT);
					deletedRecStatus = deleteTimedOutRecs.executeUpdate();
				}
				et.commit();
				updateBookingsToComplete();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			em.close();
		}
		logger.info("Clean Status=" + cleanStatus+ "\tdeletedRecStatus="+deletedRecStatus);
	}
	
	public void updateBookingsToComplete(){
		logger.info("updateBookingsToComplete() invoked");
		try{
			if(em!=null){
				et.begin();
				Query q = em.createQuery("UPDATE Bookingshistory bh set bh.bkngStatus = :BOOKINGSTATUS where bh.bkngToDate <= :TODAY and bh.bkngStatus = :UPCOMING");
				q.setParameter("BOOKINGSTATUS", "COMPLETED");
				q.setParameter("TODAY", new Date());
				q.setParameter(UPCOMING, UPCOMING);
				int completedBookingStatus = q.executeUpdate();
				logger.info("completedBookingStatus = "+completedBookingStatus);
				et.commit();
			}
		}catch(Exception e){
			e.printStackTrace();
		}
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
				//Query q1= em.createNativeQuery(QueryConstant.GET_USER_EMAIL);
				//q1.setParameter(1,uName);
				//uMail = (String) q1.getSingleResult();
				//logger.info("User Email"+uMail);
				//cu.sendEmailNotification("confirmationEmail",generatedOrderId,uMail);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			logger.info("Finally invoked");
		    em.close();
		}
		return (updateStatus == 1) ? true : false;
	}
	
	
	public boolean updateUserDetails(BigInteger userId,String[] params) {
		logger.info("Inside method UpdateUserDetails");
		CommonUtility cu = new CommonUtility();
		int updateStatus = 0;String uMail;
		try {
			if (em != null) {
				
				et.begin();
				Query q = em.createNamedQuery(USER_UPDATE_QUERY);
							// q.setParameter(1, "UPCMNG");
				q.setParameter(USERID, userId.toString());
				q.setParameter("userName",params[1]);
				q.setParameter("userEmail",params[0]);
				q.setParameter("userGender",params[4]);
				q.setParameter("userPrimaryContact",new BigInteger(params[3]));
				updateStatus = q.executeUpdate();
				et.commit();
				//cu.sendEmailNotification(generatedOrderId,uMail);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			logger.info("Finally invoked");
		    em.close();
		}
		return (updateStatus == 1) ? true : false;
	}
	
	
	
	 @SuppressWarnings("unchecked")
	public List<Object[]> getMyBookingsHistory(BigInteger userId) {
		logger.info("Inside getbookings() where userId passed = "+userId);
		List<Object[]> searchResultSet= null;
		try{
		//em.getEntityManagerFactory().getCache().evictAll();
		Query q = em.createQuery(QueryConstant.GET_BOOKING_DETAILS);
		q.setParameter("USERID", userId.toString());
		q.setParameter(UPCOMING, UPCOMING);
		q.setParameter("COMPLETED", "COMPLETED");
		q.setParameter("CANCELLED", "CANCELLED");
		q.setParameter("VENDORCANCELLED", "VENDORCANCELLED");
		searchResultSet = (List<Object[]>) q.getResultList();
		}catch(Exception e){
			e.printStackTrace();
		}
		return searchResultSet;
} 
	
	
	 @SuppressWarnings("unchecked")
	public List<Object[]> getMyProfile(BigInteger userId) {
		logger.info("Inside getMyProfile() where userId passed = "+userId);
		List<Object[]> searchResultSet= null;
		try{
		//em.getEntityManagerFactory().getCache().evictAll();
		Query q = em.createQuery(QueryConstant.GET_USER_DETAILS);
		q.setParameter("USERID", userId.toString());
		searchResultSet = (List<Object[]>) q.getResultList();
		}catch(Exception e){
			e.printStackTrace();
		}
		return searchResultSet;
} 
	 
	public int cancelBooking(String uBookId) {
		
		logger.info("Inside cancelbooking");
		logger.info("Bookingid passed="+uBookId);
		int cancelStatus = 0;
		try{
		et.begin();
		Query q = em.createQuery(QueryConstant.CANCEL_BOOKING);
		q.setParameter("BOOKINGSTATUS", CANCELLED);
		q.setParameter("BOOKINGSEQ", uBookId);
		cancelStatus = q.executeUpdate();
		et.commit();
		logger.info("cancelBooking: cancelStatus = "+cancelStatus);
		}catch(Exception e){
			e.printStackTrace();
		}
		return cancelStatus;
	} 
	
	/**
	 * This method is used to fetch selected vehicle related details using the temporarily generated booking id
	 * @param tempbookingid
	 * @return List - Having details about vehicle which will be displayed for review
	 */
	@SuppressWarnings("unchecked")
	public List fetchVehicleUsingTempBooking(String tempBookingId){
		List<Object[]> fetchedVehicleList = null;
		try{
			if (em != null) {
				Query q = em.createQuery(QueryConstant.GET_FULL_VEHICLE_DETAILS_USING_BOOKING_ID);
				q.setParameter("BOOKINGID", tempBookingId);
				q.setParameter(VIEWING, VIEWING);
				q.setParameter(UPCOMING, UPCOMING);
				fetchedVehicleList = (List<Object[]>)q.getResultList();
				if(fetchedVehicleList!=null && fetchedVehicleList.size()==1){
					logger.info("Fetched List Size using tempBookingId="+fetchedVehicleList.size());
					return fetchedVehicleList;
				}
				else{
					logger.info("Fetched List Size using tempBookingId="+fetchedVehicleList.size());
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			em.close();
		}
		return fetchedVehicleList;
	}
	
	public void cleanBookingUsingTempBookingId(String tempBookingId){
		
		try{
			if(em != null){
				et.begin();
				Query q = em.createNativeQuery("delete from bookingshistory where bkng_seq = ?2");
				q.setParameter(1, TIMEDOUT);
				q.setParameter(2, tempBookingId);
				int updateStatus = q.executeUpdate();
				et.commit();
				logger.info("Update Status from cleanBookingUsingTempBookingId = "+updateStatus);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public String resetPassword(String userName, BigInteger mobileNumber){
		logger.info("resetPassword: Username= "+userName+ " mobileNumber="+mobileNumber);
		try{
		if(em!=null){
			Query q = em.createNativeQuery("select user_id from users where user_email = ? and user_primary_contact = ?");
			q.setParameter(1, userName);
			q.setParameter(2, mobileNumber);
			Long validUserDetail = (Long) q.getSingleResult();
			if(validUserDetail!=null){
				logger.info("validUserDetail after fetch = "+validUserDetail);
				String tempPwdGenerated =  Long.toHexString(Double.doubleToLongBits(Math.random()));
				String pwdToInsert = null;
				logger.info("Generating random password = "+tempPwdGenerated);
				// Secure password
				// get Salt to be used with password
				SecurePassword securePwd = new SecurePassword();
				byte[] salt = securePwd.createSalt();
				// Hashing password with salt
				try {
					pwdToInsert = securePwd.createHash(tempPwdGenerated, salt);
				} catch (NoSuchAlgorithmException excep) {
					logger.error("Exception in algorithm");
				} catch (InvalidKeySpecException e) {
					logger.error("Invalid Key Spec Exception");
				}
				et.begin();
				Query resetPwd = em.createNativeQuery("UPDATE login_detail set LOGN_PASSWORD = ? where LOGN_USER_ID = ? ");
				resetPwd.setParameter(1, pwdToInsert);
				resetPwd.setParameter(2, validUserDetail);
				int resetStatus = resetPwd.executeUpdate();
				et.commit();
				if(resetStatus>0){
					return tempPwdGenerated;
				}else{
					return null;
				}
			}
		}
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
	
	public String ConfirmationUserRegisteration(String param){
		logger.info("Confirm registeration : confirm String = "+param);
		try{
		if(em!=null){
			System.out.println(param);
			Query q = em.createNativeQuery("select user_id from users where user_email_otp = ?");
			q.setParameter(1, param);
			int resetStatus = 0; 
			Long validUserDetail = (Long) q.getSingleResult();
			System.out.println(validUserDetail);
			if (validUserDetail != null){
				et.begin();
				q = em.createNativeQuery("Update users set user_email_otp = null where user_id = ?");
				q.setParameter(1,validUserDetail);
				 resetStatus = q.executeUpdate();
				et.commit();
				if(resetStatus > 0){
					return "Success";
				}
				else
					return "Failure";
			}
			
			
		}
		
	}
	catch (Exception e){
		e.printStackTrace();
	}
	
		return null;
	}
	
	 private static final String CHAR_LIST =
		        "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
		    private static final int RANDOM_STRING_LENGTH = 64;
		     
		    /**
		     * This method generates random string
		     * @return
		     */
		    public String generateRandomString(){
		         
		        StringBuffer randStr = new StringBuffer();
		        for(int i=0; i<RANDOM_STRING_LENGTH; i++){
		            int number = getRandomNumber();
		            char ch = CHAR_LIST.charAt(number);
		            randStr.append(ch);
		        }
		        return randStr.toString();
		    }
		    /**
		     * This method generates random numbers
		     * @return int
		     */
		    private int getRandomNumber() {
		        int randomInt = 0;
		        Random randomGenerator = new Random();
		        randomInt = randomGenerator.nextInt(CHAR_LIST.length());
		        if (randomInt - 1 == -1) {
		            return randomInt;
		        } else {
		            return randomInt - 1;
		        }
		    }
	
		    
	public void updatePaymentDetails(Map params){
		logger.info("Inside updatePaymentDetails");
		logger.info("Payment Details="+params);
		try{
			if(em!=null){
				et.begin();
				Payment p  = new Payment();
				p.setAddedon(params.get("addedon").toString());
				p.setAmount(params.get("amount").toString());
				p.setBankcode(params.get("bankcode").toString());
				p.setBankRefNum(params.get("bank_ref_num").toString());
				p.setBookingId(params.get("bookingId").toString());
				p.setCardcategory(params.get("cardcategory").toString());
				p.setCardType(params.get("card_type").toString());
				p.setDiscount(params.get("discount").toString());
				p.setEmail(params.get("email").toString());
				p.setErrorcode(params.get("errorcode").toString());
				p.setErrorMessage(params.get("error_message").toString());
				p.setFirstname(params.get("firstname").toString());
				p.setMihpayid(params.get("mihpayid").toString());
				p.setNetAmountDebit(params.get("net_amount_debit").toString());
				p.setPaymentMode(params.get("mode").toString());
				p.setPaymentsource(params.get("paymentsource").toString());
				p.setPgType(params.get("pg_type").toString());
				p.setPhone(params.get("phone").toString());
				p.setProductinfo(params.get("productinfo").toString());
				p.setStatus(params.get("status").toString());
				p.setTxnid(params.get("txnid").toString());
				p.setUnmappedstatus(params.get("unmappedstatus").toString());
				em.persist(p);
				et.commit();
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
}


