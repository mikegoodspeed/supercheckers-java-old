package com.mikegoodspeed.supercheckers.datastructures;

import static com.mikegoodspeed.supercheckers.constants.SCConst.B_MAX;
import static com.mikegoodspeed.supercheckers.constants.SCConst.B_MID_MAX;
import static com.mikegoodspeed.supercheckers.constants.SCConst.B_MID_MIN;
import static com.mikegoodspeed.supercheckers.constants.SCConst.B_MIN;

/**
 * Supercheckers board object.
 * <p>
 * project Supercheckers <br />
 * url http://www.mikegoodspeed.com/blog/projects/supercheckers/
 *
 * @author Mike Goodspeed
 * @version $Id$
 */
public class Board implements Cloneable {

	private Team board[][] = null;

	/**
	 * Constructor to create a new board
	 */
	public Board() {
		this.board = new Team[B_MAX + 1][B_MAX + 1];
		reset();
	}
//
//	/**
//	 * Clear the board by setting all the spots to EMPTY.
//	 */
//	public void clear() {
//		for (int row = B_MIN; row <= B_MAX; row++) {
//			for (int col = B_MIN; col <= B_MAX; col++) {
//				insert(Teams.EMPTY, row, col);
//			}
//		}
//	}

	@Override
	public Board clone() {
		Board b = new Board();
		for (int row = B_MIN; row <= B_MAX; row++) {
			for (int col = B_MIN; col <= B_MAX; col++) {
				b.insert(get(row, col), row, col);
			}
		}
		return b;
	}

	/**
	 * Perform a move on a board. This does not take into account if the move is valid.
	 *
	 * @param team
	 * @param move
	 * @see #isValidMove(Team, Move)
	 */
	public void doMove(Team team, Move move) {
		if (team != null && move != null && move.size() > 1) {
			boolean isJump =
				isValidJump(team, move.getRow(0), move.getCol(0), move.getRow(1), move.getCol(1));
			int jumpedRow;
			int jumpedCol;
			for (int i = 1; i < move.size(); i++) {
				jumpedRow = (move.getRow(i - 1) + move.getRow(i)) / 2;
				jumpedCol = (move.getCol(i - 1) + move.getCol(i)) / 2;
				if (isJump && !team.equals(get(jumpedRow, jumpedCol))) {
					insert(Team.EMPTY, jumpedRow, jumpedCol);
				}
				insert(Team.EMPTY, move.getRow(i - 1), move.getCol(i - 1));
				insert(team, move.getRow(i), move.getCol(i));
			}
		}
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		final Board other = (Board) obj;
		for (int row = B_MIN; row <= B_MAX; row++) {
			for (int col = B_MIN; col <= B_MAX; col++) {
				if (!get(row, col).equals(other.get(row, col))) {
					return false;
				}
			}
		}
		return true;
	}

	/**
	 * Get the Team at a given spot, specified by a row and a column
	 *
	 * @param row
	 * @param col
	 * @return the team
	 */
	public Team get(int row, int col) {
		return board[row][col];
	}

	/**
	 * Insert a team to a given Spot, specified by a row and a column
	 *
	 * @param team
	 * @param row
	 * @param col
	 */
	private void insert(Team team, int row, int col) {
		board[row][col] = team;
	}

	/**
	 * Determines if a given spot is adjacent to the middle.
	 *
	 * @param row
	 * @param col
	 * @return true if given spo is adjacent, false otherwise
	 */
	public boolean isAdjacentToMiddle(int row, int col) {
		return (row == B_MID_MIN - 1 && col >= B_MID_MIN && col <= B_MID_MAX)
		|| (row == B_MID_MAX + 1 && col >= B_MID_MIN && col <= B_MID_MAX)
		|| (col == B_MID_MIN - 1 && row >= B_MID_MIN && row <= B_MID_MAX)
		|| (col == B_MID_MAX + 1 && row >= B_MID_MIN && row <= B_MID_MAX);
	}

