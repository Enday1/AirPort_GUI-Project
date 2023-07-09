package models;
import java.util.List;
import java.time.LocalDate;
import java.util.ArrayList;

public class Trip {
	private List<Flight> legs = new ArrayList<>();;
	
	public Trip(List<Flight> legs) {
		this.legs = legs;
	}
	
	public Trip() {
		
	}
	
	public int getNumLegs() {
		return legs.size();
	}
	
	public void addLeg(Flight leg) throws IllegalArgumentException {
		int numLegs = getNumLegs();
		
		//Caused a duplicate to be made in the list
		/*if(numLegs == 0) {
			legs.add(leg);
		}*/
		
		if(numLegs >= 1) {
			if(!doesDestinationOriginMatch(legs.get(numLegs - 1), leg)) {
				String msg = "Destination of previous leg doesn't match origin of next leg";
				throw new IllegalArgumentException(msg);
			} 
		
		
			if(!doesDateMatch(legs.get(numLegs - 1), leg)) {
				String msg = "Date of previous leg doesn't match origin of next leg";
				throw new IllegalArgumentException(msg);
			}
		}
		
		legs.add(leg);
	}
	
	public List<Flight> getLegs() {
		return legs;
	}
	
	public double getDistance() {
		double totalDist = 0.0;
		for(Flight f : legs) {
			totalDist += f.getDistance();
		}
		return totalDist;
	}
	
	public double getCost() {
		double totalCost = 0.0;
		
		for(Flight f : legs) {
			totalCost += f.getCost();
		}
		
		return totalCost;
	}
	
	public Airport getOrigin() {
		Airport a = null;
		if(getNumLegs() > 0) {
			a = legs.get(0).getOrigin();
		}
		return a;
	}
	
	public Airport getDestination() {
		Airport a = null;
		if(getNumLegs() > 0) {
			a = legs.get(getNumLegs() - 1).getDestination();
		}
		return a;
	}
	
	public LocalDate getDate() {
		return legs.get(0).getDate();
	}
	
	@Override
	public String toString() {
		int i = 1;
		String tripDate = String.format("Trip Date: %s\n", getDate());
		String from		= "From: " + legs.get(0).location(getOrigin()) + "\n";
		String to 		= "To: " + legs.get(legs.size() - 1).location(getDestination()) + "\n";
		String distance = String.format("Total Distance: %.0f miles, Cost: $%.2f\n\n", getDistance(), getCost());
		
		String msg = tripDate + from + to + distance;
		
		for(Flight f : legs) {
			msg += "Leg" + i + "\n";
			msg += f.toString() + "\n";
			i++;
		}
		
		return msg;
	}
	
	private boolean doesDestinationOriginMatch(Flight leg1, Flight leg2) {
		return leg1.getDestination().equals(leg2.getOrigin());
	}
	
	private boolean doesDateMatch(Flight leg1, Flight leg2) {
		return leg1.getDate().equals(leg2.getDate());
	}
	
	//Decided not to implement these helper methods
	/*private String locationAirport() {
		
	}
	
	private String getHeader() {
		
	}
	
	private String getLegsMsg() {
		
	}*/
}
