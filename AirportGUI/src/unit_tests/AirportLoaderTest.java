package unit_tests;

import java.io.File;
import java.util.*;
import models.Airport;
import models.AirportLoader;

/**
 * Contains test method to test the getAirportMap method in the AirportLoader
 * class & these methods have already been written and provided to you.
 * @author dgibson
 *
 */
public class AirportLoaderTest {
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
		testGetAirportMap_Small();
		testGetAirportMap_Medium();
		testGetAirportMap_SameCities();
		testGetAirportMap_All();
	}
	
	public static void testGetAirportMap_Small() {
		System.out.println("-->Test 1: testGetAirportMap_Small");
		System.out.println(String.format("Result should be equivalent to: %s", airportsSmallFileName));
		Map<String,Airport> airports = AirportLoader.getAirportMap(airportsSmallFile);
		print(airports);
	}

	public static void testGetAirportMap_Medium() {
		System.out.println("\n-->Test 2: testGetAirportMap_Medium");
		System.out.println(String.format("Result should be equivalent to: %s", airportsMediumFileName));
		Map<String,Airport> airports = AirportLoader.getAirportMap(airportsMediumFile);
		print(airports);
	}

	public static void testGetAirportMap_SameCities() {
		System.out.println("\n-->Test 3: testGetAirportMap_SameCities");
		System.out.println(String.format("Result should be equivalent to: %s", airportsSameCitiesFileName));
		Map<String,Airport> airports = AirportLoader.getAirportMap(airportsSameCitiesFile);
		print(airports);
	}

	public static void testGetAirportMap_All() {
		System.out.println("\n-->Test 4: testGetAirportMap_All");
		System.out.println(String.format("Result should be equivalent to: %s", airportsAllFileName));
		Map<String,Airport> airports = AirportLoader.getAirportMap(airportsAllFile);
		print(airports);
	}

	private static void print(Map<String,Airport> airports){
		int i=1;
		for( Airport a : airports.values()) {
			String msg = String.format("%4d. %s", i++, a);
			System.out.println(msg);
		}
	}
}
