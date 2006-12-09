package com.supercheckers.datastructures;

import java.util.ArrayList;

/**
 * A move performed during a turn.
 *
 * @author Mike Goodspeed
 * @url http://www.mikegoodspeed.com/blog/projects/supercheckers/
 * @version $Id$
 * @headurl $HeadURL$
 */
public class Move {

	ArrayList<Spot> points = null;

	/**
	 * Constructor to create a new move.
	 */
	public Move() {
		points = new ArrayList<Spot>();
	}

	/**
	 * Find the size of the current move.
	 * 
	 * @return the number of spots in this move
	 */
	public int size() {
		return points.size();
	}
	
	/**
	 * Add a spot to the move.
	 * 
	 * @param row - the spot's row
	 * @param col - the spot's col
	 */
	public void add(int row, int col) {
		points.add(new Spot(row, col));
	}
	
	/**
	 * Get the row of the spot at a given index.
	 * 
	 * @param index - index of a spot
	 * @return the row of the selected spot
	 * @throws IndexOutOfBoundsException - if index is out of range (index < 0 || index >= size())
	 */
	public int getRow(int index) throws IndexOutOfBoundsException {
		return points.get(index).getRow();
	}
	
	/**
	 * Get the col of the spot at a given index.
	 * 
	 * @param index - index of a spot
	 * @return the col of the selected spot
	 * @throws IndexOutOfBoundsException - if index is out of range (index < 0 || index >= size())
	 */
	public int getCol(int index) throws IndexOutOfBoundsException {
		return points.get(index).getCol();
	}
	
	public String toString() {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < size(); i++) {
			sb.append(" " + points.get(i));
		}
		return sb.toString();
	}
}
