package org.bonn.se.ws18.uebung8.controller;


import org.bonn.se.ws18.uebung8.exceptions.AkteurListeException;
import org.bonn.se.ws18.uebung8.util.AkteurListe;
import org.bonn.se.ws18.uebung8.controller.kriterien.*;
/**
 * Start-Klasse der Anwendung.
 * c / o Sascha Alda, 2018
 * @author saschaalda
 *
 */

public class Main {
	
	public static void main (String[] args)  {
		
		
		KriteriumsManager strategy = new KriteriumsManager();
		strategy.befuellung(new MehrwertKriterium());
		strategy.befuellung(new AkteurKriterium());
		strategy.befuellung(new ShortKriterium());
		
		try {
			AkteurListe.getInstance().addAkteur("Student");
			AkteurListe.getInstance().addAkteur("Professor");
		} catch (AkteurListeException e) {
			
			e.printStackTrace();
		}
	
		EingabeDialog dialog = new EingabeDialog();
		dialog.startEingabe();
	}

}
