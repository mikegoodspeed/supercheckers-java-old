package com.supercheckers.datastructures;

/**
 * A spot selected during a move.
 *
 * @author Mike Goodspeed
 * @url http://www.mikegoodspeed.com/blog/projects/supercheckers/
 * @version $Id$
 * @headurl $HeadURL$
 */
public class Spot {

	private int row;
	private int col;
	
	/**
	 * @param row - the spot's row
	 * @param col - the spot's col
	 */
	public Spot(int row, int col) {
		this.row = row;
		this.col = col;
	}

	/**
	 * @return the col
	 */
	public int getCol() {
		return col;
	}

	/**
	 * @return the row
	 */
	public int getRow() {
		return row;
	}
}
