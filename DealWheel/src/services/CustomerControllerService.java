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
import model.LoginDetail;
import model.User;
import model.Vehicle;
import services.security.SecurePassword;
import services.utility.MessageBundle;

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
	public List<User> inserNewUser(String usr,String pwd){
	
		logger.info("ControllerService: inserting new user through insertNewUser");
		List returnUserList=null;
		//Secure password
		//get Salt to be used with password
		SecurePassword securePwd = new SecurePassword();
		byte[] salt = securePwd.createSalt();
		// convert salt to hexadecimal value
		String hexedSalt = securePwd.toHex(salt);
		
		//Hashing password with salt
		try{
			pwd = securePwd.createHash(pwd, salt);
		}catch(NoSuchAlgorithmException excep){
			System.out.println("Exception in algorithm");
		}catch(InvalidKeySpecException e){
			System.out.println("Invalid Key Spec Exception");
		}
		try{
		if(em!=null)
		{
			//Verify if the username already exists
			Query usrnmExist  = em.createNamedQuery(LOGIN_DETAIL_FIND_USING_USER_NAME_AND_TYPE);
			usrnmExist.setParameter("loginUserName", usr);
			usrnmExist.setParameter("loginUserType",USER_TYPE_USER);
			List<LoginDetail> resultSet = (List<LoginDetail>)usrnmExist.getResultList();
			if(resultSet!=null && resultSet.size()>0){
				System.out.println("Unique username encountered");
				//return null;
			}else{
			   
				//Beginning txn for User table record
				//et.begin();
				User u = new User();
				u.setUserEmail(usr);
				u.setUserType("CUST");
				u.setLastUpdated(new Date());
				u.setLastUpdatedBy(usr);
				User insertedUser = new UserDAOImpl<User>().addNewUser(u);
				//em.persist(u);
				//et.commit();
							
				//Beginning txn for LoginDetail table record
				//et.begin();
				LoginDetail l = new LoginDetail();
				l.setLognUserId(insertedUser.getUserId());
				l.setLognUserName(usr);
				l.setLognPassword(pwd);
				l.setLastUpdatedBy(usr);
				l.setLognLastLoginDetail(new Date());
				l.setLastUpdated(new Date());
				//em.persist(l);
				//et.commit();
				LoginDetail newLoginDetail = new LoginDAOImpl<LoginDetail>().addNewLogin(l);
				
				System.out.println("User ID="+insertedUser.getUserId());
				if(insertedUser.getUserId()!=null ){
					returnUserList =  getValidUserDetails(insertedUser.getUserId());
				}
				
			}
		}
		}catch(Exception e){
			System.out.println("Error while feeding user credentials to DB");
			e.printStackTrace();
		}finally{
			em.close();
		}
		return returnUserList;
	}
	
	/**
	 * This method is used to validate the entered user credentials 
	 */
	@SuppressWarnings("unchecked")
	public List<User> validateUser(String usrEntered, String pwdEntered){
		logger.info("ControllerService:Validate user");

		SecurePassword securePwd = new SecurePassword();
		List<User> validatedUserDetails=null;
		boolean validationStatus = false;
		BigInteger loginUserId = null;
	//Query to fetch hashed password and salt	
	try{
		if(em!=null)
		{
			Query q  = em.createNamedQuery(LOGIN_DETAIL_FIND_USING_USER_NAME);
			q.setParameter("loginUserName", usrEntered);
			List<LoginDetail> resultSet = (List<LoginDetail>)q.getResultList();
			if(resultSet!=null && resultSet.size()==1){
				System.out.println("Result fetched");
				LoginDetail details = (LoginDetail)resultSet.get(0);
				
				try{
					loginUserId = details.getLognUserId();
					validationStatus = securePwd.validatePassword(pwdEntered, details.getLognPassword());
				}catch(Exception e){
					System.out.println("Error while validating password");
				}
			}
			else
			{
				return null;
			}
		}
	}catch(Exception e){
		System.out.println("Exception while password verification");
	}
	
	System.out.println("validationStatus = "+validationStatus);
	if(validationStatus){
	  try{	
		if(em!=null){
			et.begin();
			Query q = em.createNamedQuery(LOGIN_DETAIL_UPDATE_LAST_LOGIN);
			q.setParameter("lastLoginDetail", new Date());
			q.setParameter("loginUserName", usrEntered);
			int updateStatus = q.executeUpdate();
			et.commit();
			if(updateStatus>0){
				validatedUserDetails = getValidUserDetails(loginUserId);
			}
			
		}
	  }catch(Exception e){
			e.printStackTrace();
		}finally{
			em.close();
		}
	    return validatedUserDetails;
	}else
		em.close();
		return null;
	}
	
	public List getValidUserDetails(BigInteger loginUserId){
		System.out.println("Update Successfull..fetching user details for ="+loginUserId);
		Query fetchUserDetails = null;
		try{
			if(em!=null)
			{
			  fetchUserDetails = em.createNamedQuery(USER_FIND_BY_ID);
			  fetchUserDetails.setParameter("userId", String.valueOf(loginUserId));
			}
		}catch(Exception e){
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
	public Map fetchSearchResult(Date from, Date to){
		System.out.println("Inside Fetch Search Result");
		Map displayResultMap = null;
		try{
			if(em!=null){
			/*	Query q  = em.createNativeQuery("SELECT v.vhcl_name,v.vhcl_make,a.addr_locality,v.vhcl_per_day_cost,v.VHCL_SECURITY_DEPOSIT,v.VHCL_ID,a.addr_id "+
												"FROM vehicles v ,  address a WHERE v.VHCL_ID NOT IN "+
												"(SELECT bh.BKNG_VEHICLE FROM bookingshistory bh WHERE bh.BKNG_FROM_DATE <= ? AND bh.BKNG_TO_DATE >= ? "+
												"AND bh.BKNG_STATUS  IN (?,?)) AND a.user_id = v.vhcl_provider_id GROUP BY v.vhcl_name,v.vhcl_provider_id "+
				  							    "ORDER BY v.vhcl_per_day_cost,v.vhcl_name",Vehicle.class);
				  							    */
				Query q  = em.createQuery("SELECT v, a "+
						"FROM Vehicle v ,  Address a WHERE v.vhclId NOT IN "+
						"(SELECT bh.bkngVehicle FROM Bookingshistory bh WHERE bh.bkngFromDate <= :toDate AND bh.bkngToDate >= :fromDate "+
						"AND bh.bkngStatus  IN (:upcoming,:viewing)) AND a.userId = v.vhclAddressId GROUP BY v.vhclName,v.vhclAddressId "+
						    "ORDER BY v.vhclPerDayCost,v.vhclName");
				q.setParameter("toDate", to);
				q.setParameter("fromDate", from);
				q.setParameter("upcoming", "UPCMNG");
				q.setParameter("viewing", "VWNG");
				System.out.println("Execution successful");
				List<Object[]> searchResultSet = (List<Object[]>)q.getResultList();
				
				if(searchResultSet!=null && searchResultSet.size()>0){
					displayResultMap = prepareSearchResultDisplay(searchResultSet);
					
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
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
		public Map prepareSearchResultDisplay(List<Object[]> searchResultSet){
			System.out.println("prepareSearchResultDisplay method invoked");
			
			Map displaySearchResultMap =  new HashMap();
			 int len = searchResultSet.size();
			// for(int i = 0;i<len;i++){
			 for(Object[] o: searchResultSet){
				// Vehicle v = (Vehicle)searchResultSet.get(i);
				 Vehicle v = (Vehicle)o[0];
				 Address a = (Address)o[1];
				if(displaySearchResultMap!=null){
					String locationValue = null;
					String key = v.getVhclName()+"$"+v.getVhclMake()+"$"+v.getVhclPerDayCost()+"$"+v.getVhclSecurityDeposit();
					if(displaySearchResultMap.containsKey(key)){
						locationValue = (String) displaySearchResultMap.get(key);
						locationValue = locationValue +"$"+ a.getAddrLocality();
					 }else{
						locationValue = a.getAddrLocality();
					 }
					locationValue = locationValue+"%"+v.getVhclId();
					displaySearchResultMap.put(key, locationValue);
				}
				 
			 }
			 System.out.println("Map Values= "+displaySearchResultMap);
			
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
	 public long updateBooking(String lockingcode,Date fromDate,Date toDate,String vehicleName, String vehicleProviderId,String usernm,String userId){
		 System.out.println("lockingcode="+lockingcode+"\nfromDate="+fromDate+"\ntoDate="+toDate+"\nvehicleName="+vehicleName+"\nvehicleProviderId="+vehicleProviderId+"\nusernm="+usernm);
		 String lastRowId = null;
		 try{
				if(em!=null)
				{
					et.begin();
					Query q = em.createNativeQuery("insert into bookingshistory (BKNG_STATUS,BKNG_FROM_DATE,BKNG_TO_DATE,BKNG_VEHICLE,BKNG_CREATION_DATE,LAST_UPDATED_BY,LAST_UPDATED,USER_ID) "
													+"select ?,?,?,v.vhcl_id,now(),?,now(),? "
													+"from vehicles v where v.vhcl_name=? and v.vhcl_id not in ( "
													+"SELECT bh.BKNG_VEHICLE FROM bookingshistory bh WHERE "
													+"bh.BKNG_FROM_DATE <= ? AND bh.BKNG_TO_DATE >= ? AND bh.BKNG_STATUS  IN ('UPCMNG','VWNG')) limit 1"
													);
					
					
					q.setParameter(1, lockingcode);
					q.setParameter(2, fromDate);
					q.setParameter(3, toDate);
					q.setParameter(4, usernm);
					q.setParameter(5, Integer.parseInt(userId));
					q.setParameter(6, vehicleName);
					
					q.setParameter(7, toDate);
					q.setParameter(8, fromDate);
					
					int entryUpdate = q.executeUpdate();
					System.out.println("value="+entryUpdate);
					et.commit();
					if(entryUpdate==1){
					Query seqQuery = em.createNamedQuery(BOOKING_HISTORY_FIND_BOOKING_BY_SEQ); 
					List rowIdList = (List)seqQuery.setMaxResults(1).getResultList();
					if(rowIdList!=null && rowIdList.size()>0){
						lastRowId = (String)rowIdList.get(0);
					}
					System.out.println("Last Row="+lastRowId);
					}
				}
		 }catch(Exception e){
			 System.out.println("Exception occured while inserting record in updateBooking()");
			 e.printStackTrace();
		 }finally{
			 em.close();
		 }
		return (lastRowId!=null)?Long.parseLong(lastRowId):0L; 
	 }
	 
	 /**
	  * This method will be executed on every page load. Its is used to clear the records which have been timed out  
	  */
	 public void cleanBookings(){
		 System.out.println("cleanBookings called..!!!");
		 int cleanStatus=0;
		try{
			if(em!=null)
			{
				et.begin();
				Query q = em.createNativeQuery("update bookingshistory b set b.BKNG_STATUS = 'TMOUT' where TIME_TO_SEC(TIMEDIFF(NOW(),b.LAST_UPDATED))>? and b.BKNG_STATUS in ('VWNG')");
				q.setParameter(1, MessageBundle.TICKERVALUE);
				cleanStatus = q.executeUpdate();
				et.commit();
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			em.close();
		}
		System.out.println("Clean Status="+cleanStatus);
	 }
	 
	 public boolean updateBookingWithOrderIdonSuccess(long tempBookingSeq, String generatedOrderId){
		 System.out.println("Inside method updateBookingWithOrderIdonSuccess");
		 int updateStatus=0;
		 try{
				if(em!=null)
				{
					et.begin();
					Query q = em.createNamedQuery(BOOKING_HISTORY_UPDATE);
					//q.setParameter(1, "UPCMNG");
					q.setParameter("bkngStatus", "UPCMNG");
					q.setParameter("bkngNumber", generatedOrderId);
					q.setParameter("bkngSeq", String.valueOf(tempBookingSeq));
					q.setParameter("bkngStatusWhereClause", "VWNG");
					updateStatus = q.executeUpdate();
					et.commit();
				}
			}catch(Exception e){
				e.printStackTrace();
			}finally{
				System.out.println("Finally invoked");
				em.close();
			}
		 return (updateStatus==1)?true:false;
	 }
	 
	 @SuppressWarnings("unchecked")
	public Map fetchStaticData(){
		 System.out.println("Inside method fetchStaticData");
		 Map staticVehicleDetails = new HashMap();
		 try{
				if(em!=null)
				{
					Query q = em.createNamedQuery(VEHICLE_FIND_ALL);
					List<Vehicle> resultList = (List<Vehicle>)q.getResultList();
					if(resultList!=null & resultList.size()>0){
						for(Vehicle veh :resultList){
							staticVehicleDetails.put(veh.getVhclName(), veh.getVhclMake()+"#"+veh.getVhclPerDayCost()+"#"+veh.getVhclSecurityDeposit());
						}
					}
				}
			}catch(Exception e){
				e.printStackTrace();
			}finally{
				System.out.println("Finally invoked");
				em.close();
			}
		 
		 return staticVehicleDetails;
	 }
	 
	 @SuppressWarnings("unchecked")
	public List<Object[]> getMyBookings(String uName) {
		
		System.out.println("getMyBookings() method invoked");
		
			Query q  = em.createQuery("Select book,veh,pd,adds "+ 
          "from Bookingshistory book,Vehicle veh,User us,User pd,Address adds "+ 
					" where book.userId = us.userId"+
					" AND book.bkngVehicle = veh.vhclId"+ 
					" AND pd.userId = veh.vhclProviderId"+
                    " AND pd.userId = adds.userId "+
					"AND (upper(us.userName) = upper(:userName) or upper(us.userEmail) = upper(:userName)) ");
			q.setParameter("userName", uName);
			
			List<Object[]> searchResultSet = (List<Object[]>)q.getResultList();
			
		
			return searchResultSet;
		} 
	 
	 
}
