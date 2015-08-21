package services;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import services.security.SecurePassword;
import model.Persondetails;


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
	}
	System.out.println("validationStatus = "+validationStatus);
	if(validationStatus){
		return true;
	}else
		return false;
	}
	
}
