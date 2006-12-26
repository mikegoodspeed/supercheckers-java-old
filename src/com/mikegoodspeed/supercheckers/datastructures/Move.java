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
package com.mikegoodspeed.supercheckers.datastructures;

import java.util.ArrayList;

/**
 * A move performed during a turn.
 * <p>
 * project Supercheckers <br />
 * url http://www.mikegoodspeed.com/blog/projects/supercheckers/
 * 
 * @author Mike Goodspeed
 * @version $Id$
 */
public class Move implements Cloneable {

	private ArrayList<Spot> points = null;

	/**
	 * Constructor to create a new move.
	 */
	public Move() {
		points = new ArrayList<Spot>();
	}

	/**
	 * Add a spot to the move.
	 * 
	 * @param row the spot's row
	 * @param col the spot's col
	 */
	public void add(int row, int col) {
		points.add(new Spot(row, col));
	}

	@Override
	protected Move clone() {
		Move m = new Move();
		for (int i = 0; i < size(); i++) {
			m.add(getRow(i), getCol(i));
		}
		return m;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		final Move other = (Move) obj;
		if (other.size() != size()) {
			return false;
		}
		for (int i = 0; i < size(); i++) {
			if (other.getRow(i) != getRow(i)) {
				return false;
			}
			if (other.getCol(i) != getCol(i)) {
				return false;
			}
		}
		return true;
	}

	/**
	 * Get the col of the spot at a given index.
	 * 
	 * @param index index of a spot
	 * @return the col of the selected spot
	 * @throws IndexOutOfBoundsException if index is out of range (index < 0 || index >= size())
	 */
	public int getCol(int index) throws IndexOutOfBoundsException {
		return points.get(index).getCol();
	}

	/**
	 * Get the row of the spot at a given index.
	 * 
	 * @param index index of a spot
	 * @return the row of the selected spot
	 * @throws IndexOutOfBoundsException - if index is out of range (index < 0 || index >= size())
	 */
	public int getRow(int index) throws IndexOutOfBoundsException {
		return points.get(index).getRow();
	}
	
	/**
	 * @param index
	 * @return the spot at the selected index
	 * @throws IndexOutOfBoundsException - if index is out of range (index < 0 || index >= size())
	 */
	public Spot getSpot(int index) throws IndexOutOfBoundsException {
		return points.get(index);
	}

	/**
	 * Find the size of the current move.
	 * 
	 * @return the number of spots in this move
	 */
	public int size() {
		return points.size();
	}

	@Override
	public String toString() {
		if (points.size() == 0) {
			return "empty";
		}
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < size(); i++) {
			sb.append(" " + points.get(i));
		}
		return sb.toString().trim();
	}
}
