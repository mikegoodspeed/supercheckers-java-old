package com.supercheckers.datastructures;

import com.supercheckers.constants.SCConstants;

/**
 * Supercheckers Board Data Structure
 * 
 * @author Mike Goodspeed
 * @url http://www.mikegoodspeed.com/blog/projects/supercheckers/
 * @id $Id$
 * @headurl $HeadURL$
 */
public class Board {
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
	
	/**
	 * Reset the board to its default state.
	 */
	public void reset() {
		for (int row = 0; row < 8; row++) {
			for (int col = 0; col < 8; col++) {
				if (row < 2 || row >= 6 || col < 2 || col >= 6) { // not center area
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

}
