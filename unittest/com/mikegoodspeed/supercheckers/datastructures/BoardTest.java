package com.mikegoodspeed.supercheckers.datastructures;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.mikegoodspeed.supercheckers.datastructures.Board;

/**
 * Test the Board class.
 * <p>
 * project Supercheckers <br />
 * url http://www.mikegoodspeed.com/blog/projects/supercheckers/
 * 
 * @author Mike Goodspeed
 * @version $Id$
 */
public class BoardTest {
	
	private Board board = null;

	/**
	 * @throws Exception
	 */
	@Before
	public void setUp() throws Exception {
		board = new Board();
	}

	/**
	 * @throws Exception
	 */
	@After
	public void tearDown() throws Exception {
		board = null;
	}

	/**
	 * Test method for {@link Board#Board()}.
	 */
	@Test
	public void testBoard() {
		new Board();
	}

	/**
	 * Test method for {@link Board#isInMiddle(int, int)}.
	 */
	@Test
	public void testIsInMiddle() {
		for (int row = -1; row < 8; row++) {
			for (int col = -1; col < 8; col++) {
				assertEquals(row > 1 && row < 6 && col > 1 && col < 6, board.isInMiddle(row, col));
			}
		}
	}

	/**
	 * Test method for {@link Board#isValidSlide(Teams, int, int, int, int)}.
	 */
//	@Test
//	public void testIsValidSlide() {
//		
//	}

	/**
	 * Test method for {@link Board#isValidSpot(int, int)}.
	 */
//	@Test
//	public void testIsValidSpot() {
//		fail("Not yet implemented");
//	}

	/**
	 * Test method for {@link Board#isValidJump(Team, int, int, int, int)}.
	 */
//	@Test
//	public void testIsValidJump() {
//		fail("Not yet implemented");
//	}

	/**
	 * Test method for {@link Board#isValidMove(Team, Move)}.
	 */
//	@Test
//	public void testIsValidMove() {
//		fail("Not yet implemented");
//	}

	/**
	 * Test method for {@link Board#insert(Team, int, int)}.
	 */
//	@Test
//	public void testInsert() {
//		fail("Not yet implemented");
//	}

	/**
	 * Test method for {@link Board#get(int, int)}.
	 */
//	@Test
//	public void testGet() {
//		fail("Not yet implemented");
//	}

	/**
	 * Test method for {@link Board#doMove(Team, Move)}.
	 */
//	@Test
//	public void testDoMove() {
//		fail("Not yet implemented");
//	}

	/**
	 * Test method for {@link Board#reset()}.
	 */
//	@Test
//	public void testReset() {
//		fail("Not yet implemented");
//	}

	/**
	 * Test method for {@link Board#isNewGame()}.
	 */
//	@Test
//	public void testIsNewGame() {
//		assertEquals(true, board.isNewGame());
//		board.insert(new Team('O'), 4, 4);
//		assertEquals(false, board.isNewGame());
//	}

	/**
	 * Test method for {@link Board#isAvailableSpot(Team, Move, int, int)}.
	 */
//	@Test
//	public void testIsAvailableSpot() {
//		fail("Not yet implemented");
//	}

	/**
	 * Test method for {@link Board#isAdjacentToMiddle(int, int)}.
	 */
//	@Test
//	public void testIsAdjacentToMiddle() {
//		fail("Not yet implemented");
//	}

	/**
	 * Test method for {@link Board#isGameOver()}.
	 */
//	@Test
//	public void testIsGameOver() {
//		fail("Not yet implemented");
//	}

	/**
	 * Test method for {@link Board#print()}.
	 */
//	@Test
//	public void testPrint() {
//		fail("Not yet implemented");
//	}

	/**
	 * Test method for {@link Board#equals(java.lang.Object)}.
	 */
//	@Test
//	public void testEqualsObject() {
//		fail("Not yet implemented");
//	}

	/**
	 * Test method for {@link Board#toString()}.
	 */
//	@Test
//	public void testToString() {
//		fail("Not yet implemented");
//	}

}
