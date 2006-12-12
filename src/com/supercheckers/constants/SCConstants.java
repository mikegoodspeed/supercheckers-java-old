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
public interface SCConstants {

	// Teams
	/** Player 1's team: X */
	public Team TEAM1 = new Team('X');
	/** Player 2's team: O */
	public Team TEAM2 = new Team('0');
	/** An empty team: (space) */
	public Team EMPTY = new Team(' ');
	
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
	public static final ImageIcon INSIDE_TEAM1 = new ImageIcon("images/inside_green.jpg");
	/** Icon for player 2's spot inside the center */
	public static final ImageIcon INSIDE_TEAM2 = new ImageIcon("images/inside_orange.jpg");
	/** Icon for an empty spot inside the center */
	public static final ImageIcon INSIDE_EMPTY = new ImageIcon("images/inside_empty.jpg");
	/** Icon for player 1's spot outside the center */
	public static final ImageIcon OUTSIDE_TEAM1 = new ImageIcon("images/outside_green.jpg");
	/** Icon for player 2's spot outside the center */
	public static final ImageIcon OUTSIDE_TEAM2 = new ImageIcon("images/outside_orange.jpg");
	/** Icon for an empty spot outside the center */
	public static final ImageIcon OUTSIDE_EMPTY = new ImageIcon("images/outside_empty.jpg");
}
