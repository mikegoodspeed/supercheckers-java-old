package com.supercheckers.players;

import com.supercheckers.Supercheckers;
import com.supercheckers.datastructures.Move;

/**
 * Abstract Player Class, to be used as the superclass for actual players.
 *
 * @author Mike Goodspeed
 * @url http://www.mikegoodspeed.com/blog/projects/supercheckers/
 * @id $Id$
 * @headurl $HeadURL$
 */
public abstract class Player {
	private String name;
	private char team;

	/**
	 * @param name - Name of the player
	 * @param team - Team representation
	 */
	public Player(String name, char team) {
		this.name = new String(name);
		this.team = team;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @return the team
	 */
	public char getTeam() {
		return team;
	}

	/**
	 * Abstract method to be created by the subclass.
	 *   
	 * @param manager - Supercheckers manager
	 * @param turnNumber - 0-based turn number
	 * @return Move - the selected move
	 */
	public abstract Move playMove(Supercheckers manager, int turnNumber);
}
