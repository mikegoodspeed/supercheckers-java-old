package com.mikegoodspeed.supercheckers.players;

import com.mikegoodspeed.supercheckers.datastructures.Board;
import com.mikegoodspeed.supercheckers.datastructures.Move;
import com.mikegoodspeed.supercheckers.datastructures.Teams;
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
	private Teams team = null;

	/**
	 * @param window
	 * @param board
	 * @param team
	 */
	public Player(GameBoardFrm window, Board board, Teams team) {
		this.window = window;
		this.board = board;
		this.team = team;
	}

	/**
	 * @return the board
	 */
	public Board getBoard() {
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
	public Teams getTeam() {
		return team;
	}

	/**
	 * @return the window
	 */
	public GameBoardFrm getWindow() {
		return window;
	}
}
