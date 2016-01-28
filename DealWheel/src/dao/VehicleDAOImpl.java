package dao;

import static services.utility.GenericConstant.LISTED_VEHICLE_GET_NAMES;
import static services.utility.GenericConstant.VEHICLE_GET_VEHICLE_DETAILS_FOR_USER;
import static services.utility.GenericConstant.VEHICLE_SEARCH_WITH_IDS;
import static services.utility.GenericConstant.VEHICLE_SEARCH_WITH_ID;

import java.math.BigInteger;
import java.util.ArrayList;
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
		Query q = em.createNamedQuery(LISTED_VEHICLE_GET_NAMES);
		List<String> nameList =  q.getResultList();
		return nameList;
	}

	@Override
	public List<Object[]> getVehicleDetailsForUserId(BigInteger userId) {
		logger.debug("Fetching all the Vehicle detail for userId "+userId);
		Query q = em.createNamedQuery(VEHICLE_GET_VEHICLE_DETAILS_FOR_USER);
		q.setParameter("vendorId", userId);
		List<Object[]> vehicleDetails = q.getResultList();
		return vehicleDetails;
	}

	public void disable(BigInteger vehId) {
		logger.debug("Disabling all the Vehicles with Ids "+vehId);
		Query q = em.createNamedQuery(VEHICLE_SEARCH_WITH_IDS);
		q.setParameter("vehicleId", vehId);
		List<Object[]> vehicleDetails = q.getResultList();
		List<Vehicle> vList = new ArrayList<Vehicle>();
		for(Object o : vehicleDetails){
			((Vehicle)o).setVehicleDisabled(true);
			vList.add(update(((Vehicle)o)));
		}
		
		logger.debug(vList.size()+" rows disabled");
	}

	public void enable(BigInteger vehicleId) {
		logger.debug("Enabling the Vehicle with Id "+vehicleId);
		Query q = em.createNamedQuery(VEHICLE_SEARCH_WITH_ID);
		q.setParameter("vehicleId", vehicleId);
		List<Object[]> vehicleDetails = q.getResultList();
		List<Vehicle> vList = new ArrayList<Vehicle>();
		for(Object o : vehicleDetails){
			((Vehicle)o).setVehicleDisabled(false);
			vList.add(update(((Vehicle)o)));
		}
		
		logger.debug(vList.size()+" vehicle enabled");
	}
}
