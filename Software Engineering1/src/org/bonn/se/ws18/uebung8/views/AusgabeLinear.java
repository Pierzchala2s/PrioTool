package org.bonn.se.ws18.uebung8.views;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import org.bonn.se.ws18.uebung8.dtos.UserStoryDTO;

public class AusgabeLinear implements IDialog {
	
	//private List<UserStoryDTO> liste = null;	
	AtomicInteger zaehler = new AtomicInteger (0);


	@Override
	public void display(List<UserStoryDTO> list) {
		// Falls Liste leer kurze Ausgabe und raus.
		if ( list.size() == 0 ) {
			System.out.println("Keine User Stories vorhanden");
			return;
		}
		

		
		// Ausgabe ueber einen Iterartor (Ausgabe kann auch optimiert werden ;-)):
		Iterator<UserStoryDTO> i = list.iterator();
		while (  i.hasNext() ) {
			UserStoryDTO p = i.next();
			System.out.println("Titel: " + p.getTitel()); 
			System.out.print("  Prio: " + p.getPrio());

			System.out.println("\n");
		}
		System.out.println("Aktuelle Anzahl User Stories: " + list.size() );
	}
	
	public void dumpLambdaWithFilterAndMapParallel(List<UserStoryDTO> liste, String status ) {
		// Ausgabe von UserStories mit den Status, nur Titel werden ausgegeben
		
		// TODO Exception Handling Nullpointer
		
		liste.parallelStream() 
			      .filter( element -> element.getStatus().equals(status))         // Filter  
			      .map( element -> element.getTitel() )                  // Map (Projektion)
			      .forEach( element -> {
			     zaehler.incrementAndGet();
			      System.out.println("Titel: " +  element );});   // Reduce (oder: System.out::println)
				System.out.println("Aktuelle Anzahl User Stories: " + zaehler );
			      	
	}

}
