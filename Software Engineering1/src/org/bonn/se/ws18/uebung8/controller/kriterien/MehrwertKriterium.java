/**
 * 
 */
package org.bonn.se.ws18.uebung8.controller.kriterien;

import org.bonn.se.ws18.uebung8.dtos.UserStoryDTO;

/**
 * @author kpierz2s
 *
 */
public class MehrwertKriterium implements QualitaetsKriterium{

	private static final String MEHRWERTHINT = "Fügen sie einen schriftlichen Mehrwert hinzu!";

	private double bestrafung = 0.3;
	
	
	public double getBestrafung() {
		return bestrafung;
	}


	public void setBestrafung(double bestrafung) {
		this.bestrafung = bestrafung;
	}


	
	@Override
	public String details(UserStoryDTO dto) {
		
		return "Kein schriftlicher Mehrwert zu erkennen! (-" + bestrafung*100 +"%)";
	}

	
	@Override
	public String hints() {
		
		return MEHRWERTHINT;
	}

	
	@Override
	public double berechneProzent(UserStoryDTO dto) {
		
		if (dto.isHatMehrwert()) {
			return 0;
		}
		
		return bestrafung;
	}

}
