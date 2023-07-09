package models;
import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Set;
import java.util.Collection;
import java.util.Collections;

public class AirportManager {
	private Map<String, Airport> airports = new HashMap<>();
	
	public AirportManager(Map<String, Airport> airports) {
		this.airports = airports;
	}
	
	public int getNumAirports() {
		return airports.size();
	}
	
	public boolean addAirport(Airport ap) {
		if(!airports.containsValue(ap)) {
			airports.put(ap.getCode(), ap);
			return true;
		}
		
		return false;
	}
	
	public Airport removeAirport(String str) {
		//dummy airport object creation
		Airport ap = new Airport(str, 0.00, 0.00, "unknown", "unknown");
		if(airports.containsKey(ap.getCode())) {
			ap = airports.remove(str);
			return ap;
		}
		
		return null;
	}
	
	public List<String> getAirportCodes() {
		Set<String> codes = airports.keySet();
		List<String> listCodes = new ArrayList<>(codes);
		return listCodes;
	}
	
	public boolean containsAirport(String str) {
		//dummy airport object creation
		Airport ap = new Airport(str, 0.00, 0.00, "unknown", "unknown");
		if(airports.containsKey(ap.getCode())) {
			return true;
		}
		
		return false;
	}
	
	public double getDistanceBetween(String str1, String str2) {
		//checks if contains or not. If not return -1
		if ( !containsAirport(str1) || !containsAirport(str2) ) {
			return -1.0;
		}
		
		//dummy variables
		Airport ap1 = airports.get(str1);
		Airport ap2 = airports.get(str2);
//		double lat1 = ap1.getLatitude();
//		double lat2 = ap2.getLatitude();
//		double long1 = ap1.getLongitude();
//		double long2 = ap2.getLongitude();
		return getDistanceBetween(ap1, ap2);
		
		//DistanceCalculator d = new DistanceCalculator();
		//return DistanceCalculator.getDistance(lat1, long1, lat2, long2);
	}
	
	public double getDistanceBetween(Airport ap1, Airport ap2) {
		//checks if contains or not. If not return -1
		if(ap1==null || ap2==null) { 
			return -1;
		}
		
		double lat1 = ap1.getLatitude();
		double lat2 = ap2.getLatitude();
		double long1 = ap1.getLongitude();
		double long2 = ap2.getLongitude();
		
		//DistanceCalculator d = new DistanceCalculator();
		return DistanceCalculator.getDistance(lat1, long1, lat2, long2);
	}
	
	public Airport getAirport(String str) {
		if(airports.containsKey(str)) {
			return airports.get(str);
		}
		
		return null;
	}
	
	public Airport getAirportClosestTo(String code) {
		List<Airport> lstAirports = new ArrayList<>(airports.values());
		// Can't pass airports.values b/c it is a Collection, and 
		// helper accepts a List
		return getAirportClosestTo(lstAirports, code);
	}
	
	public Airport getAirportClosestTo2(String code) {
		Airport a1 = new Airport(code);
		double closestDist = Double.MAX_VALUE;
		Collection<Airport> ap = airports.values();
		ArrayList<Airport> apAList = new ArrayList<>(ap);
		apAList.remove(a1);
		
		Airport returnAirport = apAList.get(0);
		
		for(Airport a : apAList) {
			if(getDistanceBetween(a1,a) < closestDist) {
				closestDist = getDistanceBetween(a1,a);
				returnAirport = a;
			}
		}
		return returnAirport;
	}
	
	//professor aided in coding. Not my own work.
	private Airport getAirportClosestTo(List<Airport> airports, String code) {
		double minDist = Double.MAX_VALUE;
		Airport closest = null;
		Airport origin = getAirport(code);
		
		for(Airport dest : airports) {
			if(origin.equals(dest)) {
				continue;
			}
			double dist = getDistanceBetween(origin, dest);
			if(dist < minDist){
				minDist = dist;
				closest = dest;
			}
		}
		return closest;
	}
	
	public List<Airport> getAirportsWithin(String code, double dist) {
		Airport ap = null;
		if(containsAirport(code)) {
			ap = airports.get(code);
		}
		List<Airport> apList = new ArrayList<>(airports.values());
		apList.remove(ap);
		List<Airport> apWithinDist = new ArrayList<>();
		
		for(Airport a : apList) {
			if(getDistanceBetween(ap,a) < dist) {
				apWithinDist.add(a);
			}
		}
		return apWithinDist;
	}
	
