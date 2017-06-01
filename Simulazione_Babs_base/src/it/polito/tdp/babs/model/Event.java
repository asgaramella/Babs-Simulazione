package it.polito.tdp.babs.model;

import java.time.LocalDateTime;

public class Event implements Comparable<Event>{
	
	public enum EventType{PICK, DROP};
	
	private Trip trip;
	private LocalDateTime ldt;
	private EventType type;
	
	
	
	public Event(Trip trip, LocalDateTime ldt, EventType type) {
		super();
		this.trip = trip;
		this.ldt = ldt;
		this.type = type;
	}
	
	





	/**
	 * @return the trip
	 */
	public Trip getTrip() {
		return trip;
	}







	/**
	 * @param trip the trip to set
	 */
	public void setTrip(Trip trip) {
		this.trip = trip;
	}







	/**
	 * @return the ldt
	 */
	public LocalDateTime getLdt() {
		return ldt;
	}







	/**
	 * @param ldt the ldt to set
	 */
	public void setLdt(LocalDateTime ldt) {
		this.ldt = ldt;
	}







	/**
	 * @return the type
	 */
	public EventType getType() {
		return type;
	}







	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((ldt == null) ? 0 : ldt.hashCode());
		result = prime * result + ((trip == null) ? 0 : trip.hashCode());
		result = prime * result + ((type == null) ? 0 : type.hashCode());
		return result;
	}







	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Event other = (Event) obj;
		if (ldt == null) {
			if (other.ldt != null)
				return false;
		} else if (!ldt.equals(other.ldt))
			return false;
		if (trip == null) {
			if (other.trip != null)
				return false;
		} else if (!trip.equals(other.trip))
			return false;
		if (type != other.type)
			return false;
		return true;
	}







	/**
	 * @param type the type to set
	 */
	public void setType(EventType type) {
		this.type = type;
	}







	@Override
	public int compareTo(Event other) {
		if(this.ldt.isAfter(other.getLdt()))
			return 1;
		else
		{
			if(this.ldt.isBefore(other.getLdt()))
				return -1;
			else
				return 0;
			
		}
	}

}
