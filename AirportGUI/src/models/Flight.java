package models;

import java.time.LocalDate;

public class Flight {
	private Airport origin;
	private Airport destination;
	private double cost;
	private double distance;
	private LocalDate date;
	private String number;
	
	public Flight(String number, LocalDate date, Airport origin, Airport destination, double cost) throws IllegalArgumentException {
		if(!isFlightNumValid(number)) {
			throw new IllegalArgumentException("Flight number is invalid, must be 4 digits");
		}
		this.number = number;
		this.date = date;
		this.origin = origin;
		this.destination = destination;
		this.cost = cost;
		this.distance = getDistanceBetween();
	}
	
	public Flight(String number, String date, Airport origin, Airport destination, double cost) {
		this(number, LocalDate.parse(date), origin, destination, cost);
	}

	public Airport getOrigin() {
		return origin;
	}

	public Airport getDestination() {
		return destination;
	}

	public double getCost() {
		return cost;
	}

	public double getDistance() {
		return distance;
	}

	public LocalDate getDate() {
		return date;
	}

	public String getNumber() {
		return number;
	}
	
	private double getDistanceBetween() {
		double lat1 = origin.getLatitude();
		double lat2 = destination.getLatitude();
		double lon1 = origin.getLongitude();
		double lon2 = destination.getLongitude();
		
		return DistanceCalculator.getDistance(lat1, lon1, lat2, lon2);
	}
	
	private boolean isFlightNumValid(String number) {
		if(number == null || number.length() != 4) {
			return false;
		}
		
		for(int i = 0; i < number.length(); i++) {
			if(!Character.isDigit(number.charAt(i))) {
				return false;
			}
		}
		
		return true;
	}
	
	String location(Airport a) {
		String location = String.format("%s-%s, %s", a.getCode(), a.getCity(), a.getState());
		return location;
	}
	
	public String flightString() {
		String str = String.format("%s-%s-%s-%s",number, date, origin.getCode(), destination.getCode());
		return str;
	}
	
	@Override
	public String toString() {
		//double dist = getDistanceBetween();
		//cast this to take out the decimals
		//int castOfDist = (int)dist;
		String flight = String.format("Flight: %s, Date: %s\n", number, date);
		String from = "From: " + location(origin) + "\n";
		String to = "To: " + location(destination) + "\n";
		String distance = String.format("Distance: %.0f miles, Cost: $%,.2f\n", getDistanceBetween(), cost);
		
		String msg = flight + from + to + distance;
		return msg;
	}
	
	
}
