/**
 * 
 */
package org.bonn.se.ws18.uebung8.controller.kriterien;

import org.bonn.se.ws18.uebung8.dtos.UserStoryDTO;
import org.bonn.se.ws18.uebung8.util.AkteurListe;

/**
 * @author kpierz2s
 *
 */
public class AkteurKriterium implements QualitaetsKriterium{

	private static final String AKTEURHINT = "Registrieren sie einen neuen Akteur!";

	private double bestrafung = 0.2;
	
	
	public double getBestrafung() {
		return bestrafung;
	}


	public void setBestrafung(double bestrafung) {
		this.bestrafung = bestrafung;
	}


	
	
	@Override
	public String details(UserStoryDTO dto) {
		
	
		String splitted [] = dto.getTitel().split(" ");
		
		return "Akteur (\"" + splitted[1] + "\") ist nicht bekannt (-" + bestrafung*100 +"%)";
		
		
	}

	
	@Override
	public String hints() {
		
		return AKTEURHINT;
	}

	
	@Override
	public double berechneProzent(UserStoryDTO dto) {
		String splitted [] = dto.getTitel().split(" ");
		if( splitted.length < 2 ) {
			System.out.println("UserStory ist zu kurz!");
			return 1;
		}
		if ( AkteurListe.getInstance().contains(splitted[1])) {
			return 0;
		}
		
		return bestrafung;
	}

}
