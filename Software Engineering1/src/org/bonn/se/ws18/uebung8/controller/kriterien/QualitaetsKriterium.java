/**
 * 
 */
package org.bonn.se.ws18.uebung8.controller.kriterien;

import org.bonn.se.ws18.uebung8.dtos.UserStoryDTO;

/**
 * @author kpierz2s
 *
 */
public interface QualitaetsKriterium {
	
	public abstract String details(UserStoryDTO dto);
	public abstract String hints();
	public abstract double berechneProzent(UserStoryDTO dto);
	
}
