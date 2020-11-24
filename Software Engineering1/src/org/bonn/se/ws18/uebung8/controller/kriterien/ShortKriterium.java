/**
 * 
 */
package org.bonn.se.ws18.uebung8.controller.kriterien;

import org.bonn.se.ws18.uebung8.dtos.UserStoryDTO;

/**
 * @author kpierz2s
 *
 */
public class ShortKriterium implements QualitaetsKriterium{

	
	private static final String SHORTHINT = "Beschreiben sie die Userstory kürzer" ;

	private double bestrafung = 0.2;
	
	
	public double getBestrafung() {
		return bestrafung;
	}


	public void setBestrafung(double bestrafung) {
		this.bestrafung = bestrafung;
	}


	
	@Override
	public String details(UserStoryDTO dto) {
		
		String detail = "";
		
		if(dto.getTitel().length()>=140) {
			int laenge = dto.getTitel().length();
			laenge = laenge - 140;
			detail = "Verkürzen sie die UserStory um " + laenge  + " Zeichen! (-" + bestrafung*100 +"%)";
			return detail;
		}
		
		return null;
	}

	
	@Override
	public String hints() {
	
		return SHORTHINT;
	}

	
	@Override
	public double berechneProzent(UserStoryDTO dto) {
		if ( dto.getTitel().length()>=140) {
			return bestrafung;
		}
		
		return 0;
	}

}
