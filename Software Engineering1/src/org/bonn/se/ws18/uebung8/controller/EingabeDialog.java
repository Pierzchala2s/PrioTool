package org.bonn.se.ws18.uebung8.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import org.bonn.se.ws18.uebung8.controller.befehle.*;
import org.bonn.se.ws18.uebung8.controller.kriterien.*;
import org.bonn.se.ws18.uebung8.dtos.UserStoryDTO;
import org.bonn.se.ws18.uebung8.exceptions.ContainerException;
import org.bonn.se.ws18.uebung8.model.Container;
import org.bonn.se.ws18.uebung8.util.AkteurListe;
import org.bonn.se.ws18.uebung8.util.UserStoryDTOFinder;
import org.bonn.se.ws18.uebung8.util.UserStoryStatus;
import org.bonn.se.ws18.uebung8.views.AusgabeLinear;
import org.bonn.se.ws18.uebung8.views.AusgabeQualitaet;

/**
 * Klasse zur Darstellung der Eingabe (laut MVC Pattern: View) Ubernimmt
 * zusätzlich die Navigtion zwischen dem Modell und der Ausgabe (laut MVC
 * Pattern: Controller)
 * 
 * @author saschaalda
 *
 */
public class EingabeDialog {

	private BefehlsManager instance = BefehlsManager.getInstance();

