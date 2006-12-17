package com.supercheckers.constants;

import javax.swing.ImageIcon;

import com.supercheckers.datastructures.Team;

/**
 * A move performed during a turn.
 * <p>
 * project Supercheckers <br />
 * url http://www.mikegoodspeed.com/blog/projects/supercheckers/
 * 
 * @author Mike Goodspeed
 * @version $Id$
 */
public class SCConst {

	// Teams
	/** Player 1's team: X */
	public static Team TEAM1 = new Team('X');
	/** Player 2's team: O */
	public static Team TEAM2 = new Team('0');
	/** An empty team: (space) */
	public static Team EMPTY = new Team(' ');
	
	// Board constants
	/**
	 * The board's minimum index.  The row with this index is on the north side of the board.  The 
	 * column with this index is on the far east of the board. 
	 */
	public static int B_MIN = 0;
	/**
	 * The board's maximum index.  The row with this index is on the south side of the board.  The 
	 * column with this index is on the west side of the board. 
	 */
	public static int B_MAX = 7;
	/**
	 * The board's middle area's minimum index.  The row with this index is on the north side of the
	 * middle of the board.  The column with this index is on the east side of the middle of the
	 * board. 
	 */
	public static int B_MID_MIN = 2;
	/**
	 * The board's middle area's maximum index.  The row with this index is on the south side of the
	 * center of the board.  The column with this index is on the west side of the middle of the 
	 * board. 
	 */
	public static int B_MID_MAX = 5;

	// Board Icons
	/** Icon for player 1's spot inside the center */
	public static final String INSIDE_TEAM1 = "images/inside_green.jpg";
	/** Icon for player 2's spot inside the center */
	public static final String INSIDE_TEAM2 = "images/inside_orange.jpg";
	/** Icon for an empty spot inside the center */
	public static final String INSIDE_EMPTY = "images/inside_empty.jpg";
	/** Icon for player 1's spot outside the center */
	public static final String OUTSIDE_TEAM1 = "images/outside_green.jpg";
	/** Icon for player 2's spot outside the center */
	public static final String OUTSIDE_TEAM2 = "images/outside_orange.jpg";
	/** Icon for an empty spot outside the center */
	public static final String OUTSIDE_EMPTY = "images/outside_empty.jpg";
	
	/**
	 * Returns an ImageIcon as specified by the given path.
	 * 
	 * @param path
	 * @return Associated icon for the given path
	 */
	public static final ImageIcon getImg(String path) {
		if (SCConst.class.getResource("/" + path) == null) {
			return new ImageIcon(path);
		} else {
			return new ImageIcon(SCConst.class.getResource("/" + path));
		}
	}
}
