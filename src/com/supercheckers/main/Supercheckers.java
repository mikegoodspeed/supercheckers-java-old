package com.supercheckers.main;

import com.supercheckers.constants.SCConstants;
import com.supercheckers.datastructures.Board;
import com.supercheckers.datastructures.Move;
import com.supercheckers.players.HumanPlayer;
import com.supercheckers.players.Player;
import com.supercheckers.ui.GameBoardFrm;

/**
 * Supercheckers main class
 * <p>
 * project Supercheckers <br />
 * url http://www.mikegoodspeed.com/blog/projects/supercheckers/
 * 
 * @author Mike Goodspeed
 * @version $Id$
 */
public class Supercheckers {

	private Board board = null;
	private GameBoardFrm window = null;

	/**
	 * Constructor to create a new game of Supercheckers.
	 */
	public Supercheckers() {
		board = new Board();
		window = new GameBoardFrm(board);
		window.setVisible(true);
		Move move;
		Player p1 = new HumanPlayer(window, board, SCConstants.TEAM1);
		Player p2 = new HumanPlayer(window, board, SCConstants.TEAM2);
		while (true) {
			window.setTurn(p1.getTeam());
			do {
				window.updateBoard(board);
				move = p1.getMove();
			} while (!board.isValidMove(p1.getTeam(), move));
			board.doMove(p1.getTeam(), move);
			System.out.println("new board after valid move:");
			board.print();
			window.setTurn(p2.getTeam());
			do {
				window.updateBoard(board);
				move = p2.getMove();
			} while (!board.isValidMove(p2.getTeam(), move));
			board.doMove(p2.getTeam(), move);
			System.out.println("new board after valid move:");
			board.print();
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
