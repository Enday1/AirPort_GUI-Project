package controllers;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
//import javafx.scene.control.Tab;
//import javafx.scene.control.TabPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import java.util.List;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import models.Airport;
import models.AirportLoader;
import models.AirportManager;

public class AirportManagerController {
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
	
	//Create file to read from and make map that can bee stored into an AirportManager object.
	protected AirportManager am = new AirportManager(AirportLoader.getAirportMap(airportsMediumFile));
	
	//button to populate all the airports from the AirportManger
	protected Button getAirports;
		
	//Create Airport
	protected Button addAirport;
	protected TextField txfCode1, txfLat, txfLon, txfCity, txfState;
		
	//Remove Airport
	protected Button removeAirport;
		
	//Get Airport
	protected Button getAirport;
		
	//Get Airport closest to
	protected Button getAirportClosestTo;
		
	//Enter Airport code (Works with the previous 3 buttons mentioned)
	protected TextField airportCode;
		
	//Sort Airports by city
	protected Button sortByCity;
		
	//Sort Airports by state
	protected Button sortByState;
		
	//Get Airport within a certain distance
	protected Button getAirportWithin;
	protected TextField txfCode2, txfDistance;
		
	//Get Airports by city 
	protected ListView<String> lvwCity = new ListView<>();
		
	//Universal display area. Displays all kinds of messages from action events
	//And allows for clearing text
	protected TextArea txaMessage;
	protected Button clearTxt;
	
	public AirportManagerController() {}	
	
	public Pane createGetAirports() {
		HBox hBox = new HBox();
		getAirports = new Button("Generate Airports");
		getAirports.setOnAction(new PopulateAirportEventHandler());
		hBox.getChildren().add(getAirports);
		return hBox;
	}
	
	public Pane addAirport() {
		HBox hBox = new HBox();
		addAirport = new Button("Add Airport");
		addAirport.setOnAction(new AddAirportEventHandler());
		//Label lblCode = new Label("Code");
		txfCode1 = new TextField("Type code");
		txfCode1.setOnMouseClicked(new ClearCodeEventHandler());
		//Label lblLat = new Label("Latitude");
		txfLat = new TextField("Type lat dist.");
		txfLat.setOnMouseClicked(new ClearLatEventHandler());
		//Label lblLon = new Label("Longitude");
		txfLon = new TextField("Type lon dist.");
		txfLon.setOnMouseClicked(new ClearLonEventHandler());
		//Label lblCity = new Label("City");
		txfCity = new TextField("Type city");
		txfCity.setOnMouseClicked(new ClearCityEventHandler());
		//Label lblState = new Label("State");
		txfState = new TextField("Type state");
		txfState.setOnMouseClicked(new ClearStateEventHandler());
		
		//this hbox.getChildren below would use the other constructor object creation code that I commented out
		// in Airport.java the Protected method
		//Update: using it again
		
		hBox.getChildren().addAll(addAirport, /*lblCode,*/ txfCode1, /*lblLat,*/ txfLat, /*lblLon,*/ txfLon, /*lblCity,*/ txfCity, /*lblState,*/ txfState);
		
		//hBox.getChildren().addAll(addAirport, txfCode1);
		return hBox;
	}
	
	public Pane removeGetAndGetClosestToWithCode() {
		HBox hBox = new HBox();
		removeAirport = new Button("Remove Airport");
		removeAirport.setOnAction(new RemoveAirportEventHandler());
		getAirport = new Button("Get Airport");
		getAirport.setOnAction(new GetAirportEventHandler());
		getAirportClosestTo = new Button("Get Airport Closest To");
		getAirportClosestTo.setOnAction(new GetAirportClosestToEventHandler());
		airportCode = new TextField("Enter Airport Code");
		hBox.getChildren().addAll(removeAirport, getAirport, getAirportClosestTo, airportCode);
		return hBox;
	}
	
	public Pane geSortedAirport() {
		HBox hBox = new HBox();
		sortByCity = new Button("Sort By City");
		sortByCity.setOnAction(new SortAirportsByCityEventHandler());
		sortByState = new Button("Sort By State");
		sortByState.setOnAction(new SortAirportsByStateEventHandler());
		hBox.getChildren().addAll(sortByCity, sortByState);
		return hBox;
	}
	
	public Pane getAirportWithin() {
		HBox hBox = new HBox();
		getAirportWithin = new Button("Get Airport Within Distance");
		getAirportWithin.setOnAction(new GetAirportWitinDistanceEventHandler());
		//Label lblCode = new Label("Airport Code");
		txfCode2 = new TextField("Add code");
		//Label lblDist = new Label("Distance"); lblDist.
		txfDistance = new TextField("Add distance");
		hBox.getChildren().addAll(getAirportWithin, /*lblCode*,*/ txfCode2, /*lblDist,*/ txfDistance);
		return hBox;
	}
	
	public Pane getMessage() {
		VBox vBox = new VBox();
		txaMessage = new TextArea();
		txaMessage.setPrefColumnCount(6);
		txaMessage.setPrefRowCount(15);
		clearTxt = new Button("Reset");
		clearTxt.setOnAction(new clearAirportsEventHandler());
		vBox.getChildren().addAll(txaMessage, clearTxt);
		return vBox;
	}
	
