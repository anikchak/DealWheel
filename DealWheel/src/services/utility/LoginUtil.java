package services.utility;

import java.math.BigInteger;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

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
			LoginDetail detail = loginDAOImpl.findLoginDetailForUserNameAndType(userName, userType);
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
			LoginDetail detail = loginDAOImpl.findLoginDetailForUserNameAndType(userName, userType);
			isUserValid = securePwd.validatePassword(password, detail.getLognPassword());
			System.out.println("isUserValid="+isUserValid);
			if(isUserValid){
				user = new UserDAOImpl<User>().findById(detail.getLognUserId().toString());
				return user;
			}
		}catch(Exception e){
			return null;
		}
		return user;
	}

	public static boolean checkUserNameExists(String userName, String userTypeVendor) {
		LoginDAOImpl<LoginDetail> loginDAOImpl = new LoginDAOImpl<LoginDetail>();
		LoginDetail detail = loginDAOImpl.findLoginDetailForUserNameAndType(userName, userTypeVendor);
		if(detail == null || detail.getLognId()==null || detail.getLognId().isEmpty())
			return false;
		else
			return true;
	}
	
	public static BigInteger generateOTP(){
		BigInteger otp = null;
		String chars = "0123456789";
		final int PW_LENGTH = 6;
		Random rnd = new SecureRandom();
		StringBuilder pass = new StringBuilder();
		for (int i = 0; i < PW_LENGTH; i++)
			pass.append(chars.charAt(rnd.nextInt(chars.length())));
		otp = new BigInteger(pass.toString());
		if(otp.compareTo(new BigInteger("100000")) == -1)
			otp = generateOTP();
		return otp;
	}
	
	public static Map<String,String> resetPassword(){
		Map<String,String> passwd = new HashMap<String, String>();
		String tempPwdGenerated =  Long.toHexString(Double.doubleToLongBits(Math.random()));
		passwd.put("tempPassword", tempPwdGenerated);
		logger.info("Generating random password = "+tempPwdGenerated);
		SecurePassword securePwd = new SecurePassword();
		byte[] salt = securePwd.createSalt();
		try {
			passwd.put("hashPassword",securePwd.createHash(tempPwdGenerated, salt));
		} catch (NoSuchAlgorithmException excep) {
			logger.error("Exception in algorithm");
		} catch (InvalidKeySpecException e) {
			logger.error("Invalid Key Spec Exception");
		}
		return passwd;
	}
}
