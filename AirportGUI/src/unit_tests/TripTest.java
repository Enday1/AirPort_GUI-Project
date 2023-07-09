package unit_tests;

import java.io.File;
import java.time.LocalDate;
import java.util.*;
import models.Trip;
import models.Airport;
import models.AirportManager;
import models.Flight;
import models.AirportLoader;
/**
 * Contains test method to test the getAirportMap method in the AirportLoader
 * class & these methods have already been written and provided to you.
 * @author dgibson
 *
 */
public class TripTest {
	static final String path = "src/text_files/";
	static final String airportsSmallFileName = path + "airportsSmall.txt";
	static final String airportsMediumFileName = path + "airportsMedium.txt";
	static final String airportsSameCitiesFileName = path + "airportsSameCities.txt";
	static final String airportsAllFileName = path + "airports.txt";
	static final File airportsSmallFile = new File(airportsSmallFileName);
	static final File airportsMediumFile = new File(airportsMediumFileName);
	static final File airportsSameCitiesFile = new File(airportsSameCitiesFileName);
	static final File airportsAllFile = new File(airportsAllFileName);

	public static void main(String[] args) {
		testCreateTrip();
	}

	public static void testCreateTrip() {
		System.out.println("\ntestCreateTrip()");
		AirportManager airportManager = getAirportManager(airportsSmallFile);

		Airport from = airportManager.getAirport("VLD");
		Airport to = airportManager.getAirport("CSG");
		LocalDate date = LocalDate.of(2020, 06, 18);
		Flight flightOrigin = new Flight("1234", date, from, to, 144.50);

		from = airportManager.getAirport("CSG");
		to = airportManager.getAirport("ANB");
		Flight flightDestination = new Flight("1235", date, from, to, 200.50);
		
		Trip trip = new Trip();
		trip.addLeg(flightOrigin);
		trip.addLeg(flightDestination);

		System.out.println(trip);
	}


	private static void print(Map<String,Airport> airports){
		int i=1;
		for( Airport a : airports.values()) {
			String msg = String.format("%4d. %s", i++, a);
			System.out.println(msg);
		}
	}
	
	private static AirportManager getAirportManager(File file) {
		Map<String,Airport> airports = AirportLoader.getAirportMap(file);
		AirportManager airportManager = new AirportManager(airports);
		return airportManager;
	}

}
