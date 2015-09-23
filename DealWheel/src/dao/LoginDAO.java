package dao;

import model.LoginDetail;

public interface LoginDAO  extends BaseDAO<LoginDetail>{

	public LoginDetail addNewLogin(LoginDetail login);
	
	public LoginDetail validateUserCredentials(String userName, String password);
}
