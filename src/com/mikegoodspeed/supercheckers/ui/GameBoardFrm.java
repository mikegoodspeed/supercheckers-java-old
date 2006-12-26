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
package com.mikegoodspeed.supercheckers.ui;

import java.awt.BorderLayout;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
import javax.swing.UIManager;

import com.mikegoodspeed.supercheckers.datastructures.Board;
import com.mikegoodspeed.supercheckers.datastructures.Move;
import com.mikegoodspeed.supercheckers.datastructures.Spot;
import com.mikegoodspeed.supercheckers.datastructures.Team;
import com.mikegoodspeed.supercheckers.players.Player;
import com.mikegoodspeed.supercheckers.utils.GUIInput;

/**
 * Supercheckers Game Board
 * <p>
 * project Supercheckers <br />
 * url http://www.mikegoodspeed.com/blog/projects/supercheckers/
 *
 * @author Mike Goodspeed
 * @version $Id$
 */
public class GameBoardFrm extends JFrame {

	private static final long serialVersionUID = -4212234804742120343L;

	private Board board = null;
	private JPanel content = null;
	private JMenuBar menu = null;
	private JMenu gameMnu = null;
	private JMenuItem newMnuItem = null;
	private JMenuItem exitMnuItem = null;
	private JMenu helpMnu = null;
	private JMenuItem aboutMnuItem = null;
	private JPanel boardPnl = null;
	private JPanel uiPanel = null;
	private JLabel[][] buttons = null;
	private JPanel playerPnl = null;
	private JPanel p1Pnl = null;
	private JLabel p1IconLbl = null;
	private JPanel p1InfoPnl = null;
	private JLabel p1TypeLbl = null;
	private JLabel p1TotalLbl = null;
	private JLabel p1MiddleLbl = null;
	private JPanel p2Pnl = null;
	private JLabel p2IconLbl = null;
	private JPanel p2InfoPnl = null;
	private JLabel p2TypeLbl = null;
	private JLabel p2TotalLbl = null;
	private JLabel p2MiddleLbl = null;
	private JPanel MovePnl = null;
	private JButton submitBtn = null;
	private JButton resetBtn = null;
	private Team currTeam = Team.X;
	private GUIInput input = new GUIInput();
	private boolean listenForInput = false;
	private Spot hoverSpot = null;

