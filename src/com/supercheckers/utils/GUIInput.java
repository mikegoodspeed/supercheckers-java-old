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
	Move coordinates = null;

	public GUIInput() {
		super();
		reset();
	}

	private void reset() {
		ready = false;
		clearCoordinates();
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

	public void addCoordinate(int row, int col) {
		coordinates.add(row, col);
	}

	public void clearCoordinates() {
		coordinates = new Move();
	}

	public Move getCoordinates() {
		return coordinates;
	}
}
