package org.bonn.se.ws18.uebung8.views;
import java.util.List;

import org.bonn.se.ws18.uebung8.dtos.UserStoryDTO;

public interface IDialog {
	
	public void display( List<UserStoryDTO> list );

}
