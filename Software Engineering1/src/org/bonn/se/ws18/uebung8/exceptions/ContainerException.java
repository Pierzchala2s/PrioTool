package org.bonn.se.ws18.uebung8.exceptions;

public class ContainerException extends Exception {
	
	private String modus;
	private Integer id;
	
	public ContainerException( String s ) {
		super ( s );
	}

	@Override
	public void printStackTrace() {
		// TODO Auto-generated method stub
		System.out.println("Das Person-Objekt mit der ID " + this.id + " ist bereits vorhanden!"); 
	} 

	public void addID(Integer id) {
		this.id = id;
	}


}
