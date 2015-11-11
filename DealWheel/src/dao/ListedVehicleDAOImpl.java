package dao;

import static services.utility.GenericConstant.LISTED_VEHICLE_GET_NAMES;
import static services.utility.GenericConstant.LISTED_VEHICLE_GET_DETAILS;
import static services.utility.GenericConstant.LISTED_VEHICLE_GET_MAKES;

import java.util.List;

import javax.persistence.Query;

import model.ListedVehicle;

import org.apache.log4j.Logger;

public class ListedVehicleDAOImpl<T> extends BaseDAOImpl<ListedVehicle> implements ListedVehicleDAO {

	private static Logger logger = Logger.getLogger(ListedVehicleDAOImpl.class);

	@Override
	public List<String> getVehiclesMakers() {
		logger.debug("Fetching all the names of Vehicles makers");
		Query q = em.createNamedQuery(LISTED_VEHICLE_GET_MAKES);
		List<String> makersList =  q.getResultList();
		return makersList;
	}

	@Override
	public List<String> getVehiclesForMakers(String maker) {
		logger.debug("Fetching all the names of Vehicles for "+maker);
		Query q = em.createNamedQuery(LISTED_VEHICLE_GET_NAMES);
		q.setParameter("makerName", maker);
		List<String> vehicleList =  q.getResultList();
		return vehicleList;
	}

	@Override
	public ListedVehicle findVehicle(String vehicleName, String vehicleMake, String vehicleType) {
		logger.debug("Fetching Vehicles details for "+vehicleName);
		Query q = em.createNamedQuery(LISTED_VEHICLE_GET_DETAILS);
		q.setParameter("vehicleName", vehicleName);
		q.setParameter("makerName", vehicleMake);
		q.setParameter("vehicleType", vehicleType);
		ListedVehicle vehicleListed =  (ListedVehicle) q.getSingleResult();
		return vehicleListed;
	}
}
