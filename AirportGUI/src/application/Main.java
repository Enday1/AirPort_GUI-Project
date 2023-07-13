package application;
	
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.stage.Stage;
import javafx.scene.Scene;
//import javafx.scene.control.Button;
//import javafx.scene.control.Label;
//import javafx.scene.control.ListView;
//import javafx.scene.control.TextArea;
//import javafx.scene.control.TextField;
import javafx.scene.control.TabPane;
import javafx.scene.control.Tab;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
//import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
//import java.io.File;
//import models.*;
//import text_files.*;
//import unit_tests.*;
import controllers.AirportManagerController;
import controllers.FlightManagerController;

public class Main extends Application {
	
	//Create file to read from and make map that can bee stored into an AirportManager object.
	AirportManagerController am = new AirportManagerController();
	FlightManagerController fm = new FlightManagerController();
	
	//Tabs for Airport and Flight manager and to hold the content within each tab
	protected TabPane tabPane = new TabPane();
	protected Tab tab1 = new Tab("Airport Manager");
	protected Tab tab2 = new Tab("Flight Manager");
	
	
	@Override
	public void start(Stage primaryStage) {
		//imported the following packages and declared Flight object to 
		//start method so that it may compile.
		//Flight f;
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
		Pane row1 = am.createGetAirports();
		grdPane.add(row1, 0, 0);
		Pane row2 = am.addAirport();
		grdPane.add(row2, 0, 1);
		Pane row3 = am.removeGetAndGetClosestToWithCode();
		grdPane.add(row3, 0, 2, 1, 1);
		Pane row4 = am.geSortedAirport();
		grdPane.add(row4, 0, 4);
		Pane col1Row4 = am.getAirportWithin();
		grdPane.add(col1Row4, 1, 4, 1, 1);
		Pane row5 = am.getMessage();
		grdPane.add(row5, 0, 5);
		Pane col1Row5 = am.getAirportByCity();
		grdPane.add(col1Row5, 1, 5);
		
		//grdPane.setHgap(15); Done in the css file
		//grdPane.setVgap(20);
		grdPane.setPadding(new Insets(10, 20, 20, 10));
		tab1.setClosable(false);
		tab1.setContent(grdPane);
		
		tabPane.getTabs().add(tab1);
		
		//Code to create FlightManager. Completes after tab2 is added to tABpANE;
		Pane p1 = fm.createGetFlightsAndAddFlight();
		Pane p2 = fm.createFlightMessage();
		Pane p3 = fm.createGetRemoveAndFlightCode();
		Pane p4 = fm.createFlightByOrigin();
		Pane p5 = fm.createFlightByOriginAndDate();
		
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
	
	
	public static void main(String[] args) {
		launch(args);
	}
}
