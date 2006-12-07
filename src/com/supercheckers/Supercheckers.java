package com.supercheckers;

import com.supercheckers.constants.SCConstants;
import com.supercheckers.datastructures.Board;
import com.supercheckers.ui.GameBoardFrm;

/**
 * Supercheckers main class
 * 
 * @author Mike Goodspeed
 * @url http://www.mikegoodspeed.com/blog/projects/supercheckers/
 * @id $Id$
 * @headurl $HeadURL$
 */
public class Supercheckers {
	private Board board = null;
	private GameBoardFrm window = null;

	public Supercheckers() {
		board = new Board();
		window = new GameBoardFrm(this);
		window.setVisible(true);
		for (int i = 0; i < 100; i++) {
			window.setTurn(SCConstants.TEAM1);
			try { Thread.sleep(1000); } catch (InterruptedException e) { }
			window.setTurn(SCConstants.TEAM2);
			try { Thread.sleep(1000); } catch (InterruptedException e) { }
		}
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		new Supercheckers();
	}

	/**
	 * @return the board
	 */
	public Board getBoard() {
		return board;
	}
}
