package services;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import model.Address;
import model.LoginDetail;
import model.User;
import services.utility.LoginUtil;
import dao.AddressDAOImpl;
import dao.LoginDAOImpl;
import dao.UserDAOImpl;


public class VendorRegistrationController {
	
	@SuppressWarnings("unchecked")
	public List doProcess(Map<String,String> params){
		List committedEntities = new ArrayList();
		
		User user = new User();
		user.setUserName(params.get("fullName"));
		user.setUserEmail(params.get("email"));
		user.setUserPrimaryContact(new BigInteger(params.get("primaryContact")));
		user.setUserSecondaryContact(new BigInteger(params.get("secondaryContact")));
		user.setUserType("VENDOR");
		user.setUserGender(params.get("gender"));
		user.setUserDateOfBirth(params.get("dob"));
		user.setLastUpdated(new Date());
		user.setLastUpdatedBy("JASMEET");
		User userNew = new UserDAOImpl<User>().addNewUser(user);
		committedEntities.add(userNew);
		
		Address addr = new Address();
		addr.setAddrLine1(params.get("addrLine1"));
		addr.setAddrLine2(params.get("addrLine2"));
		addr.setAddrLine3(params.get("addrLine3"));
		addr.setAddrLocality(params.get("locality"));
		addr.setAddrCity(params.get("city"));
		addr.setAddrState(params.get("state"));
		addr.setAddrCountry(params.get("country"));
		addr.setAddrPinCode(Integer.parseInt(params.get("pinCode")));
		addr.setUserId(new BigInteger(userNew.getUserId()));
		addr.setAddrType("VENDOR");
		addr.setLastUpdated(new Date());
		addr.setLastUpdatedBy("JASMEET");
		Address addrNew = new AddressDAOImpl<Address>().addNewAddress(addr);
		committedEntities.add(addrNew);
		
		LoginDetail login = new LoginDetail();
		login.setLognUserId(new BigInteger(userNew.getUserId()));
		login.setLognUserName(params.get("email"));
		login.setLognPassword(LoginUtil.CreateEncryptedPassword(params.get("password")));
		login.setLognLastLoginDetail(new Date());
		login.setLastUpdatedBy("JASMEET");
		login.setLastUpdated(new Date());
		LoginDetail newLogin = new LoginDAOImpl<LoginDetail>().addNewLogin(login);
		committedEntities.add(newLogin);
		
		return committedEntities;
	}
}
