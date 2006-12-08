package com.supercheckers;

import com.supercheckers.constants.SCConstants;
import com.supercheckers.datastructures.Board;
import com.supercheckers.players.HumanPlayer;
import com.supercheckers.players.Player;
import com.supercheckers.ui.GameBoardFrm;

/**
 * Supercheckers main class
 *
 * @author Mike Goodspeed
 * @url http://www.mikegoodspeed.com/blog/projects/supercheckers/
 * @version $Id$
 * @headurl $HeadURL$
 */
public class Supercheckers {

	private Board board = null;
	private GameBoardFrm window = null;

	public Supercheckers() {
		board = new Board();
		window = new GameBoardFrm(this);
		window.setVisible(true);
		Player p1 = new HumanPlayer(this, SCConstants.TEAM1);
		while (true) {
			window.setTurn(SCConstants.TEAM1);
			p1.getMove();
			window.setTurn(SCConstants.TEAM2);
			p1.getMove();
		}		
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		new Supercheckers();
	}

	/**
	 * @return the board
	 */
	public Board getBoard() {
		return board;
	}

	/**
	 * @return the window
	 */
	public GameBoardFrm getWindow() {
		return window;
	}
}