	private ActionListener newMnuItemActionListener = new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			System.out.println("show new game dialog");
		}
	};
	private ActionListener exitMnuItemActionListener = new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			System.exit(0);
		}
	};
	private MouseListener buttonMouseListener = new MouseListener() {
		public void mouseClicked(MouseEvent e) {}

		public void mouseEntered(MouseEvent e) {
			String[] loc = ((JLabel) e.getSource()).getName().split(",");
			int row = new Integer(loc[0]).intValue();
			int col = new Integer(loc[1]).intValue();
			if (listenForInput && board.isAvailableSpot(currTeam, input.getMove(), row, col)) {
				setCursor(new Cursor(Cursor.HAND_CURSOR));
			}
			hoverSpot = new Spot(row, col);
		}

		public void mouseExited(MouseEvent e) {
			setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			hoverSpot = null;
		}

		public void mousePressed(MouseEvent e) {}

		public void mouseReleased(MouseEvent e) {
			Move move = getMove();
			JLabel source = ((JLabel) e.getSource());
			String[] loc = source.getName().split(",");
			int row = new Integer(loc[0]).intValue();
			int col = new Integer(loc[1]).intValue();
			if (listenForInput && new Spot(row, col).equals(hoverSpot) 
					&& board.isAvailableSpot(currTeam, move, row, col)) {
				if (move.size() == 0) {
					source.setIcon(Team.NOBODY.getIcon(board.isInMiddle(row, col)));
				} else {
					source.setIcon(currTeam.getIcon(board.isInMiddle(row, col)));
					int oldRow = move.getRow(move.size() - 1);
					int oldCol = move.getCol(move.size() - 1);
					Board clone = board.clone();
					clone.doMove(currTeam, move);
					if (clone.isValidJump(currTeam, oldRow, oldCol, row, col)) {
						JLabel oldSpot = getButtonByName(oldRow, oldCol);
						oldSpot.setIcon(Team.NOBODY.getIcon(clone.isInMiddle(oldRow, oldCol)));
						int jumpedRow = (oldRow + row) / 2;
						int jumpedCol = (oldCol + col) / 2;
						JLabel jumpedSpot = getButtonByName(jumpedRow, jumpedCol);
						if (!currTeam.equals(clone.get(jumpedRow, jumpedCol))) {
							jumpedSpot.setIcon(Team.NOBODY.getIcon(
									clone.isInMiddle(jumpedRow, jumpedCol)));
						}
					}
				}
				setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
				input.addSpot(row, col);
				if (getMove().size() >= 2) {
					getSubmitBtn().setEnabled(true);
				}
				getResetBtn().setEnabled(true);
			}
		}
	};
	private ActionListener submitBtnActionListener = new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			input.setReady();
			getSubmitBtn().setEnabled(false);
			getResetBtn().setEnabled(false);
		}
	};
	private ActionListener resetBtnActionListener = new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			input.clearMove();
			updateBoard(board);
			getSubmitBtn().setEnabled(false);
			getResetBtn().setEnabled(false);
			getBoardPnl().requestFocus();
		}
	};

	/**
	 * This method initializes
	 *
	 * @param board
	 */
	public GameBoardFrm(Board board) {
		super();
		this.board = board;
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {}
		initialize();
		pack();
	}

	/**
	 * This method initializes aboutMnuItem
	 *
	 * @return JMenuItem
	 */
	private JMenuItem getAboutMnuItem() {
		if (aboutMnuItem == null) {
			aboutMnuItem = new JMenuItem();
			aboutMnuItem.setText("About Supercheckers");
			aboutMnuItem.setMnemonic(KeyEvent.VK_A);
		}
		return aboutMnuItem;
	}

	/**
	 * This method initializes boardPnl
	 *
	 * @return JPanel
	 */
	private JPanel getBoardPnl() {
		if (boardPnl == null) {
			GridLayout gridLayout = new GridLayout();
			gridLayout.setRows(8);
			gridLayout.setColumns(8);
			boardPnl = new JPanel();
			boardPnl.setPreferredSize(new Dimension(336, 336));
			boardPnl.setLayout(gridLayout);
			populateBoardPnl();
		}
		return boardPnl;
	}

	private JLabel getButtonByName(int rowName, int colName) {
		String name = String.valueOf(rowName) + "," + String.valueOf(colName);
		for (int row = 0; row < 8; row++) {
			for (int col = 0; col < 8; col++) {
				if (name.equals(buttons[row][col].getName())) {
					return buttons[row][col];
				}
			}
		}
		return null;
	}

	/**
	 * This method initializes content
	 *
	 * @return JPanel
	 */
	private JPanel getContent() {
		if (content == null) {
			content = new JPanel();
			content.setLayout(new BorderLayout());
			content.add(getBoardPnl(), BorderLayout.WEST);
			content.add(getUiPanel(), BorderLayout.EAST);
		}
		return content;
	}

	/**
	 * This method initializes exitMnuItem
	 *
	 * @return JMenuItem
	 */
	private JMenuItem getExitMnuItem() {
		if (exitMnuItem == null) {
			exitMnuItem = new JMenuItem();
			exitMnuItem.setText("Exit");
			exitMnuItem.setMnemonic(KeyEvent.VK_X);
			exitMnuItem.addActionListener(exitMnuItemActionListener);
		}
		return exitMnuItem;
	}

	/**
	 * This method initializes fileMnu
	 *
	 * @return JMenu
	 */
	private JMenu getGameMnu() {
		if (gameMnu == null) {
			gameMnu = new JMenu();
			gameMnu.setText("Game");
			gameMnu.setMnemonic(KeyEvent.VK_G);
			gameMnu.add(getNewMnuItem());
			gameMnu.addSeparator();
			gameMnu.add(getExitMnuItem());
		}
		return gameMnu;
	}

	/**
	 * This method initializes helpMnu
	 *
	 * @return JMenu
	 */
	private JMenu getHelpMnu() {
		if (helpMnu == null) {
			helpMnu = new JMenu();
			helpMnu.setText("Help");
			helpMnu.setMnemonic(KeyEvent.VK_H);
			helpMnu.add(getAboutMnuItem());
		}
		return helpMnu;
	}

	/**
	 * This method initializes menu
	 *
	 * @return JMenuBar
	 */
	private JMenuBar getMenu() {
		if (menu == null) {
			menu = new JMenuBar();
			menu.add(getGameMnu());
			menu.add(getHelpMnu());
		}
		return menu;
	}

	/**
	 * Returns user-chosen move after the submit button has been pressed.
	 *
	 * @return the move as chosen by the user
	 */
	public Move getMove() {
		return input.getMove();
	}

	/**
	 * This method initializes MovePnl
	 *
	 * @return JPanel
	 */
	private JPanel getMovePnl() {
		if (MovePnl == null) {
			MovePnl = new JPanel();
			MovePnl.setLayout(new BorderLayout());
			MovePnl.setBorder(BorderFactory.createTitledBorder("Move"));
			MovePnl.add(getResetBtn(), BorderLayout.WEST);
			MovePnl.add(getSubmitBtn(), BorderLayout.EAST);
		}
		return MovePnl;
	}

	/**
	 * This method initializes newMnuItem
	 *
	 * @return JMenuItem
	 */
	private JMenuItem getNewMnuItem() {
		if (newMnuItem == null) {
			newMnuItem = new JMenuItem();
			newMnuItem.setText("New...");
			newMnuItem.setMnemonic(KeyEvent.VK_N);
			newMnuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F2, 0));
			newMnuItem.addActionListener(newMnuItemActionListener);
		}
		return newMnuItem;
	}

	/**
	 * This method initializes p1InfoPnl	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getP1InfoPnl() {
		if (p1InfoPnl == null) {
			p1TypeLbl = new JLabel();
			p1TypeLbl.setText("Human");
			p1TotalLbl = new JLabel();
			p1TotalLbl.setText("Total Pieces: 24");
			p1MiddleLbl = new JLabel();
			p1MiddleLbl.setText("Middle Pieces: 0");
			p1InfoPnl = new JPanel();
			p1InfoPnl.setLayout(new BoxLayout(getP1InfoPnl(), BoxLayout.Y_AXIS));
			p1InfoPnl.setPreferredSize(new Dimension(86, 42));
			p1InfoPnl.add(p1TypeLbl, null);
			p1InfoPnl.add(p1TotalLbl, null);
			p1InfoPnl.add(p1MiddleLbl, null);
		}
		return p1InfoPnl;
	}

	/**
	 * This method initializes p1Pnl	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getP1Pnl() {
		if (p1Pnl == null) {
			p1IconLbl = new JLabel();
			p1IconLbl.setIcon(Team.X.getIcon(false));
			p1IconLbl.setText(" ");
			p1Pnl = new JPanel();
			p1Pnl.setLayout(new BorderLayout());
			p1Pnl.setBorder(BorderFactory.createTitledBorder("Player 1"));
			p1Pnl.add(p1IconLbl, BorderLayout.WEST);
			p1Pnl.add(getP1InfoPnl(), BorderLayout.EAST);
		}
		return p1Pnl;
	}

	/**
	 * This method initializes p2InfoPnl	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getP2InfoPnl() {
		if (p2InfoPnl == null) {
			p2TypeLbl = new JLabel();
			p2TypeLbl.setText("Easy Computer");
			p2TypeLbl.setEnabled(false);
			p2TotalLbl = new JLabel();
			p2TotalLbl.setText("Total Pieces: 24");
			p2TotalLbl.setEnabled(false);
			p2MiddleLbl = new JLabel();
			p2MiddleLbl.setText("Middle Pieces: 0");
			p2MiddleLbl.setEnabled(false);
			p2InfoPnl = new JPanel();
			p2InfoPnl.setLayout(new BoxLayout(getP2InfoPnl(), BoxLayout.Y_AXIS));
			p2InfoPnl.setName("p2InfoPnl");
			p2InfoPnl.setPreferredSize(new Dimension(86, 42));
			p2InfoPnl.add(p2TypeLbl, null);
			p2InfoPnl.add(p2TotalLbl, null);
			p2InfoPnl.add(p2MiddleLbl, null);
		}
		return p2InfoPnl;
	}

	/**
	 * This method initializes p2Pnl	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getP2Pnl() {
		if (p2Pnl == null) {
			p2IconLbl = new JLabel();
			p2IconLbl.setIcon(Team.O.getIcon(false));
			p2IconLbl.setName("p2IconLbl");
			p2IconLbl.setDisplayedMnemonic(KeyEvent.VK_UNDEFINED);
			p2IconLbl.setText(" ");
			p2IconLbl.setVisible(false);
			p2Pnl = new JPanel();
			p2Pnl.setLayout(new BorderLayout());
			p2Pnl.setBorder(BorderFactory.createTitledBorder("Player 2"));
			p2Pnl.add(p2IconLbl, BorderLayout.WEST);
			p2Pnl.add(getP2InfoPnl(), BorderLayout.EAST);
		}
		return p2Pnl;
	}

	/**
	 * This method initializes playerPnl	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getPlayerPnl() {
		if (playerPnl == null) {
			playerPnl = new JPanel();
			playerPnl.setLayout(new BoxLayout(getPlayerPnl(), BoxLayout.Y_AXIS));
			playerPnl.add(getP1Pnl(), null);
			playerPnl.add(getP2Pnl(), null);
		}
		return playerPnl;
	}

	/**
	 * This method initializes resetBtn
	 *
	 * @return JButton
	 */
	private JButton getResetBtn() {
		if (resetBtn == null) {
			resetBtn = new JButton();
			resetBtn.setText("Reset");
			resetBtn.setMnemonic(KeyEvent.VK_R);
			resetBtn.setEnabled(false);
			resetBtn.addActionListener(resetBtnActionListener);
		}
		return resetBtn;
	}

	/**
	 * This method initializes submitBtn
	 *
	 * @return JButton
	 */
	private JButton getSubmitBtn() {
		if (submitBtn == null) {
			submitBtn = new JButton();
			submitBtn.setMnemonic(KeyEvent.VK_S);
			submitBtn.setText("Submit");
			submitBtn.setEnabled(false);
			submitBtn.addActionListener(submitBtnActionListener);
		}
		return submitBtn;
	}

	/**
	 * This method initializes uiPanel
	 *
	 * @return JPanel
	 */
	private JPanel getUiPanel() {
		if (uiPanel == null) {
			uiPanel = new JPanel();
			uiPanel.setLayout(new BorderLayout());
			uiPanel.add(getPlayerPnl(), BorderLayout.NORTH);
			uiPanel.add(getMovePnl(), BorderLayout.SOUTH);
		}
		return uiPanel;
	}

	/**
	 * This method initializes this
	 *
	 */
	private void initialize() {
		this.setSize(new Dimension(493, 407));
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		this.setJMenuBar(getMenu());
		this.setContentPane(getContent());
		this.setTitle("Supercheckers");
		this.setLocationRelativeTo(null);
	}

	private void populateBoardPnl() {
		buttons = new JLabel[8][8];
		for (int row = 0; row < 8; row++) {
			for (int col = 0; col < 8; col++) {
				buttons[row][col] = new JLabel();
				buttons[row][col].setName(row + "," + col);
				buttons[row][col].addMouseListener(buttonMouseListener);
				boardPnl.add(buttons[row][col]);
			}
		}
		updateBoard(board);
	}
	
	/**
	 * Sets the player names on the window.
	 * 
	 * @param player1
	 * @param player2
	 */
	public void setPlayers(Player player1, Player player2) {
		p1TypeLbl.setText(player1.toString());
		p2TypeLbl.setText(player2.toString());
	}

	/**
	 * Sets the turn so the game board reflects the current player's team
	 *
	 * @param team current player's team
	 */
	public void setTurn(Team team) {
		this.currTeam = team;
		if (Team.X.equals(team)) {
			// Enable Player 1
			getP1Pnl().setEnabled(true);
			p1IconLbl.setVisible(true);
			p1TypeLbl.setEnabled(true);
			p1TotalLbl.setEnabled(true);
			p1MiddleLbl.setEnabled(true);
			// Disable Player 2
			getP2Pnl().setEnabled(false);
			p2IconLbl.setVisible(false);
			p2TypeLbl.setEnabled(false);
			p2TotalLbl.setEnabled(false);
			p2MiddleLbl.setEnabled(false);
		} else if (Team.O.equals(team)) {
			// Enable Player 2
			getP2Pnl().setEnabled(true);
			p2IconLbl.setVisible(true);
			p2TypeLbl.setEnabled(true);
			p2TotalLbl.setEnabled(true);
			p2MiddleLbl.setEnabled(true);
			// Disable Player 1
			getP1Pnl().setEnabled(false);
			p1IconLbl.setVisible(false);
			p1TypeLbl.setEnabled(false);
			p1TotalLbl.setEnabled(false);
			p1MiddleLbl.setEnabled(false);
		}
	}

	/**
	 * Update the GUI board to reflect the actual board.
	 *
	 * @param board
	 */
	public void updateBoard(Board board) {
		this.board = board;
		for (int row = 0; row < 8; row++) {
			for (int col = 0; col < 8; col++) {
				buttons[row][col].setIcon(board.get(row, col).getIcon(board.isInMiddle(row, col)));
			}
		}
	}

	/**
	 * This method blocks until the submit button is pressed.
	 */
	public void waitForInput() {
		listenForInput = true;
		input = new GUIInput();
		input.start();
		try {
			input.join();
		} catch (Exception e) {}
		listenForInput = false;
		getSubmitBtn().setEnabled(false);
		getResetBtn().setEnabled(false);
		getBoardPnl().requestFocus();
	}
}
