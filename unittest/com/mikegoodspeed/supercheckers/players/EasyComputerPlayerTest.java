package com.mikegoodspeed.supercheckers.players;

import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.mikegoodspeed.supercheckers.datastructures.Board;
import com.mikegoodspeed.supercheckers.datastructures.Move;
import com.mikegoodspeed.supercheckers.datastructures.Team;
import com.mikegoodspeed.supercheckers.players.EasyComputerPlayer;
import com.mikegoodspeed.supercheckers.ui.GameBoardFrm;

/**
 * Test the EasyComputerPlayer class
 * <p>
 * project Supercheckers <br />
 * url http://www.mikegoodspeed.com/blog/projects/supercheckers/
 * 
 * @author Mike Goodspeed
 * @version $Id$
 */
public class EasyComputerPlayerTest {
	Board board = null;
	GameBoardFrm window = null;
	EasyComputerPlayer player1 = null;
	EasyComputerPlayer player2 = null;

	/**
	 * @throws Exception
	 */
	@Before
	public void setUp() throws Exception {
		board = new Board();
		window = new GameBoardFrm(board);
		player1 = new EasyComputerPlayer(window, board, Team.X);
		player1 = new EasyComputerPlayer(window, board, Team.O);
	}

	/**
	 * @throws Exception
	 */
	@After
	public void tearDown() throws Exception {
		board = null;
		player1 = null;
	}

	/**
	 * Test method for {@link EasyComputerPlayer#getMove()}.  Play 10000 games and ensure that all 
	 * moves are valid.
	 */
	@Test
	public void testGetMove() {
		Move move;
		for (int i = 0; i < 10000; i++) {
			player1 = new EasyComputerPlayer(window, board, Team.X);
			player2 = new EasyComputerPlayer(window, board, Team.O);
			while (true) {
				// Player 1
				move = player1.getMove();
				assertTrue("Invalid X move: " + move, board.isValidMove(player1.getTeam(), move));
				board.doMove(player1.getTeam(), move);
				if (board.isGameOver() && !board.isSecondMove()) {
					break;
				}
				// Player 2
				move = player2.getMove();
				assertTrue("Invalid O move: " + move, board.isValidMove(player2.getTeam(), move));
				board.doMove(player2.getTeam(), move);
				if (board.isGameOver() && !board.isSecondMove()) {
					break;
				}
			}
			board = new Board();
		}
	}

}
