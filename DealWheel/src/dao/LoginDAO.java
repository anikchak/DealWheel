package dao;

import model.LoginDetail;

public interface LoginDAO  extends BaseDAO<LoginDetail>{

	public LoginDetail addNewLogin(LoginDetail login);
	
	public LoginDetail findLoginDetailForUserNameAndType(String userName, String userType);
	
	public String resetPasswordForEmailId(String emailId) ;
	
}
