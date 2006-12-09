package com.supercheckers.players;

import com.supercheckers.datastructures.Move;
import com.supercheckers.datastructures.Team;
import com.supercheckers.main.Supercheckers;
import com.supercheckers.ui.GameBoardFrm;

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

	private GameBoardFrm window = null;

	/**
	 * @param manager
	 * @param team
	 */
	public HumanPlayer(Supercheckers manager, Team team) {
		super(manager, team);
		this.window = manager.getWindow();
	}

	public Move getMove() {
		window.waitForInput();
		Move move = window.getMove();
		return move;
	}
}
