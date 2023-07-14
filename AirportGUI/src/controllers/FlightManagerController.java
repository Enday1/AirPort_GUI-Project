package controllers;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
//import java.io.FileNotFoundException;
//import java.time.LocalDate;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.List;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
//import java.util.Scanner;
import models.Airport;
import models.AirportManager;
import models.Flight;
import models.FlightManager;
import unit_tests.FlightManagerTest;

public class FlightManagerController {
	static final String path = "src/text_files/";
	static final String flightsMediumFileName = path + "flightsMeduim.txt";
	static final File flightsMediumFile = new File(flightsMediumFileName);
	
	//Create file to read from and make map that can bee stored into an AirportManager object.
	AirportManagerController amCon = new AirportManagerController();
	AirportManager amFromAMC = amCon.am;
	FlightManager fm = FlightManagerTest.createTestFlightManager();
	
	//Button to populate all flights from the FlightManager
	protected Button getFlights;
		
	//Create flight
	protected Button addFlight;
	TextField txfNum, txfDate, txfOrigin, txfDestination, txfCost;
		
	//Remove flight
	protected Button removeFlight;
			
	//Get flight
	protected Button getFlight;
		
	//Flight code that works with the two previous buttons
	protected TextField flightCode1;
		
	//Getting flight by origin only
	Button getFlightByOrigin;
	TextField flightCode2;
		
	//Getting flight by origin and date
	Button getFlightByOriginAndDate;
	TextField flightCode3, txfDate2;
		
	//Universal display area. Displays all kinds of messages from action events
	//And allows for clearing text
	protected TextArea txaMsg;
	protected Button clearTxt;
	
	public FlightManagerController() {}
	
	public Pane createGetFlightsAndAddFlight() {
		getFlights = new Button("Get Flights");
		getFlights.setOnAction(new PopulateFlightsEventHandler());
		addFlight = new Button("Add Flight");
		addFlight.setOnAction(new AddFlightEventHandler());
		
		Label lbl1 = new Label("Flight#:");
		Label lbl2 = new Label("Date:");
		Label lbl3 = new Label("Origin:");
		Label lbl4 = new Label("Destination:");
		Label lbl5 = new Label("Cost:");
		
		txfNum = new TextField("4 digits: ####");
		txfNum.setOnMouseClicked(new ClearFlightNumEventHandler());
		txfDate = new TextField("format: YYYY-MM-DD");
		txfDate.setOnMouseClicked(new ClearDateEventHandler());
		txfOrigin = new TextField("Airport Code");
		txfOrigin.setOnMouseClicked(new ClearOriginCodeEventHandler());
		txfDestination = new TextField("Airport Code");
		txfDestination.setOnMouseClicked(new ClearDestinationCodeEventHandler());
		txfCost = new TextField("format: ##.##");
		txfCost.setOnMouseClicked(new ClearCostEventHandler());
		
		HBox hGetFlights = new HBox();
		HBox hAddFlight = new HBox();
		HBox hB1 = new HBox();
		HBox hB2 = new HBox();
		HBox hB3 = new HBox();
		HBox hB4 = new HBox();
		HBox hB5 = new HBox();
		
		hGetFlights.getChildren().addAll(getFlights);
		hAddFlight.getChildren().addAll(addFlight);
		hB1.getChildren().addAll(lbl1, txfNum);
		hB2.getChildren().addAll(lbl2, txfDate);
		hB3.getChildren().addAll(lbl3, txfOrigin);
		hB4.getChildren().addAll(lbl4, txfDestination);
		hB5.getChildren().addAll(lbl5, txfCost);
		
		VBox vBox = new VBox();
		vBox.getChildren().addAll(hGetFlights, hAddFlight, hB1, hB2, hB3, hB4, hB5);
		
		return vBox;
	}
	
	public Pane createFlightMessage() {
		VBox vBox = new VBox();
		txaMsg = new TextArea();
		txaMsg.setPrefColumnCount(60);
		txaMsg.setPrefRowCount(20);
		clearTxt = new Button("Reset");
		clearTxt.setOnAction(new clearFlightsEventHandler());
		vBox.getChildren().addAll(txaMsg, clearTxt);
		
		return vBox;
	}
	
	public Pane createGetRemoveAndFlightCode() {
		HBox h = new HBox();
		getFlight = new Button("Get Flight");
		getFlight.setOnAction(new GetFlightEventHandler());
		removeFlight = new Button("Remove Flight");
		removeFlight.setOnAction(new RemoveFlightEventHandler());
		Label lbl = new Label("Code:");
		flightCode1 = new TextField("4 digits: ####");
		flightCode1.setOnMouseClicked(new ClearCode1EventHandler());
		h.getChildren().addAll(getFlight, removeFlight, lbl, flightCode1);
		return h;
	}
	
