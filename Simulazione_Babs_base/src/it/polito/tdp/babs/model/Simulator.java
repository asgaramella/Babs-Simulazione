package it.polito.tdp.babs.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

import it.polito.tdp.babs.model.Event.EventType;


public class Simulator {
	
	//stato del mondo
	private Map<Integer,Integer> posti;
	private Map<Integer,Integer> postiMax;
	
	
	
	//lista eventi
	private PriorityQueue<Event> queue;
	private SimulationResult simResult;

	
	public Simulator() {
		super();
		queue=new PriorityQueue<Event>();
		simResult=new SimulationResult();
		posti=new HashMap();
		postiMax=new HashMap();
	}

	public void run() {
		while (!queue.isEmpty()) {
			Event e = queue.poll();
			

			switch (e.getType()) {
			case PICK:
				processEventPick(e);
				break;
			case DROP:
				processEventDrop(e);
				break;
			}
			
		}
		
	}

	

	private void processEventDrop(Event e) {
		int idStation=e.getTrip().getEndStationID();
		int biciIn= posti.get(idStation);
		if(biciIn == postiMax.get(idStation) )
			simResult.setNumberDropMiss(simResult.getNumberDropMiss()+1);	
		
		else
			posti.put(idStation, biciIn+1);
		
		
	}

	private void processEventPick(Event e) {
		int idStation=e.getTrip().getStartStationID();
		int biciIn= posti.get(idStation);
		if(biciIn !=0)
		
			posti.put(idStation, biciIn -1);
			
		
		else
		{
			simResult.setNumberPickMiss(simResult.getNumberPickMiss()+1);
			queue.remove(new Event(e.getTrip(),e.getTrip().getEndDate(),EventType.DROP));
		}
	}

	public SimulationResult collectResults() {
		return simResult;
		
	}

	public void loadPick(List<Trip> tripsPick) {
		for(Trip ttemp:tripsPick){
			queue.add(new Event(ttemp,ttemp.getStartDate(),EventType.PICK));
		}
		
	}

	public void loadDrop(List<Trip> tripsDrop) {
		for(Trip ttemp:tripsDrop){
			queue.add(new Event(ttemp,ttemp.getEndDate(),EventType.DROP));
		}
		
	}

	public void loadStations(Double k, List<Station> list) {
		
		for(Station stemp: list ){
			int p=(int) (k*stemp.getDockCount());
			posti.put(stemp.getStationID(),p );
			postiMax.put(stemp.getStationID(),p );
			
		}
		
	}
	
	
	

}
