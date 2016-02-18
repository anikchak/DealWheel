package dao;

import model.Address;

public interface AddressDAO  extends BaseDAO<Address>{

	public Address addNewAddress(Address addr);
	
	public Address findAddressByUserIdAndType(String userId, String userType);
	
}
