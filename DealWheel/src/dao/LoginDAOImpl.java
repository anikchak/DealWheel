package dao;

import static services.utility.GenericConstant.LOGIN_DETAIL_FIND_USING_USER_NAME_AND_TYPE;

import javax.persistence.Query;

import model.LoginDetail;

import org.apache.log4j.Logger;

public class LoginDAOImpl<T> extends BaseDAOImpl<LoginDetail> implements LoginDAO {
	
	private static Logger logger = Logger.getLogger(LoginDAOImpl.class);

	public LoginDetail addNewLogin(LoginDetail lgn) {
		logger.debug("Addding new LoginDetails");
		LoginDetail l = insert(lgn);
		return l;
	}

	public LoginDetail findLoginDetailForUserNameAndType(String userName, String userType) {
		logger.debug("Finding User Name for "+userName);
		Query q = em.createNamedQuery(LOGIN_DETAIL_FIND_USING_USER_NAME_AND_TYPE);
		q.setParameter("loginUserName", userName);
		q.setParameter("loginUserType", userType);
		LoginDetail user = null;
		try{
			user = (LoginDetail) q.getSingleResult();
		}catch(Exception e){
			logger.error("No user found for "+userName);
			return null;
		}
		return user;
	}
}
