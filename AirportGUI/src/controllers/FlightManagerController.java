package controllers;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import models.AirportManager;
import models.FlightManager;

public class FlightManagerController {
	//Create file to read from and make map that can bee stored into an AirportManager object.
	AirportManagerController amCon = new AirportManagerController();
	AirportManager amFromAMC = amCon.am;
	FlightManager fm = new FlightManager(amFromAMC);
	
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
	protected Button clearTxt2;
	
	public FlightManagerController() {}
	
	public Pane createGetFlightsAndAddFlight() {
		getFlights = new Button("Get Flights");
		addFlight = new Button("Add Flight");
		
		Label lbl1 = new Label("Flight#:");
		Label lbl2 = new Label("Date:");
		Label lbl3 = new Label("Origin:");
		Label lbl4 = new Label("Destination:");
		Label lbl5 = new Label("Cost:");
		
		txfNum = new TextField();
		txfDate = new TextField();
		txfOrigin = new TextField();
		txfDestination = new TextField();
		txfCost = new TextField();
		
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
		clearTxt2 = new Button("Reset");
		vBox.getChildren().addAll(txaMsg, clearTxt2);
		
		return vBox;
	}
	
	public Pane createGetRemoveAndFlightCode() {
		HBox h = new HBox();
		getFlight = new Button("Get Flight");
		removeFlight = new Button("Remove Flight");
		Label lbl = new Label("Code:");
		flightCode1 = new TextField();
		h.getChildren().addAll(getFlight, removeFlight, lbl, flightCode1);
		return h;
	}
	
	public Pane createFlightByOrigin() {
		HBox h = new HBox();
		getFlightByOrigin = new Button("Get Flight By Origin");
		Label lbl = new Label("Code:");
		flightCode2 = new TextField();
		h.getChildren().addAll(getFlightByOrigin, lbl, flightCode2);
		return h;
	}
	
	public Pane createFlightByOriginAndDate() {
		HBox h = new HBox();
		getFlightByOriginAndDate = new Button("Get Flight By Origin/Date");
		Label lbl = new Label("Code:");
		Label lbl2 = new Label("Date:");
		flightCode3 = new TextField();
		txfDate2 = new TextField();
		h.getChildren().addAll(getFlightByOriginAndDate, lbl, flightCode3, lbl2, txfDate2);
		return h;
	}
	
	
	/*
	 * 
	 	Even Handler Section Begins Here
	 *
	 */
	
	
	
}
