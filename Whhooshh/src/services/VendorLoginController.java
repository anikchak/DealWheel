package services;

import model.User;
import services.utility.LoginUtil;

public class VendorLoginController {

	public User validateUser(String userName, String password) {
		return LoginUtil.getUserByCredentials(userName, password);
	}
}
