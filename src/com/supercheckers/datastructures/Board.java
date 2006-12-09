package com.supercheckers.datastructures;

import java.util.Arrays;

import com.supercheckers.constants.SCConstants;

/**
 * Supercheckers Board Data Structure.
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
		this.board = new Team[8][8];
		reset();
	}

	/**
	 * Insert a team to a given Spot, specified by a row and a column
	 * 
	 * @param team
	 * @param row
	 * @param col
	 */
	public void insert(Team team, int row, int col) {
		board[row][col] = team;
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

	protected Board clone() {
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

	/**
	 * Returns true if the spot, specified by a row and a column, is in the center of the board
	 * 
	 * @param row
	 * @param col
	 * @return true if the spot is in the center, false otherwise
	 */
	public static boolean isInCenter(int row, int col) {
		return row >= 2 && row < 6 && col >= 2 && col < 6;
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

	/**
	 * Determine if this game is a new game
	 * 
	 * @return true if this game is a new game, false otherwise
	 */
	public boolean isNewGame() {
		return equals(new Board());
	}

	/**
	 * Perform a move on a board. This does not take into account if the move is valid.
	 * 
	 * @param team
	 * @param move
	 * @see #isValidMove(Team, Move)
	 */
	public void doMove(Team team, Move move) {
		if (team != null && team.isValid() && move != null && move.size() > 1) {
			boolean isJump = isValidJump(this, team, move.getRow(0), move.getCol(0),
					move.getRow(1), move.getCol(1));
			int jumpedRow;
			int jumpedCol;
			for (int i = 1; i < move.size(); i++) {
				jumpedRow = (move.getRow(i - 1) + move.getRow(i)) / 2;
				jumpedCol = (move.getCol(i - 1) + move.getCol(i)) / 2;
				if (isJump && !team.equals(get(jumpedRow, jumpedCol))) {
					insert(SCConstants.EMPTY, jumpedRow, jumpedCol);
				}
				insert(SCConstants.EMPTY, move.getRow(i - 1), move.getCol(i - 1));
				insert(team, move.getRow(i), move.getCol(i));
			}
		}
	}

	/**
	 * Determines if a given move is valid based on a specific team and this board.
	 * 
	 * @param team
	 * @param move
	 * @return true if move is valid, false otherwise
	 */
	public boolean isValidMove(Team team, Move move) {
		System.out.println("validating " + move);
		if (team == null || !team.isValid() || SCConstants.EMPTY.equals(team)) {
			// Team must be valid and from either player 1 or player 2.
			return false;
		}
		if (move == null || move.size() < 2) {
			// Move must contain 2 or more Spots.
			return false;
		}
		Board b = clone();
		b.print();
		Move m;
		boolean isSlide = false;
		boolean isJump = false;
		int rowStart = move.getRow(0);
		int colStart = move.getCol(0);
		int rowEnd;
		int colEnd;
		if (!isValidSpot(rowStart, colStart)) {
			// The starting Spot must be valid.
			return false;
		}
		if (!team.equals(b.get(rowStart, colStart))) {
			// Moves must start at current Team's Spot.
			return false;
		}
		for (int i = 1; i < move.size(); i++) {
			if (isSlide && i > 1) {
				// Moves can only contain one slide
				return false;
			}
			rowEnd = move.getRow(i);
			colEnd = move.getCol(i);
			if (isValidSlide(b, team, rowStart, colStart, rowEnd, colEnd)) {
				isSlide = true;
			} else if (isValidJump(b, team, rowStart, colStart, rowEnd, colEnd)) {
				isJump = true;
			}
			if ((isSlide && isJump) || (!isSlide && !isJump)) {
				// Moves must always either be a jump or a slide, and never both.
				return false;
			}
			m = new Move();
			m.add(rowStart, colStart);
			m.add(rowEnd, colEnd);
			b.doMove(team, m);
			b.print();
			rowStart = rowEnd;
			colStart = colEnd;
		}
		return true;
	}

	/**
	 * Determines if the the move, specified by two (row, col) pairs, is a valid slide for the
	 * specified team on the specified board.
	 * 
	 * @param board
	 * @param team
	 * @param rowStart
	 * @param colStart
	 * @param rowEnd
	 * @param colEnd
	 * @return true if the move is a valid slide, false otherwise
	 */
	public static boolean isValidSlide(Board board, Team team, int rowStart, int colStart,
			int rowEnd, int colEnd) {
		if (board == null || !isValidSpot(rowStart, colStart) || !isValidSpot(rowEnd, colEnd)) {
			// The board and all spots must be valid.
			return false;
		}
		if (team == null || !team.equals(board.get(rowStart, colStart))
				|| !SCConstants.EMPTY.equals(board.get(rowEnd, colEnd))) {
			// The team must be valid, and the slide must start on a spot on the given team and end
			// on an empty spot.
			return false;
		}
		boolean horizontalSlide = rowStart - rowEnd == -1 || rowStart - rowEnd == 1;
		boolean verticalSlide = colStart - colEnd == -1 || colStart - colEnd == 1;
		// The slide must be either vertical or horizontal, and never both.
		return (horizontalSlide && !verticalSlide) || (!horizontalSlide && verticalSlide);
	}

	/**
	 * Determines if the the move, specified by two (row, col) pairs, is a valid jump for the
	 * specified team on the specified board.
	 * 
	 * @param board
	 * @param team
	 * @param rowStart
	 * @param colStart
	 * @param rowEnd
	 * @param colEnd
	 * @return true if the move is a valid jump, false otherwise
	 */
	public static boolean isValidJump(Board board, Team team, int rowStart, int colStart,
			int rowEnd, int colEnd) {
		if (board == null || !isValidSpot(rowStart, colStart) || !isValidSpot(rowEnd, colEnd)) {
			// The board and all spots must be valid.
			return false;
		}
		if (team == null || !team.equals(board.get(rowStart, colStart))
				|| !SCConstants.EMPTY.equals(board.get(rowEnd, colEnd))) {
			// The team must be valid, and the slide must start on a spot on the given team and end
			// on an empty spot.
			return false;
		}
		boolean northJump = rowStart - rowEnd == -2;
		boolean southJump = rowStart - rowEnd == 2;
		boolean westJump = colStart - colEnd == -2;
		boolean eastJump = colStart - colEnd == 2;
		if (!northJump || !southJump || !westJump || !eastJump) {
			// Jumps must go in at least one direction.
			return false;
		}
		if ((northJump && (southJump || westJump || eastJump))
				|| (southJump && (northJump || westJump || eastJump))
				|| (westJump && (northJump || southJump || eastJump))
				|| (eastJump && (northJump || southJump || westJump))) {
			// Jumps must go in exactly one direction.
			return false;
		}
		if (SCConstants.EMPTY.equals(board.get((rowStart + rowEnd) / 2, (colStart + colEnd) / 2))) {
			// Jumps must not jump over a space.
			return false;
		}
		return true;
	}

	/**
	 * Determines is a spot, specified by a row and a column, is valid on the board.
	 * 
	 * @param row
	 * @param col
	 * @return true if the spot is valid, false otherwise
	 */
	public static boolean isValidSpot(int row, int col) {
		return row >= 0 && row < 8 && col >= 0 && col < 8;
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
			// Moves must start on the current Team
			return team.equals(get(row, col));
		}
		Move proposedMove = currentMove.clone();
		proposedMove.add(row, col);
		return isValidMove(team, proposedMove);
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
		System.out.println("                    ");
		System.out.println("   0 1 2 3 4 5 6 7  ");
		for (int row = 0; row < 8; row++) {
			System.out.print(" " + row + "|");
			for (int col = 0; col < 8; col++) {
				System.out.print(get(row, col));
				if (col > 0 && col < 6 && row > 1 && row < 6) {
					System.out.print("#");
				} else {
					System.out.print("|");
				}
			}
			System.out.println(row + " ");
		}
		System.out.println("   0 1 2 3 4 5 6 7  ");
		System.out.println("                    ");
	}
}
