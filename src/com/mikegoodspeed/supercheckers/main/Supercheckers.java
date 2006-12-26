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
package com.mikegoodspeed.supercheckers.main;

import com.mikegoodspeed.supercheckers.datastructures.Board;
import com.mikegoodspeed.supercheckers.datastructures.Move;
import com.mikegoodspeed.supercheckers.datastructures.Players;
import com.mikegoodspeed.supercheckers.datastructures.Team;
import com.mikegoodspeed.supercheckers.players.EasyComputerPlayer;
import com.mikegoodspeed.supercheckers.players.HumanPlayer;
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
public class Supercheckers extends Thread {

	/**
	 * @param args all arguments are ignored
	 */
	public static void main(String[] args) {
		new Supercheckers();
	}

	private Board board = null;
	private GameBoardFrm window = null;

	/**
	 * Constructor to create a new game of Supercheckers.
	 */
	public Supercheckers() {
		super("Supercheckers Main");
		board = new Board();
		window = new GameBoardFrm(board);
		window.setVisible(true);
		this.start(); // calls run() in a new thread
	}

	/**
	 * Play a single game of Supercheckers.
	 */
	private void playGame() {
		board.reset();
		Players.PLAYER1.set(new HumanPlayer(window, board, Team.X));
		Players.PLAYER2.set(new EasyComputerPlayer(window, board, Team.O));
		window.updateBoard(board);
		window.setPlayers(Players.PLAYER1.get(), Players.PLAYER2.get());
		boolean gameOver = false;
		while (!gameOver) {
			gameOver = playRound();
		}
		window.announceWinner(Players.getByTeam(board.getWinner()));
	}

	/**
	 * Play a single round of supercheckers, where each player gets to make one valid move.
	 *
	 * @return true if game is over, false otherwise
	 */
	private boolean playRound() {
		boolean gameOver = false;
		for (Players currentPlayer : Players.values()) {
			gameOver = playTurn(currentPlayer.get());
			if (gameOver) {
				return true; // Game over
			}
		}
		return false; // Round over (game continues)
	}

	/**
	 * Play a turn for a given player.
	 *
	 * @param player the player expected to make a move
	 * @return true if game is over, false otherwise
	 */
	private boolean playTurn(Player player) {
		Move move;
		window.setTurn(player.getTeam());
		do {
			window.updateBoard(board);
			move = player.getMove();
		} while (!board.isValidMove(player.getTeam(), move));
		board.doMove(player.getTeam(), move);
		window.updateBoard(board);
		return board.isGameOver(); // return game over status
	}

	public void run() {
		playGame();
	}
}
