package dao;

import model.Address;

public interface AddressDAO  extends BaseDAO<Address>{

	public Address addNewAddress(Address addr);
	
}
