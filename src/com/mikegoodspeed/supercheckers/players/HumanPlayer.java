package com.mikegoodspeed.supercheckers.players;

import com.mikegoodspeed.supercheckers.datastructures.Board;
import com.mikegoodspeed.supercheckers.datastructures.Move;
import com.mikegoodspeed.supercheckers.datastructures.Team;
import com.mikegoodspeed.supercheckers.ui.GameBoardFrm;

/**
 * A Human Player.
 * <p>
 * project Supercheckers <br />
 * url http://www.mikegoodspeed.com/blog/projects/supercheckers/
 * 
 * @author Mike Goodspeed
 * @version $Id$
 */
public class HumanPlayer extends Player {

	/**
	 * @param window
	 * @param board
	 * @param team
	 */
	public HumanPlayer(GameBoardFrm window, Board board, Team team) {
		super(window, board, team);
	}

	public Move getMove() {
		getWindow().waitForInput();
		Move move = getWindow().getMove();
		return move;
	}
}