	public Pane getAirportByCity() {
		VBox vBox = new VBox();
		Label lbl = new Label("Filter Airports by City:");
		lvwCity.setPrefHeight(300);
		lvwCity.setPrefWidth(50);
		lvwCity.setOnMouseClicked(new GetListViewItemEventHandler());
		vBox.getChildren().addAll(lbl, lvwCity);
		return vBox;
	}
	
	
	
	/*----------------------------------------------------------------------------
	 * Event handlers
	 ----------------------------------------------------------------------------
	 */
	
	
	private class PopulateAirportEventHandler implements EventHandler<ActionEvent> {
		@Override
		public void handle(ActionEvent event) {
			LinkedHashSet<Airport> airports = new LinkedHashSet<>(am.getAirports());
			List<String> strCity = new ArrayList<>();
			
			//used to get no duplicate cities from the getCity() method of the airport objects
			//ListView wont print out the city multiple time if it already exist
			List<Airport> listViewAirports = new ArrayList<>();
			
			String msg = "";
			int size = am.getNumAirports();
			int i = 0;
			
			for(String airportCode : am.getAirportCodes()) {
				msg += am.getAirport(airportCode).toString();
				
				i++;
				
				if(i <= size - 1) {
					msg += "\n";
				}
			}
			
			txaMessage.setText(msg);
			
			//Used to not get duplicates in ListView as stated above
			for(Airport a : airports) {
				if(strCity.contains(a.getCity())) {
					continue;
				}
				strCity.add(a.getCity());
				listViewAirports.add(a);
			}
			
			if(txaMessage.getText().length() != 0) {
				if(lvwCity.getItems().isEmpty()) {
					for(Airport a : listViewAirports) {
					
						lvwCity.getItems().add(a.getCity());
					}
				}
				
				
			}
		}
	}
	
	//Need to get this to work when item is selected in ListView to sort on only those items.
	private class SortAirportsByCityEventHandler implements EventHandler<ActionEvent> {
		@Override
		public void handle(ActionEvent event) {
			String msg = "";
			int size = am.getNumAirports();
			int i = 0;
			List<Airport> airports = am.getAirportsSortedBy("City");
			
			if(txaMessage.getText().length() != 0) {
				for(Airport a : airports) {
					msg += a.toString();
					
					i++;
					
					if(i <= size - 1) {
						msg += "\n";
					}
				}
			}
			
			txaMessage.setText(msg);
		}
	}
	
	//Need to get this to work when item is selected in ListView to sort on only those items.
	private class SortAirportsByStateEventHandler implements EventHandler<ActionEvent> {
		@Override
		public void handle(ActionEvent event) {
			String msg = "";
			int size = am.getNumAirports();
			int i = 0;
			List<Airport> airports = am.getAirportsSortedBy("State");
			
			if(txaMessage.getText().length() != 0) {
				for(Airport a : airports) {
					msg += a.toString();
					
					i++;
					
					if(i <= size - 1) {
						msg += "\n";
					}
					
				}
			}
			txaMessage.setText(msg);
		}
	}
	
	private class clearAirportsEventHandler implements EventHandler<ActionEvent> {
		@Override
		public void handle(ActionEvent event) {
			txaMessage.setText("");
			lvwCity.getItems().clear();
		}
	}
	
	private class GetListViewItemEventHandler implements EventHandler<MouseEvent> {
	
		public void handle(MouseEvent event) {
			LinkedHashSet<Airport> airports = new LinkedHashSet<>(am.getAirports());
			String msg = "";
			int size = am.getNumAirports();
			int i = 0;
			
			
			
			try {
				String cityFilterFileName = path + "filterAirportsByCity-" + lvwCity.getSelectionModel().getSelectedItem();
				File cityFilterFile = new File(cityFilterFileName);
				
				PrintWriter writer = new PrintWriter(cityFilterFile);
				
				for(Airport a : airports) {
					if(lvwCity.getSelectionModel().getSelectedItem().equals(a.getCity())) {
						msg += a.toString();
						
						writer.println(a);
						
						i++;
						
						if(i <= size - 1) {
							msg += "\n";
						}
					}
				}
			
				txaMessage.setText(msg);
				
				writer.close();
			}
			
			catch(FileNotFoundException e) {
				e.printStackTrace();
			}
			
		}
	}
	
