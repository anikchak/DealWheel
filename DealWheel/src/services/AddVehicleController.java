package services;

import static services.utility.GenericConstant.ADDRESS_TYPE_PICKUP_LOCAION;
import static services.utility.GenericConstant.VEHICLE_TYPE_TWO_WHEELER;

import java.math.BigInteger;
import java.util.Date;
import java.util.List;
import java.util.Map;

import model.Address;
import model.ListedVehicle;
import model.Vehicle;
import dao.AddressDAOImpl;
import dao.ListedVehicleDAOImpl;
import dao.VehicleDAOImpl;

public class AddVehicleController {

	public List<String> getVehicleNames(){
		return new VehicleDAOImpl<Vehicle>().getNames();
	}

	public Vehicle addNewVehicle(Map<String, String> params) {

		Vehicle vehicleNew = null;
		String addressId = null;
		Address addrNew = null;
		
		try{
			if("Yes".equals(params.get("useDifferentAddress"))){
				Address addr = new Address();
				addr.setAddrLine1(params.get("addrLine1"));
				addr.setAddrLine2(params.get("addrLine2"));
				addr.setAddrLine3(params.get("addrLine3"));
				addr.setAddrLocality(params.get("locality"));
				addr.setAddrCity(params.get("city"));
				addr.setAddrState(params.get("state"));
				addr.setAddrCountry(params.get("country"));
				addr.setAddrPinCode(Integer.parseInt(params.get("pinCode")));
				addr.setUserId(new BigInteger(params.get("userId")));
				addr.setAddrType(ADDRESS_TYPE_PICKUP_LOCAION);
				addr.setLastUpdated(new Date());
				addr.setLastUpdatedBy(params.get("userName"));
				addrNew = new AddressDAOImpl<Address>().addNewAddress(addr);
				addressId = addrNew.getAddrId();
			}else{
				addressId = params.get("addrressId");
			}
			
			ListedVehicle lv = new ListedVehicleDAOImpl<ListedVehicle>().findVehicle(
					params.get("vehicleName"), params.get("vehicleManufacturer"), VEHICLE_TYPE_TWO_WHEELER);
			
			Vehicle v = new Vehicle();
			v.setVhclAddressId(new BigInteger(addressId));
			v.setListedVhclId(lv.getLvclId());
			v.setVhclRegistrationNo(params.get("registrationNo"));
			v.setVhclYearOfManufacture(params.get("yearOfManufacture"));
			v.setVhclPerDayCost(Integer.parseInt(params.get("perDayCost")));
			v.setVhclSecurityDeposit(Integer.parseInt(params.get("securityDeposit")));
			v.setLastUpdated(new Date());
			v.setLastUpdatedBy(params.get("userName"));
			
			vehicleNew = new VehicleDAOImpl<Vehicle>().addNewVehicle(v);
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return vehicleNew;
	}
}
