package dao;

import java.util.List;

import model.Vehicle;

public interface VehicleDAO  extends BaseDAO<Vehicle>{

	public Vehicle addNewVehicle(Vehicle addr);
	
	public List<String> getNames();
	
}
