/**
 * 
 */
package org.bonn.se.ws18.uebung8.controller.befehle;

import org.bonn.se.ws18.uebung8.entities.UserStory;
import org.bonn.se.ws18.uebung8.exceptions.ContainerException;
import org.bonn.se.ws18.uebung8.model.Container;

/**
 * @author kpierz2s
 *
 */
public class EnterBefehl implements BefehlInterface{
	
	private int id;
	private String titel;
	private int aufwand;
	private int strafe;
	private int mehrwert;
	private int risiko;
	

	public EnterBefehl(int id, String titel, int aufwand, int strafe, int mehrwert, int risiko) {
		
		this.id = id;
		this.titel = titel;
		this.aufwand = aufwand;
		this.strafe = strafe;
		this.mehrwert = mehrwert;
		this.risiko = risiko;
		
	}
	
	@Override
	public void execute() {
		
		double prio = ((mehrwert + strafe) / (aufwand + risiko));
		System.out.println("User Story mit ID: " + id + " hat die Prio: " + prio);
		
		// Neues Objekt vom Typ UserStory
		UserStory us = new UserStory(id, titel, mehrwert, strafe, aufwand, risiko, prio);
		
		// Hinzufügen
		try {
			Container.getInstance().addUserStory(us);
		} catch (ContainerException e) {
			// TODO Auto-generated catch block
			System.out.println("Fehler beim Abspeichern der User Story!");
		}
		
	}

	
	@Override
	public void undo() {
	
		Container.getInstance().removeUserStory(id);
		
	}

}
