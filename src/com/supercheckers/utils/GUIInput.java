package com.supercheckers.utils;

import com.supercheckers.datastructures.Move;

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
		super();
		reset();
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
}
