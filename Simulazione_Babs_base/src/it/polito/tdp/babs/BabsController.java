package it.polito.tdp.babs;

import java.net.URL;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.ResourceBundle;

import it.polito.tdp.babs.model.Model;
import it.polito.tdp.babs.model.SimulationResult;
import it.polito.tdp.babs.model.Simulator;
import it.polito.tdp.babs.model.Statistics;
import it.polito.tdp.babs.model.Trip;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Slider;
import javafx.scene.control.TextArea;

public class BabsController {

	private Model model;

	public void setModel(Model model) {
		this.model = model;
	}

	@FXML
	private ResourceBundle resources;

	@FXML
	private URL location;

	@FXML
	private DatePicker pickData;

	@FXML
	private Slider sliderK;

	@FXML
	private TextArea txtResult;

	@FXML
	void doContaTrip(ActionEvent event) {
		
		txtResult.clear();
		
		LocalDate ld= pickData.getValue();
		if(ld==null){
			txtResult.setText("Selezionare una data !");
			return; //serve per terminare l'applicazione
		}
		
		List<Statistics> stats=model.getStats(ld);
		
		Collections.sort(stats);
		
		for(Statistics stemp : stats){
			if(stemp.getPick()==0)
				txtResult.appendText(String.format("WARNING!! Stazione %s con 0 pick \n", stemp.getStazione().getName()));
			else
			txtResult.appendText(String.format("%s %d %d\n", stemp.getStazione().getName(), stemp.getPick(), stemp.getDrop()));
		}
	}

	@FXML
	void doSimula(ActionEvent event) {
		txtResult.clear();
		
		LocalDate ld= pickData.getValue();
		if(ld==null || ld.getDayOfWeek()==DayOfWeek.SATURDAY || ld.getDayOfWeek()==DayOfWeek.SUNDAY ){
			txtResult.setText("Selezionare un giorno feriale !");
			return; //serve per terminare l'applicazione
		}
		
		Double k= (double)sliderK.getValue()/100.0;
		
		List<Trip> tripsPick= model.getTripsForDayPick(ld);
	    List<Trip> tripsDrop= model.getTripsForDayDrop(ld);
		
		Simulator sim= new Simulator();
		sim.loadPick(tripsPick);
		
		
		sim.loadDrop(tripsDrop);
		sim.loadStations( k, model.getStazioni());
		sim.run();
		SimulationResult sr=sim.collectResults();
		
		txtResult.appendText("Nr di prese mancate "+sr.getNumberPickMiss()+"\n");
		txtResult.appendText("Nr di ritorni mancati "+sr.getNumberDropMiss()+"\n");
		
		
	}

	@FXML
	void initialize() {
		assert pickData != null : "fx:id=\"pickData\" was not injected: check your FXML file 'Babs.fxml'.";
		assert sliderK != null : "fx:id=\"sliderK\" was not injected: check your FXML file 'Babs.fxml'.";
		assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'Babs.fxml'.";

		pickData.setValue(LocalDate.of(2013, 9, 1));
	}
}
