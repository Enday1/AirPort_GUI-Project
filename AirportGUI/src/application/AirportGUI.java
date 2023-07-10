package application;
	
import java.io.File;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TabPane;
import javafx.scene.control.Tab;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import models.*;
//import text_files.*;
//import unit_tests.*;


public class AirportGUI extends Application {
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
	AirportManager am = new AirportManager(AirportLoader.getAirportMap(airportsMediumFile));
	
	//Tabs for Airport and Flight manager and to hold the content within each tab
	protected TabPane tabPane = new TabPane();
	protected Tab tab1 = new Tab("Airport Manager");
	protected Tab tab2 = new Tab("Flight Manager");
	
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
	
	@Override
	public void start(Stage primaryStage) {
		//imported the following packages and declared Flight object to 
		//start method so that it may compile.
		Flight f;
		try {
			Pane root = buildPane();
			Scene scene = new Scene(root,1400,650);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setTitle("Airport GUI for FMS");
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	private Pane buildPane() {
		//Code to create the AirportManager. Completes after tab1 is added to tabPane
		GridPane grdPane = new GridPane();
		Pane row1 = createGetAirports();
		grdPane.add(row1, 0, 0);
		Pane row2 = addAirport();
		grdPane.add(row2, 0, 1);
		Pane row3 = removeGetAndGetClosestToWithCode();
		grdPane.add(row3, 0, 2, 1, 1);
		Pane row4 = geSortedAirport();
		grdPane.add(row4, 0, 4);
		Pane col1Row4 = getAirportWithin();
		grdPane.add(col1Row4, 1, 4, 1, 1);
		Pane row5 = getMessage();
		grdPane.add(row5, 0, 5);
		Pane col1Row5 = getAirportByCity();
		grdPane.add(col1Row5, 1, 5);
		
		//grdPane.setHgap(15); Done in the css file
		//grdPane.setVgap(20);
		grdPane.setPadding(new Insets(10, 20, 20, 10));
		tab1.setClosable(false);
		tab1.setContent(grdPane);
		
		tabPane.getTabs().add(tab1);
		
		//Code to create FlightManager. Completes after tab2 is added to tABpANE;
		Pane p1 = createGetFlightsAndAddFlight();
		Pane p2 = createFlightMessage();
		Pane p3 = createGetRemoveAndFlightCode();
		Pane p4 = createFlightByOrigin();
		Pane p5 = createFlightByOriginAndDate();
		
		GridPane grdPane2 = new GridPane();
		grdPane2.add(p1, 0, 0);
		grdPane2.add(p2, 1, 0);
		
		GridPane grdPane3 = new GridPane();
		grdPane3.add(p3, 0, 1);
		grdPane3.add(p4, 0, 2);
		grdPane3.add(p5, 0, 3);
		
		//grdPane3.setHgap(15); Done in the css file
		//grdPane3.setVgap(20);
		grdPane2.setPadding(new Insets(10, 20, 20, 10));
		grdPane3.setPadding(new Insets(10, 20, 20, 10));
		
		VBox vBox1 = new VBox();
		vBox1.getChildren().addAll(grdPane2, grdPane3);
		tab2.setClosable(false);
		tab2.setContent(vBox1);
		
		tabPane.getTabs().add(tab2);
		
		// Build root container which encompases the tabs
		VBox root = new VBox(tabPane);
		//root.getChildren().addAll(topRow, );
		return root;
	}
	
	private Pane createGetAirports() {
		HBox hBox = new HBox();
		getAirports = new Button("Generate Airports");
		hBox.getChildren().add(getAirports);
		return hBox;
	}
	
	private Pane addAirport() {
		HBox hBox = new HBox();
		addAirport = new Button("Add Airport");
		//Label lblCode = new Label("Code");
		txfCode1 = new TextField("Type code");
		//Label lblLat = new Label("Latitude");
		txfLat = new TextField("Type lat dist.");
		//Label lblLon = new Label("Longitude");
		txfLon = new TextField("Type lon dist.");
		//Label lblCity = new Label("City");
		txfCity = new TextField("Type city");
		//Label lblState = new Label("State");
		txfState = new TextField("Type state");
		hBox.getChildren().addAll(addAirport, /*lblCode,*/ txfCode1, /*lblLat,*/ txfLat, /*lblLon,*/ txfLon, /*lblCity,*/ txfCity, /*lblState,*/ txfState);
		return hBox;
	}
	
	private Pane removeGetAndGetClosestToWithCode() {
		HBox hBox = new HBox();
		removeAirport = new Button("Remove Airport");
		getAirport = new Button("Get Airport");
		getAirportClosestTo = new Button("Get Airport Closest To");
		airportCode = new TextField("Enter Airport Code");
		hBox.getChildren().addAll(removeAirport, getAirport, getAirportClosestTo, airportCode);
		return hBox;
	}
	
	private Pane geSortedAirport() {
		HBox hBox = new HBox();
		sortByCity = new Button("Sort By City");
		sortByState = new Button("Sort By State");
		hBox.getChildren().addAll(sortByCity, sortByState);
		return hBox;
	}
	
	private Pane getAirportWithin() {
		HBox hBox = new HBox();
		getAirportWithin = new Button("Get Airport Within Distance");
		//Label lblCode = new Label("Airport Code");
		txfCode2 = new TextField("Add code");
		//Label lblDist = new Label("Distance"); lblDist.
		txfDistance = new TextField("Add distance");
		hBox.getChildren().addAll(getAirportWithin, /*lblCode*,*/ txfCode2, /*lblDist,*/ txfDistance);
		return hBox;
	}
	
	private Pane getMessage() {
		VBox vBox = new VBox();
		txaMessage = new TextArea();
		txaMessage.setPrefColumnCount(6);
		txaMessage.setPrefRowCount(15);
		clearTxt = new Button("Reset");
		vBox.getChildren().addAll(txaMessage, clearTxt);
		return vBox;
	}
	
	private Pane getAirportByCity() {
		VBox vBox = new VBox();
		Label lbl = new Label("Filter Airports by City:");
		lvwCity.setPrefHeight(300);
		lvwCity.setPrefWidth(50);
		vBox.getChildren().addAll(lbl, lvwCity);
		return vBox;
	}
	
	private Pane createGetFlightsAndAddFlight() {
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
	
	private Pane createFlightMessage() {
		VBox vBox = new VBox();
		txaMsg = new TextArea();
		txaMsg.setPrefColumnCount(60);
		txaMsg.setPrefRowCount(20);
		clearTxt2 = new Button("Reset");
		vBox.getChildren().addAll(txaMsg, clearTxt2);
		
		return vBox;
	}
	
	private Pane createGetRemoveAndFlightCode() {
		HBox h = new HBox();
		getFlight = new Button("Get Flight");
		removeFlight = new Button("Remove Flight");
		Label lbl = new Label("Code:");
		flightCode1 = new TextField();
		h.getChildren().addAll(getFlight, removeFlight, lbl, flightCode1);
		return h;
	}
	
	private Pane createFlightByOrigin() {
		HBox h = new HBox();
		getFlightByOrigin = new Button("Get Flight By Origin");
		Label lbl = new Label("Code:");
		flightCode2 = new TextField();
		h.getChildren().addAll(getFlightByOrigin, lbl, flightCode2);
		return h;
	}
	
	private Pane createFlightByOriginAndDate() {
		HBox h = new HBox();
		getFlightByOriginAndDate = new Button("Get Flight By Origin/Date");
		Label lbl = new Label("Code:");
		Label lbl2 = new Label("Date:");
		flightCode3 = new TextField();
		txfDate2 = new TextField();
		h.getChildren().addAll(getFlightByOriginAndDate, lbl, flightCode3, lbl2, txfDate2);
		return h;
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
