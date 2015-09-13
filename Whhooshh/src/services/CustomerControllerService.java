package services;

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

import model.LoginDetail;
import model.User;
import model.Vehicle;
import services.security.SecurePassword;
import services.utility.MessageBundle;

public class CustomerControllerService {

	EntityManagerFactory emf = Persistence.createEntityManagerFactory("Whhooshh");
	EntityManager em = emf.createEntityManager();
	EntityTransaction et = em.getTransaction();
	
	/**
	 * This method is invoked when a new user is registering. 
	 * @param: user entered email id(username) and password
	 * @return: true - if the user record is entered successfully
	 * 			false - if there is any exception while entering user record 
	 */
	@SuppressWarnings("unchecked")
	public boolean inserNewUser(String usr,String pwd){
		System.out.println("ControllerService: inserting new user through insertNewUser");
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
			Query usrnmExist  = em.createNativeQuery("select login.* from LOGIN_DETAIL login where login.LOGN_USER_NAME = ? " , LoginDetail.class);
			usrnmExist.setParameter(1, usr);
			List<LoginDetail> resultSet = (List<LoginDetail>)usrnmExist.getResultList();
			if(resultSet!=null && resultSet.size()>0){
				System.out.println("Unique username encountered");
				return false;
			}else{
			   
				//Beginning txn for User table record
				et.begin();
				User u = new User();
				u.setUserEmail(usr);
				u.setUserType("CUST");
				u.setLastUpdated(new Date());
				u.setLastUpdatedBy(usr);
				em.persist(u);
				et.commit();
							
				//Beginning txn for LoginDetail table record
				et.begin();
				LoginDetail l = new LoginDetail();
				l.setLognUserId(BigInteger.valueOf(Long.parseLong(u.getUserId())));
				l.setLognUserName(usr);
				l.setLognPassword(pwd);
				l.setLastUpdatedBy(usr);
				l.setLognLastLoginDetail(new Date());
				l.setLastUpdated(new Date());
				em.persist(l);
				et.commit();
				
				System.out.println("User ID="+u.getUserId());
				
				return true;
			}
		}
		}catch(Exception e){
			System.out.println("Error while feeding user credentials to DB");
		}finally{
			em.close();
		}
		return false;
	}
	
	/**
	 * This method is used to validate the entered user credentials 
	 */
	@SuppressWarnings("unchecked")
	public boolean validateUser(String usrEntered, String pwdEntered){
		System.out.println("ControllerService:Validate user");

		SecurePassword securePwd = new SecurePassword();
		Boolean validationStatus=false;
		
	//Query to fetch hashed password and salt	
	try{
		if(em!=null)
		{
			Query q  = em.createNativeQuery("select login.* from LOGIN_DETAIL login where login.LOGN_USER_NAME = ?" , LoginDetail.class);
			q.setParameter(1, usrEntered);
			List<LoginDetail> resultSet = (List<LoginDetail>)q.getResultList();
			if(resultSet!=null && resultSet.size()==1){
				System.out.println("Result fetched");
				LoginDetail details = (LoginDetail)resultSet.get(0);
				
				try{
					validationStatus = securePwd.validatePassword(pwdEntered, details.getLognPassword());
				}catch(Exception e){
					System.out.println("Error while validating password");
				}
			}
			else
			{
				return validationStatus;
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
			Query q = em.createNativeQuery("update login_detail set LOGN_LAST_LOGIN_DETAIL = ? where LOGN_USER_NAME = ?" );
			q.setParameter(1, new Date());
			q.setParameter(2, usrEntered);
			q.executeUpdate();
			et.commit();
		}
	  }catch(Exception e){
			e.printStackTrace();
		}finally{
			em.close();
		}
	    return true;
	}else
		em.close();
		return false;
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
				Query q  = em.createNativeQuery("SELECT v.vhcl_name,v.vhcl_make,a.addr_locality,v.vhcl_per_day_cost,v.VHCL_SECURITY_DEPOSIT,v.VHCL_ID,a.addr_id "+
												"FROM vehicles v ,  address a WHERE v.VHCL_ID NOT IN "+
												"(SELECT bh.BKNG_VEHICLE FROM bookingshistory bh WHERE bh.BKNG_FROM_DATE <= ? AND bh.BKNG_TO_DATE >= ? "+
												"AND bh.BKNG_STATUS  IN (?,?)) AND a.user_id = v.vhcl_provider_id GROUP BY v.vhcl_name,v.vhcl_provider_id "+
												"ORDER BY v.vhcl_per_day_cost,v.vhcl_name",Vehicle.class);
				q.setParameter(1, to);
				q.setParameter(2, from);
				q.setParameter(3, "UPCMNG");
				q.setParameter(4, "VWNG");
				System.out.println("Execution successful");
				List<Vehicle> searchResultSet = (List<Vehicle>)q.getResultList();
				
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
		public Map prepareSearchResultDisplay(List searchResultSet){
			System.out.println("prepareSearchResultDisplay method invoked");
			
			Map displaySearchResultMap =  new HashMap();
			 int len = searchResultSet.size();
			 for(int i = 0;i<len;i++){
				 Vehicle v = (Vehicle)searchResultSet.get(i);
				if(displaySearchResultMap!=null){
					String locationValue = null;
					String key = v.getVhclName()+"$"+v.getVhclMake()+"$"+v.getVhclPerDayCost()+"$"+v.getVhclSecurityDeposit();
					if(displaySearchResultMap.containsKey(key)){
						locationValue = (String) displaySearchResultMap.get(key);
						locationValue = locationValue +"$"+ v.getAddrLocality();
					 }else{
						locationValue = v.getAddrLocality();
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
	 public long updateBooking(String lockingcode,Date fromDate,Date toDate,String vehicleName, String vehicleProviderId,String usernm){
		 System.out.println("lockingcode="+lockingcode+"\nfromDate="+fromDate+"\ntoDate="+toDate+"\nvehicleName="+vehicleName+"\nvehicleProviderId="+vehicleProviderId+"\nusernm="+usernm);
		 long lastRowId = 0L;
		 try{
				if(em!=null)
				{
					et.begin();
					Query q = em.createNativeQuery("insert into bookingshistory (BKNG_STATUS,BKNG_FROM_DATE,BKNG_TO_DATE,BKNG_VEHICLE,BKNG_CREATION_DATE,LAST_UPDATED_BY,LAST_UPDATED) "
													+"select ?,?,?,v.vhcl_id,now(),?,now() "
													+"from vehicles v where v.vhcl_name=? and v.vhcl_id not in ( "
													+"SELECT bh.BKNG_VEHICLE FROM bookingshistory bh WHERE "
													+"bh.BKNG_FROM_DATE <= ? AND bh.BKNG_TO_DATE >= ? AND bh.BKNG_STATUS  IN ('UPCMNG','VWNG')) limit 1"
													);
					q.setParameter(1, lockingcode);
					q.setParameter(2, fromDate);
					q.setParameter(3, toDate);
					q.setParameter(4, usernm);
					q.setParameter(5, vehicleName);
					
					q.setParameter(6, toDate);
					q.setParameter(7, fromDate);
					
					int entryUpdate = q.executeUpdate();
					System.out.println("value="+entryUpdate);
					et.commit();
					if(entryUpdate==1){
					Query seqQuery = em.createNativeQuery("select BKNG_SEQ from bookingshistory order by BKNG_SEQ DESC limit 1;"); 
					lastRowId = (Long)seqQuery.getSingleResult();
					System.out.println("Last Row="+lastRowId);
					}
				}
		 }catch(Exception e){
			 System.out.println("Exception occured while inserting record in updateBooking()");
			 e.printStackTrace();
		 }finally{
			 em.close();
		 }
		return lastRowId; 
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
					Query q = em.createNativeQuery("update bookingshistory b set b.BKNG_STATUS = 'UPCMNG' ,b.BKNG_NUMBER = ? where b.BKNG_SEQ = ? and b.BKNG_STATUS in (?)");
					//q.setParameter(1, "UPCMNG");
					q.setParameter(1, generatedOrderId);
					q.setParameter(2, Double.valueOf(tempBookingSeq));
					q.setParameter(3, "VWNG");
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
}
