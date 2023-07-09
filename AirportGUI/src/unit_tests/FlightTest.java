package unit_tests;

import java.time.LocalDate;
import models.Airport;
import models.Flight;

public class FlightTest {
	
	public static void main(String[] args) {
		testFlightConstructor_ValidFlightNumber();
		testFlightConstructor_InvalidFlightNumber_Length();
		testFlightConstructor_InvalidFlightNumber_Digits();
		
		testGetNumber();
		testGetDate();
		testGetOrigin();
		testGetDestination();
		testGetCost();
		testGetDistance();
		
		testToString();
	}
	
	public static void testFlightConstructor_ValidFlightNumber() {
		System.out.println("\n--->testFlightConstructor_ValidFlightNumber");
		try {
			Flight f = createTestFlight();
			System.out.println("Flight Successfully created");
		}
		catch (IllegalArgumentException e) {
			System.out.println("Failed to Create Flight");
		}
		
	}
	
	public static void testFlightConstructor_InvalidFlightNumber_Length() {
		System.out.println("\n--->testFlightConstructor_InvalidFlightNumber_Length");
		try {
			Flight f = createBadTestFlightOne();
			System.out.println("Flight Successfully created");
		}
		catch (IllegalArgumentException e) {
			System.out.println("Invalid Flight Number");
		}
	}
	
	public static void testFlightConstructor_InvalidFlightNumber_Digits() {
		System.out.println("\n--->testFlightConstructor_InvalidFlightNumber_Digits");
		try {
			Flight f = createBadTestFlightTwo();
			System.out.println("Flight Successfully created");
		}
		catch (IllegalArgumentException e) {
			System.out.println("Invalid Flight Number");
		}
	}
	
	
	public static void testGetNumber() {
		System.out.println("\n--->testGetNumber");
		Flight f = createTestFlight();
		System.out.println("Expected: 1234");
		System.out.println("  Actual: " + f.getNumber());
	}
	
	public static void testGetDate() {
		System.out.println("\n--->testGetDate");
		Flight f = createTestFlight();
		LocalDate date = f.getDate();
		System.out.println("Expected: " + LocalDate.now());
		System.out.println("  Actual: " + date);
	}
	
	public static void testGetOrigin() {
		System.out.println("\n--->testGetOrigin");
		Flight f = createTestFlight();
		System.out.println("Expected: Airport Code: VPU");
		System.out.println("  Actual: Airport Code: " + f.getOrigin().getCode() );
	}
	
	public static void testGetDestination() {
		System.out.println("\n--->testGetDestination");
		Flight f = createTestFlight();
		System.out.println("Expected: Airport Code: NYB");
		System.out.println("  Actual: Airport Code: " + f.getDestination().getCode() );
	}
	
	public static void testGetCost() {
		System.out.println("\n--->testGetCost");
		Flight f = createTestFlight();
		System.out.println("Expected: $750.00");
		System.out.printf("  Actual: $%.2f \n", f.getCost() );
	}
	
	public static void testGetDistance() {
		System.out.println("\n--->testGetDistance");
		Flight f = createTestFlight();
		System.out.println("Expected: 3286.46");
		System.out.printf("  Actual: %.2f \n", f.getDistance() );
	}
	
	public static void testToString() {
		System.out.println("\n--->testGetDistance");
		Flight f = createTestFlight();
		System.out.println(f);
	}
	
	
	
	
	
	private static Flight createTestFlight() {
		Airport origin = new Airport("VPU", 34.53525, 62.87465, "Valdosta", "GA");
		Airport dest = new Airport("NYB", 01.23697, 99.32145, "New York City", "NY");
		String number = "1234";
		double cost = 750.00;
		LocalDate date = LocalDate.now();
		Flight f = new Flight(number, date, origin, dest, cost);
		return f;
	}
	
	private static Flight createBadTestFlightOne() {
		Airport origin = new Airport("VPU", 34.53525, 62.87465, "Valdosta", "GA");
		Airport dest = new Airport("NYB", 01.23697, 99.32145, "New York City", "NY");
		String number = "12345";
		double cost = 750.00;
		LocalDate date = LocalDate.now();
		Flight f = new Flight(number, date, origin, dest, cost);
		return f;
	}
	
	private static Flight createBadTestFlightTwo() {
		Airport origin = new Airport("VPU", 34.53525, 62.87465, "Valdosta", "GA");
		Airport dest = new Airport("NYB", 01.23697, 99.32145, "New York City", "NY");
		String number = "abcd";
		double cost = 750.00;
		LocalDate date = LocalDate.now();
		Flight f = new Flight(number, date, origin, dest, cost);
		return f;
	}

}
