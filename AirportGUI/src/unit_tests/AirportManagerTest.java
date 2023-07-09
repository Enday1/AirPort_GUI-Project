package unit_tests;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import models.AirportManager;
import models.AirportLoader;
import models.Airport;
/**
 * All tests utilize the helper method, getAirportManager, which reads in airports and
 * builds an AirportManager.
 * @author dgibson
 *
 */
public class AirportManagerTest {
	static final String path = "src/text_files/";
	// Airport file names
	static final String airportsSmallFileName = path + "airportsSmall.txt";
	static final String airportsMediumFileName = path + "airportsMedium.txt";
	static final String airportsSameCitiesFileName = path + "airportsSameCities.txt";
	static final String airportsAllFileName = path + "airports.txt";
	// Airport File objects
	static final File airportsSmallFile = new File(airportsSmallFileName);
	static final File airportsMediumFile = new File(airportsMediumFileName);
	static final File airportsSameCitiesFile = new File(airportsSameCitiesFileName);
	static final File airportsAllFile = new File(airportsAllFileName);

	public static void main(String[] args) {
		System.out.println("The following tests use airportsSmall.txt File");
		testGetNumAirports();
		testAddAirport();
		testAddAirport_Duplicate();
		testGetAirport_Valid();
		testGetAirport_Invalid();
		testRemoveAirport();
		testRemoveAirport_InvalideCode();
		testContainsAirport();
		testContainsAirport_InvalideCode();
		testGetAirportCodes();		
		testGetAirports();
		testGetAirportClosestTo();
		testGetAirportsClosestTo_Multiple();
		
		System.out.println("\n\n\nThe following tests use airportsSameCities.txt File");
		testGetAirportsByCity();
		testGetAirportsByCityState();
		testGetAirportsSortedBy_City();
		testGetAirportsSortedBy_State();
		
		System.out.println("\n\n\nThe following tests use airportsMedium.txt File");
		testGetDistanceBetween_Airports_Valid();
		testGetDistanceBetween_Airports_Invalid();
		testGetDistanceBetween_Code_Valid();
		testGetDistanceBetween_Code_Invalid();
		testGetAirportsWithin_Airport();
		testGetAirportsWithin_Airport_Multiple();
		testGetAirportsWithin_Location();
		testGetNWSNamedAirports();
	}

	public static void testGetNumAirports() {
		System.out.println("\n---> testGetNumAirports");
		AirportManager am = getAirportManager(airportsSmallFile);
		System.out.println("Expected Num Airports: 6");
		System.out.println("  Actual Num Airports: " + am.getNumAirports());
	}

	public static void testAddAirport() {
		System.out.println("\n---> testAddAiport_Valid");
		AirportManager am = getAirportManager(airportsSmallFile);
		Airport a = new Airport("BKV", 36.015430, -82.158867, "Bakersville", "NC");
		boolean wasAdded = am.addAirport(a);
		System.out.println("Expected Num Airports: 7, wasAdded=true");
		System.out.println("  Actual Num Airports: " + am.getNumAirports() + ", wasAdded=" + wasAdded);
	}

	public static void testAddAirport_Duplicate() {
		System.out.println("\n---> testAddAirport_Duplicate");
		AirportManager am = getAirportManager(airportsSmallFile);
		Airport a = new Airport("VLD", 30.78, 83.28, "Valdosta", "GA");
		boolean wasAdded = am.addAirport(a);
		System.out.println("Expected Num Airports: 6, wasAdded=false");
		System.out.println("  Actual Num Airports: " + am.getNumAirports() + ", wasAdded=" + wasAdded);
	}

	public static void testGetAirport_Valid() {
		System.out.println("\n---> testGetAiport_Valid");
		
		AirportManager am = getAirportManager(airportsSmallFile);
		String testCode = "CSG";
		Airport a = am.getAirportClosestTo(testCode);
		
		System.out.println("Expected: Airport with test code \"CSG\":");
		System.out.print("  Actual: ");
		if (a != null) {
			System.out.println("Airport: " + a);
		}
		else {
			System.out.println("Manager does not contain Airport with Code");
		}
		
	}
	
	public static void testGetAirport_Invalid() {
		System.out.println("\n---> testGetAiport_Invalid");
		
		AirportManager am = getAirportManager(airportsSmallFile);
		String testCode = "TFA";
		Airport a = am.getAirport(testCode);
		
		System.out.println("Expected: Airport with test code \"TFA\" (does not exist):");
		System.out.print("  Actual: ");
		if (a != null) {
			System.out.println("Airport: " + a);
		}
		else {
			System.out.println("Manager does not contain Airport with Code");
		}
		
	}
	