	public List<Airport> getAirportsWithin(double WithinDist, double lat, double lon) {
		Map<String, Airport> phony = new HashMap<>(airports);
		List<Airport> apList = new ArrayList<>(airports.values());
		List<Airport> apWithinDist = new ArrayList<>();
		Airport ap = new Airport("fake", lat, lon, "unknown", "unknown");
		
		if(!phony.containsValue(ap)) {
			for(Airport a : apList) {
				if(getDistanceBetween(ap,a) < WithinDist) {
					apWithinDist.add(a);
				}
			}
			return apWithinDist;
			
		}
		
		return null;
	}
	
	public List<Airport> getAirportsWithin(String code1, String code2, double dist) {
		List<Airport> within = new ArrayList<>();
		List<Airport> apList = new ArrayList<>(airports.values());
		Airport ap1 = null;
		Airport ap2 = null;
		if(containsAirport(code1) && containsAirport(code2)) {
			ap1 = airports.get(code1);
			ap2 = airports.get(code2);
		}
		
		for(Airport a : apList) {
			if(a.equals(ap1) || a.equals(ap2)) {
				continue;
			}
			
			if(getDistanceBetween(a, ap1) < dist && getDistanceBetween(a, ap2) < dist) {
				within.add(a);
			}	
		}
		//could have just called on one of your previous methods
		return within;
	}
	
	public List<Airport> getAirportsByCity(String city) {
		List<Airport> sameCity = new ArrayList<>();
		List<Airport> apList = new ArrayList<>(airports.values());
		
		for(Airport a : apList) {
			if(a.getCity().equals(city)) {
				sameCity.add(a);
			}
		}
		
		return sameCity;
	}
	
	public List<Airport> getAirportsByCityState(String city, String state){
		List<Airport> sameCityState = new ArrayList<>();
		List<Airport> sameCity = getAirportsByCity(city);
		
		for(Airport a : sameCity) {
			if(a.getState().equals(state)) {
				sameCityState.add(a);
			}
		}
		return sameCityState;
	}
	
	/**
	 * Returns a list of the Airports whose code follows the National Weather Service (NWS) 
	 * naming system. NWS codes have an �X� as the third character. For example: LAX is Los Angeles� airport. 
	 * @return
	 */
	public List<Airport> getNWSNamedAirports(){
		List<Airport> nwsAirports = new ArrayList<>();
		List<Airport> apList = new ArrayList<>(airports.values());
		
		for(Airport a : apList) {
			//get the character in the third position and change it back to String from char
			String thirdChar = a.getCode().charAt(2) + "";
			if(thirdChar.equals("X")) {
				nwsAirports.add(a);
			}
		}
		return nwsAirports;
	}
	
	public List<Airport> getAirportsSortedBy(String sortType){
		List<Airport> sorted = new ArrayList<>(airports.values());
		AirportCityComparator cityCompare = new AirportCityComparator();
		AirportStateComparator stateCompare = new AirportStateComparator();
		
		if(sortType.equals("City")) {
			Collections.sort(sorted,cityCompare);
			return sorted;
		}
		
		if(sortType.equals("State")) {
			Collections.sort(sorted,stateCompare);
			return sorted;
		}
		
		return sorted;
	}
	
	public List<Airport> getAirports() {
		List<Airport> ap = new ArrayList<>(airports.values());
		return ap;
	}
	
	/**
	 * Returns a list of the <code>num</code> Airports that are closest in distance (miles) to the Airport with <codecode</code>. 
	 * For example, if num=3, and code="VLD", then find the 3 closest Airports to 
	 * Valdosta. This will take some thought!
	 * @param code
	 * @param num
	 * @return
	 */
	
	//professor aided in coding. Not my own work.
	public List<Airport> getAirportsClosestTo(String code, int num){
		// Hold the closest airports
		List<Airport> closest = new ArrayList<>();
		// Holds all the airports we currently have, 
		List<Airport> tempAirports = new ArrayList<>(airports.values());
			
		// Loop "num" times
		for(int i=0; i<num; i++){
			// Helper method that finds the 1 closest airport to code and returns it.
			Airport a = getAirportClosestTo(tempAirports, code);
			// Add closest to list
			closest.add(a);
			// Remove closest from current, temp list
			tempAirports.remove(a);
		}
		return closest;
	}
	
}
