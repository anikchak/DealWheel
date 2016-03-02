package dao;

import java.math.BigInteger;
import java.util.List;

import model.Vehicle;

public interface VehicleDAO  extends BaseDAO<Vehicle>{

	public Vehicle addNewVehicle(Vehicle addr);
	
	public List<String> getNames();
	
	public List<Object[]> getVehicleDetailsForUserId(String userId);
	
	public void disable(BigInteger vehicleId);
	
	public void changeCostAndDeposit(BigInteger vehicleId,String changedCost,String changedDeposit);

	public void enable(BigInteger bigInteger);
}
