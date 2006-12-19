package com.mikegoodspeed.supercheckers.unittest;

import static com.mikegoodspeed.supercheckers.constants.SCConst.EMPTY;
import static com.mikegoodspeed.supercheckers.constants.SCConst.TEAM1;
import static com.mikegoodspeed.supercheckers.constants.SCConst.TEAM2;
import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.mikegoodspeed.supercheckers.datastructures.Board;
import com.mikegoodspeed.supercheckers.datastructures.Team;

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
	 * Test method for {@link Board#isValidSlide(Team, int, int, int, int)}.
	 */
	@Test
	public void testIsValidSlide() {
		board.clear();
		board.insert(TEAM1, 2, 2);
		int row = 2;
		int col = 2;
		// Correct team, correct spot.
		assertEquals(false, board.isValidSlide(TEAM1, row, col, row - 2, col - 2));
		assertEquals(false, board.isValidSlide(TEAM1, row, col, row - 2, col - 1));
		assertEquals(false, board.isValidSlide(TEAM1, row, col, row - 2, col - 0));
		assertEquals(false, board.isValidSlide(TEAM1, row, col, row - 2, col + 1));
		assertEquals(false, board.isValidSlide(TEAM1, row, col, row - 2, col + 2));
		assertEquals(false, board.isValidSlide(TEAM1, row, col, row - 1, col - 2));
		assertEquals(false, board.isValidSlide(TEAM1, row, col, row - 1, col - 1));
		assertEquals(true, board.isValidSlide(TEAM1, row, col, row - 1, col - 0)); // north
		assertEquals(false, board.isValidSlide(TEAM1, row, col, row - 1, col + 1));
		assertEquals(false, board.isValidSlide(TEAM1, row, col, row - 1, col + 2));
		assertEquals(false, board.isValidSlide(TEAM1, row, col, row - 0, col - 2));
		assertEquals(true, board.isValidSlide(TEAM1, row, col, row - 0, col - 1)); // west
		assertEquals(false, board.isValidSlide(TEAM1, row, col, row - 0, col - 0));
		assertEquals(true, board.isValidSlide(TEAM1, row, col, row - 0, col + 1)); // east
		assertEquals(false, board.isValidSlide(TEAM1, row, col, row - 0, col + 2));
		assertEquals(false, board.isValidSlide(TEAM1, row, col, row + 1, col - 2));
		assertEquals(false, board.isValidSlide(TEAM1, row, col, row + 1, col - 1));
		assertEquals(true, board.isValidSlide(TEAM1, row, col, row + 1, col - 0)); // south
		assertEquals(false, board.isValidSlide(TEAM1, row, col, row + 1, col + 1));
		assertEquals(false, board.isValidSlide(TEAM1, row, col, row + 1, col + 2));
		// Wrong team, correct spot
        assertEquals(false, board.isValidSlide(TEAM2, row, col, row - 2, col - 2));
        assertEquals(false, board.isValidSlide(TEAM2, row, col, row - 2, col - 1));
        assertEquals(false, board.isValidSlide(TEAM2, row, col, row - 2, col - 0));
        assertEquals(false, board.isValidSlide(TEAM2, row, col, row - 2, col + 1));
        assertEquals(false, board.isValidSlide(TEAM2, row, col, row - 2, col + 2));
        assertEquals(false, board.isValidSlide(TEAM2, row, col, row - 1, col - 2));
        assertEquals(false, board.isValidSlide(TEAM2, row, col, row - 1, col - 1));
        assertEquals(false, board.isValidSlide(TEAM2, row, col, row - 1, col - 0));
        assertEquals(false, board.isValidSlide(TEAM2, row, col, row - 1, col + 1));
        assertEquals(false, board.isValidSlide(TEAM2, row, col, row - 1, col + 2));
        assertEquals(false, board.isValidSlide(TEAM2, row, col, row - 0, col - 2));
        assertEquals(false, board.isValidSlide(TEAM2, row, col, row - 0, col - 1));
        assertEquals(false, board.isValidSlide(TEAM2, row, col, row - 0, col - 0));
        assertEquals(false, board.isValidSlide(TEAM2, row, col, row - 0, col + 1));
        assertEquals(false, board.isValidSlide(TEAM2, row, col, row - 0, col + 2));
        assertEquals(false, board.isValidSlide(TEAM2, row, col, row + 1, col - 2));
        assertEquals(false, board.isValidSlide(TEAM2, row, col, row + 1, col - 1));
        assertEquals(false, board.isValidSlide(TEAM2, row, col, row + 1, col - 0));
        assertEquals(false, board.isValidSlide(TEAM2, row, col, row + 1, col + 1));
        assertEquals(false, board.isValidSlide(TEAM2, row, col, row + 1, col + 2));
        // Empty team, correct spot
        assertEquals(false, board.isValidSlide(EMPTY, row, col, row - 2, col - 2));
        assertEquals(false, board.isValidSlide(EMPTY, row, col, row - 2, col - 1));
        assertEquals(false, board.isValidSlide(EMPTY, row, col, row - 2, col - 0));
        assertEquals(false, board.isValidSlide(EMPTY, row, col, row - 2, col + 1));
        assertEquals(false, board.isValidSlide(EMPTY, row, col, row - 2, col + 2));
        assertEquals(false, board.isValidSlide(EMPTY, row, col, row - 1, col - 2));
        assertEquals(false, board.isValidSlide(EMPTY, row, col, row - 1, col - 1));
        assertEquals(false, board.isValidSlide(EMPTY, row, col, row - 1, col - 0));
        assertEquals(false, board.isValidSlide(EMPTY, row, col, row - 1, col + 1));
        assertEquals(false, board.isValidSlide(EMPTY, row, col, row - 1, col + 2));
        assertEquals(false, board.isValidSlide(EMPTY, row, col, row - 0, col - 2));
        assertEquals(false, board.isValidSlide(EMPTY, row, col, row - 0, col - 1));
        assertEquals(false, board.isValidSlide(EMPTY, row, col, row - 0, col - 0));
        assertEquals(false, board.isValidSlide(EMPTY, row, col, row - 0, col + 1));
        assertEquals(false, board.isValidSlide(EMPTY, row, col, row - 0, col + 2));
        assertEquals(false, board.isValidSlide(EMPTY, row, col, row + 1, col - 2));
        assertEquals(false, board.isValidSlide(EMPTY, row, col, row + 1, col - 1));
        assertEquals(false, board.isValidSlide(EMPTY, row, col, row + 1, col - 0));
        assertEquals(false, board.isValidSlide(EMPTY, row, col, row + 1, col + 1));
        assertEquals(false, board.isValidSlide(EMPTY, row, col, row + 1, col + 2));
        // Null team, correct spot
        assertEquals(false, board.isValidSlide(null, row, col, row - 2, col - 2));
        assertEquals(false, board.isValidSlide(null, row, col, row - 2, col - 1));
        assertEquals(false, board.isValidSlide(null, row, col, row - 2, col - 0));
        assertEquals(false, board.isValidSlide(null, row, col, row - 2, col + 1));
        assertEquals(false, board.isValidSlide(null, row, col, row - 2, col + 2));
        assertEquals(false, board.isValidSlide(null, row, col, row - 1, col - 2));
        assertEquals(false, board.isValidSlide(null, row, col, row - 1, col - 1));
        assertEquals(false, board.isValidSlide(null, row, col, row - 1, col - 0));
        assertEquals(false, board.isValidSlide(null, row, col, row - 1, col + 1));
        assertEquals(false, board.isValidSlide(null, row, col, row - 1, col + 2));
        assertEquals(false, board.isValidSlide(null, row, col, row - 0, col - 2));
        assertEquals(false, board.isValidSlide(null, row, col, row - 0, col - 1));
        assertEquals(false, board.isValidSlide(null, row, col, row - 0, col - 0));
        assertEquals(false, board.isValidSlide(null, row, col, row - 0, col + 1));
        assertEquals(false, board.isValidSlide(null, row, col, row - 0, col + 2));
        assertEquals(false, board.isValidSlide(null, row, col, row + 1, col - 2));
        assertEquals(false, board.isValidSlide(null, row, col, row + 1, col - 1));
        assertEquals(false, board.isValidSlide(null, row, col, row + 1, col - 0));
        assertEquals(false, board.isValidSlide(null, row, col, row + 1, col + 1));
        assertEquals(false, board.isValidSlide(null, row, col, row + 1, col + 2));
        row = 5;
        col = 5;
		// Correct team, wrong spot
		assertEquals(false, board.isValidSlide(TEAM1, row, col, row - 2, col - 2));
		assertEquals(false, board.isValidSlide(TEAM1, row, col, row - 2, col - 1));
		assertEquals(false, board.isValidSlide(TEAM1, row, col, row - 2, col - 0));
		assertEquals(false, board.isValidSlide(TEAM1, row, col, row - 2, col + 1));
		assertEquals(false, board.isValidSlide(TEAM1, row, col, row - 2, col + 2));
		assertEquals(false, board.isValidSlide(TEAM1, row, col, row - 1, col - 2));
		assertEquals(false, board.isValidSlide(TEAM1, row, col, row - 1, col - 1));
		assertEquals(false, board.isValidSlide(TEAM1, row, col, row - 1, col - 0));
		assertEquals(false, board.isValidSlide(TEAM1, row, col, row - 1, col + 1));
		assertEquals(false, board.isValidSlide(TEAM1, row, col, row - 1, col + 2));
		assertEquals(false, board.isValidSlide(TEAM1, row, col, row - 0, col - 2));
		assertEquals(false, board.isValidSlide(TEAM1, row, col, row - 0, col - 1));
		assertEquals(false, board.isValidSlide(TEAM1, row, col, row - 0, col - 0));
		assertEquals(false, board.isValidSlide(TEAM1, row, col, row - 0, col + 1));
		assertEquals(false, board.isValidSlide(TEAM1, row, col, row - 0, col + 2));
		assertEquals(false, board.isValidSlide(TEAM1, row, col, row + 1, col - 2));
		assertEquals(false, board.isValidSlide(TEAM1, row, col, row + 1, col - 1));
		assertEquals(false, board.isValidSlide(TEAM1, row, col, row + 1, col - 0));
		assertEquals(false, board.isValidSlide(TEAM1, row, col, row + 1, col + 1));
		assertEquals(false, board.isValidSlide(TEAM1, row, col, row + 1, col + 2));
		// Wrong team, wrong spot
        assertEquals(false, board.isValidSlide(TEAM2, row, col, row - 2, col - 2));
        assertEquals(false, board.isValidSlide(TEAM2, row, col, row - 2, col - 1));
        assertEquals(false, board.isValidSlide(TEAM2, row, col, row - 2, col - 0));
        assertEquals(false, board.isValidSlide(TEAM2, row, col, row - 2, col + 1));
        assertEquals(false, board.isValidSlide(TEAM2, row, col, row - 2, col + 2));
        assertEquals(false, board.isValidSlide(TEAM2, row, col, row - 1, col - 2));
        assertEquals(false, board.isValidSlide(TEAM2, row, col, row - 1, col - 1));
        assertEquals(false, board.isValidSlide(TEAM2, row, col, row - 1, col - 0));
        assertEquals(false, board.isValidSlide(TEAM2, row, col, row - 1, col + 1));
        assertEquals(false, board.isValidSlide(TEAM2, row, col, row - 1, col + 2));
        assertEquals(false, board.isValidSlide(TEAM2, row, col, row - 0, col - 2));
        assertEquals(false, board.isValidSlide(TEAM2, row, col, row - 0, col - 1));
        assertEquals(false, board.isValidSlide(TEAM2, row, col, row - 0, col - 0));
        assertEquals(false, board.isValidSlide(TEAM2, row, col, row - 0, col + 1));
        assertEquals(false, board.isValidSlide(TEAM2, row, col, row - 0, col + 2));
        assertEquals(false, board.isValidSlide(TEAM2, row, col, row + 1, col - 2));
        assertEquals(false, board.isValidSlide(TEAM2, row, col, row + 1, col - 1));
        assertEquals(false, board.isValidSlide(TEAM2, row, col, row + 1, col - 0));
        assertEquals(false, board.isValidSlide(TEAM2, row, col, row + 1, col + 1));
        assertEquals(false, board.isValidSlide(TEAM2, row, col, row + 1, col + 2));
        // Empty team, wrong spot
        assertEquals(false, board.isValidSlide(EMPTY, row, col, row - 2, col - 2));
        assertEquals(false, board.isValidSlide(EMPTY, row, col, row - 2, col - 1));
        assertEquals(false, board.isValidSlide(EMPTY, row, col, row - 2, col - 0));
        assertEquals(false, board.isValidSlide(EMPTY, row, col, row - 2, col + 1));
        assertEquals(false, board.isValidSlide(EMPTY, row, col, row - 2, col + 2));
        assertEquals(false, board.isValidSlide(EMPTY, row, col, row - 1, col - 2));
        assertEquals(false, board.isValidSlide(EMPTY, row, col, row - 1, col - 1));
        assertEquals(false, board.isValidSlide(EMPTY, row, col, row - 1, col - 0));
        assertEquals(false, board.isValidSlide(EMPTY, row, col, row - 1, col + 1));
        assertEquals(false, board.isValidSlide(EMPTY, row, col, row - 1, col + 2));
        assertEquals(false, board.isValidSlide(EMPTY, row, col, row - 0, col - 2));
        assertEquals(false, board.isValidSlide(EMPTY, row, col, row - 0, col - 1));
        assertEquals(false, board.isValidSlide(EMPTY, row, col, row - 0, col - 0));
        assertEquals(false, board.isValidSlide(EMPTY, row, col, row - 0, col + 1));
        assertEquals(false, board.isValidSlide(EMPTY, row, col, row - 0, col + 2));
        assertEquals(false, board.isValidSlide(EMPTY, row, col, row + 1, col - 2));
        assertEquals(false, board.isValidSlide(EMPTY, row, col, row + 1, col - 1));
        assertEquals(false, board.isValidSlide(EMPTY, row, col, row + 1, col - 0));
        assertEquals(false, board.isValidSlide(EMPTY, row, col, row + 1, col + 1));
        assertEquals(false, board.isValidSlide(EMPTY, row, col, row + 1, col + 2));
        // Null team, wrong spot
        assertEquals(false, board.isValidSlide(null, row, col, row - 2, col - 2));
        assertEquals(false, board.isValidSlide(null, row, col, row - 2, col - 1));
        assertEquals(false, board.isValidSlide(null, row, col, row - 2, col - 0));
        assertEquals(false, board.isValidSlide(null, row, col, row - 2, col + 1));
        assertEquals(false, board.isValidSlide(null, row, col, row - 2, col + 2));
        assertEquals(false, board.isValidSlide(null, row, col, row - 1, col - 2));
        assertEquals(false, board.isValidSlide(null, row, col, row - 1, col - 1));
        assertEquals(false, board.isValidSlide(null, row, col, row - 1, col - 0));
        assertEquals(false, board.isValidSlide(null, row, col, row - 1, col + 1));
        assertEquals(false, board.isValidSlide(null, row, col, row - 1, col + 2));
        assertEquals(false, board.isValidSlide(null, row, col, row - 0, col - 2));
        assertEquals(false, board.isValidSlide(null, row, col, row - 0, col - 1));
        assertEquals(false, board.isValidSlide(null, row, col, row - 0, col - 0));
        assertEquals(false, board.isValidSlide(null, row, col, row - 0, col + 1));
        assertEquals(false, board.isValidSlide(null, row, col, row - 0, col + 2));
        assertEquals(false, board.isValidSlide(null, row, col, row + 1, col - 2));
        assertEquals(false, board.isValidSlide(null, row, col, row + 1, col - 1));
        assertEquals(false, board.isValidSlide(null, row, col, row + 1, col - 0));
        assertEquals(false, board.isValidSlide(null, row, col, row + 1, col + 1));
        assertEquals(false, board.isValidSlide(null, row, col, row + 1, col + 2));
        row = 2;
        col = 2;
        board.insert(TEAM1, row - 1, col);
        board.insert(TEAM1, row, col - 1);
        board.insert(TEAM1, row, col + 1);
        board.insert(TEAM1, row + 1, col);
        // End spot is TEAM1
        assertEquals(false, board.isValidSlide(TEAM1, row, col, row - 1, col));
        assertEquals(false, board.isValidSlide(TEAM1, row, col, row, col - 1));
        assertEquals(false, board.isValidSlide(TEAM1, row, col, row, col + 1));
        assertEquals(false, board.isValidSlide(TEAM1, row, col, row + 1, col));
        assertEquals(false, board.isValidSlide(TEAM2, row, col, row - 1, col));
        assertEquals(false, board.isValidSlide(TEAM2, row, col, row, col - 1));
        assertEquals(false, board.isValidSlide(TEAM2, row, col, row, col + 1));
        assertEquals(false, board.isValidSlide(TEAM2, row, col, row + 1, col));
        assertEquals(false, board.isValidSlide(EMPTY, row, col, row - 1, col));
        assertEquals(false, board.isValidSlide(EMPTY, row, col, row, col - 1));
        assertEquals(false, board.isValidSlide(EMPTY, row, col, row, col + 1));
        assertEquals(false, board.isValidSlide(EMPTY, row, col, row + 1, col));
        assertEquals(false, board.isValidSlide(null, row, col, row - 1, col));
        assertEquals(false, board.isValidSlide(null, row, col, row, col - 1));
        assertEquals(false, board.isValidSlide(null, row, col, row, col + 1));
        assertEquals(false, board.isValidSlide(null, row, col, row + 1, col));
        board.insert(TEAM2, row - 1, col);
        board.insert(TEAM2, row, col - 1);
        board.insert(TEAM2, row, col + 1);
        board.insert(TEAM2, row + 1, col);
        // End spot is TEAM2
        assertEquals(false, board.isValidSlide(TEAM1, row, col, row - 1, col));
        assertEquals(false, board.isValidSlide(TEAM1, row, col, row, col - 1));
        assertEquals(false, board.isValidSlide(TEAM1, row, col, row, col + 1));
        assertEquals(false, board.isValidSlide(TEAM1, row, col, row + 1, col));
        assertEquals(false, board.isValidSlide(TEAM2, row, col, row - 1, col));
        assertEquals(false, board.isValidSlide(TEAM2, row, col, row, col - 1));
        assertEquals(false, board.isValidSlide(TEAM2, row, col, row, col + 1));
        assertEquals(false, board.isValidSlide(TEAM2, row, col, row + 1, col));
        assertEquals(false, board.isValidSlide(EMPTY, row, col, row - 1, col));
        assertEquals(false, board.isValidSlide(EMPTY, row, col, row, col - 1));
        assertEquals(false, board.isValidSlide(EMPTY, row, col, row, col + 1));
        assertEquals(false, board.isValidSlide(EMPTY, row, col, row + 1, col));
        assertEquals(false, board.isValidSlide(null, row, col, row - 1, col));
        assertEquals(false, board.isValidSlide(null, row, col, row, col - 1));
        assertEquals(false, board.isValidSlide(null, row, col, row, col + 1));
        assertEquals(false, board.isValidSlide(null, row, col, row + 1, col));
	}

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
	 * Test method for {@link Board#clear()}.
	 */
	@Test
	public void testClear() {
		board.clear();
		for (int row = 0; row < 8; row++) {
			for (int col = 0; col < 8; col++) {
				assertEquals(true, EMPTY.equals(board.get(row, col)));
			}
		}
	}

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