	//Need to fix to where when txaMessage is empty, the button that uses this event should not run
	private class GetAirportWitinDistanceEventHandler implements EventHandler<ActionEvent> {
		@Override
		public void handle(ActionEvent event) {
			String airportCode = txfCode2.getText();
			String distanceStr = "";
			double distance = 0;
			String msg = "";
			int size = am.getNumAirports();
			int i = 0;
			
			try {
				distanceStr = txfDistance.getText();
				distance = Double.parseDouble(distanceStr);
				
				List<Airport> airportsWithinDistance = am.getAirportsWithin(airportCode, distance);
			
				if(airportCode.length() == 3 && am.containsAirport(airportCode)) {
					for(Airport a : airportsWithinDistance) {
						msg += a.toString();
						
						i++;
						
						if(i <= size - 1) {
							msg += "\n";
						}
					}
					txaMessage.setText(msg);
					txfCode2.clear();
					txfDistance.clear();
					
					if(txaMessage.getText().length() == 0) {
						txaMessage.setText("No airport found within radius. Try a greater distance.");
					}
				}
				
				else {
					txaMessage.setText("Please enter a valid Airport Code!\n");
				}
				
			}
			catch(NumberFormatException e) {
				System.out.println(e);
				System.out.println("Please enter a valid number!");
				txaMessage.clear();
			}
			
			finally {
				if(txaMessage.getText().length() == 0) {
					txaMessage.setText("Please enter a valid number!");
				}
				
			}
			
			
			
		}
	}
	
	//Need to fix to where when txaMessage is empty, the button that uses this event should not run
	//More so dealing with data persistence so if map is cleared or message area is empty then this
	//method does not run.
	private class GetAirportEventHandler implements EventHandler<ActionEvent> {
		@Override
		public void handle(ActionEvent event) {
				String code = airportCode.getText();
				Airport ap = am.getAirport(code);
				
				if(code.length() == 3 && ap != null) {
					txaMessage.setText(ap.toString());
				}
				
				else {
					txaMessage.setText("Airport code used to search for Airport not found in system.");
				}
		}
	}
	
	//Needs Work uses an airport object with randomly created distances to use for latitude and longitude.
	//See AirportManager for more info. specifically the getAirportClosestTo2(code) method.
	private class GetAirportClosestToEventHandler implements EventHandler<ActionEvent> {
		public void handle(ActionEvent event) {
			String code = airportCode.getText();
			Airport ap = am.getAirportClosestTo2(code);
			
			if(code.length() == 3 && ap != null) {
				txaMessage.setText(ap.toString());
				airportCode.clear();
			}
		}
	}
	
	private class RemoveAirportEventHandler implements EventHandler<ActionEvent> {
		public void handle(ActionEvent event) {
			String code = airportCode.getText();
			
			if(am.containsAirport(code)) {
				Airport removedAirport = am.removeAirport(code);
				txaMessage.setText("Airport removed:\n" + removedAirport.toString());
				airportCode.clear();
					
			}
			
			else {
				txaMessage.setText("Not a valid Airport Code! Please Try again.");
			}
			
		}
	}
	
	private class AddAirportEventHandler implements EventHandler<ActionEvent> {
		public void handle(ActionEvent event) {
			//txfCode1, txfLat, txfLon, txfCity, txfState;
			String airportCode = txfCode1.getText();
			String latitude = txfLat.getText();
			String longitude = txfLon.getText();
			double lat = 0.00;
			double lon = 0.00;
			String city = txfCity.getText();
			String state = txfState.getText();
			
			
			try {
				FileWriter fw = new FileWriter(airportsMediumFile, true);
				
				//Appending text files.
				PrintWriter writer = new PrintWriter(fw);
				
				lat = Double.parseDouble(latitude);
				lon = Double.parseDouble(longitude);
				
				if(airportCode.length() == 3) {
					Airport airport = new Airport(airportCode, lat, lon, city, state);
					boolean bol = am.addAirport(airport);
					
					txaMessage.setText("Airport added: " + bol + "\n" + am.getAirport(airportCode));
					
					writer.print("\n" + airportCode + "\t" + latitude + "\t" + longitude + "\t" + city + "\t" + state);
					writer.close();
					
					txfCode1.clear();
					txfLat.clear();
					txfLon.clear();
					txfCity.clear();
					txfState.clear();
				}
				
				/*else {
					txaMessage.setText("Invalid airport code length. Must be of length 3.");
				}*/
			}
			catch(NumberFormatException e) {
				e.printStackTrace();
			}
			catch(IOException e) {
				System.out.println(e);
				//System.out.println("Please enter valid distance(s).");
				//txaMessage.clear();
			}
			finally {
				if(!am.containsAirport(airportCode)) {
					txaMessage.setText("Invalid Airport object created! Try again.");
					txfCode1.clear();
					txfLat.clear();
					txfLon.clear();
					txfCity.clear();
					txfState.clear();
				}
			}
			
		}
	}
	
	private class ClearCodeEventHandler implements EventHandler<MouseEvent> {
		
		public void handle(MouseEvent event) {
			txfCode1.clear();
		}
	}
	
	private class ClearLatEventHandler implements EventHandler<MouseEvent> {
		
		public void handle(MouseEvent event) {
			txfLat.clear();
		}
	}
	
	private class ClearLonEventHandler implements EventHandler<MouseEvent> {
		
		public void handle(MouseEvent event) {
			txfLon.clear();
		}
	}
	
	private class ClearCityEventHandler implements EventHandler<MouseEvent> {
		
		public void handle(MouseEvent event) {
			txfCity.clear();
		}
	}
	
	private class ClearStateEventHandler implements EventHandler<MouseEvent> {
		
		public void handle(MouseEvent event) {
			txfState.clear();
		}
	}
}

