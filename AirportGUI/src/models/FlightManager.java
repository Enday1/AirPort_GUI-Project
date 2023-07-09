package models;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.time.LocalDate;
import java.util.ArrayList;

public class FlightManager {
	private AirportManager airportManager;
	private HashMap<String, Flight> flights = new HashMap<>();
	
	public FlightManager(AirportManager airportManager) {
		this.airportManager = airportManager;
		
	}
	
	public boolean addFlight(Flight flight) {
		if(!flights.containsKey(flight.getNumber())) {
			flights.put(flight.getNumber(), flight);
			return true;
		}
		
		return false;
	}
	
	public int getNumFlights() {
		return flights.size();
	}
	
	public Flight getFlight(String flightNumber) {
		if(flights.containsKey(flightNumber)) {
			return flights.get(flightNumber);
		}
		
		return null;
	}
	
	public List<Flight> getFlights() {
		List<Flight> listOfFlights = new ArrayList<>(flights.values());
		return listOfFlights;
	}
	
	public List<Flight> getFlightsByOrigin(String originCode) {
		List<Flight> flightsWithOrigin = new ArrayList<>();
		Collection<Flight> allFlightsAsCollection = flights.values();
		List<Flight> allFlightsAsList = new ArrayList<>(allFlightsAsCollection);
		
		for(Flight f : allFlightsAsList) {
			if(f.getOrigin().getCode().equals(originCode)) {
				flightsWithOrigin.add(f);
			}
		}
		
		return flightsWithOrigin;
	}
	
	public List<Flight> getFlightsByOrigin(String originCode, LocalDate date) {
		List<Flight> flightsWithOriginCode = getFlightsByOrigin(originCode);
		List<Flight> flightsWithDateAndOriginCode = new ArrayList<>();
		
		for(Flight f : flightsWithOriginCode) {
			if(f.getDate().equals(date)) {
				flightsWithDateAndOriginCode.add(f);
			}
		}
		
		return flightsWithDateAndOriginCode;
	}
	
	public List<Flight> getFlightsByOrigin(String originCode, String date) {
		List<Flight> flights = getFlightsByOrigin(originCode, LocalDate.parse(date));
		return flights;
	}
	
	public List<Flight> getFlightsByOriginAndDestination(String originCode, String destinationCode, LocalDate date) {
		List<Flight> flightsWithOriginAndDate = getFlightsByOrigin(originCode, date);
		List<Flight> flightsWithOriginDestinationAndDate = new ArrayList<>();
		
		for(Flight f: flightsWithOriginAndDate) {
			if(f.getDestination().getCode().equals(destinationCode)) {
				flightsWithOriginDestinationAndDate.add(f);
			}
		}
		
		return flightsWithOriginDestinationAndDate;
	}

	public List<Flight> getFlightsByOriginAndDestination(String originCode, String destinationCode, String date) {
		return getFlightsByOriginAndDestination(originCode, destinationCode, LocalDate.parse(date));
	}
	
	public Flight removeFlight(String number) {
		if(flights.containsKey(number)) {
			return flights.remove(number);
		}
		
		return null;
	}
	
	public Flight removeFlight(Flight flight) {
		/*if(flights.containsValue(flight)) {
			return flights.remove(flight.getNumber());
		}
		
		return null;*/
		
		return removeFlight(flight.getNumber());
	}
	
	//opted to not write private helper methods show in the class diagram of HW10
}
