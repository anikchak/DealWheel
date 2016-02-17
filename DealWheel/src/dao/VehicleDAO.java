package dao;

import java.math.BigInteger;
import java.util.List;

import model.Vehicle;

public interface VehicleDAO  extends BaseDAO<Vehicle>{

	public Vehicle addNewVehicle(Vehicle addr);
	
	public List<String> getNames();
	
	public List<Object[]> getVehicleDetailsForUserId(String userId);
	
	//public void disable(List<BigInteger> listIds);
	
	public void enable(BigInteger bigInteger);
}
