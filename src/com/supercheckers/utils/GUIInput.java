package com.supercheckers.utils;

import com.supercheckers.datastructures.Move;

/**
 * GUI Input Thread.
 *
 * @author Mike Goodspeed
 * @url http://www.mikegoodspeed.com/blog/projects/supercheckers/
 * @version $Id$
 * @headurl $HeadURL$
 */
public class GUIInput extends Thread {

	boolean ready = false;
	Move move = null;

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
			}
			catch(Exception e) { }
		}
	}

	public void setReady() {
		ready = true;
	}

	public void addSpot(int row, int col) {
		move.add(row, col);
	}

	public void clearMove() {
		move = new Move();
	}

	public Move getMove() {
		return move;
	}
}
