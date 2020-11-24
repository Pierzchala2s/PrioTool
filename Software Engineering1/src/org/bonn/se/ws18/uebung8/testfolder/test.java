/**
 * 
 */
package org.bonn.se.ws18.uebung8.testfolder;

import static org.junit.Assert.*;

import org.bonn.se.ws18.uebung8.controller.kriterien.*;
import org.bonn.se.ws18.uebung8.controller.befehle.*;
import org.bonn.se.ws18.uebung8.dtos.UserStoryDTO;
import org.bonn.se.ws18.uebung8.exceptions.AkteurListeException;
import org.bonn.se.ws18.uebung8.model.Container;
import org.bonn.se.ws18.uebung8.util.AkteurListe;
import org.bonn.se.ws18.uebung8.util.UserStoryDTOFinder;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * @author kpierz2s
 *
 */
public class test {

	static KriteriumsManager strategy = new KriteriumsManager();
	static BefehlsManager instance = BefehlsManager.getInstance();
	private Container container = Container.getInstance();

	@BeforeClass
	public static void setUp() {

		strategy.befuellung(new MehrwertKriterium());
		strategy.befuellung(new AkteurKriterium());
		strategy.befuellung(new ShortKriterium());

		// ID 1 = - 30% Mehrwert
		instance.setEnterBefehl(new EnterBefehl(1, "Als Student will ich trinken", 10, 10, 0, 10));
		instance.doEnterBefehl();
		// Id 2 = 100%
		instance.setEnterBefehl(new EnterBefehl(2, "Als Professor will ich korrigieren", 8, 10, 10, 10));
		instance.doEnterBefehl();
		// Id 3 = -20% Akteur + (- 30% Mehrwert)
		instance.setEnterBefehl(new EnterBefehl(3, "Als Anwender will ich korrigieren", 10, 10, 0, 10));
		instance.doEnterBefehl();
		// Id 4 =  (- 20% Short) 
		instance.setEnterBefehl(new EnterBefehl(4,
				"Als Anwender will ich korrigieren.................................................................................................................................................................................................................................................................................................................",
				10, 10, 10, 10));
		instance.doEnterBefehl();

	}

	@Test
	public void addAkteurTest() {

		AkteurListe testliste = AkteurListe.getInstance();

		assertFalse(testliste.contains("Student"));

		instance.setAddElementBefehl(new AddElementBefehl(AkteurListe.getInstance(), "Student"));
		instance.doAddElementBefehl();

		assertTrue(testliste.contains("Student"));

		instance.undo();

	}

	@Test
	public void addElementUndoTest() {

		AkteurListe testliste = AkteurListe.getInstance();

		assertFalse(testliste.contains("Student"));

		instance.setAddElementBefehl(new AddElementBefehl(AkteurListe.getInstance(), "Student"));
		instance.doAddElementBefehl();

		assertTrue(testliste.contains("Student"));

		instance.undo();
		assertFalse(testliste.contains("Student"));

	}

	@Test
	public void containsTest() {
		AkteurListe testliste = AkteurListe.getInstance();
		try {
			testliste.addAkteur("Professor");
		} catch (AkteurListeException e) {

			e.printStackTrace();
		}
		assertFalse(testliste.contains("Student"));
		testliste.removeAkteur("Professor");

	}

	@Test
	public void changeStatusTest() {

		String status = UserStoryDTOFinder.getUserStoryDTO(1, container.getCurrentListOfUserStoriesAsDTO()).getStatus();
		assertEquals("todo", status);
		instance.setStatusBefehl(new StatusBefehl(1, "done"));
		instance.doStatusBefehl();
		status = UserStoryDTOFinder.getUserStoryDTO(1, container.getCurrentListOfUserStoriesAsDTO()).getStatus();
		assertEquals("done", status);
		instance.undo();

	}

	@Test
	public void undoTest() {
		String status = UserStoryDTOFinder.getUserStoryDTO(2, container.getCurrentListOfUserStoriesAsDTO()).getStatus();
		assertEquals("todo", status);
		instance.setStatusBefehl(new StatusBefehl(2, "done"));
		instance.doStatusBefehl();
		status = UserStoryDTOFinder.getUserStoryDTO(2, container.getCurrentListOfUserStoriesAsDTO()).getStatus();
		assertEquals("done", status);
		instance.undo();
		status = UserStoryDTOFinder.getUserStoryDTO(2, container.getCurrentListOfUserStoriesAsDTO()).getStatus();
		assertEquals("todo", status);

	}

	@Test
	public void berechneQualitaetAkteurTest() {
		AkteurListe testliste = AkteurListe.getInstance();
		assertFalse(testliste.contains("Professor"));

		UserStoryDTO dto = UserStoryDTOFinder.getUserStoryDTO(2, container.getCurrentListOfUserStoriesAsDTO());

		Double strafe = QualitaetsPruefer.getInstance().durchfuehrung(dto);

		assertEquals(new Double(0.2), strafe);

		try {
			testliste.addAkteur("Professor");
		} catch (AkteurListeException e) {

			e.printStackTrace();
		}
		strafe = QualitaetsPruefer.getInstance().durchfuehrung(dto);

		assertEquals(new Double(0.0), strafe);

		testliste.removeAkteur("Professor");
	}
	
	@Test
	public void berechneQualitaetShortTest(){
		AkteurListe testliste = AkteurListe.getInstance();
		UserStoryDTO dto = UserStoryDTOFinder.getUserStoryDTO(4, container.getCurrentListOfUserStoriesAsDTO());
		
		try {
			testliste.addAkteur("Anwender");
		} catch (AkteurListeException e) {

			e.printStackTrace();
		}
		Double strafe = QualitaetsPruefer.getInstance().durchfuehrung(dto);

		assertEquals(new Double(0.2), strafe);
		testliste.removeAkteur("Anwender");
		
	}
	
	@Test
	public void berechneQualitaetMehrwertTest() {
		AkteurListe testliste = AkteurListe.getInstance();
		UserStoryDTO dto = UserStoryDTOFinder.getUserStoryDTO(1, container.getCurrentListOfUserStoriesAsDTO());
		try {
			testliste.addAkteur("Student");
		} catch (AkteurListeException e) {

			e.printStackTrace();
		}
		Double strafe = QualitaetsPruefer.getInstance().durchfuehrung(dto);

		assertEquals(new Double(0.3), strafe);
		testliste.removeAkteur("Student");
	}

	@Test
	public void enterTest() {

		assertEquals(4, container.getCurrentListOfUserStoriesAsDTO().size());
		instance.setEnterBefehl(new EnterBefehl(5, "Als Text will ich korrigieren", 8, 10, 10, 10));
		instance.doEnterBefehl();
		assertEquals(5, container.getCurrentListOfUserStoriesAsDTO().size());
		instance.undo();
		assertEquals(4, container.getCurrentListOfUserStoriesAsDTO().size());

	}
}
