/*
 * Supercheckers - the game of Kings Court
 * Copyright (C) 2002-2007 Mike Goodspeed
 * 
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, USA.
 */
package com.mikegoodspeed.supercheckers.players;

import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.mikegoodspeed.supercheckers.datastructures.Board;
import com.mikegoodspeed.supercheckers.datastructures.Move;
import com.mikegoodspeed.supercheckers.datastructures.Team;
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
//		int x = 0;
//		int o = 0;
//		int tie = 0;
		for (int i = 0; i < 10000; i++) {
			player1 = new EasyComputerPlayer(window, board, Team.X);
			player2 = new EasyComputerPlayer(window, board, Team.O);
			while (true) {
				// Player 1
				move = player1.getMove();
				assertTrue("Invalid X move: " + move, board.isValidMove(player1.getTeam(), move));
				board.doMove(player1.getTeam(), move);
				if (board.isGameOver()) {
					break;
				}
				// Player 2
				move = player2.getMove();
				assertTrue("Invalid O move: " + move, board.isValidMove(player2.getTeam(), move));
				board.doMove(player2.getTeam(), move);
				if (board.isGameOver()) {
					break;
				}
			}
//			switch (board.getWinner()) {
//				case X: x++; break;
//				case O: o++; break;
//				default: tie++; break;
//			}
			board = new Board();
		}
//		System.out.println("x=" + x + " o=" + o + " tie=" + tie);
	}
}
