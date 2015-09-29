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

	public LoginDetail validateUserCredentials(String userName, String password, String userType) {
		logger.debug("Validating User Credentials for "+userName);
		Query q = em.createNamedQuery(LOGIN_DETAIL_FIND_USING_USER_NAME_AND_TYPE);
		q.setParameter("loginUserName", userName);
		q.setParameter("loginUserType", userType);
		LoginDetail user = (LoginDetail) q.getSingleResult();
		return user;
	}

}
