package lab7;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.Scene;
import java.text.DecimalFormat;
import java.util.Scanner;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.layout.*;
import javafx.event.EventHandler;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.VBox;
import javafx.scene.control.TextField;

public class TravelExpenses extends Application{
	
	// Create label objects to display total cost
	private Label _displayTotalExpenses, _displayTotalReimburses, _displayExcessAmount,
	_displayTotalAmountSaved;
	
	// Create our TextField Objects
	private TextField _daysTxt,_airfareTxt, _carRentalTxt, 
	_milesDriverTxt, _parkingFeesTxt,
	_taxiChargesTxt, _regFeesTxt, _lodgingChargesTxt;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		launch();
	}
	
	@Override
	public void start(Stage primaryStage) 
	{
		// Instantiate our Label objects
		Label _daysLbl = new Label("Input the number of days on the trip. (If any)");
		Label _airfareLbl = new Label("Input the amount of airfare. (If any)");
		Label _carRentalLbl = new Label("Input the amount of car rental fees. (If any)");
		Label _milesDriverLbl = new Label("Input the amount of miles driven."
				+ "(If a private vehicle was used)");
		Label _parkingFeesLbl = new Label("Input the amount for the parking fees.(If any)");
		Label _taxiChargesLbl = new Label("Input the amount of taxi charges. (If any)");
		Label _regFeesLbl = new Label("Input the amount for conference or seminar "
				+ "registration fees. (If any)");
		Label _lodgingChargesLbl = new Label("Input the lodging charges amount per night");
		
		// Instantiate our Label objects to display
		// our totals
		_displayTotalExpenses = new Label();
		_displayTotalReimburses = new Label();
		_displayExcessAmount = new Label();
		_displayTotalAmountSaved = new Label();
		
		// Instantiate our TextField objects
		_daysTxt = new TextField("0");
		_airfareTxt = new TextField("0");
		_carRentalTxt = new TextField("0");
		_milesDriverTxt = new TextField("0");
		_parkingFeesTxt = new TextField("0");
		_taxiChargesTxt = new TextField("0");
		_regFeesTxt = new TextField("0");
		_lodgingChargesTxt = new TextField("0");
		
		// Create our Button 
		Button btn = new Button("CLICK");
		
		// Set our Button to have an Event Handler
		btn.setOnAction(new CalculateExpenses());
		
		// Create our HBox objects to hold our controls
		HBox hBox1 = new HBox(10, _daysLbl, _daysTxt);
		HBox hBox2 = new HBox(10, _airfareLbl, _airfareTxt);
		HBox hBox3 = new HBox(10, _carRentalLbl, _carRentalTxt);
		HBox hBox4 = new HBox(10,  _milesDriverLbl, _milesDriverTxt);
		HBox hBox5 = new HBox(10, _parkingFeesLbl, _parkingFeesTxt, _taxiChargesLbl, _taxiChargesTxt);
		HBox hBox6 = new HBox(10, _taxiChargesLbl, _taxiChargesTxt);
		HBox hBox7 = new HBox(10, _regFeesLbl, _regFeesTxt);
		HBox hBox8 = new HBox(10, _lodgingChargesLbl, _lodgingChargesTxt);
		
		// Create our VBox to hold our containers
		VBox vBox = new VBox(hBox1, hBox2, hBox3, hBox4, hBox5, 
				hBox6, hBox7, hBox8, btn, _displayTotalExpenses,
				_displayTotalReimburses, _displayExcessAmount, _displayTotalAmountSaved);
		
		// Set the alignment for Center position
		vBox.setAlignment(Pos.CENTER);
		
		// Set the padding to 10
		vBox.setPadding(new Insets(10));
		
		// Create a scene 
		Scene scene = new Scene(vBox);
		
		// Add our Scene to our Stage
		primaryStage.setScene(scene);
		
		// Add a title to our Stage
		primaryStage.setTitle("Travel Expenses");
		
		// Show the window
		primaryStage.show();
		
	}
	
	/**
	 * Creates an Event Handler to Calculate 
	 * Total Cost for User's Traveling Expenses.
	 */
	class CalculateExpenses implements EventHandler<ActionEvent>
	{
		@Override
		public void handle(ActionEvent event) 
		{
			// Gets days entered as an int
			int days = Integer.parseInt(_daysTxt.getText());
			// Gets airfare amount as a double
			double airfareAmount = Double.parseDouble(_airfareTxt.getText());
			// Gets car rental fees as a double
			double carRentalFees = Double.parseDouble(_carRentalTxt.getText());
			// Gets miles driven as a double
			double milesDriven = Double.parseDouble(_milesDriverTxt.getText());
			// Gets parking fees as a double
			double parkingFees = Double.parseDouble(_parkingFeesTxt.getText());
			// Gets taxi charges as a double
			double taxiCharges = Double.parseDouble(_taxiChargesTxt.getText());
			// Gets conference fees as a double
			double conferenceFees = Double.parseDouble(_regFeesTxt.getText());
			// Gets lodging fees as a double
			double lodgingFees = Double.parseDouble(_lodgingChargesTxt.getText());
			
			//Calculate the number of Day charges
			int totalDays = (47 * days);
			// Calculate the total parking fees for the trip
			double totalParking = (20.00 * days);
			// Calculate the total taxi charges for the trip
			double totalTaxiCharges = (40.00 * days);
			// Calculate the total lodging charges per day
			double totalLodgingCharges = (195.00 * days);
			// Calculate the total charges if a vehicle was used
			double totalVehicleCharges = (.42 * milesDriven);
			
			// Calculates total expenses the business reimburses
			double reimbursesExpenses = (totalDays + totalParking + totalTaxiCharges + 
					totalLodgingCharges + totalVehicleCharges);
			
			// Calculate total expenses the business has spent
			double totalExpenses = (airfareAmount + carRentalFees + 
					parkingFees + taxiCharges + conferenceFees + lodgingFees);
			double excess=0, savedAmount=0;
			
			DecimalFormat formatter = new DecimalFormat("$#,###.00");
			
			_displayTotalExpenses.setText("Total Expenses " + formatter.format(totalExpenses));
			_displayTotalReimburses.setText("Total Reimbursment Fees " + formatter.format(reimbursesExpenses));
			
			_displayTotalAmountSaved.setText("You have saved " + formatter.format(savedAmount));
			_displayExcessAmount.setText("Must pay excessed amount" + formatter.format(excess));
			
			if(reimbursesExpenses > totalExpenses) 
			{
				savedAmount = (totalExpenses - reimbursesExpenses);
				_displayTotalAmountSaved.setText("You have saved " + formatter.format(savedAmount));
				
			}
			else if(totalExpenses < reimbursesExpenses) 
			{
				excess = (reimbursesExpenses - totalExpenses);
				_displayExcessAmount.setText("Must pay excessed amount " + formatter.format(excess));
			}
			
			if(reimbursesExpenses < totalExpenses) 
			{
				excess = (reimbursesExpenses - totalExpenses);
				_displayExcessAmount.setText("Must pay excessed amount " + formatter.format(excess));
			}
			else if(totalExpenses > reimbursesExpenses) 
			{
				savedAmount = (totalExpenses - reimbursesExpenses);
				_displayTotalAmountSaved.setText("You have saved " + formatter.format(savedAmount));
			}
		}
	}

}
