package com.mikegoodspeed.supercheckers.datastructures;

import javax.swing.ImageIcon;

/**
 * Teams as an enumeration.
 * <p>
 * project Supercheckers <br />
 * url http://www.mikegoodspeed.com/blog/projects/supercheckers/
 *
 * @author Mike Goodspeed
 * @version $Id$
 */
public enum Team {

	/**
	 * Team 1: X
	 */
	X('X', "images/outside_green.jpg", "images/inside_green.jpg"),

	/**
	 * Team 2: O
	 */
	O('O', "images/outside_orange.jpg", "images/inside_orange.jpg"),

	/**
	 * Empty team: (space)
	 */
	NOBODY(' ', "images/outside_empty.jpg", "images/inside_empty.jpg");

	private char representation;
	private String outsideImagePath;
	private String insideImagePath;

	private Team(char representation, String outsideImagePath, String insideImagePath) {
		this.representation = representation;
		this.outsideImagePath = outsideImagePath;
		this.insideImagePath = insideImagePath;
	}

	/**
	 * Returns the ImageIcon of the team, depending on if it is in the middle of the board or not.
	 * 
	 * @param isInMiddle true if spot is in the middle of the board
	 * @return image representation of the team
	 */
	public ImageIcon getIcon(boolean isInMiddle) {
		String path = isInMiddle ? insideImagePath : outsideImagePath;
		if (Team.class.getResource("/" + path) == null) {
			return new ImageIcon(path);
		} else {
			return new ImageIcon(Team.class.getResource("/" + path));
		}
	}

	/**
	 * Returns the representation of a team.
	 * 
	 * @return the representation as a String
	 */
	public String get() {
		return new String(new char[] { representation });
	}
}