	public Pane createFlightByOrigin() {
		HBox h = new HBox();
		getFlightByOrigin = new Button("Get Flight By Origin");
		getFlightByOrigin.setOnAction(new GetFlightByOriginEventHandler());
		Label lbl = new Label("Code:");
		flightCode2 = new TextField("Enter 3 digit airport code");
		flightCode2.setOnMouseClicked(new ClearCode2EventHandler());
		h.getChildren().addAll(getFlightByOrigin, lbl, flightCode2);
		return h;
	}
	
	public Pane createFlightByOriginAndDate() {
		HBox h = new HBox();
		getFlightByOriginAndDate = new Button("Get Flight By Origin/Date");
		getFlightByOriginAndDate.setOnAction(new GetFlightByOriginAndDateEventHandler());
		Label lbl = new Label("Code:");
		Label lbl2 = new Label("Date:");
		flightCode3 = new TextField("Enter 3 digit airport code");
		flightCode3.setOnMouseClicked(new ClearCode3EventHandler());
		txfDate2 = new TextField("format: YYYY-MM-DD");
		txfDate2.setOnMouseClicked(new ClearDate2EventHandler());
		h.getChildren().addAll(getFlightByOriginAndDate, lbl, flightCode3, lbl2, txfDate2);
		return h;
	}
	
	
	/*
	 * 
	 	Even Handler Section Begins Here
	 *
	 */
	
	
	private class PopulateFlightsEventHandler implements EventHandler<ActionEvent> {
		@Override
		public void handle(ActionEvent event) {
			List<Flight> flights = fm.getFlights();
			String msg = "";
			int size = flights.size();
			int i = 0;
				
			try {
				String path = "src/text_files/";
				String flightsMediumFileName = path + "flightsMeduim.txt";
				File flightsMediumFile = new File(flightsMediumFileName);
				PrintWriter writer = new PrintWriter(flightsMediumFile);
				
				
				for(Flight f : flights) {
					msg += f.toString();
					writer.println(f.toString());
					
					i++;
					if(i < size) {
						msg += "\n";
					}
				}
				
				writer.close();
			}
			catch(FileNotFoundException e) {
				System.out.println(e);
			}
			
			txaMsg.setText(msg);
		}
	}
	
	private class clearFlightsEventHandler implements EventHandler<ActionEvent> {
		@Override
		public void handle(ActionEvent event) {
			txaMsg.setText("");
			
		}
	} 
	
	private class AddFlightEventHandler implements EventHandler<ActionEvent> {
		@Override
		public void handle(ActionEvent event) {
			Flight flight = null;
			
			try {
				String flightNum = txfNum.getText();
				String date = txfDate.getText();
				String originCode = txfOrigin.getText();
				String destinationCode = txfDestination.getText();
				String costStr = txfCost.getText();
				double cost = Double.parseDouble(costStr);
				
				if(!amFromAMC.containsAirport(originCode) || !amFromAMC.containsAirport(destinationCode)) {
					txaMsg.setText("Invald airport code! Airport does not exist.");
				}
				
				
				
				else if(flightNum.length() == 4) {
					Airport aOrigin = amFromAMC.getAirport(originCode);
					Airport aDestination = amFromAMC.getAirport(destinationCode);
					
					flight = new Flight(flightNum, date, aOrigin, aDestination, cost);
					
					boolean bol = fm.addFlight(flight);
					
					txaMsg.setText("Flight added: " + bol + "\n" + fm.getFlight(flightNum));
					FileWriter fw = new FileWriter(flightsMediumFile, true);
					PrintWriter writer = new PrintWriter(fw);
					
					writer.println(flight.toString());
					
					writer.close();
					
					txfNum.clear();
					txfDate.clear();
					txfOrigin.clear();
					txfDestination.clear();
					txfCost.clear();
				}
				
				else {
					txaMsg.setText("Invalid flight creation!");
				}
				
			}
			catch(NumberFormatException e) {
				//e.printStackTrace();
				System.out.println(e);
				System.out.println("Please enter a valid price!");
				txaMsg.clear();
			}
			catch(FileNotFoundException e) {
				System.out.println(e);
				System.out.println("Could not locate file!");
			}
			catch(IOException e) {
				System.out.println(e);
			}
			finally {
				if(txaMsg.getText().length() == 0) {
					txaMsg.setText("Please enter a valid price!");
				}
			}
		}
	}
	