	public static void testRemoveAirport() {
		System.out.println("\n---> testRemoveAirport");
		AirportManager am = getAirportManager(airportsSmallFile);
		Airport removedAirport = am.removeAirport("VLD");
		System.out.println("Expected Num Airports: 5, removedAirport=(VLD-Valdosta, GA:  30.8,  83.3)");
		System.out.println("  Actual Num Airports: " + am.getNumAirports() + ", removedAirport=" + removedAirport);
	}

	public static void testRemoveAirport_InvalideCode() {
		System.out.println("\n---> testRemoveAirport_InvalideCode");
		AirportManager am = getAirportManager(airportsSmallFile);
		Airport removedAirport = am.removeAirport("XYZ");
		System.out.println("Expected Num Airports: 6, removedAirport=null");
		System.out.println("  Actual Num Airports: " + am.getNumAirports() + ", removedAirport=" + removedAirport);
	}

	public static void testGetAirportCodes() {
		System.out.println("\n---> testGetAirportCodes");
		AirportManager am = getAirportManager(airportsSmallFile);
		List<String> codes = am.getAirportCodes();
		System.out.println("Expected Codes: [ANB, CSG, HSV, LAL, MXF, VLD]");
		System.out.println("  Actual Codes: " + codes);
	}

	public static void testContainsAirport() {
		System.out.println("\n---> testContainsAirport");
		AirportManager am = getAirportManager(airportsSmallFile);
		boolean isPresent = am.containsAirport("VLD");
		System.out.println("Expected: true");
		System.out.println("  Actual: " + isPresent);
	}

	public static void testContainsAirport_InvalideCode() {
		System.out.println("\n---> testContainsAirport_InvalideCode");
		AirportManager am = getAirportManager(airportsSmallFile);
		boolean isPresent = am.containsAirport("XYZ");
		System.out.println("Expected: false");
		System.out.println("  Actual: " + isPresent);
	}

	public static void testGetAirports() {
		System.out.println("\n---> testGetAiports");
		
		AirportManager am = getAirportManager(airportsSmallFile);
		List<Airport> ports = am.getAirports();
		
		System.out.println("Expected: Airports from Small File");
		System.out.println("  Actual");
		print(ports);
	}

	public static void testGetAirportClosestTo() {
		System.out.println("\n---> testGetAiportClosestTo");
		AirportManager am = getAirportManager(airportsSmallFile);
		String testCode = "VLD";
		Airport a = am.getAirportClosestTo(testCode);
		
		System.out.println("Airport closest to test code \"VLD\":");
		System.out.println("Expected: CSG");
		System.out.println("  Actual: " + a.getCode());
	}
	
	public static void testGetAirportsClosestTo_Multiple() {
		System.out.println("\n---> testGetAiportsClosestTo_Multiple");
		System.out.println("\n---> NEEDS WORK (According to professor)");
		
//		AirportManager am = getAirportManager(airportsSmallFile);
//		String testCode = "GHS";
//		List<Airport> ports = am.getAirportsClosestTo(testCode, 3);
//		
//		System.out.println("Three Airports closest to test code \"GHS\":");
//		System.out.println("Expected: HSV, CSG, ANB");
//		System.out.println("  Actual: " + ports.get(0).getCode() + ", "
//							+ ports.get(1).getCode() + ", " + ports.get(2).getCode());
//		
	}
	
	public static void testGetAirportsByCity() {
		System.out.println("\n---> testGetAirportsByCity");
		
		AirportManager am = getAirportManager(airportsSameCitiesFile);
		String city = "Greenville";
		List<Airport> ports = am.getAirportsByCity(city);
		
		System.out.println("Airports in City \"Greenville\":");
		System.out.println("Expected: GVT, GSP, GLH, GMU, 3B1");
		System.out.println("Actual: ");
		print(ports);
	}
	
	public static void testGetAirportsByCityState() {
		System.out.println("\n---> testGetAirportsByCityState");
		
		AirportManager am = getAirportManager(airportsSameCitiesFile);
		String city = "Greenville";
		String state = "SC";
		List<Airport> ports = am.getAirportsByCityState(city, state);
		
		System.out.println("Airports in City \"Greenville\" and State \"SC\":");
		System.out.println("Expected: GSP, GMU");
		System.out.println("Actual: ");
		print(ports);
	}

	public static void testGetAirportsSortedBy_City() {
		System.out.println("\n---> testGetAirportsSortedBy_City");
		
		AirportManager am = getAirportManager(airportsSameCitiesFile);
		List<Airport> sorted = am.getAirportsSortedBy("City");
		
		System.out.println("Expected: Airports from Same Cities file sorted by City:");
		System.out.println("  Actual: ");
		print(sorted);
	}

	public static void testGetAirportsSortedBy_State() {
		System.out.println("\n---> testGetAirportsSortedBy_State");
		
		AirportManager am = getAirportManager(airportsSameCitiesFile);
		List<Airport> sorted = am.getAirportsSortedBy("State");
		
		System.out.println("Expected: Airports from Same Cities file sorted by State");
		System.out.println("  Actual: ");
		print(sorted);
	}
	
