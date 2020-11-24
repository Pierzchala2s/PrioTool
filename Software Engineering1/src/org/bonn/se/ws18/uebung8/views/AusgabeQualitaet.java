/**
 * 
 */
package org.bonn.se.ws18.uebung8.views;

import org.bonn.se.ws18.uebung8.util.Benotung;

/**
 * @author kpierz2s
 *
 */
public class AusgabeQualitaet {
	
	public void ausgabeStrafe ( int id, double strafe) {
		
		System.out.println("Die UserStory mit der ID: " + id + " hat folgende Qualität: " 
		+ (1-strafe)*100 + "% (" + Benotung.notenCheck(strafe) + ")");
	}

	
	public void ausgabeStrafeDurchschnitt(double durchschnitt , int anzahl) {
		System.out.println("Ihre " + anzahl + " UserStorys haben durchschnittlich folgende Qualität: " 
				+ (1-durchschnitt)*100 + "% (" + Benotung.notenCheck(durchschnitt) + ")");
	}
	
	public void ausgabeDetails (String details) {
		
		System.out.println("\nDetails: \n");
		if ( details.equals("")) {
			System.out.println("Alles Ok");
		}
		else {
			
			System.out.println(details);
		}
		
	}
	
	public void ausgabeHints ( String hints ) {
		
		System.out.println("\nHints: \n");
		
		if ( hints.equals("")) {
			System.out.println("Keine Hints");
		}
		else {
			
			System.out.println(hints);
		}
	}

}
