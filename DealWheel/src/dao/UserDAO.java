package dao;

import model.User;

public interface UserDAO  extends BaseDAO<User>{

	public User addNewUser(User user);

	public User findByUserId(String lognUserId);
	
	public User findUserByEmailAddress(String email);
}
