package dao;

import java.math.BigInteger;

import model.Address;

public interface AddressDAO  extends BaseDAO<Address>{

	public Address addNewAddress(Address addr);
	
	public Address findAddressByUserIdAndType(BigInteger userId, String userType);
	
}
