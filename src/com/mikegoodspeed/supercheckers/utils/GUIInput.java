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
package com.mikegoodspeed.supercheckers.utils;

import com.mikegoodspeed.supercheckers.datastructures.Move;

/**
 * GUI Input Thread.
 * <p>
 * project Supercheckers <br />
 * url http://www.mikegoodspeed.com/blog/projects/supercheckers/
 * 
 * @author Mike Goodspeed
 * @version $Id$
 */
public class GUIInput extends Thread {

	boolean ready = false;
	Move move = null;

	/**
	 * Constructor to create a new imput thread.
	 */
	public GUIInput() {
		super("Supercheckers GUI Waiter");
		reset();
	}

	/**
	 * Adds a spot, specified by a row and a column, to the current move.
	 * 
	 * @param row
	 * @param col
	 */
	public void addSpot(int row, int col) {
		move.add(row, col);
	}

	/**
	 * Clear out previous spots and start a new move.
	 */
	public void clearMove() {
		move = new Move();
	}

	/**
	 * Returns move as chosen by the user.
	 * 
	 * @return the move
	 */
	public Move getMove() {
		return move;
	}

	private void reset() {
		ready = false;
		clearMove();
	}

	public void run() {
		reset();
		while (!ready) {
			try {
				sleep(100);
			} catch (Exception e) {}
		}
	}

	/**
	 * Tell the input thread that it is ready to be processed.
	 */
	public void setReady() {
		ready = true;
	}
}
