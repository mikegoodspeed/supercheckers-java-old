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
	
	public String toString() {
		return row + "," + col;
	}

	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		} else if (obj == null) {
			return false;
		} else if (getClass() != obj.getClass()) {
			return false;
		}
		final Spot other = (Spot) obj;
		if (col != other.col) {
			return false;
		} else if (row != other.row) {
			return false;
		}
		return true;
	}
}
