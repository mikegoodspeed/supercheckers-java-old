package com.mikegoodspeed.supercheckers.datastructures;

/**
 * A spot selected during a move.
 * <p>
 * project Supercheckers <br />
 * url http://www.mikegoodspeed.com/blog/projects/supercheckers/
 * 
 * @author Mike Goodspeed
 * @version $Id$
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
	
	@Override
	protected Spot clone() {
		return new Spot(getRow(), getCol());
	}

	@Override
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

	@Override
	public String toString() {
		return "(" + row + "," + col + ")";
	}
}
