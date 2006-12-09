package com.supercheckers.players;

import com.supercheckers.datastructures.Move;
import com.supercheckers.datastructures.Team;
import com.supercheckers.main.Supercheckers;

/**
 * Abstract Player Class, to be used as the superclass for actual players.
 * <p>
 * project Supercheckers <br />
 * url http://www.mikegoodspeed.com/blog/projects/supercheckers/
 * 
 * @author Mike Goodspeed
 * @version $Id$
 */
public abstract class Player {

	private Supercheckers manager = null;
	private Team team = null;

	/**
	 * @param manager
	 * @param team
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
