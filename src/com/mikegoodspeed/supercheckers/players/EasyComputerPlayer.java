package com.mikegoodspeed.supercheckers.players;

import static com.mikegoodspeed.supercheckers.constants.SCConst.EMPTY;
import static com.mikegoodspeed.supercheckers.constants.SCConst.TEAM1;
import static com.mikegoodspeed.supercheckers.constants.SCConst.TEAM2;

import java.util.Random;

import com.mikegoodspeed.supercheckers.datastructures.Board;
import com.mikegoodspeed.supercheckers.datastructures.Move;
import com.mikegoodspeed.supercheckers.datastructures.Team;
import com.mikegoodspeed.supercheckers.ui.GameBoardFrm;

/**
 *
 *
 * @author Mike
 * @version $Id$
 */
public class EasyComputerPlayer extends Player {

	Team myTeam = null;
	Team opponentTeam = null;

	/**
	 * @param window
	 * @param board
	 * @param team
	 */
	public EasyComputerPlayer(GameBoardFrm window, Board board, Team team) {
		super(window, board, team);
		myTeam = team;
		opponentTeam = TEAM1.equals(team) ? TEAM2 : TEAM1;
	}

	public Move getMove() {
		try {
			Thread.sleep(50); // wait 50 milliseconds to play
		} catch (Exception e) {}

		Move move = new Move();
		if (getBoard().isFirstMove()) {
			// If we are first to go in the first round then randomly slide into the middle.
			Random randomNumber = new Random();
			if (TEAM1.equals(myTeam)) {
				switch (randomNumber.nextInt(8)) {
					case 0:
						move = new Move();
						move.add(1, 3);
						move.add(2, 3);
						break;
					case 1:
						move = new Move();
						move.add(1, 5);
						move.add(2, 5);
						break;
					case 2:
						move = new Move();
						move.add(2, 6);
						move.add(2, 5);
						break;
					case 3:
						move = new Move();
						move.add(3, 1);
						move.add(3, 2);
						break;
					case 4:
						move = new Move();
						move.add(4, 6);
						move.add(4, 5);
						break;
					case 5:
						move = new Move();
						move.add(5, 1);
						move.add(5, 2);
						break;
					case 6:
						move = new Move();
						move.add(6, 2);
						move.add(5, 2);
						break;
					case 7:
						move = new Move();
						move.add(6, 4);
						move.add(5, 4);
						break;
				}
			} else { // if (TEAM2.equals(myTeam))
				switch (randomNumber.nextInt(8)) {
					case 0:
						move = new Move();
						move.add(1, 2);
						move.add(2, 2);
						break;
					case 1:
						move = new Move();
						move.add(1, 4);
						move.add(2, 4);
						break;
					case 2:
						move = new Move();
						move.add(2, 1);
						move.add(2, 2);
						break;
					case 3:
						move = new Move();
						move.add(3, 6);
						move.add(3, 5);
						break;
					case 4:
						move = new Move();
						move.add(4, 1);
						move.add(4, 2);
						break;
					case 5:
						move = new Move();
						move.add(5, 6);
						move.add(5, 5);
						break;
					case 6:
						move = new Move();
						move.add(6, 3);
						move.add(5, 3);
						break;
					case 7:
						move = new Move();
						move.add(6, 5);
						move.add(5, 5);
						break;
				}
			}
			return move;
		} else if (getBoard().isSecondMove()) {
			// If we are second to go in the second round, then randomly slide in from the opposite
			// side of the center that the opponent came from.
			// Find the side that our opponent slid in from.
			Random randomNumber = new Random();
			int emptyCellRow = 0;
			int emptyCellCol = 0;
			for (int row = 1; row <= 6; row++) {
				for (int col = 1; col <= 6; col++) {
					if (row == 1 || row == 6 || col == 1 || col == 6) {// adjacent to middle
						if (getBoard().get(row, col).equals(EMPTY)) {
							emptyCellRow = row;
							emptyCellCol = col;
						}
					}
				}
			}
			if (emptyCellCol == 6) { // empty on right side
				if (TEAM1.equals(myTeam)) {
					switch (randomNumber.nextInt(2)) {
						case 0:
							move = new Move();
							move.add(3, 1);
							move.add(3, 2);
							break;
						case 1:
							move = new Move();
							move.add(5, 1);
							move.add(5, 2);
							break;
					}
				} else { // if (TEAM2.equals(myTeam)) {
					switch (randomNumber.nextInt(2)) {
						case 0:
							move = new Move();
							move.add(2, 1);
							move.add(2, 2);
							break;
						case 1:
							move = new Move();
							move.add(4, 1);
							move.add(4, 2);
							break;
					}
				}
			} else if (emptyCellRow == 1) { // empty on top side
				if (TEAM1.equals(myTeam)) {
					switch (randomNumber.nextInt(2)) {
						case 0:
							move = new Move();
							move.add(6, 2);
							move.add(5, 2);
							break;
						case 1:
							move = new Move();
							move.add(6, 4);
							move.add(5, 4);
							break;
					}
				} else { // if (TEAM2.equals(myTeam)) {
					switch (randomNumber.nextInt(2)) {
						case 0:
							move = new Move();
							move.add(6, 3);
							move.add(5, 3);
							break;
						case 1:
							move = new Move();
							move.add(6, 5);
							move.add(5, 5);
							break;
					}
				}
			} else if (emptyCellCol == 1) { // empty on left side
				if (TEAM1.equals(myTeam)) {
					switch (randomNumber.nextInt(2)) {
						case 0:
							move = new Move();
							move.add(2, 6);
							move.add(2, 5);
							break;
						case 1:
							move = new Move();
							move.add(4, 6);
							move.add(4, 5);
							break;
					}
				} else { // if (TEAM2.equals(myTeam)) {
					switch (randomNumber.nextInt(2)) {
						case 0:
							move = new Move();
							move.add(3, 6);
							move.add(3, 5);
							break;
						case 1:
							move = new Move();
							move.add(5, 6);
							move.add(5, 5);
							break;
					}
				}
			} else { // if (emptyCellRow == 6) { // empty on bottom side
				if (TEAM1.equals(myTeam)) {
					switch (randomNumber.nextInt(2)) {
						case 0:
							move = new Move();
							move.add(1, 3);
							move.add(2, 3);
							break;
						case 1:
							move = new Move();
							move.add(1, 5);
							move.add(2, 5);
							break;
					}
				} else { // if (TEAM2.equals(myTeam)) {
					switch (randomNumber.nextInt(2)) {
						case 0:
							move = new Move();
							move.add(1, 2);
							move.add(2, 2);
							break;
						case 1:
							move = new Move();
							move.add(1, 4);
							move.add(2, 4);
							break;
					}
				}
			}
			return move;
		} else {
			// Now that the first round is over, use strategy.
			Move secondChoice = new Move();

			// try to jump an opponent inside (without leaving the middle)
			for (int row = 2; row <= 5; row++) {
				for (int col = 2; col <= 5; col++) {
					if (row < 4) { // able to jump up
						if (getBoard().get(row, col).equals(myTeam) &&
								getBoard().get(row + 1, col).equals(opponentTeam) &&
								getBoard().get(row + 2, col).equals(EMPTY)) {
							move = new Move();
							move.add(row, col);
							move.add(row + 2, col);
						}
					}
					if (col < 4) { // able to jump right
						if (getBoard().get(row, col).equals(myTeam) &&
								getBoard().get(row, col + 1).equals(opponentTeam) &&
								getBoard().get(row, col + 2).equals(EMPTY)) {
							move = new Move();
							move.add(row, col);
							move.add(row, col + 2);
						}
					}
					if (row > 3) { // able to jump down
						if (getBoard().get(row, col).equals(myTeam) &&
								getBoard().get(row - 1, col).equals(opponentTeam) &&
								getBoard().get(row - 2, col).equals(EMPTY)) {
							move = new Move();
							move.add(row, col);
							move.add(row - 2, col);
						}
					}
					if (col > 3) { // able ot jump left
						if (getBoard().get(row, col).equals(myTeam) &&
								getBoard().get(row, col - 1).equals(opponentTeam) &&
								getBoard().get(row, col - 2).equals(EMPTY)) {
							move = new Move();
							move.add(row, col);
							move.add(row, col - 2);
						}
					}
				}
			}
			if (move.size() != 0)
				return move; // if a better move was found, do it
			if (secondChoice.size() != 0)
				return secondChoice; // second choice move

			// try to jump an opponent or a teammate to get inside
			for (int row = 6; row <= 7; row++)
				for (int col = 2; col <= 5; col++)
					if (getBoard().get(row, col).equals(myTeam) &&
							getBoard().get(row - 2, col).equals(EMPTY)) {
						if (getBoard().get(row - 1, col).equals(opponentTeam)) {
							move = new Move();
							move.add(row, col);
							move.add(row - 2, col);
						} else if (getBoard().get(row - 1, col).equals(myTeam)) {
							secondChoice = new Move();
							secondChoice.add(row, col);
							secondChoice.add(row - 2, col);
						}
					}
			for (int row = 2; row <= 5; row++)
				for (int col = 6; col <= 7; col++)
					if (getBoard().get(row, col).equals(myTeam) &&
							getBoard().get(row, col - 2).equals(EMPTY)) {
						if (getBoard().get(row, col - 1).equals(opponentTeam)) {
							move = new Move();
							move.add(row, col);
							move.add(row, col - 2);
						} else if (getBoard().get(row, col - 1).equals(myTeam)) {
							secondChoice = new Move();
							secondChoice.add(row, col);
							secondChoice.add(row, col - 2);
						}
					}
			for (int row = 0; row <= 1; row++)
				for (int col = 2; col <= 5; col++)
					if (getBoard().get(row, col).equals(myTeam) &&
							getBoard().get(row + 2, col).equals(EMPTY)) {
						if (getBoard().get(row + 1, col).equals(opponentTeam)) {
							move = new Move();
							move.add(row, col);
							move.add(row + 2, col);
						} else if (getBoard().get(row + 1, col).equals(myTeam)) {
							secondChoice = new Move();
							secondChoice.add(row, col);
							secondChoice.add(row + 2, col);
						}
					}
			for (int row = 2; row <= 5; row++) {
				for (int col = 0; col <= 1; col++) {
					if (getBoard().get(row, col).equals(myTeam) &&
							getBoard().get(row, col + 2).equals(EMPTY)) {
						if (getBoard().get(row, col + 1).equals(opponentTeam)) {
							move = new Move();
							move.add(row, col);
							move.add(row, col + 2);
						} else if (getBoard().get(row, col + 1).equals(myTeam)) {
							secondChoice = new Move();
							secondChoice.add(row, col);
							secondChoice.add(row, col + 2);
						}
					}
				}
			}
			if (move.size() != 0)
				return move; // if a better move was found, do it
			if (secondChoice.size() != 0)
				return secondChoice; // second choice move

			// try to slide inside
			for (int col = 2; col <= 5; col++)
				if (getBoard().get(6, col).equals(myTeam) &&
						getBoard().get(5, col).equals(EMPTY)) {
					move = new Move();
					move.add(6, col);
					move.add(5, col);
				}
			for (int row = 2; row <= 5; row++)
				if (getBoard().get(row, 6).equals(myTeam) &&
						getBoard().get(row, 5).equals(EMPTY)) {
					move = new Move();
					move.add(row, 6);
					move.add(row, 5);
				}
			for (int col = 2; col <= 5; col++)
				if (getBoard().get(1, col).equals(myTeam) &&
						getBoard().get(2, col).equals(EMPTY)) {
					move = new Move();
					move.add(1, col);
					move.add(2, col);
				}
			for (int row = 2; row <= 5; row++)
				if (getBoard().get(row, 1).equals(myTeam) &&
						getBoard().get(row, 2).equals(EMPTY)) {
					move = new Move();
					move.add(row, 1);
					move.add(row, 2);
				}
			if (move.size() != 0)
				return move; // if a move was found, do it

			// try to slide in from the edges
			for (int col = 1; col <= 6; col++)
				if (getBoard().get(7, col).equals(myTeam) &&
						getBoard().get(6, col).equals(EMPTY)) {
					move = new Move();
					move.add(7, col);
					move.add(6, col);
				}
			for (int row = 1; row <= 6; row++)
				if (getBoard().get(row, 7).equals(myTeam) &&
						getBoard().get(row, 6).equals(EMPTY)) {
					move = new Move();
					move.add(row, 7);
					move.add(row, 6);
				}
			for (int col = 1; col <= 6; col++)
				if (getBoard().get(0, col).equals(myTeam) &&
						getBoard().get(1, col).equals(EMPTY)) {
					move = new Move();
					move.add(0, col);
					move.add(1, col);
				}
			for (int row = 1; row <= 6; row++)
				if (getBoard().get(row, 0).equals(myTeam) &&
						getBoard().get(row, 1).equals(EMPTY)) {
					move = new Move();
					move.add(row, 0);
					move.add(row, 1);
				}
			if (move.size() != 0)
				return move; // if a move was found, do it

			// create random numbers for random jumping
			Random randomNumber = new Random();
			int randomRow = randomNumber.nextInt(8);
			int randomCol = randomNumber.nextInt(8);
			int randomDirection = randomNumber.nextInt(4);
			//d System.out.println("Random Jump Start: (" + randomRow + "," + randomCol + ") Direction:" + randomDirection);

			// try to randomly jump an opponent
			for (int row = randomRow; row <= 7; row++) {
				for (int col = randomCol; col <= 7; col++) {
					//d System.out.println("Randomly looking for a jump at (" + row + "," + col + ")");
					if (row <= 5) // able to jump down
						if (getBoard().get(row, col).equals(myTeam) &&
								getBoard().get(row + 2, col).equals(EMPTY)) {
							if (getBoard().get(row + 1, col).equals(opponentTeam)) {
								move = new Move();
								move.add(row, col);
								move.add(row + 2, col);
							}
						}
					if (col <= 5) // able to jump right
						if (getBoard().get(row, col).equals(myTeam) &&
								getBoard().get(row, col + 2).equals(EMPTY)) {
							if (getBoard().get(row, col + 1).equals(opponentTeam)) {
								move = new Move();
								move.add(row, col);
								move.add(row, col + 2);
							}
						}
					if (row >= 2) // able to jump up
						if (getBoard().get(row, col).equals(myTeam) &&
								getBoard().get(row - 2, col).equals(EMPTY)) {
							if (getBoard().get(row - 1, col).equals(opponentTeam)) {
								move = new Move();
								move.add(row, col);
								move.add(row - 2, col);
							}
						}
					if (col >= 2) // able ot jump left
						if (getBoard().get(row, col).equals(myTeam) &&
								getBoard().get(row, col - 2).equals(EMPTY)) {
							if (getBoard().get(row, col - 1).equals(opponentTeam)) {
								move = new Move();
								move.add(row, col);
								move.add(row, col - 2);
							}
						}
				}
				for (int col = 0; col <= randomCol - 1; col++) {
					//d System.out.println("Randomly looking for a jump at (" + row + "," + col + ")");
					if (row <= 5) // able to jump down
						if (getBoard().get(row, col).equals(myTeam) &&
								getBoard().get(row + 2, col).equals(EMPTY)) {
							if (getBoard().get(row + 1, col).equals(opponentTeam)) {
								move = new Move();
								move.add(row, col);
								move.add(row + 2, col);
							}
						}
					if (col <= 5) // able to jump right
						if (getBoard().get(row, col).equals(myTeam) &&
								getBoard().get(row, col + 2).equals(EMPTY)) {
							if (getBoard().get(row, col + 1).equals(opponentTeam)) {
								move = new Move();
								move.add(row, col);
								move.add(row, col + 2);
							}
						}
					if (row >= 2) // able to jump up
						if (getBoard().get(row, col).equals(myTeam) &&
								getBoard().get(row - 2, col).equals(EMPTY)) {
							if (getBoard().get(row - 1, col).equals(opponentTeam)) {
								move = new Move();
								move.add(row, col);
								move.add(row - 2, col);
							}
						}
					if (col >= 2) // able ot jump left
						if (getBoard().get(row, col).equals(myTeam) &&
								getBoard().get(row, col - 2).equals(EMPTY)) {
							if (getBoard().get(row, col - 1).equals(opponentTeam)) {
								move = new Move();
								move.add(row, col);
								move.add(row, col - 2);
							}
						}
				}
			}
			for (int row = 0; row <= randomRow - 1; row++) {
				if (randomCol < 7) {
					for (int col = randomCol; col <= 7; col++) {
						//d System.out.println("Randomly looking for a jump at (" + row + "," + col + ")");
						if (row <= 5) // able to jump down
							if (getBoard().get(row, col).equals(myTeam) &&
									getBoard().get(row + 2, col).equals(EMPTY)) {
								if (getBoard().get(row + 1, col).equals(opponentTeam)) {
									move = new Move();
									move.add(row, col);
									move.add(row + 2, col);
								}
							}
						if (col <= 5) // able to jump right
							if (getBoard().get(row, col).equals(myTeam) &&
									getBoard().get(row, col + 2).equals(EMPTY)) {
								if (getBoard().get(row, col + 1).equals(opponentTeam)) {
									move = new Move();
									move.add(row, col);
									move.add(row, col + 2);
								}
							}
						if (row >= 2) // able to jump up
							if (getBoard().get(row, col).equals(myTeam) &&
									getBoard().get(row - 2, col).equals(EMPTY)) {
								if (getBoard().get(row - 1, col).equals(opponentTeam)) {
									move = new Move();
									move.add(row, col);
									move.add(row - 2, col);
								}
							}
						if (col >= 2) // able ot jump left
							if (getBoard().get(row, col).equals(myTeam) &&
									getBoard().get(row, col - 2).equals(EMPTY)) {
								if (getBoard().get(row, col - 1).equals(opponentTeam)) {
									move = new Move();
									move.add(row, col);
									move.add(row, col - 2);
								}
							}
					}
				}
				for (int col = 0; col <= randomCol - 1; col++) {
					//d System.out.println("Randomly looking for a jump at (" + row + "," + col + ")");
					if (row <= 5) // able to jump down
						if (getBoard().get(row, col).equals(myTeam) &&
								getBoard().get(row + 2, col).equals(EMPTY)) {
							if (getBoard().get(row + 1, col).equals(opponentTeam)) {
								move = new Move();
								move.add(row, col);
								move.add(row + 2, col);
							}
						}
					if (col <= 5) // able to jump right
						if (getBoard().get(row, col).equals(myTeam) &&
								getBoard().get(row, col + 2).equals(EMPTY)) {
							if (getBoard().get(row, col + 1).equals(opponentTeam)) {
								move = new Move();
								move.add(row, col);
								move.add(row, col + 2);
							}
						}
					if (row >= 2) // able to jump up
						if (getBoard().get(row, col).equals(myTeam) &&
								getBoard().get(row - 2, col).equals(EMPTY)) {
							if (getBoard().get(row - 1, col).equals(opponentTeam)) {
								move = new Move();
								move.add(row, col);
								move.add(row - 2, col);
							}
						}
					if (col >= 2) // able ot jump left
						if (getBoard().get(row, col).equals(myTeam) &&
								getBoard().get(row, col - 2).equals(EMPTY)) {
							if (getBoard().get(row, col - 1).equals(opponentTeam)) {
								move = new Move();
								move.add(row, col);
								move.add(row, col - 2);
							}
						}
				}
			}
			//d System.out.println("");
			if (move.size() != 0)
				return move; // return a jump if we have one

			// create new random numbers for random sliding
			randomRow = randomNumber.nextInt(8);
			randomCol = randomNumber.nextInt(8);
			randomDirection = randomNumber.nextInt(4);
			//d System.out.println("Random Slide Start: (" + randomRow + "," + randomCol + ") Direction:" + randomDirection);

			// try to randomly slide anywhere (base case)
			for (int row = randomRow; row <= 7; row++) {
				for (int col = randomRow; col <= 7; col++) {
					for (int i = 0; i <= 3; i++) { // times through with a direction
						if (randomDirection == 0 && row <= 6) // able to slide down
							if (getBoard().get(row, col).equals(myTeam) &&
									getBoard().get(row + 1, col).equals(EMPTY)) {
								move = new Move();
								move.add(row, col);
								move.add(row + 1, col);
							}
						if (randomDirection == 1 && col <= 6) // able to slide right
							if (getBoard().get(row, col).equals(myTeam) &&
									getBoard().get(row, col + 1).equals(EMPTY)) {
								move = new Move();
								move.add(row, col);
								move.add(row, col + 1);
							}
						if (randomDirection == 2 && row >= 1) // able to slide up
							if (getBoard().get(row, col).equals(myTeam) &&
									getBoard().get(row - 1, col).equals(EMPTY)) {
								move = new Move();
								move.add(row, col);
								move.add(row - 1, col);
							}
						if (randomDirection == 3 && col >= 1) // able to slide left
							if (getBoard().get(row, col).equals(myTeam) &&
									getBoard().get(row, col - 1).equals(EMPTY)) {
								move = new Move();
								move.add(row, col);
								move.add(row, col - 1);
							}
						if (randomDirection != 3)
							randomDirection++; // increment random direction
						else
							randomDirection = 0; // loop it back to the top
					}
				}
				for (int col = 0; col <= randomCol - 1; col++) {
					for (int i = 0; i <= 3; i++) { // times through with a direction
						if (randomDirection == 0 && row <= 6) // able to slide down
							if (getBoard().get(row, col).equals(myTeam) &&
									getBoard().get(row + 1, col).equals(EMPTY)) {
								move = new Move();
								move.add(row, col);
								move.add(row + 1, col);
							}
						if (randomDirection == 1 && col <= 6) // able to slide right
							if (getBoard().get(row, col).equals(myTeam) &&
									getBoard().get(row, col + 1).equals(EMPTY)) {
								move = new Move();
								move.add(row, col);
								move.add(row, col + 1);
							}
						if (randomDirection == 2 && row >= 1) // able to slide up
							if (getBoard().get(row, col).equals(myTeam) &&
									getBoard().get(row - 1, col).equals(EMPTY)) {
								move = new Move();
								move.add(row, col);
								move.add(row - 1, col);
							}
						if (randomDirection == 3 && col >= 1) // able to slide left
							if (getBoard().get(row, col).equals(myTeam) &&
									getBoard().get(row, col - 1).equals(EMPTY)) {
								move = new Move();
								move.add(row, col);
								move.add(row, col - 1);
							}
						if (randomDirection != 3)
							randomDirection++; // increment random direction
						else
							randomDirection = 0; // loop it back to the top
					}
				}
			}
			for (int row = 0; row <= randomRow - 1; row++) {
				for (int col = randomCol; col <= 7; col++) {
					for (int i = 0; i <= 3; i++) { // times through with a direction
						if (randomDirection == 0 && row <= 6) // able to slide down
							if (getBoard().get(row, col).equals(myTeam) &&
									getBoard().get(row + 1, col).equals(EMPTY)) {
								move = new Move();
								move.add(row, col);
								move.add(row + 1, col);
							}
						if (randomDirection == 1 && col <= 6) // able to slide right
							if (getBoard().get(row, col).equals(myTeam) &&
									getBoard().get(row, col + 1).equals(EMPTY)) {
								move = new Move();
								move.add(row, col);
								move.add(row, col + 1);
							}
						if (randomDirection == 2 && row >= 1) // able to slide up
							if (getBoard().get(row, col).equals(myTeam) &&
									getBoard().get(row - 1, col).equals(EMPTY)) {
								move = new Move();
								move.add(row, col);
								move.add(row - 1, col);
							}
						if (randomDirection == 3 && col >= 1) // able to slide left
							if (getBoard().get(row, col).equals(myTeam) &&
									getBoard().get(row, col - 1).equals(EMPTY)) {
								move = new Move();
								move.add(row, col);
								move.add(row, col - 1);
							}
						if (randomDirection != 3)
							randomDirection++; // increment random direction
						else
							randomDirection = 0; // loop it back to the top
					}
				}
				for (int col = 0; col <= randomCol - 1; col++) {
					for (int i = 0; i <= 3; i++) { // times through with a direction
						if (randomDirection == 0 && row <= 6) // able to slide down
							if (getBoard().get(row, col).equals(myTeam) &&
									getBoard().get(row + 1, col).equals(EMPTY)) {
								move = new Move();
								move.add(row, col);
								move.add(row + 1, col);
							}
						if (randomDirection == 1 && col <= 6) // able to slide right
							if (getBoard().get(row, col).equals(myTeam) &&
									getBoard().get(row, col + 1).equals(EMPTY)) {
								move = new Move();
								move.add(row, col);
								move.add(row, col + 1);
							}
						if (randomDirection == 2 && row >= 1) // able to slide up
							if (getBoard().get(row, col).equals(myTeam) &&
									getBoard().get(row - 1, col).equals(EMPTY)) {
								move = new Move();
								move.add(row, col);
								move.add(row - 1, col);
							}
						if (randomDirection == 3 && col >= 1) // able to slide left
							if (getBoard().get(row, col).equals(myTeam) &&
									getBoard().get(row, col - 1).equals(EMPTY)) {
								move = new Move();
								move.add(row, col);
								move.add(row, col - 1);
							}
						if (randomDirection != 3)
							randomDirection++; // increment random direction
						else
							randomDirection = 0; // loop it back to the top
					}
				}
			}
			return move; // return whatever we got
		}
	}
}