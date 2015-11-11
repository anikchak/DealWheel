package dao;

import java.util.List;

import model.ListedVehicle;

public interface ListedVehicleDAO  extends BaseDAO<ListedVehicle>{

	public List<String> getVehiclesMakers();
	
	public List<String> getVehiclesForMakers(String makers);
	
	public ListedVehicle findVehicle(String vehicleName, String vehicleMake, String vehicleType);
}
