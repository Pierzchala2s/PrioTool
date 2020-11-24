/**
 * 
 */
package org.bonn.se.ws18.uebung8.controller.kriterien;

/**
 * @author kpierz2s
 *
 */
public class KriteriumsManager {
	
	public void befuellung (QualitaetsKriterium kriterium) {
		
		QualitaetsPruefer pruefer = QualitaetsPruefer.getInstance();
		pruefer.addKriterium(kriterium);
		
	}

}
