package it.polito.tdp.babs.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


import it.polito.tdp.babs.db.BabsDAO;

public class Model {
	
	private List<Station> stazioni;
	private BabsDAO dao;
	
	public Model() {
		super();
		dao=new BabsDAO();
	}
	
	public List<Station> getStazioni(){
		if(stazioni==null)
			stazioni=dao.getAllStations();
		
		return stazioni;
		
	}





	public List<Statistics> getStats(LocalDate ld) {
		List<Statistics> stats = new ArrayList<Statistics>();
		
		//ricordati di non iterare su lista del model ma con il metodo !!!!
		for(Station stemp : this.getStazioni()){
			int picks=dao.getPickNumber(stemp,ld);
			int drops=dao.getDropNumber(stemp,ld);
			Statistics st=new Statistics (stemp,picks,drops);
			stats.add(st);
		}
		
		return stats;
	}

	public List<Trip> getTripsForDayPick(LocalDate ld) {
		
		return dao.getTripsForDayPick(ld);
	}
	
	public List<Trip> getTripsForDayDrop(LocalDate ld) {
		
		return dao.getTripsForDayDrop(ld);
	}

}
