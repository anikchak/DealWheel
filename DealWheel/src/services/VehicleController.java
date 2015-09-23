package services;

import java.util.List;

import model.Vehicle;
import dao.VehicleDAOImpl;


public class VehicleController {
	
	public List<String> getNames(){
		return new VehicleDAOImpl<Vehicle>().getNames();
	}
}
