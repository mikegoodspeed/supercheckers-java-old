package com.supercheckers.utils;

import java.util.ArrayList;
import java.util.List;

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
	List<String> coordinates = null;

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

	public void addCoordinate(String coords) {
		coordinates.add(coords);
	}

	public void clearCoordinates() {
		coordinates = new ArrayList<String>();
	}

	public List<String> getCoordinates() {
		return coordinates;
	}
}