	public static void testGetDistanceBetween_Airports_Valid() {
		System.out.println("\n---> testGetDistanceBetween_Airports_Valid");
		
		AirportManager am = getAirportManager(airportsMediumFile);
		Airport a1 = am.getAirport("AQQ");
		Airport a2 = am.getAirport("MXF");
		double dist = am.getDistanceBetween(a1, a2);
		
		System.out.println("Distance Between Airports \"AQQ\" and \"MXF\":");
		System.out.println("Expected: 199.53");
		System.out.printf("  Actual: %.2f \n", dist);
	}
	
	public static void testGetDistanceBetween_Airports_Invalid() {
		System.out.println("\n---> testGetDistanceBetween_Airports_Invalid");
		
		AirportManager am = getAirportManager(airportsMediumFile);
		Airport a1 = am.getAirport("AAA");
		Airport a2 = am.getAirport("MXF");
		double dist = am.getDistanceBetween(a1, a2);
		
		System.out.println("Distance Between Airports \"AAA\"(does not exist) and \"MXF\":");
		System.out.println("Expected: -1.0");
		System.out.println("  Actual: " + dist);
	}
	
	public static void testGetDistanceBetween_Code_Valid() {
		System.out.println("\n---> testGetDistanceBetween_Codes_Valid");
		
		AirportManager am = getAirportManager(airportsMediumFile);
		double dist = am.getDistanceBetween("ANB", "AUO");
		
		System.out.println("Distance Between Codes \"ANB\" and \"AUO\":");
		System.out.println("Expected: 67.20");
		System.out.printf("  Actual: %.2f \n", dist);
	}
	
	public static void testGetDistanceBetween_Code_Invalid() {
		System.out.println("\n---> testGetDistanceBetween_Codes_Invalid");
		
		AirportManager am = getAirportManager(airportsMediumFile);
		double dist = am.getDistanceBetween("AAA", "AUO");
		
		System.out.println("Distance Between Codes \"AAA\"(does not exist) and \"AUO\":");
		System.out.println("Expected: -1.0");
		System.out.println("  Actual: " + dist);
	}
	
	public static void testGetAirportsWithin_Airport() {
		System.out.println("\n---> testGetAirportsWithin_Airport");
		
		AirportManager am = getAirportManager(airportsMediumFile);
		List<Airport> ports = am.getAirportsWithin("ANB", 75);
		
		System.out.println("Airports within 75 miles of \"ANB\"");
		System.out.println("Expected: AUO, LGC, GAD, BHM, RMG");
		System.out.println("  Actual: ");
		print(ports);
	}
	
	public static void testGetAirportsWithin_Airport_Multiple() {
		System.out.println("\n---> testGetAirportsWithin_Airport_Multiple");
		
		AirportManager am = getAirportManager(airportsMediumFile);
		List<Airport> ports = am.getAirportsWithin("ANB", "VLD", 150);
		
		System.out.println("Airports within 150 miles of \"ANB\" and \"VLD\"");
		System.out.println("Expected: LSF, WRB, MCN");
		System.out.println("  Actual: ");
		print(ports);
	}
	
	public static void testGetAirportsWithin_Location() {
		System.out.println("\n---> testGetAirportsWithin_Location");
		
		AirportManager am = getAirportManager(airportsMediumFile);
		List<Airport> ports = am.getAirportsWithin(100, 30, 80);
		
		System.out.println("Airports within 100 miles of Latitude 30.00 and Longitude 80.00");
		System.out.println("Expected: CRG, DAB, NRB");
		System.out.println("  Actual: ");
		print(ports);
	}
	
	public static void testGetNWSNamedAirports() {
		System.out.println("\n---> testGetNWSNamedAirports");
		
		AirportManager am = getAirportManager(airportsMediumFile);
		List<Airport> ports = am.getNWSNamedAirports();
		
		System.out.println("Airports whose code end in an \'X\'");
		System.out.println("Expected: JAX, NQX, TIX");
		System.out.println("  Actual: ");
		print(ports);
	}

	/*---------------------------------------------------------------------
	 * Helper methods. 
	 *---------------------------------------------------------------------*/
	
	private static AirportManager getAirportManager(File file) {
		Map<String, Airport> airports = null;
		try {
			airports = AirportLoader.getAirportMap(file);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		AirportManager am = new AirportManager(airports);
		return am;
	}
	
	private static void print(List<Airport> airports) {
		for (Airport a : airports) {
			System.out.println(a);
		}
	}
	
	private static void print(Map<String,Airport> airports) {
		ArrayList<Airport> ports = (ArrayList<Airport>) airports.values();
		print(ports);
	}
	

}
