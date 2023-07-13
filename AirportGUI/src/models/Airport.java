package models;

public class Airport {
	private String code;
	private String city;
	private String state;
	private double latitude;
	private double longitude;
	
	public Airport(String code, double latitude, double longitude, String city, String state) {
		this.code = code;
		this.city = city;
		this.state = state;
		this.latitude = latitude;
		this.longitude = longitude;
	}
	
	protected Airport(String code) {
		//this(code, 0.00, 0.00, "city", "state"); not what was done by professor 
		this.code = code;
	}
	
	public boolean equals(Object o) {
		if(o instanceof Airport) {
			Airport ap = (Airport)o;
			return this.code.equals(ap.code);
		}
		
		return false;
	}
	
	public String getCode() {
		return code;
	}

	public String getCity() {
		return city;
	}

	public String getState() {
		return state;
	}

	public double getLatitude() {
		return latitude;
	}

	public double getLongitude() {
		return longitude;
	}
	
	public String toString() {
		String msg = String.format("code =  %s, latitude = %.2f, longitude = %.2f, city = %s, state = %s",
					 			   code, latitude, longitude, city, state);
		return msg;
		
	}

	
}
