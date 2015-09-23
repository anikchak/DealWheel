package dao;

import static services.utility.GenericConstant.USER_FIND_BY_ID;

import java.math.BigInteger;

import javax.persistence.Query;

import org.apache.log4j.Logger;

import model.User;

public class UserDAOImpl<T>  extends  BaseDAOImpl<User> implements UserDAO {

	private static Logger logger = Logger.getLogger(UserDAOImpl.class);
	
	public User addNewUser(User usr) {
		logger.debug("Adding new User");
		User u = insert(usr);
		return u;
	}

	public User findByUserId(BigInteger lognUserId) {
		logger.debug("Finding User with ID: "+lognUserId);
		Query q = getEntityManager().createNamedQuery(USER_FIND_BY_ID);
		q.setParameter("userId", lognUserId);
		User user = (User) q.getSingleResult();
		return user;
	}
}
