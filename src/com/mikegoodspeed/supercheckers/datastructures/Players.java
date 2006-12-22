package com.mikegoodspeed.supercheckers.datastructures;

import com.mikegoodspeed.supercheckers.players.Player;

/**
 * A listing of players as an enumeration.
 * <p>
 * project Supercheckers <br />
 * url http://www.mikegoodspeed.com/blog/projects/supercheckers/
 *
 * @author Mike Goodspeed
 * @version $Id$
 */
public enum Players {

	/**
	 * Player 1
	 */
	PLAYER1 {
		public Player get() {
			return p1;
		}

		public void set(Player p) {
			p1 = p;
		}
	},

	/**
	 * Player 2
	 */
	PLAYER2 {
		public Player get() {
			return p2;
		}

		public void set(Player p) {
			p2 = p;
		}
	};

	private static Player p1 = null;
	private static Player p2 = null;

	/**
	 * Retrieve a player from an enumeration.
	 *
	 * @return Player associated with an enumeration
	 */
	public abstract Player get();

	/**
	 * Assign a player to an enumeration.
	 *
	 * @param p Player to be assigned.
	 */
	public abstract void set(Player p);
}
