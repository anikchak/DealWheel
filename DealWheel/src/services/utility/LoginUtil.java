package services.utility;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

import model.LoginDetail;
import model.User;

import org.apache.log4j.Logger;

import services.security.SecurePassword;
import dao.LoginDAOImpl;
import dao.UserDAOImpl;

public class LoginUtil {
	
	private static Logger logger = Logger.getLogger(LoginUtil.class);
	
	public static  String CreateEncryptedPassword(String password){
		SecurePassword securePwd = new SecurePassword();
		byte[] salt = securePwd.createSalt();
		try{
			password = securePwd.createHash(password, salt);
		}catch(NoSuchAlgorithmException excep){
			logger.error("Exception in algorithm");
		}catch(InvalidKeySpecException e){
			logger.error("Invalid Key Spec Exception");
		}
		return password;
	}
	
	public static boolean validateUserCredentials(String userName, String password, String userType){
		boolean isUserValid = false;
		try{
			SecurePassword securePwd = new SecurePassword();
			LoginDAOImpl<LoginDetail> loginDAOImpl = new LoginDAOImpl<LoginDetail>();
			LoginDetail detail = loginDAOImpl.validateUserName(userName, userType);
			isUserValid = securePwd.validatePassword(password, detail.getLognPassword());
		}catch(Exception e){
			
		}
		return isUserValid;
	}
	
	public static User getUserByCredentials(String userName, String password, String userType){
		boolean isUserValid = false;
		User user = null;
		try{
			SecurePassword securePwd = new SecurePassword();
			LoginDAOImpl<LoginDetail> loginDAOImpl = new LoginDAOImpl<LoginDetail>();
			LoginDetail detail = loginDAOImpl.validateUserName(userName, userType);
			isUserValid = securePwd.validatePassword(password, detail.getLognPassword());
			if(isUserValid){
				user = new UserDAOImpl<User>().findById(detail.getLognUserId());
				return user;
			}
		}catch(Exception e){
			return null;
		}
		return user;
	}

	public static boolean checkUserNameExists(String userName, String userTypeVendor) {
		LoginDAOImpl<LoginDetail> loginDAOImpl = new LoginDAOImpl<LoginDetail>();
		LoginDetail detail = loginDAOImpl.validateUserName(userName, userTypeVendor);
		if(detail == null || detail.getLognId()==null || detail.getLognId().isEmpty())
			return false;
		else
			return true;
	}
	
}
