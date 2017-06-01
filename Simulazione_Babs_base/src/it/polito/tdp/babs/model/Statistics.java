package it.polito.tdp.babs.model;

public class Statistics implements Comparable<Statistics> {
	
	private Station stazione;
	private int pick;
	private int drop;
	/**
	 * @return the stazione
	 */
	public Station getStazione() {
		return stazione;
	}
	/**
	 * @param stazione the stazione to set
	 */
	public void setStazione(Station stazione) {
		this.stazione = stazione;
	}
	/**
	 * @return the pick
	 */
	public int getPick() {
		return pick;
	}
	/**
	 * @param pick the pick to set
	 */
	public void setPick(int pick) {
		this.pick = pick;
	}
	/**
	 * @return the drop
	 */
	public int getDrop() {
		return drop;
	}
	/**
	 * @param drop the drop to set
	 */
	public void setDrop(int drop) {
		this.drop = drop;
	}
	/**
	 * @param stazione
	 * @param pick
	 * @param drop
	 */
	public Statistics(Station stazione, int pick, int drop) {
		super();
		this.stazione = stazione;
		this.pick = pick;
		this.drop = drop;
	}
	@Override
	public int compareTo(Statistics other) {
		//occhio per confronto tra i double esiste il compare !!
		return Double.compare(this.getStazione().getLat(), other.getStazione().getLat());
	}
	
	
	

}
