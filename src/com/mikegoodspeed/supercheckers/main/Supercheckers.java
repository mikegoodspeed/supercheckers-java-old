package com.mikegoodspeed.supercheckers.main;

import com.mikegoodspeed.supercheckers.datastructures.Board;
import com.mikegoodspeed.supercheckers.datastructures.Move;
import com.mikegoodspeed.supercheckers.datastructures.Team;
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
		while (true) {
			Player p1 = new EasyComputerPlayer(window, board, Team.X);
			Player p2 = new EasyComputerPlayer(window, board, Team.O);
			while (true) {
				window.setTurn(p1.getTeam());
				do {
					window.updateBoard(board);
					move = p1.getMove();
					if (!board.isValidMove(p1.getTeam(), move)) {
						System.out.println("INVALID!!! X move: " + move);
						board.print();
					}
				} while (!board.isValidMove(p1.getTeam(), move));
				board.doMove(p1.getTeam(), move);
				window.updateBoard(board);
				if (board.isGameOver() && !board.isSecondMove()) {
					System.out.println(board.getWinner() + " wins!");
					break;
				}
				window.setTurn(p2.getTeam());
				do {
					window.updateBoard(board);
					move = p2.getMove();
					if (!board.isValidMove(p2.getTeam(), move)) {
						System.out.println("INVALID!!! O move: " + move);
						board.print();
					}
				} while (!board.isValidMove(p2.getTeam(), move));
				board.doMove(p2.getTeam(), move);
				window.updateBoard(board);
				if (board.isGameOver() && !board.isSecondMove()) {
					System.out.println(board.getWinner() + " wins!");
					break;
				}
			}
			board = new Board();
			window.updateBoard(board);
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
