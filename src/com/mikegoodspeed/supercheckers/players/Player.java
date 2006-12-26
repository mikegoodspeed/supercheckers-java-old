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
package com.mikegoodspeed.supercheckers.players;

import com.mikegoodspeed.supercheckers.datastructures.Board;
import com.mikegoodspeed.supercheckers.datastructures.Move;
import com.mikegoodspeed.supercheckers.datastructures.Team;
import com.mikegoodspeed.supercheckers.ui.GameBoardFrm;

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

	private GameBoardFrm window = null;
	private Board board = null;
	private Team team = null;

	/**
	 * @param window
	 * @param board
	 * @param team
	 */
	public Player(GameBoardFrm window, Board board, Team team) {
		this.window = window;
		this.board = board;
		this.team = team;
	}

	/**
	 * @return the board
	 */
	protected Board getBoard() {
		return board;
	}

	/**
	 * Abstract method to be created by the subclass.
	 *
	 * @return the selected Move
	 */
	public abstract Move getMove();

	/**
	 * @return the team
	 */
	public Team getTeam() {
		return team;
	}
	
	public abstract String toString();

	/**
	 * @return the window
	 */
	protected GameBoardFrm getWindow() {
		return window;
	}
}
