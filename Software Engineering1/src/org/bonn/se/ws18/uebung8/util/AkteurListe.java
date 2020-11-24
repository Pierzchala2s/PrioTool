/**
 * 
 */
package org.bonn.se.ws18.uebung8.util;

import java.util.ArrayList;
import java.util.List;


import org.bonn.se.ws18.uebung8.exceptions.AkteurListeException;



/**
 * @author kpierz2s
 *
 */
public class AkteurListe {
	
	private List<String> liste = null;
	
	// Statische Klassen-Variable, um die Referenz
	// auf das einzigeAkteurListe Objekt abzuspeichern
	
	private static AkteurListe instance = null;
	
	public static synchronized AkteurListe getInstance() {
	if (instance == null) {
		instance = new AkteurListe();
	}
		return instance;
	}

	private AkteurListe () {
		liste = new ArrayList<String>();
	}
	
	public void addAkteur ( String akteur ) throws AkteurListeException {		
		if ( contains(akteur) == true ) {
			AkteurListeException ex = new AkteurListeException("Akteur bereits vorhanden!");
			throw ex;
		}
		liste.add(akteur);
		
	} 
	
	public boolean contains(String akteur) {
	
		return liste.contains(akteur);
	}
	
	public boolean removeAkteur ( String akteur) {
		return liste.remove(akteur);
		
	}
	
	public String toString () {
		String akteure = "";
		for (String akteur : liste) {
			akteure += akteur+"\n";
		}
		return akteure.trim();
	}
	
	
}
