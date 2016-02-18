package dao;

import static services.utility.GenericConstant.USER_FIND_BY_EMAIL;
import static services.utility.GenericConstant.USER_FIND_BY_ID;

import javax.persistence.Query;

import model.User;

import org.apache.log4j.Logger;

public class UserDAOImpl<T>  extends  BaseDAOImpl<User> implements UserDAO {

	private static Logger logger = Logger.getLogger(UserDAOImpl.class);
	
	@Override
	public User addNewUser(User usr) {
		logger.debug("Adding new User");
		User u = insert(usr);
		return u;
	}

	@Override
	public User findByUserId(String lognUserId) {
		logger.debug("Finding User with ID: "+lognUserId);
		Query q = em.createNamedQuery(USER_FIND_BY_ID);
		q.setParameter("userId", lognUserId);
		User user = (User) q.getSingleResult();
		return user;
	}

	@Override
	public User findUserByEmailAddress(String email) {
		logger.debug("Finding User with email: "+email);
		Query q = em.createNamedQuery(USER_FIND_BY_EMAIL);
		q.setParameter("email", email);
		User user = (User) q.getSingleResult();
		return user;
	}
}
