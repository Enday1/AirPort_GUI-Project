package models;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Map;
//import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Scanner;

public class AirportLoader {
	
	//public AirportLoader() {}
	
	public static Map<String, Airport> getAirportMap(File file) {
		Map<String, Airport> airports = new LinkedHashMap<>();
		try {
			Scanner input = new Scanner(file);
			String code = "";
			double lat = 0.00;
			double lon = 0.00;
			String city = "";
			String state = "";
			
			while(input.hasNextLine()) {
				code = input.next();
				lat = input.nextDouble();
				lon = input.nextDouble();
				city = input.next();
				state = input.next();
				
				Airport ap = new Airport(code, lat, lon, city, state);
				
				airports.put(ap.getCode(), ap);
			}
			
			input.close();
			//return airports;
		}
		catch(FileNotFoundException e) {
			System.out.println(e);
		}
		
		//airports.put("Failed", null);
		//return airports;
		return airports;
	}

}
