package dao;

import static services.utility.GenericConstant.LISTED_VEHICLE_GET_NAMES;
import static services.utility.GenericConstant.VEHICLE_GET_VEHICLE_DETAILS_FOR_USER;
import static services.utility.GenericConstant.VEHICLE_SEARCH_WITH_ID;

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

	@SuppressWarnings("unchecked")
	public List<String> getNames() {
		logger.debug("Fetching all the names of Vehicles");
		Query q = em.createNamedQuery(LISTED_VEHICLE_GET_NAMES);
		List<String> nameList =  q.getResultList();
		return nameList;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Object[]> getVehicleDetailsForUserId(String userId) {
		logger.debug("Fetching all the Vehicle detail for userId "+userId);
		Query q = em.createNamedQuery(VEHICLE_GET_VEHICLE_DETAILS_FOR_USER);
		q.setParameter("vendorId", userId);
		List<Object[]> vehicleDetails = q.getResultList();
		return vehicleDetails;
	}

	public void disable(BigInteger vehicleId) {
		logger.debug("Disabling all the Vehicles with Ids "+vehicleId);
		Query q = em.createNamedQuery(VEHICLE_SEARCH_WITH_ID);
		q.setParameter("vehicleId", vehicleId);
		Vehicle vehicleDetail = (Vehicle) q.getSingleResult();
		vehicleDetail.setVehicleDisabled(true);
		update(vehicleDetail);
		logger.debug("Vehicle with ID "+vehicleId+" disabled");
	}

	public void enable(BigInteger vehicleId) {
		logger.debug("Enabling the Vehicle with Id "+vehicleId);
		Query q = em.createNamedQuery(VEHICLE_SEARCH_WITH_ID);
		q.setParameter("vehicleId", vehicleId);
		Vehicle vehicleDetail = (Vehicle) q.getSingleResult();
		vehicleDetail.setVehicleDisabled(false);
		update(vehicleDetail);
		logger.debug("Vehicle with ID "+vehicleId+" enabled");
	}

	public void changeCostAndDeposit(BigInteger vehicleId,String changedCost, String changedDeposit) {
		logger.debug("Changing cost for the Vehicle with Id "+vehicleId);
		Query q = em.createNamedQuery(VEHICLE_SEARCH_WITH_ID);
		q.setParameter("vehicleId", vehicleId);
		Vehicle vehicleDetail = (Vehicle) q.getSingleResult();
		vehicleDetail.setVhclPerDayCost(Integer.parseInt(changedCost));
		vehicleDetail.setVhclSecurityDeposit(Integer.parseInt(changedDeposit));
		update(vehicleDetail);
		logger.debug("Vehicle ID "+vehicleId+"'s cost changed to "+changedCost+" and deposit changed to "+changedDeposit);
	}
}
