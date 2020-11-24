/**
 * 
 */
package org.bonn.se.ws18.uebung8.controller.befehle;


import java.util.List;
import org.bonn.se.ws18.uebung8.dtos.UserStoryDTO;
import org.bonn.se.ws18.uebung8.model.Container;
import org.bonn.se.ws18.uebung8.util.UserStoryDTOFinder;

/**
 * @author kpierz2s
 *
 */
public class StatusBefehl implements BefehlInterface{

	
	private int id;
	private String status;
	
	public StatusBefehl(int id, String status) {
		
		this.id = id;
		this.status = status;
		
	}
	
	
	@Override
	public void execute() {
		
		String hilfe = "";
		List <UserStoryDTO> dto = Container.getInstance().getCurrentListOfUserStoriesAsDTO();
		hilfe = UserStoryDTOFinder.getUserStoryDTO(id, dto).getStatus();
	
		Container.getInstance().changeStatus(id, status);
		status = hilfe;
		
	}

	@Override
	public void undo() {
		
		Container.getInstance().changeStatus(id, status);
		
	}

}
