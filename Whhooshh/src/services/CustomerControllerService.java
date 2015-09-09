package services;

import java.math.BigInteger;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import model.LoginDetail;
import model.User;
import services.security.SecurePassword;

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
				u.setUserType("CUSTOMER");
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
	
	
}
