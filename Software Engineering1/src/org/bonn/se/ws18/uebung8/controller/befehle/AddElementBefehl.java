/**
 * 
 */
package org.bonn.se.ws18.uebung8.controller.befehle;

import org.bonn.se.ws18.uebung8.exceptions.AkteurListeException;
import org.bonn.se.ws18.uebung8.util.AkteurListe;

/**
 * @author kpierz2s
 *
 */
public class AddElementBefehl implements BefehlInterface{

	private AkteurListe actors;
	
	private String actor;
	
	
	public AddElementBefehl(AkteurListe actors, String actor) {
		this.actors = actors;
		this.actor = actor;
		
	}
	
	
	
	@Override
	public void execute() {
		try {
			actors.addAkteur(actor);
		} catch (AkteurListeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}


	@Override
	public void undo() {
		
		actors.removeAkteur(actor);
		
	}
	
	

}
