package com.supercheckers.players;

import com.supercheckers.Supercheckers;
import com.supercheckers.datastructures.Move;
import com.supercheckers.datastructures.Team;

/**
 * Abstract Player Class, to be used as the superclass for actual players.
 *
 * @author Mike Goodspeed
 * @url http://www.mikegoodspeed.com/blog/projects/supercheckers/
 * @version $Id$
 * @headurl $HeadURL$
 */
public abstract class Player {

	private Supercheckers manager = null;
	private Team team = null;

	/**
	 * @param name - Name of the player
	 * @param team - Team representation
	 */
	public Player(Supercheckers manager, Team team) {
		this.manager = manager;
		this.team = team;
	}

	/**
	 * @return the manager
	 */
	public Supercheckers getManager() {
		return manager;
	}

	/**
	 * @return the team
	 */
	public Team getTeam() {
		return team;
	}

	/**
	 * Abstract method to be created by the subclass.
	 * 
	 * @return the selected Move
	 */
	public abstract Move getMove();
}
