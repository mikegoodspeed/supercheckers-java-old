package com.supercheckers.players;

import com.supercheckers.Supercheckers;
import com.supercheckers.datastructures.Move;
import com.supercheckers.datastructures.Team;
import com.supercheckers.ui.GameBoardFrm;

/**
 * A Human Player.
 *
 * @author Mike Goodspeed
 * @url http://www.mikegoodspeed.com/blog/projects/supercheckers/
 * @version $Id$
 * @headurl $HeadURL$
 */
public class HumanPlayer extends Player {

	public GameBoardFrm window = null;

	/**
	 * @param getManager()
	 * @param team
	 */
	public HumanPlayer(Supercheckers manager, Team team) {
		super(manager, team);
		this.window = manager.getWindow();
	}

	public Move getMove() {
//		int row1, col1, row2, col2;
		window.waitForInput();
		window.getCoordinates();
//		row1 = getManager().getSelectedRow();
//		col1 = getManager().getSelectedCol();
//		getManager().waitForInput();
//		row2 = getManager().getSelectedRow();
//		col2 = getManager().getSelectedCol();
//		if ((row1 - row2 == 0 && (col1 - col2 == -1 || col1 - col2 == 1)) ||
//				(col1 - col2 == 0 && (row1 - row2 == -1 || row1 - row2 == 1)))
//			return new Move('s', row1, col1, row2, col2, null);
//		getManager().setEndButtonEnabled(true);
//		Move firstMove = new Move('j', row1, col1, row2, col2, null);
//		Move previousMove = firstMove;
//		Move currentMove = firstMove;
//		Move nextMove = null;
//		do {
//			getManager().waitForInput();
//			row1 = currentMove.getRow2();
//			col1 = currentMove.getCol2();
//			row2 = getManager().getSelectedRow();
//			col2 = getManager().getSelectedCol();
//			nextMove = new Move('j', row1, col1, row2, col2, null);
//			currentMove.setNext(nextMove);
//			previousMove = currentMove;
//			currentMove = nextMove;
//		} while (!getManager().isEndButtonPressed());
//		previousMove.setNext(null);
//		return firstMove;
		return null;
	}
}
