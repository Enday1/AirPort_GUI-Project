package models;

import java.util.Comparator;

public class AirportCityComparator implements Comparator<Airport> {
	public int compare(Airport a1, Airport a2) {
		String city1 = a1.getCity();
		String city2 = a2.getCity();
		
		int diff = city1.compareTo(city2);
		
		return diff;
	}
}
