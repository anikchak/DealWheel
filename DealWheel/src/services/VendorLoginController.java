package services;

import model.User;
import services.utility.LoginUtil;
import static services.utility.GenericConstant.*;

public class VendorLoginController {

	public User validateVendor(String userName, String password) {
		return LoginUtil.getUserByCredentials(userName, password,USER_TYPE_VENDOR);
	}
}
