/*
 * Supercheckers - the game of Kings Court
 * Copyright (C) 2002-2007 Mike Goodspeed
 * 
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, USA.
 */
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
