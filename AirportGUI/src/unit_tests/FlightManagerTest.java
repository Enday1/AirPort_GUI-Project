package unit_tests;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import models.AirportLoader;
import models.Airport;
import models.AirportManager;
import models.Flight;
import models.FlightManager;
/**
 * Some of the test methods here use a previous version of the Flight class.
 * Thus, they don't compile and have not been rewritten.
 * @author dgibson
 *
 */
public class FlightManagerTest {
	
	static final String path = "src/text_files/";
	static final String airportsMediumFileName = path + "airportsMedium.txt";
	static final File airportsMediumFile = new File(airportsMediumFileName);
	
	static final String flightsMediumFileName = path + "flightsMeduim.txt";
	static final File flightsMediumFile = new File(flightsMediumFileName);

	public static void main(String[] args) {
		testAddFlight();
		testAddFlight_Duplicate();
		testAddFlight_WithParameters_Valid();
		testAddFlight_WithParameters_InvalidFlightNumber_Length();
		testAddFlight_WithParameters_InvalidFlightNumber_Digits();
		testAddFlight_WithParameters_InvalidAirportCode();
		testGetFlight_ValidFlightNumber();
		testGetFlight_InvalidFlightNumber();
		testGetFlightsByOrigin();
		testGetFlightsByOriginAndDate();
		testGetFlightsByOriginAndDestinationAndDate();
		testGetNumFlights();
		testRemoveFlight_ValidFlightNumber();
		testRemoveFlight_InvalidFlightNumber();
	}
	
	
	public static void testAddFlight() {
		System.out.println("\n--->testAddFlight");
		AirportManager am = getAirportManager();
		FlightManager fm = new FlightManager(am);
		Flight f = createTestFlight("1234", am.getAirport("VLD"), am.getAirport("TCL"), 750.00);
		boolean wasAdded = fm.addFlight(f);
		System.out.println("Expected: true");
		System.out.println("  Actual: " + wasAdded);
	}
	
	public static void testAddFlight_Duplicate() {
		System.out.println("\n--->testAddFlight_Duplicate");
		AirportManager am = getAirportManager();
		FlightManager fm = new FlightManager(am);
		Flight f1 = createTestFlight("1234", am.getAirport("VLD"), am.getAirport("TCL"), 800.00);
		Flight f2 = createTestFlight("1234", am.getAirport("VLD"), am.getAirport("SEM"), 750.00);
		fm.addFlight(f1);
		boolean wasAdded = fm.addFlight(f2);
		System.out.println("Expected: false");
		System.out.println("  Actual: " + wasAdded);
	}
	
	public static void testAddFlight_WithParameters_Valid() {
		System.out.println("\n--->testAddFlight_WithParameters_Valid");
		AirportManager am = getAirportManager();
		FlightManager fm = new FlightManager(am);
		Flight f = new Flight("1234", "2021-12-01", am.getAirport("VLD"), am.getAirport("TCL"), 750.00);
		boolean wasAdded = fm.addFlight(f);
		System.out.println("Expected: true");
		System.out.println("  Actual: " + wasAdded);
	}

	/**
	 * Not useful, should be a Flight test as Flight constructor tests flight num to see 
	 * if correct.
	 */
	public static void testAddFlight_WithParameters_InvalidFlightNumber_Length() {
		System.out.println("\n--->testAddFlight_WithParameters_InvalidFligthNumber_Length");
		
//		FlightManager fm = new FlightManager(getAirportManager());
//		boolean tf = fm.addFlight("12345", "2021-12-01", "VLD", "TCL", 750.00);
//		System.out.println("Expected: false");
//		System.out.println("  Actual: " + tf);
	}
	
	/**
	 * Not useful, should be a Flight test as Flight constructor tests flight num to see 
	 * if correct.
	 */
	public static void testAddFlight_WithParameters_InvalidFlightNumber_Digits() {
		System.out.println("\n--->testAddFlight_WithParameters_InvalidFligthNumber_Digits");
		
//		FlightManager fm = new FlightManager(getAirportManager());
//		boolean tf = fm.addFlight("abcd", "2021-12-01", "VLD", "TCL", 750.00);
//		System.out.println("Expected: false");
//		System.out.println("  Actual: " + tf);
	}
	
	/**
	 * Not useful, should be a Flight test as Flight constructor tests flight num to see 
	 * if correct.
	 */
	public static void testAddFlight_WithParameters_InvalidAirportCode() {
		System.out.println("\n--->testAddFlight_WithParameters_InvalidAirportCode");
		
//		FlightManager fm = new FlightManager(getAirportManager());
//		boolean tf = fm.addFlight("abcd", "2021-12-01", "AAA", "TCL", 750.00);
//		System.out.println("Expected: false");
//		System.out.println("  Actual: " + tf);
	}
	
	public static void testGetFlight_ValidFlightNumber() {
		System.out.println("\n--->testGetFlight_ValidFlightNumber");
		
		FlightManager fm = createTestFlightManager();
		Flight f = fm.getFlight("9865");
		System.out.println("Expected: Flight Number: 9865");
		System.out.println("  Actual: ");
		if (f != null) {
			System.out.println(f);
		}
		else {
			System.out.println("null");
		}
	}
	