	/**
	 * Determines if the spot, specified by a row and a column, can be added to the specified move
	 * and have the move still be valid.
	 *
	 * @param team
	 * @param currentMove
	 * @param row
	 * @param col
	 * @return true if the spot is viable as the next spot in the move, false otherwise
	 */
	public boolean isAvailableSpot(Team team, Move currentMove, int row, int col) {
		if (team == null) {
			// Must have a valid Team
			return false;
		}
		if (currentMove == null || currentMove.size() == 0) {
			if (isFirstMove()) {
				return isAdjacentToMiddle(row, col) && team.equals(get(row, col));
			} else if (isSecondMove()) {
				boolean p1North = Team.EMPTY.equals(get(1, 2)) || Team.EMPTY.equals(get(1, 3))
				|| Team.EMPTY.equals(get(1, 4)) || Team.EMPTY.equals(get(1, 5));
				boolean p1South = Team.EMPTY.equals(get(6, 2)) || Team.EMPTY.equals(get(6, 3))
				|| Team.EMPTY.equals(get(6, 4)) || Team.EMPTY.equals(get(6, 5));
				boolean p1East = Team.EMPTY.equals(get(2, 6)) || Team.EMPTY.equals(get(3, 6))
				|| Team.EMPTY.equals(get(4, 6)) || Team.EMPTY.equals(get(5, 6));
				boolean p1West = Team.EMPTY.equals(get(2, 1)) || Team.EMPTY.equals(get(3, 1))
				|| Team.EMPTY.equals(get(4, 1)) || Team.EMPTY.equals(get(5, 1));
				if (p1North) {
					// Move from South
					return row == 6 && col >= B_MID_MIN && col <= B_MID_MAX
					&& team.equals(get(row, col));
				} else if (p1South) {
					// Move from North
					return row == 1 && col >= B_MID_MIN && col <= B_MID_MAX
					&& team.equals(get(row, col));
				} else if (p1East) {
					// Move from West
					return row >= B_MID_MIN && row <= B_MID_MAX && col == 1
					&& team.equals(get(row, col));
				} else if (p1West) {
					// Move from East
					return row >= B_MID_MIN && row <= B_MID_MAX && col == 6
					&& team.equals(get(row, col));
				}
				// Should not happen.
				return false;
			}
			// Moves must start on the current Team
			return team.equals(get(row, col));
		}
		Move proposedMove = currentMove.clone();
		proposedMove.add(row, col);
		return isValidMove(team, proposedMove);
	}

	/**
	 * Determines if the board is in the position for a first move.
	 *
	 * @return true if the game is on move one, false otherwise.
	 */
	public boolean isFirstMove() {
		return equals(new Board());
	}

	/**
	 * Determines if the board is in a game over state, as determined by one or more players
	 * occupying no spots in the center of the board.
	 *
	 * @return true if the game is over, false otherwise
	 */
	public boolean isGameOver() {
		int p1 = 0;
		int p2 = 0;
		for (int row = B_MID_MIN; row <= B_MID_MAX; row++) {
			for (int col = B_MID_MIN; col <= B_MID_MAX; col++) {
				if (Team.X.equals(get(row, col))) {
					p1++;
				} else if (Team.O.equals(get(row, col))) {
					p2++;
				}
			}
		}
		return p1 == 0 || p2 == 0;
	}

	/**
	 * Returns true if the spot, specified by a row and a column, is in the middle of the board
	 *
	 * @param row
	 * @param col
	 * @return true if the spot is in the middle, false otherwise
	 */
	public boolean isInMiddle(int row, int col) {
		return row >= B_MID_MIN && row <= B_MID_MAX && col >= B_MID_MIN && col <= B_MID_MAX;
	}

	/**
	 * Determine if this game is a new game
	 *
	 * @return true if this game is a new game, false otherwise
	 */
	public boolean isNewGame() {
		return equals(new Board());
	}

	/**
	 * Determines if the board is in the position for a second move.
	 *
	 * @return true if the game is on move two, false otherwise.
	 */
	public boolean isSecondMove() {
		int in = 0;
		int out = 0;
		for (int row = B_MIN; row <= B_MAX; row++) {
			for (int col = B_MIN; col <= B_MAX; col++) {
				if (!Team.EMPTY.equals(get(row, col))) {
					if (isInMiddle(row, col)) {
						in++;
					} else {
						out++;
					}
				}
			}
		}
		return in == 1 && out == 47;
	}

	/**
	 * Determines if the the move, specified by two (row, col) pairs, is a valid jump for the
	 * specified team on this board.
	 *
	 * @param team
	 * @param rowStart
	 * @param colStart
	 * @param rowEnd
	 * @param colEnd
	 * @return true if the move is a valid jump, false otherwise
	 */
	public boolean isValidJump(Team team, int rowStart, int colStart, int rowEnd, int colEnd) {
		if (team == null) {
			// All parameters must not be null.
			return false;
		}
		if (!isValidSpot(rowStart, colStart) || !isValidSpot(rowEnd, colEnd)) {
			// The board and all spots must be valid.
			return false;
		}
		if (!team.equals(get(rowStart, colStart)) || !Team.EMPTY.equals(get(rowEnd, colEnd))) {
			// The team must be valid, and the jump must start on a spot on the given team and end
			// on an empty spot.
			return false;
		}
		if (Team.EMPTY.equals(get((rowStart + rowEnd) / 2, (colStart + colEnd) / 2))) {
			// Jumps must not jump over a space.
			return false;
		}
		boolean north = rowStart - rowEnd == 2;
		boolean south = rowStart - rowEnd == -2;
		boolean east = colStart - colEnd == 2;
		boolean west = colStart - colEnd == -2;
		boolean vertical = rowStart - rowEnd != 0;
		boolean horizontal = colStart - colEnd != 0;
		return ((north || south) && !horizontal) || ((east || west) && !vertical);
	}

