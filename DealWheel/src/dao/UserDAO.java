package dao;

import java.math.BigInteger;

import model.User;

public interface UserDAO  extends BaseDAO<User>{

	public User addNewUser(User user);

	public User findByUserId(BigInteger lognUserId);
	
	public User findUserByEmailAddress(String email);
}
