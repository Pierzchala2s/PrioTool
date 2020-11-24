/**
 * 
 */
package org.bonn.se.ws18.uebung8.controller.kriterien;

import java.util.ArrayList;
import java.util.List;

import org.bonn.se.ws18.uebung8.dtos.UserStoryDTO;


/**
 * @author kpierz2s
 *
 */
public class QualitaetsPruefer {
	
	private static QualitaetsPruefer instance = null;
	private List <QualitaetsKriterium> qualitaetsKriterium;

	public static synchronized QualitaetsPruefer getInstance() {
	if (instance == null) {
		instance = new QualitaetsPruefer();
	}
		return instance;
	}
	
	private QualitaetsPruefer () { 
		qualitaetsKriterium = new ArrayList<QualitaetsKriterium>();
	}
	
	
	
	public void addKriterium (QualitaetsKriterium kriterium) {
		
		qualitaetsKriterium.add(kriterium);
		
	}

	public void removeKriterium (QualitaetsKriterium kriterium) {
		
		qualitaetsKriterium.remove(kriterium);
		
	}
	
	public double durchfuehrung (UserStoryDTO dto) {
		double bestrafung=0;
		for ( QualitaetsKriterium x : qualitaetsKriterium) {
			 double ergebnis = x.berechneProzent(dto);
			bestrafung += ergebnis;
		}
		return bestrafung;
	}
	
	public String relevanteDetails (UserStoryDTO dto) {
		String details ="";
	
		for ( QualitaetsKriterium x : qualitaetsKriterium) {
			if ( x.berechneProzent(dto) != 0 ) {
				details += x.details(dto)+"\n";
				
			}
			 
		}
		return details.trim();
	}
	
	public String relevanteHints (UserStoryDTO dto) {
		
		String hints = "";
		
		for ( QualitaetsKriterium x : qualitaetsKriterium) {
			
			if ( x.berechneProzent(dto) != 0 ) {
				hints += x.hints()+"\n";
				
				}
			}
		
		return hints.trim();
	}
	
}
