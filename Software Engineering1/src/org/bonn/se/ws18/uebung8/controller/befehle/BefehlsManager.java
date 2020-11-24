/**
 * 
 */
package org.bonn.se.ws18.uebung8.controller.befehle;


import java.util.Stack;

/**
 * @author kpierz2s
 *
 */
public class BefehlsManager {
	
	private static BefehlsManager instance = null;
	
	public static synchronized BefehlsManager getInstance() {
		if (instance == null) {
			instance = new BefehlsManager();
		}
			return instance;
		}
		
		private BefehlsManager () { 
			this.letzteBefehle = new Stack<BefehlInterface>();
		}
	
	
	private Stack<BefehlInterface> letzteBefehle;
	private BefehlInterface addElementBefehl; 
	private BefehlInterface statusBefehl;
    private BefehlInterface enterBefehl;

    
    public void doAddElementBefehl(){
    
    	addElementBefehl.execute();
    	
    	letzteBefehle.push(addElementBefehl);
    }
    public void doStatusBefehl(){
    	statusBefehl.execute();
    	letzteBefehle.push(statusBefehl);
    }
    public void doEnterBefehl(){
    	enterBefehl.execute();
    	letzteBefehle.push(enterBefehl);
    }
    public void undo(){
    	letzteBefehle.pop().undo();
    }
    
    public void setAddElementBefehl(BefehlInterface addElementBefehl) {
        this.addElementBefehl = addElementBefehl;
    }
    public void setStatusBefehl(BefehlInterface statusBefehl) {
		this.statusBefehl = statusBefehl;
	}
	public void setEnterBefehl(BefehlInterface enterBefehl) {
		this.enterBefehl = enterBefehl;
	}
    
    
}
