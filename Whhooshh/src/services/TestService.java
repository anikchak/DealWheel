package services;

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

import services.security.SecurePassword;
import model.Persondetails;
import model.Providerdetail;


public class TestService {

	EntityManagerFactory emf = Persistence.createEntityManagerFactory("Whhooshh");
	EntityManager em = emf.createEntityManager();
	EntityTransaction et = em.getTransaction();
	
	@SuppressWarnings("unchecked")
	public boolean inserNewUser(String usr,String pwd){
		System.out.println("inserting new user through insertNewUser");
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
			Query usrnmExist  = em.createNativeQuery("select l.* from persondetails l where l.username = ? " , Persondetails.class);
			usrnmExist.setParameter(1, usr);
			List<Persondetails> resultSet = (List<Persondetails>)usrnmExist.getResultList();
			if(resultSet!=null && resultSet.size()>0){
				System.out.println("Unique username encountered");
				return false;
			}else{
			
				et.begin();
				Persondetails p = new Persondetails();
				p.setUsername(usr);
				p.setUserpassword(pwd);
				p.setSalt(hexedSalt);
				em.persist(p);
				et.commit();
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
	
	@SuppressWarnings("unchecked")
	public boolean validateUser(String usrEntered, String pwdEntered){
		System.out.println("Validate user");

		SecurePassword securePwd = new SecurePassword();
		Boolean validationStatus=false;
		
	//Query to fetch hashed password and salt	
	try{
		if(em!=null)
		{
			Query q  = em.createNativeQuery("select l.* from persondetails l where l.username = ? " , Persondetails.class);
			q.setParameter(1, usrEntered);
			List<Persondetails> resultSet = (List<Persondetails>)q.getResultList();
			if(resultSet!=null && resultSet.size()==1){
				System.out.println("Result fetched");
				Persondetails details = (Persondetails)resultSet.get(0);
				
				try{
					validationStatus = securePwd.validatePassword(pwdEntered, details.getUserpassword());
				}catch(Exception e){
					System.out.println("Error while validating password");
				}
			}
			else
			{
				return false;
			}
		}
	}catch(Exception e){
		System.out.println("Exception while password verification");
	}finally{
		em.close();
	}
	System.out.println("validationStatus = "+validationStatus);
	if(validationStatus){
		return true;
	}else
		return false;
	}
	
	@SuppressWarnings({"unchecked", "rawtypes" })
	public Map fetchSearchResult(Date from, Date to){
		System.out.println("Inside Fetch Search Result");
		Map displayResultMap = null;
		try{
			if(em!=null){
				Query q  = em.createNativeQuery("select p.seq,p.pickuplocation,b.bikename,b.company,b.bikeseq from providerdetails p, bikelookup b "+
							"where b.bikeseq = p.vehicleId "+
						    "and p.seq not in (select bd.vehicleprovider from bookingdetails bd where bookingfromdate between ? and ? "+
						    "and bookingtodate between ? and ? and bd.bookingstatus in ('VIEWING','CONFIRMED')) "+
							" group by p.pickuplocation,b.bikename,b.company order by b.bikename" , Providerdetail.class);
				q.setParameter(1, from);
				q.setParameter(2, to);
				q.setParameter(3, from);
				q.setParameter(4, to);
				System.out.println("Execution successful");
				List<Providerdetail> searchResultSet = (List<Providerdetail>)q.getResultList();
				
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
	 @SuppressWarnings({ "unchecked", "rawtypes" })
	public Map prepareSearchResultDisplay(List searchResultSet){
		System.out.println("prepareSearchResultDisplay method invoked");
		
		Map displaySearchResultMap =  new HashMap();
		 int len = searchResultSet.size();
		 for(int i = 0;i<len;i++){
			 Providerdetail pd = (Providerdetail)searchResultSet.get(i);
			if(displaySearchResultMap!=null){
				String locationValue = null;
				if(displaySearchResultMap.containsKey(pd.getBikeName()+"$"+pd.getCompany()+"$"+pd.getBikeSeq())){
					locationValue = (String) displaySearchResultMap.get(pd.getBikeName()+"$"+pd.getCompany()+"$"+pd.getBikeSeq());
					locationValue = locationValue +"$"+ pd.getPickupLocation();
					//displaySearchResultMap.put(pd.getBikeName()+"$"+pd.getCompany(), locationValue);
				 }else{
					// displaySearchResultMap.put(pd.getBikeName()+"$"+pd.getCompany(), pd.getPickupLocation());
					 locationValue = pd.getPickupLocation();
				 }
				displaySearchResultMap.put(pd.getBikeName()+"$"+pd.getCompany()+"$"+pd.getBikeSeq(), locationValue);
			}
			 
		 }
		 System.out.println("Map Values= "+displaySearchResultMap);
		
		 return displaySearchResultMap;
	 }
	 
	 public int updateBooking(String lockingcode,Date fromDate,Date toDate,String pickupLocation, int vehicleId,String usernm){
		 System.out.println("fromDate="+fromDate+"\ntoDate="+toDate+"\npickupLocation="+pickupLocation+"\nvehicleId="+vehicleId+"\nusernm="+usernm);
		 Integer lastRowId = 0;
		 try{
				if(em!=null)
				{
					et.begin();
					Query q = em.createNativeQuery("insert into bookingdetails "+""
							+ "(bookingStatus,BookingFromDate,BookingToDate,pickupLocation,username,vehicleId,vehicleprovider)"
							+ " select ?,?,?,?,?,?,p.seq from providerdetails p"
							+" where p.vehicleid=? and p.pickuplocation = ? and"
							+" p.seq not in (select bd.vehicleprovider from bookingdetails bd where bookingfromdate between ? and ?"
							+" and bookingtodate between ? and ? and bd.bookingstatus = ? and bd.pickuplocation = ?)"
							+" limit 1");
					q.setParameter(1, lockingcode);
					q.setParameter(2, fromDate);
					q.setParameter(3, toDate);
					q.setParameter(4, pickupLocation);
					q.setParameter(5, usernm);
					q.setParameter(6, vehicleId);
					q.setParameter(7, vehicleId);
					q.setParameter(8, pickupLocation);
					q.setParameter(9, fromDate);
					q.setParameter(10, toDate);
					q.setParameter(11, fromDate);
					q.setParameter(12, toDate);
					q.setParameter(13, lockingcode);
					q.setParameter(14, pickupLocation);
					int x = q.executeUpdate();
					System.out.println("value="+x);
					et.commit();
					
					Query seqQuery = em.createNativeQuery("select bookingseq from bookingdetails order by bookingseq DESC limit 1;"); 
					lastRowId = (Integer)seqQuery.getSingleResult();
					System.out.println("Last Row="+lastRowId);
				}
		 }catch(Exception e){
			 System.out.println("Exception occured while inserting record in updateBooking()");
			 e.printStackTrace();
		 }finally{
			 em.close();
		 }
		return lastRowId; 
	 }
	 
	 public void cleanBookings(){
		 System.out.println("cleanBookings called..!!!");
		try{
			if(em!=null)
			{
				et.begin();
				Query q = em.createNativeQuery("update bookingdetails b set b.bookingStatus = 'TIMEDOUT' where TIME_TO_SEC(TIMEDIFF(NOW(),LastUpdated))>? and bookingstatus not in ('CLOSED','UPCOMING')");
				q.setParameter(1, 100);
				q.executeUpdate();
				et.commit();
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			em.close();
		}
	 }
}
