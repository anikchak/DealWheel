package dao;

import static services.utility.GenericConstant.ADDRESS_FIND_FOR_USER_ID_AND_TYPE;

import javax.persistence.Query;

import model.Address;

import org.apache.log4j.Logger;

public class AddressDAOImpl<T>  extends  BaseDAOImpl<Address> implements AddressDAO {

	private static Logger logger = Logger.getLogger(AddressDAOImpl.class);
	
	@Override
	public Address addNewAddress(Address addr) {
		logger.debug("Add new Address");
		Address add = insert(addr);
		return add;
	}

	@Override
	public Address findAddressByUserIdAndType(String userId, String userType) {
		logger.debug("Getting Address for "+userType+"  with ID:"+userId+" and userType value="+userType);
		Query q = em.createNamedQuery(ADDRESS_FIND_FOR_USER_ID_AND_TYPE);
		q.setParameter("userId", userId);
		q.setParameter("userType", userType);
		Address add = (Address) q.getSingleResult();
		return add;
	}

}
