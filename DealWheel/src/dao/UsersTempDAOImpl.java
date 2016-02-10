
	package dao;

	import static services.utility.GenericConstant.*;

	import java.math.BigInteger;

	import javax.persistence.Query;

	import org.apache.log4j.Logger;

	import model.UsersTemp;

	public class UsersTempDAOImpl<T>  extends  BaseDAOImpl<UsersTemp> implements UsersTempDAO {

		private static Logger logger = Logger.getLogger(UsersTempDAOImpl.class);
		
		@Override
		public UsersTemp addNewUser(UsersTemp usr) {
			logger.debug("Adding new User");
			UsersTemp u = insert(usr);
			return u;
		}
		
		

		@Override
		public UsersTemp findByUserId(BigInteger lognUserId) {
			logger.debug("Finding User with ID: "+lognUserId);
			Query q = em.createNamedQuery(USERTEMP_FIND_BY_ID);
			q.setParameter("userId", lognUserId);
			UsersTemp user = (UsersTemp) q.getSingleResult();
			return user;
		}

		@Override
		public UsersTemp findUserByEmailAddress(String email) {
			logger.debug("Finding User with email: "+email);
			Query q = em.createNamedQuery(USERTEMP_FIND_BY_EMAIL);
			q.setParameter("email", email);
			UsersTemp user = (UsersTemp) q.getSingleResult();
			return user;
		}
	}



