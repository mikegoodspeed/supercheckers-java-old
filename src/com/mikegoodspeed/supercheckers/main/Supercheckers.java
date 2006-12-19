package com.mikegoodspeed.supercheckers.main;

import static com.mikegoodspeed.supercheckers.constants.SCConst.TEAM1;
import static com.mikegoodspeed.supercheckers.constants.SCConst.TEAM2;

import com.mikegoodspeed.supercheckers.datastructures.Board;
import com.mikegoodspeed.supercheckers.datastructures.Move;
import com.mikegoodspeed.supercheckers.players.EasyComputerPlayer;
import com.mikegoodspeed.supercheckers.players.Player;
import com.mikegoodspeed.supercheckers.ui.GameBoardFrm;

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
		Player p1 = new EasyComputerPlayer(window, board, TEAM1);
		Player p2 = new EasyComputerPlayer(window, board, TEAM2);
		boolean joshing = true;
		while (true) {
			window.setTurn(p1.getTeam());
			do {
				window.updateBoard(board);
				move = p1.getMove();
				System.out.println("player1 move: " + move);
			} while (!board.isValidMove(p1.getTeam(), move));
			board.doMove(p1.getTeam(), move);
			window.updateBoard(board);
			if (board.isGameOver() && !joshing) {
				System.out.println("game over, p1 wins");
				break;
			}
			joshing = false;
			window.setTurn(p2.getTeam());
			do {
				window.updateBoard(board);
				move = p2.getMove();
				System.out.println("p2 move: " + move);
			} while (!board.isValidMove(p2.getTeam(), move));
			board.doMove(p2.getTeam(), move);
			window.updateBoard(board);
			if (board.isGameOver()) {
				System.out.println("game over, p2 wins");
				break;
			}
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
