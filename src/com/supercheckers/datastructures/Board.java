package com.supercheckers.datastructures;

import java.util.Arrays;

import com.supercheckers.constants.SCConstants;

/**
 * Supercheckers Board Data Structure
 *
 * @author Mike Goodspeed
 * @url http://www.mikegoodspeed.com/blog/projects/supercheckers/
 * @version $Id$
 * @headurl $HeadURL$
 */
public class Board implements Cloneable {
	private Team board[][] = null;

	public Board() {
		this.board = new Team[8][8];
		reset();
	}

	public void insert(Team t, int row, int col) {
		board[row][col] = t;
	}

	public Team get(int row, int col) {
		return board[row][col];
	}

	public Board clone() {
		Board b = new Board();
		for (int row = 0; row < 8; row++) {
			for (int col = 0; col < 8; col++) {
				b.insert(get(row, col), row, col);
			}
		}
		return b;
	}

	/**
	 * Reset the board to its default state.
	 */
	public void reset() {
		for (int row = 0; row < 8; row++) {
			for (int col = 0; col < 8; col++) {
				if (!Board.isInCenter(row, col)) { // not center area
					if ((row + col) % 2 == 0) {
						insert(SCConstants.TEAM1, row, col);
					} else { // if ((row + col) % 2 == 1)
						insert(SCConstants.TEAM2, row, col);
					}
				} else { // center area
					insert(SCConstants.EMPTY, row, col);
				}
			}
		}
	}
	
	public static boolean isInCenter(int row, int col) {
		return row >= 2 && row < 6 && col >= 2 && col < 6;
	}

	public boolean validate(Move move, Team team) {
		return true;
	}

	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		final Board other = (Board) obj;
		if (!Arrays.equals(board, other.board))
			return false;
		return true;
	}
	
	public String toString() {
		StringBuffer sb = new StringBuffer();
		for (int row = 0; row < 8; row++) {
			for (int col = 0; col < 8; col++) {
				sb.append(get(row, col).getTeam());
			}
		}
		return sb.toString();
	}
}