	public static void testGetFlight_InvalidFlightNumber() {
		System.out.println("\n--->testGetFlight_InvalidFlightNumber");
		
		FlightManager fm = createTestFlightManager();
		Flight f = fm.getFlight("1111");
		
		System.out.println("Expected: Flight Number: null");
		System.out.println("  Actual: ");
		if (f != null) {
			System.out.println(f);
		}
		else {
			System.out.println("null");
		}
	}
	
	public static void testGetFlightsByOrigin() {
		System.out.println("\n--->testGetFlightsByOrigin");
		
		FlightManager fm = createTestFlightManager();
		List<Flight> flights = fm.getFlightsByOrigin("VLD");
		System.out.println("Expected: Flight Numbers \"1234\" and \"4429\"");
		System.out.println("  Actual:");
		print(flights);
	}
	
	public static void testGetFlightsByOriginAndDate() {
		System.out.println("\n--->testGetFlightsByOriginAndDate");
		String date = LocalDate.now().toString();
		FlightManager fm = createTestFlightManager();
		List<Flight> flights = fm.getFlightsByOrigin("MIA", date);
		System.out.println("Expected: Flight Numbers \"9876\" and \"2587\" ");
		System.out.println("  Actual:");
		print(flights); 
	}
	
	public static void testGetFlightsByOriginAndDestinationAndDate() {
		System.out.println("\n--->testGetFlightsByOriginAndDestinationAndDate");
		String date = LocalDate.now().toString();
		FlightManager fm = createTestFlightManager();
		List<Flight> flights = fm.getFlightsByOriginAndDestination("MIA", "AGR", date);
		System.out.println("Expected: Flight Numbers \"2587\" ");
		System.out.println("  Actual:");
		print(flights);
	}
	
	public static void testGetNumFlights() {
		System.out.println("\n--->testGetNumFlights");
		
		FlightManager fm = createTestFlightManager();
		int num = fm.getNumFlights();
		System.out.println("Expected: 6");
		System.out.println("  Actual: " + num);
	}
	
	public static void testRemoveFlight_ValidFlightNumber() {
		System.out.println("\n--->testRemoveFlight_ValidFlightNumber");
		
		FlightManager fm = createTestFlightManager();
		Flight f = fm.removeFlight("9876");
		
		System.out.println("Expected: Flight Number: 9876");
		System.out.println("  Actual: ");
		if (f != null) {
			System.out.println(f);
		}
		else {
			System.out.println("null");
		}
	}
	
	public static void testRemoveFlight_InvalidFlightNumber() {
		System.out.println("\n--->testRemoveFlight_InvalidFlightNumber");
		
		FlightManager fm = createTestFlightManager();
		Flight f = fm.removeFlight("9999");
		
		System.out.println("Expected: Flight Number: null");
		System.out.println("  Actual: ");
		if (f != null) {
			System.out.println(f);
		}
		else {
			System.out.println("null");
		}
	}
	
	
	/*----------------------------------------------------------------------------
	 * Helper methods
	 *--------------------------------------------------------------------------*/
	/**
	 * Creates an AirportManager from the medium file (89 airports)
	 * @return
	 */
	private static AirportManager getAirportManager() {
		Map<String, Airport> airports = null;
		try {
			airports = AirportLoader.getAirportMap(airportsMediumFile);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		AirportManager am = new AirportManager(airports);
		return am;
	}
	
	private static void print(List<Flight> flights) {
		for (Flight f : flights) {
			System.out.println(f);
		}
	}

	/***
	 * Creates a FlightManager with 6 flights
	 * @return
	 */
	public static FlightManager createTestFlightManager() {
		AirportManager am = getAirportManager();
		FlightManager fm = new FlightManager(am);
		Flight f1 = createTestFlight("1234", am.getAirport("VLD"), am.getAirport("TCL"), 800.00);
		Flight f2 = createTestFlight("5879", am.getAirport("TIX"), am.getAirport("SEM"), 750.00);
		Flight f3 = createTestFlight("9865", am.getAirport("SEM"), am.getAirport("ATL"), 800.00);
		Flight f4 = createTestFlight("2587", am.getAirport("MIA"), am.getAirport("AGR"), 750.00);
		Flight f5 = createTestFlight("4429", am.getAirport("VLD"), am.getAirport("OPF"), 750.00);
		Flight f6 = createTestFlight("9876", am.getAirport("MIA"), am.getAirport("TIX"), 750.00);
		fm.addFlight(f1);
		fm.addFlight(f2);
		fm.addFlight(f3);
		fm.addFlight(f4);
		fm.addFlight(f5);
		fm.addFlight(f6);
		
		return fm;
	}

	/**
	 * Creates a Flight with current date (LocalDate.now()). More sophisticated tests would require 
	 * different dates.
	 * @param num
	 * @param origin
	 * @param dest
	 * @param cost
	 * @return
	 */
	public static Flight createTestFlight(String num, Airport origin, Airport dest, double cost) {
		Flight f = new Flight(num, LocalDate.now(), origin, dest, cost);
		return f;
	}
	

}