	private class GetFlightEventHandler implements EventHandler<ActionEvent> {
		@Override
		public void handle(ActionEvent event) {
			String flightCode = flightCode1.getText();
			Flight f = fm.getFlight(flightCode);
			
			if(f != null) {
				txaMsg.setText(f.toString());
				flightCode1.clear();
			}
			
			else {
				txaMsg.setText("Invalid flight code!");
				flightCode1.clear();
			}
		}
	}
	
	private class RemoveFlightEventHandler implements EventHandler<ActionEvent> {
		@Override
		public void handle(ActionEvent event) {
			String flightCode = flightCode1.getText();
			Flight f = fm.removeFlight(flightCode);
			
			if(f != null) {
				txaMsg.setText("Flight removed:\n" + f.toString());
				flightCode1.clear();
			}
			
			else {
				txaMsg.setText("Flight code entered does not exist!");
				flightCode1.clear();
			}
		}
	}
	
	private class GetFlightByOriginEventHandler implements EventHandler<ActionEvent> {
		public void handle(ActionEvent event) {
			String originCode = flightCode2.getText();
			String msg = "";
			int i = 0;
			
			if(originCode.length() == 3) {
				List<Flight> flights = fm.getFlightsByOrigin(originCode);
				int size = flights.size();
				
				for(Flight flight : flights) {
					msg += flight.toString();
					
					i++;
					
					if(i <= size - 1) {
						msg += "\n";
					}
				}
				
				txaMsg.setText(msg);
				flightCode2.clear();
				
				if(txaMsg.getText().length() == 0) {
					txaMsg.setText("There are no flights with origin code entered.");
				}
			}
					
			else {
				txaMsg.setText("Invalid airport origin code!");
				flightCode2.clear();
			}
			
		}
	}
	
	private class GetFlightByOriginAndDateEventHandler implements EventHandler<ActionEvent> {
		public void handle(ActionEvent event) {
			String originCode = flightCode3.getText();
			String date = txfDate2.getText();
			String msg = "";
			int i = 0;
			
			if(originCode.length() == 3 && date.length() == 10) {
				List<Flight> flights = fm.getFlightsByOrigin(originCode, date);
				int size = flights.size();
				
				for(Flight flight : flights) {
					msg += flight.toString();
					
					i++;
					
					if(i <= size - 1) {
						msg += "\n";
					}
				}
				
				txaMsg.setText(msg);
				flightCode3.clear();
				txfDate2.clear();
				
				if(txaMsg.getText().length() == 0) {
					txaMsg.setText("There are no flights with origin code entered and/or date entered.");
				}	
				
			}
			
			else {
				txaMsg.setText("Invalid airport origin code and/or date!");
				flightCode3.clear();
				txfDate2.clear();
			}
		}
	}
		
	
	private class ClearFlightNumEventHandler implements EventHandler<MouseEvent> {
		
		public void handle(MouseEvent event) {
			txfNum.clear();
		}
	}
	
	private class ClearDateEventHandler implements EventHandler<MouseEvent> {
		
		public void handle(MouseEvent event) {
			txfDate.clear();
		}
	}
	
	private class ClearOriginCodeEventHandler implements EventHandler<MouseEvent> {
		
		public void handle(MouseEvent event) {
			txfOrigin.clear();
		}
	}
	
	private class ClearDestinationCodeEventHandler implements EventHandler<MouseEvent> {
		
		public void handle(MouseEvent event) {
			txfDestination.clear();
		}
	}
	
	private class ClearCostEventHandler implements EventHandler<MouseEvent> {
		
		public void handle(MouseEvent event) {
			txfCost.clear();
		}
	}
	
	private class ClearCode1EventHandler implements EventHandler<MouseEvent> {
		
		public void handle(MouseEvent event) {
			flightCode1.clear();
		}
	}
	
	private class ClearCode2EventHandler implements EventHandler<MouseEvent> {
		
		public void handle(MouseEvent event) {
			flightCode2.clear();
		}
	}
	
	private class ClearCode3EventHandler implements EventHandler<MouseEvent> {
		
		public void handle(MouseEvent event) {
			flightCode3.clear();
		}
	}
	
	private class ClearDate2EventHandler implements EventHandler<MouseEvent> {
		
		public void handle(MouseEvent event) {
			txfDate2.clear();
		}
	}
	
}
