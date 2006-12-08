package com.supercheckers;

import com.supercheckers.constants.SCConstants;
import com.supercheckers.datastructures.Board;
import com.supercheckers.players.HumanPlayer;
import com.supercheckers.players.Player;
import com.supercheckers.ui.GameBoardFrm;
import com.supercheckers.utils.GUIInput;

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
	private GUIInput inputListener = null;

	public Supercheckers() {
		board = new Board();
		inputListener = new GUIInput();
		window = new GameBoardFrm(this);
		window.setVisible(true);
		Player p1 = new HumanPlayer(this, SCConstants.TEAM1);
		p1.getMove();
		for (int i = 0; i < 100; i++) {
			window.setTurn(SCConstants.TEAM1);
			try { Thread.sleep(1000); } catch (InterruptedException e) { }
			window.setTurn(SCConstants.TEAM2);
			try { Thread.sleep(1000); } catch (InterruptedException e) { }
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

	/**
	 * @return the inputListener
	 */
	private GUIInput getInputListener() {
		return inputListener;
	}
}
