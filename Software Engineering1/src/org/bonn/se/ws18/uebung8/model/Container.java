package org.bonn.se.ws18.uebung8.model;


import java.util.ArrayList;
import java.util.List;

import org.bonn.se.ws18.uebung8.dtos.UserStoryDTO;
import org.bonn.se.ws18.uebung8.entities.UserStory;
import org.bonn.se.ws18.uebung8.exceptions.ContainerException;
import org.bonn.se.ws18.uebung8.util.UserStoryStatus;



/**
 * Klasse zum Management sowie zur Eingabe unnd Ausgabe von User Stories.
 * (Laut MVC Pattern: Controller)
 * Die Anwendung wird über dies Klasse auch gestartet (main-Methode hier vorhanden)
 * 
 */

public class Container {
	
	// Interne ArrayList zur Abspeicherung der Objekte
	private List<UserStory> liste = null; 
	
	// Statische Klassen-Variable, um die Referenz
	// auf das einzige Container-Objekt abzuspeichern
	private static Container instance = new Container();
	
	public static synchronized Container getInstance() {
	if (instance == null) {
		instance = new Container();
	}
		return instance;
	}
	
	/*
	 * Ueberschreiben des Konstruktors. 
	 * 
	 */
	private Container(){
		liste = new ArrayList<UserStory>();
	}
	

	/**
	 * Methode zum Einfuegen eines Story
	 * @param r
	 * @throws ContainerException
	 */
	public void addUserStory ( UserStory r ) throws ContainerException {		
		if ( contains(r) == true ) {
			ContainerException ex = new ContainerException("ID bereits vorhanden!");
			throw ex;
		}
		liste.add(r);
		
	} 
	
	public void removeUserStory (int id) {
		liste.remove(getUserStory(id));
	}
	

	private boolean contains(UserStory r) {
		int ID = r.getId();
		for ( UserStory rec : liste) {
			if ( rec.getId() == ID ) {
				return true;
			}
		}
		return false;
	}
	
	public void changeStatus (int id, String status) {
		if ( status.equals(UserStoryStatus.DONE)) getUserStory(id).setStatus(status);; 
		if ( status.equals(UserStoryStatus.INPROGRESS))getUserStory(id).setStatus(status);;
		if ( status.equals(UserStoryStatus.TODO))getUserStory(id).setStatus(status);;

		
		System.out.println("Die UserStory mit der ID " + getUserStory(id).getId() + " wurde auf den Status " + getUserStory(id).getStatus() +" gesetzt");
		
	}
	
	
	public int getAnzahl(){
		return liste.size();
	}
	

	public List<UserStory> getCurrentList() {
		return this.liste;
	}
	
	/*
	 * Methode zur Auslieferung der UserStory-Objekte.
	 * Es werden keine Referenzen auf die Entity selber übergeben,
	 * sondern nur DTO
	 * 
	 */
	public List<UserStoryDTO> getCurrentListOfUserStoriesAsDTO() {
		List<UserStoryDTO> listeDTO = new ArrayList<UserStoryDTO>();
		
		for ( UserStory us : this.liste ) {
			UserStoryDTO dto = new UserStoryDTO();
			dto.setTitel( us.getTitel() );
			dto.setPrio( us.getPrio());
			dto.setStatus(us.getStatus());
			dto.setId(us.getId());
			dto.setHatMehrwert(us.getMehrwert()>0);
			listeDTO.add(dto);
		}
		
		return listeDTO;
	}
	

	/*
	 * Interne Methode zur Ermittlung einer Person
	 * 
	 */
	private UserStory getUserStory(int id) {
		for ( UserStory rec : liste) {
			if (id == rec.getId() ){
				return rec;
			}
		}
		return null;
	}

	public void load() {
		this.liste = PersistenceManager.load();
		
	}

	public void store() throws ContainerException {
		PersistenceManager.store( this.liste );
		
	}



}
