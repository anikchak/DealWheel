package dao;

import static services.utility.GenericConstant.VEHICLE_GET_NAMES;
import static services.utility.GenericConstant.VEHICLE_GET_VEHICLE_DETAILS_FOR_USER;

import java.math.BigInteger;
import java.util.List;

import javax.persistence.Query;

import model.Vehicle;

import org.apache.log4j.Logger;

public class VehicleDAOImpl<T>  extends  BaseDAOImpl<Vehicle> implements VehicleDAO {

	private static Logger logger = Logger.getLogger(VehicleDAOImpl.class);
	
	public Vehicle addNewVehicle(Vehicle vhcl) {
		logger.debug("Adding new Vehicle added");
		Vehicle vehicle = insert(vhcl);
		return vehicle;
	}

	public List<String> getNames() {
		logger.debug("Fetching all the names of Vehicles");
		Query q = em.createNamedQuery(VEHICLE_GET_NAMES);
		List<String> nameList =  q.getResultList();
		return nameList;
	}

	@Override
	public List<Object[]> getVehicleDetailsForUserId(BigInteger userId) {
		logger.debug("Fetching all the Vehicle detail for userId "+userId);
		Query q = em.createNamedQuery(VEHICLE_GET_VEHICLE_DETAILS_FOR_USER);
		q.setParameter("userId", userId);
		List<Object[]> vehicleDetails = q.getResultList();
		return vehicleDetails;
	}
}
