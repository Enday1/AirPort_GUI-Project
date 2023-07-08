package models;

import java.util.Comparator;

public class AirportStateComparator implements Comparator<Airport> {
	public int compare(Airport a1, Airport a2) {
		String state1 = a1.getState();
		String state2 = a2.getState();
		
		int diff = state1.compareTo(state2);
		
		return diff;
	}
		
}