	/**
	 * Determines if a given move is valid based on a specific team and this board.
	 *
	 * @param team
	 * @param move
	 * @return true if move is valid, false otherwise
	 */
	public boolean isValidMove(Team team, Move move) {
//		System.out.println("validating " + move);
		if (team == null || move == null) {
			// Parameters must not be null.
			return false;
		}
		if (move.size() < 2) {
			// Parameters must be valid.
			return false;
		}
		if (isValidSlide(team, move.getRow(0), move.getCol(0), move.getRow(1), move.getCol(1))) {
			// If the move is a slide, it must only be a slide.
			return move.size() == 2;
		}
		// Test to see if the move is a legal jump series.
		Board boardClone = clone();
		int rowStart = move.getRow(0);
		int colStart = move.getCol(0);
		int rowEnd;
		int colEnd;
		for (int i = 1; i < move.size(); i++) {
			rowEnd = move.getRow(i);
			colEnd = move.getCol(i);
			if (!boardClone.isValidJump(team, rowStart, colStart, rowEnd, colEnd)) {
				// All moves in a jump series must be a valid jump.
				return false;
			}
			Move mTest = new Move();
			mTest.add(rowStart, colStart);
			mTest.add(rowEnd, colEnd);
			boardClone.doMove(team, mTest);
			if (boardClone.isGameOver() && i != move.size() - 1) {
				// Jump series can not leave the board in a game over state before finishing.
				return false;
			}
			rowStart = rowEnd;
			colStart = colEnd;
		}
		// All tests passed!
		return true;
	}

	/**
	 * Determines if the the move, specified by two (row, col) pairs, is a valid slide for the
	 * specified team on this board.
	 *
	 * @param team
	 * @param rowStart
	 * @param colStart
	 * @param rowEnd
	 * @param colEnd
	 * @return true if the move is a valid slide, false otherwise
	 */
	public boolean isValidSlide(Team team, int rowStart, int colStart, int rowEnd, int colEnd) {
		if (team == null) {
			// All parameters must not be null.
			return false;
		}
		if (!isValidSpot(rowStart, colStart) || !isValidSpot(rowEnd, colEnd)) {
			// The board and all spots must be valid.
			return false;
		}
		if (Team.EMPTY.equals(team) || !team.equals(get(rowStart, colStart))
				|| !Team.EMPTY.equals(get(rowEnd, colEnd))) {
			// The team must be valid, and the slide must start on a spot on the given team and end
			// on an empty spot.
			return false;
		}
		boolean north = rowStart - rowEnd == 1;
		boolean south = rowStart - rowEnd == -1;
		boolean east = colStart - colEnd == 1;
		boolean west = colStart - colEnd == -1;
		boolean vertical = rowStart - rowEnd != 0;
		boolean horizontal = colStart - colEnd != 0;
		return ((north || south) && !horizontal) || ((east || west) && !vertical);
	}

	/**
	 * Determines is a spot, specified by a row and a column, is valid on the board.
	 *
	 * @param row
	 * @param col
	 * @return true if the spot is valid, false otherwise
	 */
	public boolean isValidSpot(int row, int col) {
		return row >= B_MIN && row <= B_MAX && col >= B_MIN && col <= B_MAX;
	}

	/**
	 * Prints the current state of the board to standard output.
	 * <p>
	 * Example board:
	 *
	 * <pre>
	 *    0 1 2 3 4 5 6 7
	 *  0|O|X|O|X|O|X|O|X|0
	 *  1|X|O|X|O|X|O|X|O|1
	 *  2|O|X# # # # #O|X|2
	 *  3|X|O# # # # #X|O|3
	 *  4|O|X# # # # #O|X|4
	 *  5|X|O# # # # #X|O|5
	 *  6|O|X|O|X|O|X|O|X|6
	 *  7|X|O|X|O|X|O|X|O|7
	 *    0 1 2 3 4 5 6 7
	 * </pre>
	 */
	public void print() {
		System.out.println("   0 1 2 3 4 5 6 7  ");
		for (int row = B_MIN; row <= B_MAX; row++) {
			System.out.print(" " + row + "|");
			for (int col = B_MIN; col <= B_MAX; col++) {
				System.out.print(get(row, col));
				if (row >= B_MID_MIN && row <= B_MID_MAX & col >= B_MID_MIN - 1
						&& col <= B_MID_MAX) {
					System.out.print("#");
				} else {
					System.out.print("|");
				}
			}
			System.out.println(row + " ");
		}
		System.out.println("   0 1 2 3 4 5 6 7  ");
	}

	/**
	 * Reset the board to its default state.
	 */
	private void reset() {
		for (int row = 0; row < 8; row++) {
			for (int col = 0; col < 8; col++) {
				if (isInMiddle(row, col)) {
					insert(Team.EMPTY, row, col);
				} else {
					if ((row + col) % 2 == 0) {
						insert(Team.X, row, col);
					} else { // if ((row + col) % 2 == 1)
						insert(Team.O, row, col);
					}
				}
			}
		}
	}

	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		for (int row = B_MIN; row <= B_MAX; row++) {
			for (int col = B_MIN; col <= B_MAX; col++) {
				sb.append(get(row, col));
			}
		}
		return sb.toString();
	}
}
