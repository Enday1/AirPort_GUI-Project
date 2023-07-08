package unit_tests;

import models.Airport;

public class AirportTest {

	public static void main(String[] args) {
		testGetCity();
		testGetCode();
		testGetLatitude();
		testGetLongitude();
		testGetState();
		testToString();
		testEquals_True();
		testEquals_False();
	}
	
	public static void testGetCity() {
		System.out.println("\n--> testGetCity");
		Airport a1 = createTestAirportOne();
		System.out.println("Expected: Valdosta");
		String city = a1.getCity();
		System.out.println("  Actual: " + city);
	}
	
	public static void testGetCode() {
		System.out.println("\n--> testGetCode");
		Airport a1 = createTestAirportOne();
		System.out.println("Expected: VPU");
		String code = a1.getCode();
		System.out.println("  Actual: " + code);
	}
	
	public static void testGetLatitude() {
		System.out.println("\n--> testGetLatitude");
		Airport a1 = createTestAirportOne();
		System.out.println("Expected: 62.87465");
		double lat = a1.getLatitude();
		System.out.println("  Actual: " + lat);
	}
	
	public static void testGetLongitude() {
		System.out.println("\n--> testGetLongitude");
		Airport a1 = createTestAirportOne();
		System.out.println("Expected: 34.53525");
		double lon = a1.getLongitude();
		System.out.println("  Actual: " + lon);
	}
	
	public static void testGetState() {
		System.out.println("\n--> testGetState");
		Airport a1 = createTestAirportOne();
		System.out.println("Expected: GA");
		String state = a1.getState();
		System.out.println("  Actual: " + state);
	}
	
	public static void testToString() {
		System.out.println("\n--> testToString");
		Airport a1 = createTestAirportOne();
		System.out.println(a1);
	}
	
	public static void testEquals_True() {
		System.out.println("\n--> testEquals_True");
		Airport a1 = createTestAirportOne();
		Airport a2 = createTestAirportTwo();
		System.out.println("Expected: true");
		boolean tf = a1.equals(a2);
		System.out.println("  Actual: " + tf);
	}
	
	public static void testEquals_False() {
		System.out.println("\n--> testEquals_False");
		Airport a1 = createTestAirportOne();
		Airport a3 = createTestAirportThree();
		System.out.println("Expected: false");
		boolean tf = a1.equals(a3);
		System.out.println("  Actual: " + tf);
	}
	
	
	
	
	private static Airport createTestAirportOne() {
		Airport a1 = new Airport("VPU", 62.87465, 34.53525, "Valdosta", "GA");
		return a1;
	}
	
	private static Airport createTestAirportTwo() {
		Airport a2 = new Airport("VPU", 98.21033, 72.58423, "Valdosta", "GA");
		return a2;
	}
	
	private static Airport createTestAirportThree() {
		Airport a3 = new Airport("NYB", 99.32145, 01.23697, "New York City", "NY");
		return a3;
	}

}