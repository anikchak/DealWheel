package dao;

import org.apache.log4j.Logger;

import model.Address;

public class AddressDAOImpl<T>  extends  BaseDAOImpl<Address> implements AddressDAO {

	private static Logger logger = Logger.getLogger(AddressDAOImpl.class);
	
	public Address addNewAddress(Address addr) {
		logger.debug("Add new Address");
		Address add = insert(addr);
		return add;
	}

}
