package com.supercheckers.constants;

import javax.swing.ImageIcon;

import com.supercheckers.datastructures.Team;

/**
 * A move performed during a turn.
 *
 * @author Mike Goodspeed
 * @url http://www.mikegoodspeed.com/blog/projects/supercheckers/
 * @version $Id$
 * @headurl $HeadURL$
 */
public interface SCConstants {

	// Teams
	public Team TEAM1 = new Team('X');
	public Team TEAM2 = new Team('0');
	public Team EMPTY = new Team(' ');

	// Board Icons
	public static final ImageIcon INSIDE_TEAM1 = new ImageIcon("images/inside_green.jpg");
	public static final ImageIcon INSIDE_TEAM2 = new ImageIcon("images/inside_orange.jpg");
	public static final ImageIcon INSIDE_EMPTY = new ImageIcon("images/inside_empty.jpg");
	public static final ImageIcon OUTSIDE_TEAM1 = new ImageIcon("images/outside_green.jpg");
	public static final ImageIcon OUTSIDE_TEAM2 = new ImageIcon("images/outside_orange.jpg");
	public static final ImageIcon OUTSIDE_EMPTY = new ImageIcon("images/outside_empty.jpg");
}
