package com.mikegoodspeed.supercheckers.datastructures;

import javax.swing.ImageIcon;

import com.mikegoodspeed.supercheckers.constants.SCConst;

/**
 * Teams as an enumeration.
 * 
 * @author Mike Goodspeed
 * @version $Id$
 */
public enum Teams {
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
	EMPTY(' ', "images/outside_empty.jpg", "images/inside_empty.jpg");
	
	private char representation;
	private String outsideImagePath;
	private String insideImagePath;

	Teams(char representation, String outsideImagePath, String insideImagePath) {
		this.representation = representation;
		this.outsideImagePath = outsideImagePath;
		this.insideImagePath = insideImagePath;
	}
	
	/**
	 * @return character representation of the team
	 */
	public char get() {
		return representation;
	}
	
	/**
	 * @param isInMiddle
	 * @return image representation of the team
	 */
	public ImageIcon getImage(boolean isInMiddle) {
		String path = isInMiddle ? insideImagePath : outsideImagePath;
		if (SCConst.class.getResource("/" + path) == null) {
			return new ImageIcon(path);
		} else {
			return new ImageIcon(SCConst.class.getResource("/" + path));
		}
	}
}
