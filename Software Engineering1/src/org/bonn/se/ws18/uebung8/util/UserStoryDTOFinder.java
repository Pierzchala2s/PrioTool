/**
 * 
 */
package org.bonn.se.ws18.uebung8.util;

import java.util.List;

import org.bonn.se.ws18.uebung8.dtos.UserStoryDTO;


/**
 * @author kpierz2s
 *
 */
public class UserStoryDTOFinder {
	
	public static UserStoryDTO getUserStoryDTO(int id , List<UserStoryDTO> liste) {
		
		for ( UserStoryDTO rec : liste) {
			
			if (id == rec.getId() ){
				return rec;
			}
		}
		return null;
	}

}
