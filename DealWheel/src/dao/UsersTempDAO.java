package dao;

import java.math.BigInteger;

import model.UsersTemp;

public interface UsersTempDAO extends BaseDAO<UsersTemp> {
	
	public UsersTemp addNewUser(UsersTemp user);

	public UsersTemp findByUserId(BigInteger lognUserId);
	
	public UsersTemp findUserByEmailAddress(String email);
	

	
}
