package services;

import static services.utility.GenericConstant.ADDRESS_TYPE_VENDOR_OFFICE_LOCATION;
import static services.utility.GenericConstant.USER_TYPE_VENDOR;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import model.Address;
import model.LoginDetail;
import model.User;
import services.utility.LoginUtil;
import dao.AddressDAOImpl;
import dao.LoginDAOImpl;
import dao.UserDAOImpl;

public class VendorRegistrationController {
	
	
	/**
	 * pass set of params to add  in db
	 * 
	 * @param params
	 * @return userModel, addressModel, loginDetailsModel
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List registerVendor(Map<String,String> params){
		List committedEntities = new ArrayList();
		BigInteger pNum = new BigInteger(params.get("primaryContact"));
		BigInteger sNum = params.get("secondaryContact").equals("") ? BigInteger.ZERO : new BigInteger(params.get("secondaryContact"));
		User user = new User();
		user.setUserName(params.get("fullName"));
		user.setUserEmail(params.get("email"));
		user.setUserPrimaryContact(pNum);
		user.setUserSecondaryContact(sNum);
		user.setUserType(USER_TYPE_VENDOR);
		user.setUserGender(params.get("gender"));
		user.setUserDateOfBirth(params.get("dob"));
		user.setLastUpdated(new Date());
		user.setLastUpdatedBy(params.get("fullName"));
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
		addr.setUserId(userNew.getUserId());
		addr.setAddrType(ADDRESS_TYPE_VENDOR_OFFICE_LOCATION);
		addr.setLastUpdated(new Date());
		addr.setLastUpdatedBy(params.get("fullName"));
		Address addrNew = new AddressDAOImpl<Address>().addNewAddress(addr);
		committedEntities.add(addrNew);
		
		LoginDetail login = new LoginDetail();
		login.setLognUserId(userNew.getUserId());
		login.setLognUserName(params.get("email"));
		login.setLognPassword(LoginUtil.CreateEncryptedPassword(params.get("password")));
		login.setLognLastLoginDetail(new Date());
		login.setLastUpdatedBy(userNew.getUserName());
		login.setLastUpdated(new Date());
		LoginDetail newLogin = new LoginDAOImpl<LoginDetail>().addNewLogin(login);
		committedEntities.add(newLogin);
		return committedEntities;
	}

	/**
	 * 
	 * @param params
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List updateVendor(Map<String, String> params) {
		List committedEntities = new ArrayList();
		BigInteger pNum = new BigInteger(params.get("primaryContact"));
		BigInteger sNum = params.get("secondaryContact").equals("") ? BigInteger.ZERO : new BigInteger(params.get("secondaryContact"));
		
		User user = new UserDAOImpl<User>().findUserByEmailAddress(params.get("email"));
		user.setUserName(params.get("fullName"));
		user.setUserPrimaryContact(pNum);
		user.setUserSecondaryContact(sNum);
		user.setUserType(USER_TYPE_VENDOR);
		user.setLastUpdated(new Date());
		user.setLastUpdatedBy(params.get("fullName"));
		User userUpdated = new UserDAOImpl<User>().update(user);
		committedEntities.add(userUpdated);
		
		Address addr = new AddressDAOImpl<Address>().findAddressByUserIdAndType(user.getUserId(), ADDRESS_TYPE_VENDOR_OFFICE_LOCATION);
		addr.setAddrLine1(params.get("addrLine1"));
		addr.setAddrLine2(params.get("addrLine2"));
		addr.setAddrLine3(params.get("addrLine3"));
		addr.setAddrLocality(params.get("locality"));
		addr.setAddrCity(params.get("city"));
		addr.setAddrState(params.get("state"));
		addr.setAddrPinCode(Integer.parseInt(params.get("pinCode")));
		addr.setAddrType(USER_TYPE_VENDOR);
		addr.setLastUpdated(new Date());
		addr.setLastUpdatedBy(params.get("fullName"));
		Address addrUpdated = new AddressDAOImpl<Address>().update(addr);
		committedEntities.add(addrUpdated);
		
		return committedEntities;
	}
}
