package com.supercheckers.datastructures;

/**
 * A move performed during a turn.
 *
 * @author Mike Goodspeed
 * @url http://www.mikegoodspeed.com/blog/projects/supercheckers/
 * @version $Id$
 * @headurl $HeadURL$
 */
public class Move {

	private char type;
	private int row1;
	private int col1;
	private int row2;
	private int col2;
	private Move next;

	/**
	 * @param type
	 * @param row1
	 * @param col1
	 * @param row2
	 * @param col2
	 * @param next - the next move
	 */
	public Move(char type, int row1, int col1, int row2, int col2, Move next) {
		this.type = type;
		this.row1 = row1;
		this.col1 = col1;
		this.row2 = row2;
		this.col2 = col2;
		this.next = next;
	}

	/**
	 * @return the next
	 */
	public Move getNext() {
		return next;
	}

	/**
	 * @param next - the next move to set
	 */
	public void setNext(Move next) {
		this.next = next;
	}

	/**
	 * @return the col1
	 */
	public int getCol1() {
		return col1;
	}

	/**
	 * @return the col2
	 */
	public int getCol2() {
		return col2;
	}

	/**
	 * @return the row1
	 */
	public int getRow1() {
		return row1;
	}

	/**
	 * @return the row2
	 */
	public int getRow2() {
		return row2;
	}

	/**
	 * @return the type
	 */
	public char getType() {
		return type;
	}
}