	// Diese Methode realisiert die Eingabe
	public void startEingabe() {

		String strInput = null;

		// Ausgabe eines Texts zur Begruessung
		System.out.println("Prio-Tool V3.0");
		System.out.println("c/o Kevin Ehlen und Kevin Pierzchala in 2020\n");

		// Initialisierung des Eingabe-View
		BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

		// Kontinuierliche Abfrage der Eingabe ueber While-Schleife.
		// Dann Abfrage ueber switch/case oder (hier:) if-Schleifen
		// Optimierung: Command Pattern (Kapitel 6)
		while (true) {

			try {
				System.out.print("> ");
				strInput = input.readLine();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			// Extrahiert ein Array aus der Eingabe
			String[] strings = strInput.split(" ");

			// Falls 'help' eingegeben wurde, werden alle Befehle ausgedruckt
			if (strings[0].equals("help")) {
				System.out.println(
						"Folgende Befehle stehen zur Verfuegung: help, dump, addElement, actors, status, analyze, load , store, enter");
			}

			if (strings[0].equals("addElement") && strings[1].equals("-") && strings[2].equals("actor")) {

				instance.setAddElementBefehl(new AddElementBefehl(AkteurListe.getInstance(), strings[3]));
				instance.doAddElementBefehl();
			}

			if (strings[0].equals("actors")) {

				System.out.println(AkteurListe.getInstance());
			}
			
			if (strings[0].equals("undo")) {
				instance.undo();
			}

			if (strings[0].equals("status")) {

				this.setStatusVonUserStory(new Integer(strings[1]).intValue(), strings[2]);

			}

			if (strings[0].equals("analyze")) {

				if (strings.length > 4 && strings[2].equals("-") && strings[3].equals("details")
						&& strings[4].equals("-") && strings[5].equals("hints")) {
					try {

						this.berechneQualitaetMitDetailsUndHints(new Integer(strings[1]).intValue());
						continue;
					} catch (NumberFormatException e) {

					}
				}

				if (strings.length > 2 && strings[2].equals("-") && strings[3].equals("details")) {
					try {

						this.berechneQualitaetMitDetails(new Integer(strings[1]).intValue());
						continue;
					} catch (NumberFormatException e) {

					}

				}

				try {

					this.berechneQualitaet(new Integer(strings[1]).intValue());
					continue;
				} catch (NumberFormatException e) {

				}

				if (strings[1].equals("-") && strings[2].equals("all")) {
					this.berechneQualitaetAlle();
					continue;
				}

				else {
					// Exception
				}

			}

			if (strings[0].equals("dump")) {
				if (strings.length > 3 && strings[1].equals("-") && strings[2].equals("status")) {

					List<UserStoryDTO> dtoList = Container.getInstance().getCurrentListOfUserStoriesAsDTO();
					AusgabeLinear dialog = new AusgabeLinear();
					;
					if (strings[3].equals(UserStoryStatus.DONE)) {

						dialog.dumpLambdaWithFilterAndMapParallel(dtoList, UserStoryStatus.DONE);
					}
					if (strings[3].equals(UserStoryStatus.INPROGRESS)) {

						dialog.dumpLambdaWithFilterAndMapParallel(dtoList, UserStoryStatus.INPROGRESS);
					}
					if (strings[3].equals(UserStoryStatus.TODO)) {

						dialog.dumpLambdaWithFilterAndMapParallel(dtoList, UserStoryStatus.TODO);
					}

				}

				else {
					this.startAusgabe();
				}
			}

			if (strings[0].equals("load")) {
				Container.getInstance().load();
			}

			if (strings[0].equals("store")) {
				try {
					Container.getInstance().store();
				} catch (ContainerException e) {
					// TODO Auto-generated catch block
					System.out.println("Fehler beim Abspeichern!");
				}
			}

			if (strings[0].equals("enter")) {

				MyConsole console = new MyConsole();
				// Ausgabe einer Eingabeaufforderung
				System.out.println("Geben sie die Grunddaten der User Story ein:");

				// Eingabe der ID:
				int id = console.readLineInt("ID der User Story: ");

				// Abfrage der einzelnen Werte der User Story:
				String titel = console.readLine("Titel der User Story: ");

				// Eingabe des Aufwands:
				int aufwand = console.readLineInt("Aufwand der User Story: ");

				// Eingabe des Risikos:
				int risk = console.readLineInt("Risiko der User Story: ");

				// Eingabe des Mehrwerts:
				int mehrwert = console.readLineInt("Mehrwert der User Story: ");

				// Eingabe der Strafe:
				int strafe = console.readLineInt("Strafe der User Story: ");

				instance.setEnterBefehl(new EnterBefehl(id, titel, aufwand, strafe, mehrwert, risk));
				instance.doEnterBefehl();

			}
		} // Ende der Schleife
	}

	private void startAusgabe() {
		AusgabeLinear dialog = new AusgabeLinear();
		dialog.display(Container.getInstance().getCurrentListOfUserStoriesAsDTO());

	}

	private void setStatusVonUserStory(int id, String status) {

		instance.setStatusBefehl(new StatusBefehl(id, status));
		instance.doStatusBefehl();

	}

	private void berechneQualitaet(int id) {

		Container container = Container.getInstance();

		UserStoryDTO dto = UserStoryDTOFinder.getUserStoryDTO(id, container.getCurrentListOfUserStoriesAsDTO());

		double gesamtStrafe = QualitaetsPruefer.getInstance().durchfuehrung(dto);

		AusgabeQualitaet dialog = new AusgabeQualitaet();
		dialog.ausgabeStrafe(id, gesamtStrafe);

	}

	private void berechneQualitaetAlle() {
		Container container = Container.getInstance();
		List<UserStoryDTO> dtoliste = container.getCurrentListOfUserStoriesAsDTO();
		double durchschnitt = dtoliste.parallelStream()
				.mapToDouble(element -> QualitaetsPruefer.getInstance().durchfuehrung(element)).average().getAsDouble();
		AusgabeQualitaet dialog = new AusgabeQualitaet();
		dialog.ausgabeStrafeDurchschnitt(durchschnitt, dtoliste.size());
	}

	private void berechneQualitaetMitDetails(int id) {

		Container container = Container.getInstance();

		UserStoryDTO dto = UserStoryDTOFinder.getUserStoryDTO(id, container.getCurrentListOfUserStoriesAsDTO());

		String details = QualitaetsPruefer.getInstance().relevanteDetails(dto);
		AusgabeQualitaet dialog = new AusgabeQualitaet();
		this.berechneQualitaet(id);
		dialog.ausgabeDetails(details);

	}

	private void berechneQualitaetMitDetailsUndHints(int id) {

		Container container = Container.getInstance();

		UserStoryDTO dto = UserStoryDTOFinder.getUserStoryDTO(id, container.getCurrentListOfUserStoriesAsDTO());

		AusgabeQualitaet dialog = new AusgabeQualitaet();
		this.berechneQualitaetMitDetails(id);
		String hints = QualitaetsPruefer.getInstance().relevanteHints(dto);
		dialog.ausgabeHints(hints);

	}

}
