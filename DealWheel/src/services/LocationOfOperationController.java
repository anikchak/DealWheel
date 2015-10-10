package services;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import  static services.utility.GenericConstant.COUNTRY_OF_OPERATIONS_FILE;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import org.apache.log4j.Logger;

public class LocationOfOperationController {

	Location location;
	private static Logger logger = Logger.getLogger(LocationOfOperationController.class);

	
	public void addToXML(String countryName, String stateName, String cityName) {
		City city = new City();
		city.setCityName(cityName);

		State state = new State();
		state.setStateName(stateName);
		state.getCities().add(city);

		Country country = new Country();
		country.setCountryName(countryName);
		country.getStates().add(state);

		Location location = new Location();
		location.getCountries().add(country);

		Location exstngLocations = getLocations();
		
		boolean addCity = true, addState = true, addCountry = true;

		if (exstngLocations != null) {
			Iterator<Country> cItr = exstngLocations.getCountries().iterator();
			while (cItr.hasNext()) {
				Country exstngCountry = (Country) cItr.next(); 
				if(exstngCountry.getCountryName().equalsIgnoreCase(countryName)){
					Iterator<State> sItr = exstngCountry.getStates().iterator();
					while(sItr.hasNext()){
						State exstngState = (State) sItr.next();
						if(exstngState.getStateName().equalsIgnoreCase(stateName)){
							Iterator<City> ctItr = exstngState.getCities().iterator();
							while (ctItr.hasNext()) {
								City exstngCity = (City) ctItr.next();
								if(exstngCity.getCityName().equalsIgnoreCase(cityName)){
									addCity = false;
									addState = false;
									addCountry = false;
								}
							}
							if(addCity){
								exstngState.getCities().add(city);
								addState = false;
								addCountry = false;
							}
						}
					}
					if(addState){
						exstngCountry.getStates().add(state);
						addCountry  = false;
					}
				}
			}
			if(addCountry){
				exstngLocations.getCountries().add(country);
			}
			
		}else{
			exstngLocations = new Location();
			exstngLocations.getCountries().add(country);
		}
		jaxbObjectToXML(exstngLocations);
	}

	
	
	private static Location jaxbXMLToObject() {
		try {
			JAXBContext context = JAXBContext.newInstance(Location.class);
			Unmarshaller un = context.createUnmarshaller();
			Location loc = (Location) un.unmarshal(new File(LocationOfOperationController.class.getClassLoader().getResource(COUNTRY_OF_OPERATIONS_FILE).getFile()));
			return loc;
		} catch (JAXBException e) {
			logger.info("File is Blank");
		}
		return null;
	}

	private static void jaxbObjectToXML(Location loc) {

		try {
			JAXBContext context = JAXBContext.newInstance(Location.class);
			Marshaller m = context.createMarshaller();
			m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
			m.marshal(loc, new File(LocationOfOperationController.class.getClassLoader().getResource(COUNTRY_OF_OPERATIONS_FILE).getFile()));
		} catch (JAXBException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 
	 * @return
	 */
	public Location getLocations() {
		return jaxbXMLToObject();
	}
	
	/**
	 * 
	 * @return
	 */
	public List<String> getCountryNames(){
		List<String> names = new ArrayList<String>();
		for(Country c : getLocations().getCountries()){
			names.add(c.getCountryName());
		}
		return names;
	}
	
	/**
	 * 
	 * @param country
	 * @return
	 */
	public List<String> getStateNamesForCountry(String country){
		List<String> names = new ArrayList<String>();
		List<Country> countries = getLocations().getCountries();
		for(Country c : countries){
			if(c.getCountryName().equals(country)){
				 for(State s : c.getStates()){
					 names.add(s.getStateName());
				 }
			}
		}
		return names;
	}
	
	/**
	 * 
	 * @param country
	 * @param state
	 * @return
	 */
	public List<String> getCityNamesForCountryState(String country, String state){
		List<String> names = new ArrayList<String>();
		List<Country> countries = getLocations().getCountries();
		for(Country c : countries){
			if(c.getCountryName().equals(country)){
				 for(State s : c.getStates()){
					 if(s.getStateName().equals(state)){
						 for(City ct : s.getCities()){
							 names.add(ct.getCityName());
						 }
					 }
				 }
			}
		}
		return names;
	}
}


	// Classes
	@XmlRootElement(name = "Locations")
	@XmlType(propOrder={"countries"})
	class Location {
		private List<Country> country;

		public List<Country> getCountries() {
			if (country == null)
				country = new CopyOnWriteArrayList<Country>();
			return country;
		}

		public void setCountries(List<Country> countries) {
			this.country = countries;
		}
	}

	@XmlRootElement(name = "Country")
	@XmlType(propOrder={"countryName", "states"})
	class Country {
		private String countryName;
		private List<State> state;

		public String getCountryName() {
			return countryName;
		}

		public void setCountryName(String countryName) {
			this.countryName = countryName;
		}

		public List<State> getStates() {
			if (state == null)
				state = new CopyOnWriteArrayList<State>();
			return state;
		}

		public void setStates(List<State> states) {
			this.state = states;
		}

		@Override
		public boolean equals(Object obj) {
			if (obj instanceof Country
					&& this.countryName.equals(((Country) obj).countryName))
				return true;
			return false;
		}
	}

	@XmlRootElement(name = "State")
	@XmlType(propOrder={"stateName", "cities"})
	class State {
		private String stateName;
		private List<City> city;

		public String getStateName() {
			return stateName;
		}

		public void setStateName(String stateName) {
			this.stateName = stateName;
		}

		public List<City> getCities() {
			if (city == null)
				city = new CopyOnWriteArrayList<City>();
			return city;
		}

		public void setCities(List<City> cities) {
			this.city = cities;
		}

		@Override
		public boolean equals(Object obj) {
			if (obj instanceof State
					&& this.stateName.equals(((State) obj).stateName))
				return true;
			return false;
		}

	}

	@XmlRootElement(name = "City")
	@XmlType(propOrder={"cityName"})
	class City {
		private String cityName;

		public String getCityName() {
			return cityName;
		}

		public void setCityName(String cityName) {
			this.cityName = cityName;
		}

		@Override
		public boolean equals(Object obj) {
			if (obj instanceof City
					&& this.cityName.equals(((City) obj).cityName))
				return true;
			return false;
		}
	}