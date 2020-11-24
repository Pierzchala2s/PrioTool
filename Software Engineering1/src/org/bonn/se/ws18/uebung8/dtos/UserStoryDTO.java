package org.bonn.se.ws18.uebung8.dtos;



public class UserStoryDTO implements Comparable<UserStoryDTO> {
	
	private String titel;
	
	private double prio;
	
	private int id;
	
	private String status;
	
	private boolean hatMehrwert;
	
	public String toString() {
		
		return "Titel: " + getTitel() + "\n" + "  Prio: " + getPrio();
	}
	
	public double getPrio() {
		return prio;
	}

	public void setPrio(double prio) {
		this.prio = prio;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public boolean isHatMehrwert() {
		return hatMehrwert;
	}

	public void setHatMehrwert(boolean hatMehrwert) {
		this.hatMehrwert = hatMehrwert;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getTitel() {
		return this.titel;
	}

	public void setTitel(String titel) {
		this.titel = titel;
		
	}
	
	/*
	 * Methode zum Vergleich zweier UserStories.
	 * Vergleich ist implementiert auf Basis des Vergleichs
	 * von zwei Prio-Werten. 
	 */
	public int compareTo(UserStoryDTO input) {
		if ( input.getPrio() == this.getPrio() ) {
			return 0;
		}
		
		if ( input.getPrio() > this.getPrio() ) {
			return 1;
		}
		else return -1;
	}
	

}
