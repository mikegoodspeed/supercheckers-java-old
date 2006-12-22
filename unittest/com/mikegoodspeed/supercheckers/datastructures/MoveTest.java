package com.mikegoodspeed.supercheckers.datastructures;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.mikegoodspeed.supercheckers.datastructures.Move;

/**
 * Test the Move class.
 * <p>
 * project Supercheckers <br />
 * url http://www.mikegoodspeed.com/blog/projects/supercheckers/
 * 
 * @author Mike Goodspeed
 * @version $Id$
 */
public class MoveTest {

	private Move move = null;

	/**
	 * @throws Exception
	 */
	@Before
	public void setUp() throws Exception {
		move = new Move();
	}

	/**
	 * @throws Exception
	 */
	@After
	public void tearDown() throws Exception {
		move = null;
	}

	/**
	 * Test method for {@link Move#add(int, int)}.
	 */
	@Test
	public void testAdd() {
		move.add(0, 0);
		assertEquals(1, move.size());
		move.add(0, 0);
		assertEquals(2, move.size());
	}

	/**
	 * Test method for {@link Move#clone()}.
	 */
	@Test
	public void testEquals() {
		assertEquals(false, move.equals(null));
		Move differentMove = new Move();
		assertEquals(true, move.equals(differentMove));
		move.add(1, 2);
		differentMove.add(1, 2);
		assertEquals(true, move.equals(differentMove));
	}

	/**
	 * Test method for {@link Move#getCol(int)}.
	 */
	@Test
	public void testGetCol() {
		move.add(2, 1);
		assertEquals(1, move.getCol(0));
		move.add(4, 3);
		assertEquals(3, move.getCol(1));
	}

	/**
	 * Test method for {@link Move#getRow(int)}.
	 */
	@Test
	public void testGetRow() {
		move.add(1, 2);
		assertEquals(1, move.getRow(0));
		move.add(3, 4);
		assertEquals(3, move.getRow(1));
	}

	/**
	 * Test method for {@link Move#Move()}.
	 */
	@Test
	public void testMove() {
		assertNotNull(move);
		assertEquals(0, move.size());
	}

	/**
	 * Test method for {@link Move#size()}.
	 */
	@Test
	public void testSize() {
		assertEquals(0, move.size());
	}

	/**
	 * Test method for {@link Move#toString()}.
	 */
	@Test
	public void testToString() {
		assertEquals("empty", move.toString());
		move.add(1, 2);
		assertEquals("(1,2)", move.toString());
		move.add(3, 4);
		assertEquals("(1,2) (3,4)", move.toString());
	}
}
