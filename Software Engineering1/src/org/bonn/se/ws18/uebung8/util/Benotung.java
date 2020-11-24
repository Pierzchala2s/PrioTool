/**
 * 
 */
package org.bonn.se.ws18.uebung8.util;

/**
 * @author kpierz2s
 *
 */
public class Benotung {

	public static String notenCheck(double strafe) {

		if (1 - strafe >= 0.9) {
			return "sehr gut";
		}
		if (1 - strafe >= 0.75) {
			return "gut";
		}
		if (1 - strafe >= 0.6) {
			return "befriedigend";
		}
		if (1 - strafe >= 0.5) {
			return "ausreichend";
		}
		else { 
			return "mangelhaft";
		}

		
	}

}
